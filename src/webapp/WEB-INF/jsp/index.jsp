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
        div.row > div > a:hover {
            text-decoration: none;
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
                <h1 class="page-information">환영합니다&nbsp;
                    <c:if test="${sessionScope.users ne null}">
                        ${sessionScope.users.userNickname} 님
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
                                            <c:when test="${sessionScope.users.lastUsed ne null and sessionScope.users.lastUsed != 'null'}">
                                                <div>최근에</div>
                                                <div class="huge">${sessionScope.users.lastUsed}</div>
                                                <div>사용하셨군요!</div>
                                            </c:when>
                                            <c:otherwise>
                                                <div>처음이시군요!</div>
                                                <div class="huge">이미지 편집</div>
                                                <div>를 한 번 시도해 보는것이 어떨까요?</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                            <a href="#" id="recent_href">
                                <div class="panel-footer">
                                    <span class="pull-left">바로가기</span>
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
                                        <div class="huge">${sessionScope.membership.mname}</div>
                                        <div>구독을 사용하고 계십니다</div>
                                    </div>
                                </div>
                            </div>
                            <a href="/subscribe">
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
        <div class="row">
            <div class="col-lg-6 col-md-12">
                <div class="panel panel-default" style="background-color: #FAFAFA">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>미디어 편집</h2>
                                <span style="font-size:16px;">
                                        간단한 편집기능이 필요하십니까? 이런 편집기들은 어떠세요?
                                    </span>
                            </div>
                        </div>
                        <br><br><br>
                        <div class="row">
                            <div class="col-lg-6 col-md-12">
                                <a href="/editor/image_editor">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <p class="text-center" style="font-size:20px;">이미지 편집</p>
                                        </div>
                                        <div class="panel-footer" style="background-color: white">
                                            <span class="pull-left">이동</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <a href="/editor/video_editor">
                                    <div class="panel panel-success">
                                        <div class="panel-heading">
                                            <p class="text-center" style="font-size:20px;">동영상 편집</p>
                                        </div>
                                        <div class="panel-footer" style="background-color: white">
                                            <span class="pull-left">이동</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-12">
                <div class="panel panel-default" style="background-color: #FAFAFA">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>유틸리티 툴</h2>
                                <span style="font-size:16px;">
                                    편리한 온라인 도구가 필요하십니까? 이런 도구들은 어떠세요?
                                </span>
                            </div>
                        </div>
                        <br><br><br>
                        <div class="row">
                            <div class="col-lg-6 col-md-12">
                                <a href="/crypto/encryptWord">
                                    <div class="panel panel-danger">
                                        <div class="panel-heading">
                                            <p class="text-center" style="font-size:20px;">암/복호화</p>
                                        </div>
                                        <div class="panel-footer" style="background-color: white">
                                            <span class="pull-left">이동</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <a href="/viewer/ssh_terminal">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">
                                            <p class="text-center" style="font-size:20px;">SSH 터미널</p>
                                        </div>
                                        <div class="panel-footer" style="background-color: white">
                                            <span class="pull-left">이동</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


<%@include file="assets/IncFooter.jsp" %>
<script>
    $(document).ready(function () {
        var recentUsed = '${sessionScope.users.lastUsedUrl}';
        if (recentUsed === 'null')
            recentUsed = "/editor/image_editor";

        $("#recent_href").prop("href", recentUsed);
    });
</script>
</body>

</html>
