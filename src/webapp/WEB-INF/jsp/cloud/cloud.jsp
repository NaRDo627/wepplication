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

    <%@include file="../assets/IncAsset.jsp" %>
</head>

<body>
<div id="wrapper">
    <%@include file="../assets/IncHeader.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">클라우드</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row" style="height:60px;">
                                <div class="col-md-4 col-xs-12">
                                    <div class="input-group custom-search-form" style="height:100%;">
                                        <input type="text" class="form-control" placeholder="Search...">
                                        <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="height:50px;">
                                <div class="col-xs-12">
                                    <div class="form-group">
                                        <div class="centered-outer">
                                            <div class="centered-inner" style="text-align:left;">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox">
                                                    </label>
                                                    <button type="button" class="btn btn-primary"><span class="visible-lg-inline">업로드 </span><i class="fa fa-upload"></i></button>
                                                    <button type="button" class="btn btn-primary"><span class="visible-lg-inline">다운로드 </span><i class="fa fa-download"></i></button>
                                                    <button type="button" class="btn btn-danger"><span class="visible-lg-inline">삭제 </span><i class="fa fa-trash"></i></button>
                                                    <button type="button" class="btn btn-default"><span class="visible-lg-inline">공유 </span><i class="fa fa-share-alt"></i></button>
                                                    <button type="button" class="btn btn-default"><span class="visible-lg-inline">보내기 </span><i class="fa fa-mail-forward"></i></button>
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

<%@include file="../assets/IncFooter.jsp" %>
</body>

</html>
