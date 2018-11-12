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

<body style="width: 100%; height: 100%;">
    <div id="wrapper">
        <%@include file="../assets/IncHeader.jsp"%>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">이미지 편집</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-8 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <br>
                            </div>
                            <div class="panel-body" style="height:400px;">
                                <div class="centered-outer">
                                    <div class="centered-inner">
                                        <div class="centered">
                                            <i class="fa fa-image fa-5x"></i>
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
                    <div class="col-lg-2 col-xs-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-6 col-xs-1">
                                               <p class="text-center"><i class="fa fa-cut fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-hand-o-up fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-pencil fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-paint-brush fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-eraser fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-eraser fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-bookmark fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-spoon fa-2x"></i></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-xs-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>네비게이터</strong><br>
                                        <strong>X : </strong><br>
                                        <strong>Y : </strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>레이어</strong><br>
                                        <strong>배경</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>히스토리</strong><br>
                                        <strong>새 이미지</strong>
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
