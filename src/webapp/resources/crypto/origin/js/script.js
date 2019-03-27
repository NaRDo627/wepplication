$(function(){

    var body = $('body'),
        stage = $('#stage'),
        back = $('a.back');

    /* Step 1 */

    $('#step1 .encrypt').click(function(){
        body.attr('class', 'encrypt');

        // Go to step 2
        step(2);
    });

    $('#step1 .decrypt').click(function(){
        body.attr('class', 'decrypt');
        step(2);
    });


    /* Step 2 */


    $('#step2 .button').click(function(){
        // Trigger the file browser dialog
        $(this).parent().find('input').click();
    });


    // Set up events for the file inputs

    var file = null;

    $('#step2').on('change', '#encrypt-input', function(e){

        // Has a file been selected?

        if(e.target.files.length!=1){
            alert('암호화하실 파일을 선택하세요!');
            return false;
        }

        file = e.target.files[0];

        if(file.size > 1024*1024*30){
            alert('30MB이하의 파일을 선택하실 수 있습니다');
            return;
        }

        step(3);
    });

    $('#step2').on('change', '#decrypt-input', function(e){

        if(e.target.files.length!=1){
            alert('복호화하실 파일을 선택하세요!');
            return false;
        }

        file = e.target.files[0];
        step(3);
    });


    /* Step 3 */


    $('a.button.process').click(function(){

        var input = $(this).parent().find('input[type=password]'),
            a = $('#step4 a.download'),
            password = input.val();

        input.val('');

        if(password.length<5){
            alert('비밀 번호는 5자 이상 입력 가능합니다!');
            return;
        }

        // The HTML5 FileReader object will allow us to read the
        // contents of the	selected file.

        var reader = new FileReader();

        if(body.hasClass('encrypt')){

            // Encrypt the file!

            reader.onload = function(e){

                // Use the CryptoJS library and the AES cypher to encrypt the
                // contents of the file, held in e.target.result, with the password

                var encrypted = CryptoJS.AES.encrypt(e.target.result, password);

                //성공했던 코드_아래 참고 사이트
                //https://www.reddit.com/r/webdev/comments/7bu0la/google_chrome_download_failed_network_error_when/
                var encryptedFileArray = [encrypted];

                var blobEncrypted = new Blob(encryptedFileArray, {
                    type : "application/octet-stream;"
                });

                var blobUrl = URL.createObjectURL(blobEncrypted);
                $(a).attr({
                    'download': file.name + '.encrypted',
                    'href': blobUrl
                });

                // The download attribute will cause the contents of the href
                // attribute to be downloaded when clicked. The download attribute
                // also holds the name of the file that is offered for download.
                step(4);
            };

            // This will encode the contents of the file into a data-uri.
            // It will trigger the onload handler above, with the result

            reader.readAsDataURL(file);
        }
        else {

            // Decrypt it!

            reader.onload = function(e){

                var decrypted = CryptoJS.AES.decrypt(e.target.result, password).toString(CryptoJS.enc.Latin1);

                if(!/^data:/.test(decrypted)){
                    alert("파일을 잘못 선택하셨거나 비밀번호가 틀렸습니다. 다시 시도해주세요!");
                    return false;
                }
                //성공했던 코드_아래 참고 사이트
                //https://aspdotnet.tistory.com/1992
                var url = decrypted; //자동 다운
                var filename = file.name.replace('.encrypted',''); //자동 다운
                var xhr = new XMLHttpRequest(); //자동 다운

                xhr.responseType = 'blob';
                xhr.onload = function () {
                    $(a).attr({
                        'download': filename,
                        'href': window.URL.createObjectURL(xhr.response) // xhr.response is a blob
                    });
                };
                xhr.open('GET', url);
                xhr.send();

                step(4);
            };

            reader.readAsText(file);
        }
    });


    /* The back button */


    back.click(function(){

        // Reinitialize the hidden file inputs,
        // so that they don't hold the selection
        // from last time

        $('#step2 input[type=file]').replaceWith(function(){
            return $(this).clone();
        });

        step(1);
    });


    // Helper function that moves the viewport to the correct step div

    function step(i){

        if(i == 1){
            back.fadeOut();
        }
        else{
            back.fadeIn();
        }

        // Move the #stage div. Changing the top property will trigger
        // a css transition on the element. i-1 because we want the
        // steps to start from 1:

        stage.css('top',(-(i-1)*100)+'%');
    }

});
