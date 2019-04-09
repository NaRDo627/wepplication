<%--
  Created by IntelliJ IDEA.
  User: localmaster
  Date: 2019-04-03
  Time: 오후 8:05
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

    <link href="../../../resources/gifMaker/css/gifMakerStyle.css" rel="stylesheet" />
    <style>
        .sidebar_left .item{
            background-image: url(/resources/images/sprites.png);
        }
        .block.details .row {
            margin-left:0px;
        }
        .sidebar_right .block.layers {
            overflow-y: visible;
        }
        .main_wrapper{
            height: 125vh;
        }
        .attributes{
            width: 100%;
        }

    </style>
</head>

<body style="width: 100%; height: 100%;overflow:scroll;">
<div id="wrapper">
    <%@include file="../assets/IncHeader.jsp"%>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">GIF 이미지 변환</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div id="gif_header">
                    <h1>GIF 이미지 만드는 순서</h1>
                    <p>
                        동영상을 DRAG & DROP!
                    </p>
                    <p>
                        그리고 짤 재생 시간과 짤 크기를 정해주세요!
                    </p>
                    <p>
                        원하시는 시간만큼 재생시키고 end를 누르면 완성!
                    </p>
                </div>

                <div id="pages" class="container">
                    <section id="video" data-url="video" data-default-page="true">
                        <a href="#gif" class="btn btn-default" data-transition="slide">짤</a>

                        <div id="drop">
                            <div id="selectVideo">
                                동영상을 가져오세요!<button onclick="document.querySelector('input').click()">아니면 선택하세요!</button>
                            </div>

                            <input id="dropUpload" type="file" onchange="upload(this.files[0])">

                            <div id="output"></div>

                            <div id="controlVideo">

                                <div id="gifSpeed">
                                    <label>Speed</label>
                                    <input type="range" id="speed" min="30" max="1000" step="1" value="100">
                                    <span id="speedrate">100</span>
                                </div>

                                <div id="videoSize">
                                    <label>Size</label>
                                    <input type="text" id="sizeVideo" min="1" max="100" value="50" /><lable>%</lable>
                                    <button onclick="input()">비율 입력</button>
                                </div>

                                <div id="frameControl">
                                    <button class="buttonStyle1" id="start-button" style="vertical-align:middle"><span>start GIF frames</span></button>
                                    <br>
                                    <button class="buttonStyle1" id="end-button" style="vertical-align:middle"><span>End GIF frames</span></button>
                                    <br>
                                    <button class="buttonStyle1" onclick="playPause()" style="vertical-align:middle"><span>Play/Pause</span></button>
                                </div>
                            </div>
0
                            <video id="vMake" controls></video>
                        </div>
                    </section>

                    <section id="gif" data-url="gif" >
                        <a href="#video" class="btn btn-default" data-transition="slide">영상</a>
                        <div id="gifDiv">
                            <h3>GIF 이미지:</h3>
                            <a id="link" onclick="downloadFile()"><img id="image" src=""></a>
                        </div>
                        <canvas id="c"></canvas>
                    </section>
                </div>
                <script type="text/javascript" src="../../../resources/gifMaker/js/jquery-3.3.1.js"></script>

                <script type="text/javascript" src="../../../resources/gifMaker/js/details/transition/transition-min.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/details/underscore/underscore-min.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/details/backbone/backbone-min.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/details/multipage-router/multipage-min.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/details/multipage-router/multipage-starter-min.js"></script>

                <script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/techslidesGifMaker/gaq.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/gifMaker.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/details/videoCont.js"></script>
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
