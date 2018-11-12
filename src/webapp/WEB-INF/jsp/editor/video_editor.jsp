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
                        <h1 class="page-header">동영상 편집</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-10 col-xs-9">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <br>
                            </div>
                            <div class="panel-body" style="height:500px;">
                                <div class="centered-outer">
                                    <div class="centered-inner">
                                        <div class="centered">
                                            <i class="fa fa-film fa-5x"></i>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <%--<p class="text-center"></p>--%>
                                <br>
                            </div>
                            <div class="panel-footer">
                                <br>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-xs-3">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <p class="text-center"><strong>메뉴</strong></p>
                                    </div>
                                    <div class="panel-body" style="height:500px;">
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-lg-2 col-xs-3">
                                <div class="panel panel-default">
                                    <div class="panel-body" style="height:150px;">
                                        <strong><i class="fa fa-play-circle-o fa-3x"></i></strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-10 col-xs-9">
                                <div class="panel panel-default">
                                    <div class="row">
                                        <div class="panel-body" style="height:75px;">
                                            <div class="col-xs-12">
                                                <strong><i class="fa fa-video-camera fa-3x"></i></strong>
                                            </div>
                                        </div>
                                        <div class="panel-body" style="height:75px;">
                                            <div class="col-xs-12">
                                                <strong><i class="fa fa-music fa-3x"></i></strong>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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

</html>
