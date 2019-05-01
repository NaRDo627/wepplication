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
    <!-- style -->
    <link rel="stylesheet" href="/resources/vendor/normalize/normalize.css" />
    <link rel="stylesheet" href="/resources/vendor/jquery.terminal/css/jquery.terminal-0.11.12.min.css" />
    <link rel="stylesheet" href="/resources/css/webconsole.css" />
    <!-- javascript -->
    <script type="text/javascript" src="/resources/vendor/jquery.terminal/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/resources/vendor/jquery.terminal/js/jquery.mousewheel-min.js"></script>
    <script type="text/javascript" src="/resources/vendor/jquery.terminal/js/jquery.terminal-0.11.12.js"></script>
    <script type="text/javascript" src="/resources/js/webconsole.js"></script>

    <%@include file="../assets/IncAsset.jsp"%>

</head>

<body>
    <div id="wrapper">
        <%@include file="../assets/IncHeader.jsp"%>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">SSH 터미널</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="alert alert-warning">경고 : WEB기반 터미널이기 때문에, 보안 문제상 ROOT 혹은 그 권한을 가진 계정의 접속은 권장되지 않습니다.</div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <form role="form">
                                    <div id="input-collapse">
                                        <div class="form-group">
                                            <label>호스트 이름</label><br>
                                            <input class="form-control" placeholder="호스트 이름을 입력하세요" id="host" style="width: 55%; display: inline;">
                                            <input class="form-control" placeholder="포트번호를 입력하세요" id="port" value="22" style="width: 40%; display: inline;">
                                        </div>
                                        <div class="form-group">
                                            <label>사용자 이름</label>
                                            <input class="form-control" placeholder="사용자 이름을 입력하세요" id="user">
                                        </div>
                                        <div class="form-group">
                                            <label>비밀번호</label>
                                            <input class="form-control" placeholder="비밀번호를 입력하세요" type="password" id="pass">
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-primary" id="connect">연결</button>
                                    <button type="button" class="btn btn-danger" id="disconnect" style="display:none;">연결 끊기</button>
                                </form>
                            </div>
                            <div class="panel-body ssh-terminal-wrap" style="height:500px;padding: 0;background-color: black;">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@include file="../assets/IncFooter.jsp"%>
</body>
<script>
    $(document).ready(function () {
        $("#host").keyup(function(e){if(e.keyCode == 13) $("#connect").trigger("click"); });
        $("#user").keyup(function(e){if(e.keyCode == 13) $("#connect").trigger("click"); });
        $("#pass").keyup(function(e){if(e.keyCode == 13) $("#connect").trigger("click"); });
    });
</script>
</html>
