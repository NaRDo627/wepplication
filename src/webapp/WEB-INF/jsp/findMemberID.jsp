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
                <h1 class="page-header">아이디 찾기</h1>
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
                        <c:choose>
                            <c:when test="${found eq false}">
                                <p>입력하신 정보를 확인해 주세요.</p>
                                <br>
                                <p><a href="/findMember">돌아가기</a> </p>
                            </c:when>
                            <c:otherwise>
                                <p>고객님의 아이디는 ${foundID} 입니다.</p>
                                <br>
                                <p><a href="/login">로그인 창으로</a> </p>
                            </c:otherwise>
                        </c:choose>
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
   /* $(document).ready(function () {
       $("a[href='#id-tab").parent().addClass("active");
       $("#id-tab").addClass("in");
       $("#id-tab").addClass("active");
       


       $("#btnChangePassword").click(function () {
           // 빈 칸 검사
           if($("#curPassword").val() === "" || $("#newPassword").val() === "" || $("#newPasswordCheck").val() === "") {
               alert("입력을 확인해 주세요!");
               return false;
           }

           // 비밀번호와 확인이 서로 같은 지 검사
           if($("#newPassword").val() !== $("#newPasswordCheck").val()) {
               alert("비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.")
               return false;
           }

           $.ajax({
               url: "/changePW",
               type: 'POST',
               data: {
                   'curPassword': $("#curPassword").val(),
                   'newPassword': $("#newPassword").val()
               },
               success: function (result) {
                   if(result === "ok"){
                       alert("비밀번호 변경이 완료되었습니다.");
                       $("#curPassword").val("");
                       $("#newPassword").val("");
                       $("#newPasswordCheck").val("");
                   } else if(result === "login_first"){
                       alert("세션에서 로그아웃 되었습니다.");
                   } else if(result === "wrong_user"){
                       alert("현재 비밀번호가 일치하지 않습니다.");
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

        var isAutoSubscribe = Number('${sessionScope.user_memberShip.isAutoSubscribe}');

        $("#btnToggleAutoRenewal").click(function () {

            $.ajax({
                url: "/modifyAutoRenewal",
                type: 'POST',
                data: {
                    'autoRenewal': (isAutoSubscribe === 0)? 1 : 0
                },
                success: function (result) {
                    isAutoSubscribe = (isAutoSubscribe === 0)? 1 : 0;
                    $("#autoRenewalText").empty();

                    if(isAutoSubscribe === 1)
                        $("#autoRenewalText").append("<span class=\"text-success\">ON</span>");
                    else
                        $("#autoRenewalText").append("<span class=\"text-danger\">OFF</span>");

                    console.log(result);
                },
                error: function (jaXHR, textStatus, errorThrown) {
                    console.log(jaXHR);
                }
            });
        });

        $("input").keyup(function (e) {
            if (e.keyCode == 13) $("#btnChangePassword").trigger("click");
        });
    });*/
</script>
</body>

</html>
