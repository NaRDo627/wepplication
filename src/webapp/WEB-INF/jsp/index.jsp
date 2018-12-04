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
        <%--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">웹플리케이션</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>--%>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-information">환영합니다&nbsp;
                    <c:if test="${sessionScope.users ne null}">
                        ${sessionScope.users.get('userNickname')} 님
                    </c:if>!
                </h1>
            </div>
            <div class="clear"></div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row clear">
            &nbsp;
        </div>
        <div class="row">
            <c:choose>
                <c:when test="${sessionScope.users eq null}">
                    <!-- 비회원일 경우 -->
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <p class="text-info" style="font-size:25px;">현재 비회원 상태입니다.<br>
                                    지금 로그인 하시거나 회원가입 하시고 혜택을 누리세요!</p>
                                <a href="/login" class="btn btn-primary">로그인</a>
                                <a href="/member" class="btn btn-primary">회원가입</a>
                            </div>
                        </div>
                    </div>
                    <!-- /.비회원일 경우 -->
                </c:when>
                <c:otherwise>
                    <!-- 회원일 경우 -->
                    <div class="col-lg-6 col-md-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-clock-o fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <c:choose>
                                            <c:when test="${sessionScope.users.get('lastUsed').equals('/viewer/image_viewer')}">
                                                <div>최근에</div>
                                                <div class="huge">이미지 뷰어</div>
                                                <div>사용하셨군요!</div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>최근에 사용하신 도구가 없습니다</div>
                                                <div class="huge">이미지 뷰어</div>
                                                <div>를 한 번 시도해 보는것이 어떨까요?</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <a href="#" id="recent_href">
                                <div class="panel-footer">
                                    <span class="pull-left">이동</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-users fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div>현재 고객님께서는</div>
                                        <div class="huge">무료</div>
                                        <div>멤버십을 사용하고 계십니다</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">상세 확인</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>

                    <%--<div class="col-lg-6 col-md-12">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-bell fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div>새로운 알림이</div>
                                        <div class="huge">13</div>
                                        <div>개 있습니다</div>
                                    </div>
                                </div>
                            </div>
                            <a href="#">
                                <div class="panel-footer">
                                    <span class="pull-left">알림센터 가기</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>--%>

                    <!-- /.회원일 경우 -->
                </c:otherwise>
            </c:choose>
        </div>
        <!-- /.row -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


<%@include file="assets/IncFooter.jsp" %>
<script>
    $(document).ready(function () {
        var recentUsed = '${sessionScope.users.get('lastUsed')}';
        if(recentUsed === 'null')
            recentUsed = "/viewer/image_viewer";

        $("#recent_href").prop("href", recentUsed);
    });
</script>
</body>

</html>
