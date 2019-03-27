<%--
  Created by IntelliJ IDEA.
  User: localmaster
  Date: 2019-02-19
  Time: 오후 10:20
  To change this template use File | Settings | File Templates.
--%>
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
<script src="../../../resources/crypto/origin/js/aes.js"></script>
<script src="../../../resources/crypto/origin/js/jquery-3.3.1.js"></script>
<script src="../../../resources/crypto/origin/js/script.js"></script>
<link href="../../../resources/crypto/origin/css/style.css" rel="stylesheet" />


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
                        <div class="panel-body" style="height:350px;">
                            <a class="back"></a>

                            <div id="stage">

                                <div id="step1">
                                    <div class="content">
                                        <h1>파일 암복호화</h1>
                                        <a class="button encrypt green">파일 암호화</a>
                                        <a class="button decrypt magenta">파일 복호화</a>
                                    </div>
                                </div>

                                <div id="step2">

                                    <div class="content if-encrypt">
                                        <h1>암호화할 파일을 선택하세요</h1>
                                        <a class="button browse blue">파일 탐색</a>

                                        <input type="file" id="encrypt-input" />
                                    </div>

                                    <div class="content if-decrypt">
                                        <h1>복호화할 파일을 선택하세요</h1>
                                        <h2>오직 암호화하신 파일만 선택할 수 있습니다</h2>
                                        <a class="button browse blue">파일 탐색</a>

                                        <input type="file" id="decrypt-input" />
                                    </div>

                                </div>

                                <div id="step3">

                                    <div class="content if-encrypt">
                                        <h1>비밀번호를 입력해주세요</h1>
                                        <h2>비밀번호를 입력해주셔야 합니다. 기억해두셨다가 복호화하실 때 입력해주세요.</h2>

                                        <input type="password" />
                                        <a class="button process red">파일 잠금!</a>
                                    </div>

                                    <div class="content if-decrypt">
                                        <h1>비밀번호를 입력해주세요</h1>
                                        <h2>파일을 암호화하실 때 입력하셨던 비밀번호를 입력해주세요. 비밀번호를 입력하셔야 복호화가 가능합니다.</h2>

                                        <input type="password" />
                                        <a class="button process red">잠금 해재!</a>
                                    </div>

                                </div>

                                <div id="step4">

                                    <div class="content">
                                        <h1>당신의 파일이 준비되었어요!</h1>
                                        <a class="button download green">다운로드</a>
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

