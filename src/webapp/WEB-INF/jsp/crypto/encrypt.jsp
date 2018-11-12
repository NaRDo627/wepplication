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
                        <h1 class="page-header">암호화 / 복호화</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="row" style="height:50px;">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <div class="centered-outer">
                                                <div class="centered-inner" style="text-align:left;">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox">
                                                    </label>
                                                    <button type="button" class="btn btn-primary"><span class="visible-lg-inline">클라우드에 </span>저장 <i class="fa fa-cloud"></i></button>
                                                    <button type="button" class="btn btn-default">암호<span class="visible-lg-inline">화</span> <i class="fa fa-lock"></i></button>
                                                    <button type="button" class="btn btn-default">복호<span class="visible-lg-inline">화</span> <i class="fa fa-unlock"></i></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body" style="height:350px;">

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
