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
                                                <button type="button" onclick="output_Encrypt()" class="btn btn-primary">암호화<span class="visible-lg-inline"></span> <i class="fa fa-lock"></i></button>
                                                <button type="button" onclick="output_Decrypt()" class="btn btn-danger">복호화<span class="visible-lg-inline"></span> <i class="fa fa-unlock"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body" style="height:350px;">
                            <div class="row">
                                <div class="col-xs-5">
                                    <label for="BOARD_CONTENT_ENC">암호화할 내용</label><br>
                                    <textarea id="BOARD_CONTENT_ENC" name="BOARD_CONTENT" cols="35" rows="5" style="width: 100%;"></textarea>
                                </div>
                                <div class="col-xs-2">
                                    <div class="centered-outer">
                                        <div class="centered-inner">
                                            <div class="centered">
                                                <br><br>
                                                <i class="fa fa-arrow-right fa-5x" style="font-size:36px"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-5">
                                    <label for="ENCRYPT_RESULT">암호문 결과</label><br>
                                    <textarea id="ENCRYPT_RESULT" name="BOARD_CONTENT" cols="35" rows="5" style="width: 100%;"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-5">
                                    <label for="BOARD_CONTENT_DEC">복호화할 내용</label><br>
                                    <textarea id="BOARD_CONTENT_DEC" name="BOARD_CONTENT" cols="35" rows="5" style="width: 100%;"></textarea>
                                </div>
                                <div class="col-xs-2">
                                    <div class="centered-outer">
                                        <div class="centered-inner">
                                            <div class="centered">
                                                <br><br>
                                                <i class="fa fa-arrow-right fa-5x" style="font-size:36px"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-5">
                                    <label for="DECRYPT_RESULT">복호문 결과</label><br>
                                    <textarea id="DECRYPT_RESULT" name="BOARD_CONTENT" cols="35" rows="5" style="width: 100%;"></textarea>
                                </div>
                            </div>


                            <%--
                            <table>
                                <tr>
                                    <td class="td_left">
                                        <label>암호화할 내용</label>
                                    </td>

                                    <td>

                                    </td>

                                    <td>
                                    </td>

                                    <td>
                                    <td class="td_left">
                                        <label>암호문 결과</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea id="BOARD_CONTENT_ENC" name="BOARD_CONTENT" cols="35" rows="5"></textarea>
                                    </td>
                                    <td>
                                        <i class='fas fa-arrow-right' style='font-size:48px;color:white'></i>
                                    </td>
                                    <td>

                                        <i class="fa fa-arrow-right" style="font-size:36px"></i>

                                    </td>
                                    <td>
                                        <i class='fas fa-arrow-right' style='font-size:48px;color:white'></i>
                                    </td>
                                    <td>
                                        <textarea id="ENCRYPT_RESULT" name="BOARD_CONTENT" cols="35" rows="5"></textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="td_left">
                                       <label>복호화할 내용</label>
                                    </td>

                                    <td>
                                    </td>

                                    <td>
                                    </td>

                                    <td>
                                    </td>
                                    <td class="td_left">
                                        <label>복호문 결과</label>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <textarea id="BOARD_CONTENT_DEC" name="BOARD_CONTENT" cols="35" rows="5"></textarea>
                                    </td>
                                    <td>
                                        <i class='fas fa-arrow-right' style='font-size:48px;color:white'></i>
                                    </td>
                                    <td>
                                        <i class="fa fa-arrow-right" style="font-size:36px"></i>
                                    </td>
                                    <td>
                                        <i class='fas fa-arrow-right' style='font-size:48px;color:white'></i>
                                    </td>
                                    <td>
                                        <textarea id="DECRYPT_RESULT" name="BOARD_CONTENT" cols="35" rows="5"></textarea>
                                    </td>
                                </tr>
                            </table>
                            --%>

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

