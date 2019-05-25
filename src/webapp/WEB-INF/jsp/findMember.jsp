<%@ page import="com.wepplication.Util.DateTimeUtil" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.wepplication.Domain.UserMemberShip" %>
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
    <style>
        li.active > a {
            background-color: #f8f8f8 !important;
        }

        div.tab-pane.fade {
            border-left: solid 1px #dddddd;
            border-bottom: solid 1px #dddddd;
            border-right: solid 1px #dddddd;
        }
    </style>
</head>

<body>
<div id="wrapper">
    <%@include file="assets/IncHeader.jsp" %>

    <div id="page-wrapper">
        <%--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">웹플리케이션</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>--%>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">아이디/비밀번호 찾기</h1>
            </div>
            <div class="clear"></div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li><a href="#id-tab" data-toggle="tab">아이디 찾기</a>
                            </li>
                            <li><a href="#password-tab" data-toggle="tab">비밀번호 찾기</a>
                            </li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content" style="background-color: #f8f8f8;">
                            <div class="tab-pane fade" id="id-tab">
                                <div class="row">
                                    <br><br>
                                    <div class="col-md-8 col-md-offset-2">
                                        <div class="table-responsive">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <form method="post" action="/findMemberID" id="frmFindID">
                                                        <div class="form-group">
                                                            <label for="ID_userName">이름</label>
                                                            <input class="form-control" placeholder="Name" name="userName" type="text" id="ID_userName"
                                                                   autofocus required>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="hidden" name="email" id="email">
                                                            <label for="ID_email1">이메일</label><br>
                                                            <input class="form-control" placeholder="test" type="text"
                                                                   id="ID_email1" style="width:40%; display: inline;"
                                                                   required>&nbsp;@&nbsp;<input class="form-control"
                                                                                                placeholder="example.com"
                                                                                                type="text" id="ID_email2"
                                                                                                style="width:50%; display: inline;"
                                                                                                required>
                                                        </div>
                                                    </form>
                                                    <a href="#" class="btn btn-lg btn-success btn-block" id="btnFindID">아이디 찾기</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="password-tab">
                                <div class="row">
                                    <br><br>
                                    <div class="col-md-8 col-md-offset-2">
                                        <div class="table-responsive">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="form-group">
                                                        <label for="PW_userId">아이디</label>
                                                        <input class="form-control" placeholder="ID" name="userId" type="text" id="PW_userId"
                                                               autofocus required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="PW_email1">이메일</label><br>
                                                        <input class="form-control" placeholder="test" name="email1" type="text"
                                                               id="PW_email1" style="width:40%; display: inline;"
                                                               required>&nbsp;@&nbsp;<input class="form-control"
                                                                                            placeholder="example.com" name="email2"
                                                                                            type="text" id="PW_email2"
                                                                                            style="width:50%; display: inline;"
                                                                                            required>
                                                    </div>
                                                    <a href="#" class="btn btn-lg btn-success btn-block" id="btnFindPW">비밀번호 찾기</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->

            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@include file="assets/IncFooter.jsp" %>
<script>
    $(document).ready(function () {
       $("a[href='#id-tab").parent().addClass("active");
       $("#id-tab").addClass("in");
       $("#id-tab").addClass("active");
       
       $("#btnFindID").click(function () {
           var email = $("#ID_email1").val() + "@" + $("#ID_email2").val();
           $("#email").val(email);

           $("#frmFindID").submit();
       });

       $("#btnFindPW").click(function () {
           var email = $("#PW_email1").val() + "@" + $("#PW_email2").val();

           $.ajax({
               url: "/sendChangePWMail",
               type: 'POST',
               data: {
                   'userID':  $("#PW_userId").val(),
                   'email': email
               },
               success: function (result) {
                   if(result === "ok"){
                       alert("메일이 전송되었습니다. 메일함을 확인해 주세요.");
                   } else if(result === "no_user_exists"){
                       alert("해당되는 회원 정보를 찾지 못하였습니다. 입력을 확인해 주세요.");
                   }
                   else {
                       alert("비밀번호 찾기 중 오류가 발생하였습니다.");
                   }
                   console.log(result);
               },
               error: function (jaXHR, textStatus, errorThrown) {
                   alert("비밀번호 찾기 중 오류가 발생하였습니다.");
                   console.log(jaXHR);
               }
           });
       });

        $("#id-tab input").keyup(function (e) {
            if (e.keyCode == 13) $("#btnFindID").trigger("click");
        });

        $("#password-tab input").keyup(function (e) {
            if (e.keyCode == 13) $("#btnFindPW").trigger("click");
        });
    });
</script>
</body>

</html>
