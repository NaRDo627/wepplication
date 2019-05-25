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

    <!--필요 css파일-->
    <link rel="stylesheet" href="../../resources/chart/css/ref/morris.css">
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
                            </li>
                            <li><a href="#usageGraph-tab" data-toggle="tab" id="line_tab">사용량</a>
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
                                                                <p class="form-control-static">${membership.mname}</p>
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
                                                                <p class="form-control-static">현재 비밀번호</p>
                                                            </th>
                                                            <td>
                                                                <input class="form-control" placeholder="Password" name="password" type="password" id="curPassword" value="">
                                                            </td>
                                                        </tr>
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
                                                                <p class="form-control-static">${membership.mname}</p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th>
                                                                <p class="form-control-static">지속기간</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">
                                                                    <c:choose>
                                                                        <c:when test="${membership.duration == 0}">
                                                                            평생
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${membership.duration}
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
                                                                        <c:when test="${membership.duration == 0}">
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
                                                                <p class="form-control-static">자동 구독 갱신</p>
                                                            </th>
                                                            <td>
                                                                <p class="form-control-static">
                                                                    <span id="autoRenewalText">
                                                                        <c:choose>
                                                                            <c:when test="${sessionScope.user_membership.isAutoSubscribe == 1}">
                                                                                <span class="text-success">ON</span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="text-danger">OFF</span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </span>
                                                                    &nbsp;&nbsp;&nbsp;
                                                                    <a href="#" class="btn btn-success" style="display:inline;" id="btnToggleAutoRenewal">자동 구독 변경</a>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="text-align: center;">
                                                                <a href="#" class="btn btn-lg btn-primary btn-block" style="width:30%; display:inline-block;" id="btnRegisterMembership">구독 신청</a>
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
                            <div class="tab-pane fade" id="usageGraph-tab">
                                <div class="row">
                                    <br><br>
                                    <div class="col-xs-12">
<%--                                        <div class="table-responsive">--%>
                                            <div class="panel panel-default">
                                                <div class="panel-body" style="height: 600px;">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <p class="form-control-static">월별 사용량 그래프</p>
                                                            <div id="morrisLine_who2" style=""></div>
                                                        </div>
                                                    </div>


                                                    <%--
                                                  <table class="table table-hover">
                                                      <tbody>
                                                      <tr>
                                                          <th>
                                                              <p class="form-control-static">월별 사용량 그래프</p>
                                                          </th>
                                                      </tr>
                                                      <tr>
                                                          <td>
                                                              <div id="morrisLine_who2" style="width:520px; height:500px;"></div>
                                                          </td>
                                                      </tr>
                                                      </tbody>

                                                    </table>--%>
                                                    <!-- ./table responsive -->
                                                </div>
                                            </div>
<%--                                        </div>--%>
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
        <!--필수 js-->
     <%--   <script type="text/javascript" src="../../resources/chart/js/ref/jquery-3.4.1.js"></script>
        <script type="text/javascript" src="../../resources/chart/js/ref/morris.js"></script>
        <script type="text/javascript" src="../../resources/chart/js/ref/raphael.js"></script>--%>
        <!--chart display js-->
<%--        <script type="text/javascript" src="../../resources/chart/js/displayChart_profile.js"></script>--%>
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
                   console.log(jaXHR);
               }
           });
       });

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

       $("#btnRegisterMembership").click(function () {
           $.ajax({
               url: "/registerMemberShip",
               type: 'POST',
               data: {
                   'mno': 2
               },
               success: function (result) {
                   alert("TEST: Pro 구독으로 변경되었습니다!");
                   location.reload();

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

        var dataString =
            //값을 가져오는 형태
            //time: "년-월"(문자열), imageEditor: 횟수, videoEditor: 횟수, gifEditor: 횟수, encryption: 횟수, ssh: 횟수
            '[{"time": "2019-5", "imageEditor": 4, "videoEditor": 3, "gifEditor":  5, "encryption": 8, "ssh": 1}, ' +
            '{"time": "2019-4", "imageEditor": 3, "videoEditor": 1, "gifEditor":  0, "encryption": 1, "ssh": 9}, ' +
            '{"time": "2019-3", "imageEditor": 0, "videoEditor": 5, "gifEditor":  3, "encryption": 12, "ssh": 5}, ' +
            '{"time": "2019-2", "imageEditor": 2, "videoEditor": 4, "gifEditor":  4, "encryption": 0, "ssh": 15}]';
        var data = JSON.parse(dataString);

        var line = Morris.Line({
            element: 'morrisLine_who2', //jsp 파일에 있는 그래프의 아이디 값 -> <div id="morrisLine_who2"></div>
            data: data, //json 데이터 받아오는 곳
            xkey: 'time', //X 축으로 설정할 값, 무조건 1개, 년-월로 설정
            ykeys: ['imageEditor', 'videoEditor', 'gifEditor', 'encryption', 'ssh'], //Y축으로 설정할 값, 1개 이상
            labels: ['imageEditor', 'videoEditor', 'gifEditor', 'encryption', 'ssh'], //네모난 박스로 설명란에 적을 값
            axes: true, //X축, Y축이 보이지 않도록 설정, X축은 공간 부족, Y축은 실제 값과 맞지가 않아서 제거
            resize: true
        });


        $("#line_tab").click(function () {
            setTimeout(function() {
                resizeLine()
            }, 100);
        });


    });
    function resizeLine() {
        $("#morrisLine_who2 svg").trigger("resize");
    }



</script>
</body>

</html>
