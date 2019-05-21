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
                <h1 class="page-header">비밀번호 변경</h1>
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
                        <table class="table table-hover">
                            <tbody>
                            <tr>
                                <th>
                                    <p class="form-control-static">새 비밀번호</p>
                                </th>
                                <td>
                                    <input class="form-control" placeholder="Password" name="password" type="password" id="newPassword" value="">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <p class="form-control-static">새 비밀번호 확인</p>
                                </th>
                                <td>
                                    <input class="form-control" placeholder="Password-Check" name="password-check" type="password" id="newPasswordCheck" value="">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align: center;">
                                    <a href="#" class="btn btn-lg btn-primary btn-block" style="width:30%; display:inline-block;" id="btnChangePassword">비밀번호 변경</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
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
       $("#btnChangePassword").click(function () {
           // 빈 칸 검사
           if($("#newPassword").val() === "" || $("#newPasswordCheck").val() === "") {
               alert("입력을 확인해 주세요!");
               return false;
           }

           // 비밀번호와 확인이 서로 같은 지 검사
           if($("#newPassword").val() !== $("#newPasswordCheck").val()) {
               alert("비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.")
               return false;
           }

           $.ajax({
               url: "/findMemberPW",
               type: 'POST',
               data: {
                   'uno': Number('${uno}'),
                   'newPassword': $("#newPassword").val()
               },
               success: function (result) {
                   if(result === "ok"){
                       alert("비밀번호 변경이 완료되었습니다.");
                       location.href = "/login";
                   } else {
                       alert("비밀번호 변경 중 오류가 발생하였습니다.");
                   }
                   console.log(result);
               },
               error: function (jaXHR, textStatus, errorThrown) {
                   alert("비밀번호 변경 중 오류가 발생하였습니다.");
                   console.log(jaXHR);
               }
           });
       });


        $("input").keyup(function (e) {
            if (e.keyCode == 13) $("#btnChangePassword").trigger("click");
        });
    });
</script>
</body>

</html>
