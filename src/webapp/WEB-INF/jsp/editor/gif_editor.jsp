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

    <link href="../../../resources/gifMaker/css/font-awesome.min.css" rel="stylesheet" />
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
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading" id="gif_header">
                            <p>
                                <strong>1. 동영상을 DRAG & DROP!</strong>
                            </p>
                            <p>
                                <strong>2. GIF 속도, 크기 설정</strong>
                            </p>
                            <p>
                                <strong>3. 원하는 시점에서 캡쳐와 변환 버튼을 누르면 끝!</strong>
                            </p>
                        </div>

                        <div class="panel-body" id="pages" class="container">
                            <section id="video" data-url="video" data-default-page="true">
                                <div id="drop">
                                    <div id="selectVideo">
                                        <button class="btn btn-primary" id="uploadButton" onclick="document.querySelector('input').click()">업로드</button>
                                    </div>

                                    <input id="dropUpload" type="file" onchange="upload(this.files[0])">

                                    <video id="vMake" controls poster="../../../resources/gifMaker/icons/draganddrop.jpeg"></video>

                                    <div id="videoControl">
                                        <div id="controlVideo_1">
                                            <div id="gifSpeed">
                                                <label>GIF 변환 속도: </label><span id="speedrate">100</span>
                                                <br>
                                                <label>FAST</label>&nbsp;&nbsp;<input type="range" id="speed" min="30" max="1000" step="1" value="100">&nbsp;&nbsp;<label>SLOW</label>
                                            </div>
                                            <div id="videoSize">
                                                <label>GIF 크기</label><br>
                                                <label>기본 GIF 크기 비율: 동영상의 50%</label><br>
                                                <input type="text" id="sizeVideo" class ="tb5" min="1" max="100" value="50"><lable>%</lable>
                                                <button class="btn btn-primary" onclick="inputRatio();">비율 입력</button>
                                            </div>
                                        </div>
                                        <div id="whatdoyouwanttosee">
                                            <label>동영상 시간</label>
                                            <br>
                                            <div id="startTimeVideo" style="display: inline-block;">00:00:00</div><label>-</label><div id="finishTimeVideo" style="display: inline-block;">00:00:00</div>
                                            <br><br>
                                            <div id="videoTimeSetting" style="display: none;">
                                                <label>시작 시간</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>끝나는 시간</label>
                                                <br>
                                                <input type="text" id="firstHourVideo1" class ="tb5" value="00" style="width: 30px;"><label>시</label><input type="text" id="firstMinVideo1" class ="tb5" value="00" style="width: 30px;"><label>분</label><input type="text" id="firstSecVideo1" class ="tb5" value="00" style="width: 30px;"><label>초</label>
                                                <label>-</label>
                                                <input type="text" id="lastHourVideo1" class ="tb5" value="00" style="width: 30px;"><label>시</label><input type="text" id="lastMinVideo1" class ="tb5" value="00" style="width: 30px;"><label>분</label><input type="text" id="lastSecVideo1" class ="tb5" value="00" style="width: 30px;"><label>초</label>

                                                <button class="btn btn-primary" id="setSectionTime1" type="button">구간</button>
                                            </div>
                                            <div id="videoRangeSetting" style="display: none;">
                                                <label>시작 시간</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>추가 시간</label>
                                                <br>
                                                <input type="text" id="firstHourVideo2" class ="tb5" value="00" style="width: 30px;"><label>시</label><input type="text" id="firstMinVideo2" class ="tb5" value="00" style="width: 30px;"><label>분</label><input type="text" id="firstSecVideo2" class ="tb5" value="00" style="width: 30px;"><label>초</label>
                                                <label>-</label>
                                                <input type="text" id="lastHourVideo2" class ="tb5" value="00" style="width: 30px;"><label>시</label><input type="text" id="lastMinVideo2" class ="tb5" value="00" style="width: 30px;"><label>분</label><input type="text" id="lastSecVideo2" class ="tb5" value="00" style="width: 30px;"><label>초</label>

                                                <button class="btn btn-primary" id="setSectionTime2" type="button">범위</button>
                                            </div>
                                            <div class="dropDownForSetting">
                                                <button onclick="myFunction()" class="dropDownForSettingBtn">구간 설정<span class="caret"></span></button>
                                                <div id="dropDownForSetting_myDropdown" class="dropDownForSetting_content">
                                                    <a href="javascript:void(0);" onclick="videoNoneDisplay();">설정 안함</a>
                                                    <a href="javascript:void(0);" onclick="videoTimeDisplay();">시간 설정</a>
                                                    <a href="javascript:void(0);" onclick="videoRangeDisplay();">범위 설정</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="controlVideo_2">
                                        <div id="frameControl">
                                            <button class="buttonStyle1" id="start-button" style="vertical-align:middle"><span>캡쳐</span></button>
                                            <a href="#gif" data-transition="slide">
                                                <button class="buttonStyle1" id="end-button" onclick="pauseVideo();" style="vertical-align:middle"><span>GIF 변환</span></button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </section>

                            <section id="gif" data-url="gif" >
                                <a href="#video" class="btn btn-primary" data-transition="slide">영상</a>
                                <div class="gifDiv">
                                    <h3>GIF 이미지:</h3>
                                    <div id="output">영상을 변환해보세요</div>
                                </div>
                                <br>
                                <div class="bar"></div>
                                <canvas id="c"></canvas>
                            </section>

                            <section id="gifImage" data-url="gifImage">
                                <a href="#video" class="btn btn-primary" data-transition="slide">영상</a>
                                <div class="gifDiv">
                                    <h3>GIF 이미지:</h3>
                                    <a id="linkImage" onclick="downloadFile()"><img id="image" src=""></a>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
        <!--jquery-->
        <script type="text/javascript" src="../../../resources/gifMaker/js/jquery-3.3.1.js"></script>
        <!--section slide-->
        <script type="text/javascript" src="../../../resources/gifMaker/js/sectionSlide/transition/transition-min.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/sectionSlide/underscore/underscore-min.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/sectionSlide/backbone/backbone-min.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/sectionSlide/multipage-router/multipage-min.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/sectionSlide/multipage-router/multipage-starter-min.js"></script>
        <!--techslide gif maker-->
        <script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/techslidesGifMaker/gaq.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/gifMaker.js"></script>
        <!--gif file download-->
        <script type="text/javascript" src="../../../resources/gifMaker/js/gifDownload.js"></script>
        <script type="text/javascript" src="../../../resources/gifMaker/js/dowload/download.js"></script>
        <!--video play control-->
        <script type="text/javascript" src="../../../resources/gifMaker/js/videoCont.js"></script>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<%@include file="../assets/IncFooter.jsp"%>

</body>

</html>
