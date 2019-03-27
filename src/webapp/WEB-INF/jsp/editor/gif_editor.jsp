<%--
  Created by IntelliJ IDEA.
  User: localmaster
  Date: 2019-03-26
  Time: 오전 9:28
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
                <script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script>
                <script type="text/javascript" src="../../../resources/gifMaker/js/gaq.js"></script>

                <h1>GIF 이미지 만드는 순서</h1>

                <p>동영상을 가져오거나 선택합니다.
                    <br>
                    이미지 변환 시간은 선택합니다.
                    <br>
                    "start GIF frames"를 눌러 이미지 캡쳐를 시작합니다
                    <br>
                    "end GIF frames"를 눌러 이미지 캡쳐를 종료합니다
                    <br>
                    기다리시면 GIF 이미지 변환이 완성됩니다
                </p>
                <br>

                <div id="drop">
                    동영상을 가져오세요!<button onclick="document.querySelector('input').click()">아니면 선택하세요!</button>
                    <input style="visibility: collapse; width: 0px;" type="file" onchange="upload(this.files[0])">
                    <button id="start-button">start GIF frames</button>
                    <button id="end-button">End GIF frames</button>

                    <label>Speed</label>
                    <input type="range" id="speed" min="30" max="1000" step="1" value="100">
                    <span id="speedrate">100</span>
                </div>

                <div id="output"></div>

                <h3>Video:</h3>
                <canvas id="c" style="visibility:hidden;"></canvas>

                <div style="text-align:center">
                    <button onclick="playPause()">Play/Pause</button>
                    <br><br>
                    <video id="v" controls autoplay></video>
                </div>
                <script>
                    var myVideo = document.getElementById("v");

                    function playPause() {
                        if (myVideo.paused)
                            myVideo.play();
                        else
                            myVideo.pause();
                    }
                </script>

                <h3>GIF 이미지:</h3>
                <a id="link" href="" download=""><img id="image" src=""></a>

                <script type="text/javascript" src="../../../resources/gifMaker/js/jquery-3.3.1.js"></script>
                <script type="text/javascript">

                    var worker = new Worker('../../../resources/gifMaker/js/w.js');
                    var URL = window.URL || window.webkitURL;
                    if (!URL) {
                        document.getElementById("output").innerHTML = 'Your browser is not <a href="http://caniuse.com/bloburls">supported</a>!';
                    } else {

                        worker.addEventListener('message', function(e) {
                            document.getElementById("output").innerHTML = "완성입니다. 아래를 보세요!";
                            image.src = e.data;
                        }, false);

                        var start = document.getElementById("start-button");
                        var end = document.getElementById("end-button");
                        var image = document.getElementById('image');
                        var speed = document.getElementById("speed");
                        var speedrate = document.getElementById("speedrate");

                        var flag = false;
                        var delay = 100; //default speed

                        //control play speed
                        speed.addEventListener('change', function(){
                            var s = this.value;
                            delay = s;
                            speedrate.innerHTML = s;
                        }, false);

                        var v = document.getElementById("v");
                        var canvas = document.getElementById('c');
                        var context = canvas.getContext('2d');
                        var cw,ch;

                        v.addEventListener('play', function(){
                            cw = v.clientWidth;
                            ch = v.clientHeight;
                            canvas.width = cw;
                            canvas.height = ch;
                            draw(v,context,cw,ch);
                        },false);

                        function draw(v,c,w,h) {
                            //if(v.paused || v.ended)	return false;
                            c.drawImage(v,0,0,w,h);
                            if(flag == true){
                                var imdata = c.getImageData(0,0,w,h);
                                worker.postMessage({frame: imdata});
                            }
                            setTimeout(draw,delay,v,c,w,h);
                        }

                        start.addEventListener('click', function(){
                            flag = true;
                            worker.postMessage({delay:delay,w:cw,h:ch});
                            document.getElementById("output").innerHTML = "Capturing video frames.";
                        },false);

                        end.addEventListener('click', function(){
                            flag = false;
                            worker.postMessage({});
                            document.getElementById("output").innerHTML = "Processing the GIF.";
                        },false);

                        /* Drag drop stuff */
                        window.ondragover = function(e) {e.preventDefault()}
                        window.ondrop = function(e) {
                            e.preventDefault();
                            document.getElementById("output").innerHTML = "Reading...";
                            var length = e.dataTransfer.items.length;
                            if(length > 1){
                                document.getElementById("output").innerHTML = "Please only drop 1 file.";
                            } else {
                                upload(e.dataTransfer.files[0]);
                            }
                        }

                        /* main upload function */
                        function upload(file) {

                            //check if its a video file
                            if(file.type.match(/video\/*/)){
                                /*
                                oFReader = new FileReader();
                                oFReader.onloadend = function() {

                                    document.getElementById("output").innerHTML = "";

                                    var blob = new Blob([this.result], {type: file.type});
                                    var url = URL.createObjectURL(blob);

                                    v.src = url;
                                };
                                //oFReader.readAsBinaryString(file);
                                oFReader.readAsArrayBuffer(file);
                                */

                                //why read the whole video into memory when you can stream!!
                                document.getElementById("output").innerHTML = "";
                                var url = URL.createObjectURL(file);
                                v.src = url;

                            } else {
                                document.getElementById("output").innerHTML = "This file does not seem to be a video.";
                            }
                        }
                    }
                </script>
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