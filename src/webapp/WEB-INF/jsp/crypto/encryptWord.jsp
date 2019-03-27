<%--
  Created by IntelliJ IDEA.
  User: localmaster
  Date: 2019-02-19
  Time: 오후 10:19
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
<script src="../../../resources/crypto/words/js/security.js"></script>
<script src="../../../resources/crypto/words/js/script.js"></script>

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
                                                <button type="button" onclick="input_Encrypt()" class="btn btn-default">암호문 입력<span class="visible-lg-inline"></span> <i class="fa fa-lock"></i></button>
                                                <button type="button" onclick="output_Encrypt()" class="btn btn-default">암호문 출력<span class="visible-lg-inline"></span> <i class="fa fa-lock"></i></button>
                                                <button type="button" onclick="output_Decrypt()" class="btn btn-default">복호문 출력<span class="visible-lg-inline"></span> <i class="fa fa-unlock"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body" style="height:350px;">
                            <table>
                                <tr>
                                    <td class="td_left">
                                        <label>내용</label>
                                    </td>
                                    <td>
                                        <textarea id="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15"></textarea>
                                    </td>
                                    <td class="td_left">
                                        <label>암호문 결과</label>
                                    </td>
                                    <td>
                                        <textarea id="ENCRYPT_RESULT" name="BOARD_CONTENT" cols="40" rows="15"></textarea>
                                    </td>
                                    <td class="td_left">
                                        <label>복호문 결과</label>
                                    </td>
                                    <td>
                                        <textarea id="DECRYPT_RESULT" name="BOARD_CONTENT" cols="40" rows="15"></textarea>
                                    </td>
                                </tr>
                            </table>
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

