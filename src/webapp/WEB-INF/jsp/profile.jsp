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
                            <li><a href="#membership-tab" data-toggle="tab">멤버십</a>
                            </li>
                            <li><a href="#settings-tab" data-toggle="tab">설정</a>
                            </li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane fade" id="home-tab">
                                <div class="table-responsive">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">아이디</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">testID</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">이름</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">testName</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">별명</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">testNickName</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">이메일</p>
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
                                                </tr>
                                                </tbody>
                                            </table>
                                            <!-- ./table responsive -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="password-tab">
                                <div class="table-responsive">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <table class="table table-bordered">
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
                            <div class="tab-pane fade" id="membership-tab">
                                <div class="table-responsive">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">멤버십 이름</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">무료</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">지속기간</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">평생</p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>
                                                        <p class="form-control-static">남은기간</p>
                                                    </th>
                                                    <td>
                                                        <p class="form-control-static">무제한</p>
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
                            </div>
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
    });
</script>
</body>

</html>
