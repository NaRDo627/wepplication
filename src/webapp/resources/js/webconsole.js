(function($) {
    $(document).ready(function() {
        var settings = {'url': 'http://127.0.0.1:8081',
                        'prompt_path_length': 32,
                        'domain': document.domain || window.location.host,
                        'is_small_window': $(document).width() < 625 ? true : false};
        var environment = {'user': '', 'hostname': '', 'path': ''};
        var no_login = typeof(__NO_LOGIN__) !== 'undefined' ? __NO_LOGIN__ : false;
        var silent_mode = false;

        
        // Output
        function show_output(output) {
            if (output) {
                if (typeof output === 'string') terminal.echo(output);
                else if (output instanceof Array) terminal.echo($.map(output, function(object) {
                                                      return $.json_stringify(object);
                                                  }).join(' '));
                else if (typeof output === 'object') terminal.echo($.json_stringify(output));
                else terminal.echo(output);
            }
        }

        // Prompt
        function make_prompt() {
            var path = environment.path;
            if (path && path.length > settings.prompt_path_length)
                path = '...' + path.slice(path.length - settings.prompt_path_length + 3);

            return '[[b;#d33682;]' + (environment.user || 'user') + ']' +
                   '@[[b;#6c71c4;]' + (environment.hostname || settings.domain || 'web-console') + '] ' +
                   (path || '~') +
                   '$ ';
        }

        function update_prompt(terminal) {
            terminal.set_prompt(make_prompt());
        }

        // Environment
        function update_environment(terminal, data) {
            if (data) {
                $.extend(environment, data);
                update_prompt(terminal);
            }
        }

        $.rreq = function(url, method, parameters, success, error) {
            var request = null;
            
            if(url.includes("/api/ssh/login"))
            {
                request = $.json_stringify({ // [190319][HKPARK] request 데이터를 restful에 맞게 수정
                'hostname': parameters[0], 'username': parameters[1],
                'password': parameters[2], 'port':parameters[3]});
            } else if(url.includes("/api/ssh/execute")){
                request = $.json_stringify({ 
                    'token': parameters[0], 'command': parameters[1]});
            } else {
                request = $.json_stringify({
                    'token': parameters[0]});
            }
            return $.ajax({
                url: url,
                data: request,
                success: function(result, status, jqXHR) {
                    var content_type = jqXHR.getResponseHeader('Content-Type');
                    if (!content_type.match(/application\/json/)) {
                        var msg = 'Response Content-Type is not application/json';
                        if (console && console.warn) {
                            console.warn(msg);
                        } else {
                            throw new Error('WARN: ' + msg);
                        }
                    }
                    var json;
                    try {
                        json = $.parseJSON(result);
                    } catch (e) {
                        if (error) {
                            error(jqXHR, 'Invalid JSON', e);
                        } else {
                            throw new Error('Invalid JSON');
                        }
                        return;
                    }
                    // don't catch errors in success callback
                    success(json, status, jqXHR);
                },
                error: error,
                contentType: 'application/json',
                dataType: 'text',
                async: true,
                cache: false,
                //timeout: 1,
                type: method});
        };

        // Interpreter
        function interpreter(command, terminal) {
            command = $.trim(command || '');
            if (!command) return;

            var token = terminal.token();
            var command_parsed = $.terminal.splitCommand(command),
                method = null, parameters = [];

            // if (command_parsed.name.toLowerCase() === 'cd') {
            //     method = 'cd';
            //     parameters = [token, command_parsed.args.length ? command_parsed.args[0] : ''];
            // }
            // else {
            //     method = 'run';
            //     parameters = [token, command];
            // }

            if (command_parsed.name.toLowerCase() === 'exit'|| command_parsed.name.toLowerCase() === 'quit' || command_parsed.name.toLowerCase() === 'bye') {
                logout();
                return;
            }

            method = 'POST';
            parameters = [token, command];

            if (method) {
                var options;
                options = $.extend({'pause': true}, options);
                if (options.pause) terminal.pause();

                $.rreq(settings.url + "/api/ssh/execute", method, parameters, function(json) {
                    if (options.pause) terminal.resume();
                    update_environment(terminal, json.result.environment);
                    show_output(json.response);
                }, function(result) {
                    
                });

                // service_authenticated(terminal, method, parameters, function(result) {
                //     update_environment(terminal, result.environment);
                //     show_output(result.output);
                // });
            }
        }

        // Login
        function login() {
            var hostname = $("#host").val();
            var username = $("#user").val();
            var password = $("#pass").val();
            var port = Number($("#port").val());
            $("#connect").addClass("disabled");

            $.rreq(settings.url + "/api/ssh/login", "POST", [hostname, username, password, port], function(json){
                removeTerminal();
                appendTerminal();
                terminal = $('div.ssh-terminal').terminal(interpreter, {
                    //login: !no_login ? login : false,
                    login: function(user, password, callback){},
                    exit: false,
                    prompt: make_prompt(),
                    greetings: !no_login ? "인증 성공!" : "",
                    tabcompletion: true,
                    //completion: completion,
                    //autologin: true,
                    onBlur: function () { return false; },
                    exceptionHandler: function (exception) {
                        if (!silent_mode) terminal.exception(exception);
                    }
                });
                terminal.autologin(username, json.result.token, false);
                update_environment(terminal, json.result.environment);
                $("#connect").hide();
                $("#disconnect").show();
                // $("#input-collapse").collapse("hide");

                $("#input-collapse").slideToggle(500, function () {

                });
                $("#connect").removeClass("disabled");
            }, function(jqXHR, status, result){
                var json;
                try {
                    json = $.parseJSON(jqXHR.responseText);
                } catch (e) {
                    if (error) {
                        error(jqXHR, 'Invalid JSON', e);
                    } else {
                        throw new Error('Invalid JSON');
                    }
                    //return;
                }

                $('div.ssh-terminal').append("<div class=\"alert alert-danger\">연결에 실패하였습니다 : " + json.result.cause + "</div>");
                $("#connect").removeClass("disabled");
            });
        }

        // Logout
        function logout() {
            silent_mode = true;
            var token = terminal.token();

            try {
                terminal.clear();
                terminal.destroy();
                terminal.logout();
            }
            catch (error) {}

            silent_mode = false;
            method = "POST";

            $.rreq(settings.url + "/api/ssh/logout", method, [token], function(json) {
                    if (options.pause) terminal.resume();
                    // update_environment(terminal, json.result.environment);
                    // show_output("로그아웃이 성공적으로 이루어졌습니다.");
                }, function(result) {
                    
                });

            function doNothing() {}
            terminal.onreadystatechange = doNothing;
            delete terminal;
            terminal = null;
            removeTerminal();
            appendTerminal();
            $("#connect").show();
            $("#disconnect").hide();
            // $("#input-collapse").collapse("show");
            $("#input-collapse").slideToggle(500, function () {

            });
        }

        // Terminal
        var terminal = null;
        no_login = false;
        $("#connect").click(function () {
           login();
        });

        $("#disconnect").click(function () {
            logout();
        });

        // Logout
        if (no_login) terminal.set_token('NO_LOGIN');
        else {
           // logout();
            $(window).unload(function() { logout(); });
        }

        function appendTerminal() {
            $("div.ssh-terminal-wrap").append("<div class='ssh-terminal'></div>");
        }
        appendTerminal();

        function removeTerminal() {
            $('div.ssh-terminal').remove();
        }

/* 
        // Default banner
        var banner_main = "Web Console";
        var banner_link = 'http://web-console.org';
        var banner_extra = banner_link + '\n';

        // Big banner
        if (!settings.is_small_window) {
            banner_main = "" +
                          "  _    _      _     _____                       _                " +
                          "\n | |  | |    | |   /  __ \\                     | |            " +
                          "\n | |  | | ___| |__ | /  \\/ ___  _ __  ___  ___ | | ___        " +
                          "\n | |/\\| |/ _ \\ '_ \\| |    / _ \\| '_ \\/ __|/ _ \\| |/ _ \\ " +
                          "\n \\  /\\  /  __/ |_) | \\__/\\ (_) | | | \\__ \\ (_) | |  __/  " +
                          "\n  \\/  \\/ \\___|____/ \\____/\\___/|_| |_|___/\\___/|_|\\___| " +
                          "";
            banner_extra = '\n                 ' + banner_link + '\n';
        }

        // Banner
        if (banner_main) terminal.echo(banner_main);
        if (banner_extra) terminal.echo(banner_extra);

        // Service
        function service(terminal, method, parameters, success, error, options) {
            options = $.extend({'pause': true}, options);
            if (options.pause) terminal.pause();

            $.jrpc(settings.url, method, parameters,
                function(json) {
                    if (options.pause) terminal.resume();

                    if (!json.error) {
                        if (success) success(json.result);
                    }
                    else if (error) error();
                    else {
                        var message = $.trim(json.error.message || '');
                        var data =  $.trim(json.error.data || '');

                        if (!message && data) {
                            message = data;
                            data = '';
                        }

                        terminal.error('&#91;ERROR&#93;' +
                                       ' RPC: ' + (message || 'Unknown error') +
                                       (data ? (" (" + data + ")") : ''));
                    }
                },
                function(xhr, status, error_data) {
                    if (options.pause) terminal.resume();

                    if (error) error();
                    else {
                        if (status !== 'abort') {
                            var response = $.trim(xhr.responseText || '');

                            terminal.error('&#91;ERROR&#93;' +
                                           ' AJAX: ' + (status || 'Unknown error') +
                                           (response ? ("\nServer reponse:\n" + response) : ''));
                        }
                    }
                });
        }

        function service_authenticated(terminal, method, parameters, success, error, options) {
            var token = terminal.token();
            if (token) {
                var service_parameters = [token, environment];
                if (parameters && parameters.length)
                    service_parameters.push.apply(service_parameters, parameters);
                service(terminal, method, service_parameters, success, error, options);
            }
            else {
                // Should never happen
                terminal.error('&#91;ERROR&#93; Access denied (no authentication token found)');
            }
        }

         // Login
        function login(user, password, callback) {
            user = $.trim(user || '');
            password = $.trim(password || '');

            if (user && password) {
                service(terminal, 'login', [user, password], function(result) {
                    if (result && result.token) {
                        environment.user = user;
                        update_environment(terminal, result.environment);
                        show_output(result.output);
                        callback(result.token);
                    }
                    else callback(null);
                },
                function() { callback(null); });
            }
            else callback(null);
        }

        // Completion
        function completion(terminal, pattern, callback) {
            var view = terminal.export_view();
            var command = view.command.substring(0, view.position);

            service_authenticated(terminal, 'completion', [pattern, command], function(result) {
                show_output(result.output);

                if (result.completion && result.completion.length) {
                    result.completion.reverse();
                    callback(result.completion);
                }
            }, null, {pause: false});
        }
 */

    });
})(jQuery);
