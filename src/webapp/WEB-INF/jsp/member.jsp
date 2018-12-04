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

    <%@include file="assets/IncAsset.jsp"%>

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">회원가입</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="memberForm">
                            <fieldset>
                                <div class="form-group">
                                    <label for="id">아이디</label>
                                    <input class="form-control" placeholder="ID" name="id" type="text" id="id" autofocus>
                                </div>
                                <div class="form-group">
                                    <label for="password">비밀번호</label>
                                    <input class="form-control" placeholder="Password" name="password" type="password" id="password" value="">
                                </div>
                                <div class="form-group">
                                    <label for="password-check">비밀번호 확인</label>
                                    <input class="form-control" placeholder="Password Check" name="password-check" type="password" id="password-check" value="">
                                </div>
                                <div class="form-group">
                                    <label for="name">이름</label>
                                    <input class="form-control" placeholder="Name" name="name" type="text" id="name">
                                </div>
                                <div class="form-group">
                                    <label for="nickname">별명</label>
                                    <input class="form-control" placeholder="NickName" name="nickname" type="text" id="nickname">
                                </div>
                                <div class="form-group">
                                    <label for="email1">이메일</label><br>
                                    <input class="form-control" placeholder="test" name="email1" type="text" id="email1" style="width:40%; display: inline;">&nbsp;@&nbsp;<input class="form-control" placeholder="example.com" name="email2" type="text" id="email2" style="width:50%; display: inline;">
                                </div>

                                <%--<div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">자동 로그인
                                    </label>
                                </div>--%>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#" class="btn btn-lg btn-primary btn-block" id="btn-submit">가입하기</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="assets/IncFooter.jsp"%>
    <script>
        $(document).ready(function () {
            $("#btn-submit").click(function () {
                var Users = {
                    "userId": $("#id").val(),
                    "password": $("#password").val(),
                    "userName": $("#name").val(),
                    "userNickname": $("#nickname").val(),
                    "email": $("#email1").val() + "@" + $("#email2").val()
                };

                $.ajax({
                    url : REST_URL + "/users",
                    dataType : 'json',
                    type : 'POST',
                    data : JSON.stringify(Users),
                    contentType : 'application/json; charset=UTF-8',
                    success : function(result) {
                        alert("회원가입이 완료되었습니다. 로그인 해주세요.");
                        console.log(result);
                        location.href = "/login";
                    }
                });

            });
        });
    </script>
</body>

</html>
