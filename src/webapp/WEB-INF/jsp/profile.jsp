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
                <h1 class="page-header">유저 프로필</h1>
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
                            <li><a href="#home-tab" data-toggle="tab">개요</a>
                            </li>
                            <li><a href="#password-tab" data-toggle="tab">비밀번호</a>
                            </li>
                            <li><a href="#membership-tab" data-toggle="tab">구독</a>
                            </li><%--
                            <li><a href="#settings-tab" data-toggle="tab">설정</a>
                            </li>--%>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content" style="background-color: #f8f8f8;">
                            <div class="tab-pane fade" id="home-tab">
                                <div class="row">
                                    <br><br>
                                    <div class="col-md-8 col-md-offset-2">
                                        <div class="table-responsive">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <table class="table table-hover">
                                                        <tbody>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">아이디</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.users.userId}</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">이름</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.users.userName}</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">별명</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.users.userNickname}</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">이메일</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.users.email}&nbsp;
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.users.verified == 0}">
                                                                            <span class="text-danger"><i class="fa fa-warning"></i>인증이 필요합니다.</span>
                                                                            <a href="#" id="sendVerify" class="btn btn-success">인증하기</a>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span class="text-success">인증 완료</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:if test="">

                                                                    </c:if>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">구독</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.membership.mname}</p>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <!-- ./table responsive -->
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
                                                    <table class="table table-hover">
                                                        <tbody>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">비밀번호</p>
                                                            </th>
                                                            <td>
                                                                <input class="form-control" placeholder="Password" name="password" type="password" id="password" value="">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">비밀번호 확인</p>
                                                            </th>
                                                            <td>
                                                                <input class="form-control" placeholder="Password-Check" name="password-check" type="password" id="password-check" value="">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="text-align: center;">
                                                                <a href="#" class="btn btn-lg btn-primary btn-block" style="width:30%; display:inline-block;">비밀번호 변경</a>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <!-- ./table responsive -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="membership-tab">
                                <div class="row">
                                    <br><br>
                                    <div class="col-md-8 col-md-offset-2">
                                        <div class="table-responsive">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <table class="table table-hover">
                                                        <tbody>
                                                        <tr>
                                                            <th width="30%">
                                                                <p class="form-control-static">구독 이름</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">${sessionScope.membership.mname}</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">지속기간</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.membership.duration == 0}">
                                                                            평생
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${sessionScope.membership.duration}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">남은기간</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.membership.duration == 0}">
                                                                            평생
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <%=((((UserMemberShip)session.getAttribute("user_membership")).getEndTime().getTime()) -
                                                                                    DateTimeUtil.now().getTime()) / (1000*60*60*24) + 1%>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">자동 구독</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.user_membership.isAutoSubscribe == 1}">
                                                                            ON
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            OFF
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <%--<tr>
                                                            <th>
                                                                <p class="form-control-static">멤버십 등록</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">email@example.com</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">멤버십</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">무료</p>
                                                            </td>
                                                        </tr>--%>
                                                        </tbody>
                                                    </table>
                                                    <!-- ./table responsive -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--
                            <div class="tab-pane fade" id="settings-tab">
                                <div class="table-responsive">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">뷰어</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static"></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">이미지편집</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static"></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">동영상편집</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static"></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">클라우드</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static"></p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">암호화/복호화</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static"></p>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <!-- ./table responsive -->
                                        </div>
                                    </div>
                                </div>
                            </div>--%>
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
       $("a[href='#"+'${tab_select}'+"'").parent().addClass("active");
       $("#" + '${tab_select}').addClass("in");
       $("#" + '${tab_select}').addClass("active");
       
       $("#sendVerify").click(function () {
           $.ajax({
               url: "/send_verify_mail",
               type: 'POST',
               success: function (result) {
                   if(result === "ok"){
                       alert("인증 메일이 전송되었습니다. 이메일을 확인해 주세요.");
                   } else if(result === "login first"){
                       alert("세션에서 로그아웃 되었습니다.");
                   } else {
                       alert("인증메일 전송 중 오류가 발생하였습니다.");
                   }
                   console.log(result);
               },
               error: function (jaXHR, textStatus, errorThrown) {
                   alert("인증메일 전송 중 오류가 발생하였습니다.");
                   console.log(jaXHR.responseText);
               }
           });
       });
    });
</script>
</body>

</html>
