<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>웹플리케이션</title>

    <%@include file="assets/IncAsset.jsp" %>

</head>

<body>
<div id="wrapper">

    <%@include file="assets/IncHeader.jsp" %>
    <div id="page-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">로그인</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <fieldset>
                                    <div class="form-group">
                                        <label for="id">아이디</label>
                                        <input class="form-control" placeholder="ID" name="id" type="text" id="id"
                                               autofocus>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">비밀번호</label>
                                        <input class="form-control" placeholder="Password" name="password"
                                               type="password" id="password" value="">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="remember" id="remember" type="checkbox" value="Remember Me">자동 로그인
                                        </label>
                                    </div>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <a href="#" class="btn btn-lg btn-success btn-block" id="btn-submit">로그인</a>
                                    <a href="/member" class="btn btn-lg btn-primary btn-block">회원가입</a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="assets/IncFooter.jsp" %>
<script>
    $(document).ready(function () {
        // Auto-Login
        var autoLogin = 0;
        if($("#remember").is(":checked"))
            autoLogin = 1;

        $("#btn-submit").click(function () {
            var arg1 = {
                "userId": $("#id").val(),
                "password": $("#password").val(),
                "autoLogin": autoLogin
            };

            $.post("/login", arg1, function (data) {
                if (data === "ok") {
                    location.href = "/";
                }
                else if (data === "unauthorized") {
                    alert("아이디나 비밀번호를 확인해주세요!");
                }
                else {
                    alert("에러발생, 코드 : " + data);
                }
            });

        });

        $("#id").keyup(function (e) {
            if (e.keyCode == 13) $("#btn-submit").trigger("click");
        });
        $("#password").keyup(function (e) {
            if (e.keyCode == 13) $("#btn-submit").trigger("click");
        });
    });
</script>
</body>

</html>
