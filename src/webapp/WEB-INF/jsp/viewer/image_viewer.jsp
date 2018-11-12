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
                        <h1 class="page-header">이미지 뷰어</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-10 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6" style="border-right: 1px solid lightgray; text-align: left;">
                                        <div>이름</div>
                                    </div>
                                    <div class="col-xs-3" style="border-right: 1px solid lightgray; text-align: center;">
                                        <div>최종 수정 날짜</div>
                                    </div>
                                    <div class="col-xs-3" style="text-align: center;">
                                        <div>크기</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6" style="border-right: 1px solid lightgray; text-align: left;">
                                        <p>파일 1</p>
                                    </div>
                                    <div class="col-xs-3" style="border-right: 1px solid lightgray; text-align: center;">
                                        <p>2018-11-03</p>
                                    </div>
                                    <div class="col-xs-3" style="text-align: center;">
                                        <p>32KB</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-6" style="border-right: 1px solid lightgray; text-align: left;">
                                        <p>파일 2</p>
                                    </div>
                                    <div class="col-xs-3" style="border-right: 1px solid lightgray; text-align: center;">
                                        <p>2018-11-03</p>
                                    </div>
                                    <div class="col-xs-3" style="text-align: center;">
                                        <p>32KB</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-6" style="border-right: 1px solid lightgray; text-align: left;">
                                        <p>파일 3</p>
                                    </div>
                                    <div class="col-xs-3" style="border-right: 1px solid lightgray; text-align: center;">
                                        <p>2018-11-03</p>
                                    </div>
                                    <div class="col-xs-3" style="text-align: center;">
                                        <p>32KB</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 visible-lg">
                        <div class="panel panel-info">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p><strong>세부정보</strong></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <i class="fa fa-folder fa-5x"></i>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>유형</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>이름</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>크기</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>최종</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <p>수정날짜</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@include file="../assets/IncFooter.jsp"%>
</body>

</html>
