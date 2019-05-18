var workerPath = '../../../resources/gifMaker/js/techslidesGifMaker/w.js';
var worker = new Worker(workerPath);
var URL = window.URL || window.webkitURL;
if (!URL) {
    document.getElementById("output").innerHTML = 'Your browser is not <a href="http://caniuse.com/bloburls">supported</a>!';
} else {
    /*=====변수 선언=====*/
    /*-----GIF 및 비디오 설정-----*/
    //태그
    //GIF 전환 속도 태그
    var speed = document.getElementById("speed");
    var speedrate = document.getElementById("speedrate");
    //동영상 태그
    var vMake = document.getElementById("vMake");
    //GIF 구간 설정
    var setSectionTime1 = document.getElementById("setSectionTime1");
    var setSectionTime2 = document.getElementById("setSectionTime2");
    var finishTimeVideo = document.getElementById("finishTimeVideo");
    //GIF 재생 속도
    var flag = false;
    var delay = 100; //default speed
    //GIF 크기 비율
    var ratio = 0.5; //기본 크기가 동영상 크기의 50%
    var cw,ch;

    /*-----GIF 생성-----*/
    //FRAME 생성
    var start = document.getElementById("start-button");
    var end = document.getElementById("end-button");
    //GIF 그리기
    var image = document.getElementById('image');
    var canvas = document.getElementById('c');
    var context = canvas.getContext('2d');

    /*=====함수 선언=====*/
    /*-----업로드-----*/
    // 드래그 앤 드롭
    window.ondragover = function(e) {e.preventDefault()};
    window.ondrop = function(e) {
        e.preventDefault();
        document.getElementById("output").innerHTML = "영상을 업로드하고 있어요";
        var length = e.dataTransfer.items.length;
        if(length > 1){
            document.getElementById("output").innerHTML = "1개의 영상만 올려주세요!";
        } else {
            upload(e.dataTransfer.files[0]);
        }
    };
    //업로드
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
            vMake.src = url;

            //동영상 업로드시, 총 동영상 길이를 보여줌
            vMake.addEventListener("durationchange", function() {
                var duration = vMake.duration;
                var durationHour = Math.floor(duration / 3600);
                var durationMin = Math.floor((duration - (durationHour * 3600)) / 60);
                var durationSec = Math.floor((duration - (durationHour * 3600)) - (durationMin * 60));
                if (durationHour < 10) {
                    durationHour = "0" + durationHour;
                }
                if (durationMin < 10) {
                    durationMin = "0" + durationMin;
                }
                if (durationSec < 10) {
                    durationSec = "0" + durationSec;
                }
                var durationTotal = durationHour + ":" + durationMin + ":" + durationSec;
                finishTimeVideo.innerHTML = durationTotal;
            });

            /*-----동영상 구간 설정-----*/
            /*-----setSectionTime1, 2에 값을 넣고 클릭을 하면 아래의 이벤트가 발생-----*/
            //1. 동영상 원하는 시작 지점과 끝 지점을 입력
            setSectionTime1.addEventListener("click", function() {
                var firstHourTime1 = document.getElementById("firstHourVideo1").value;
                var firstMinTime1 = document.getElementById("firstMinVideo1").value;
                var firstSecTime1 = ((document.getElementById("firstSecVideo1").value) * 0.1);
                var lastHourTime1 = document.getElementById("lastHourVideo1").value;
                var lastMinTime1 = document.getElementById("lastMinVideo1").value;
                var lastSecTime1 = ((document.getElementById("lastSecVideo1").value) * 0.1);
                var firstTimeVideoDuration = (firstHourTime1 * 3600) + (firstMinTime1 * 60) + (firstSecTime1*10);
                var lastTimeVideoDuration = (lastHourTime1 * 3600) + (lastMinTime1 * 60) + (lastSecTime1*10);
                var firstTimeVideo1 = firstTimeVideoDuration;
                var lastTimeVideo1 = lastTimeVideoDuration;
                if((firstTimeVideo1 < vMake.duration) && (lastTimeVideo1 < vMake.duration)) {
                    if(firstTimeVideo1 < lastTimeVideo1) {
                        vMake.src = url + "#t=" + firstTimeVideo1 + "," + lastTimeVideo1;
                    }
                    else {
                        alert("시작 시간은 끝나는 시간보다 빨라야 해요!");
                    }
                }
                else {
                    alert("시작 시간과 끝나는 시간은 총 재생 시간보다 빨라야 해요!");
                }
            });
            //2. 동영상 원하는 시작 지점과 GIF로 만들기 원하는 시간을 입력
            setSectionTime2.addEventListener("click", function() {
                var firstHourTime2 = document.getElementById("firstHourVideo2").value;
                var firstMinTime2 = document.getElementById("firstMinVideo2").value;
                var firstSecTime2 = ((document.getElementById("firstSecVideo2").value) * 0.1);
                var lastHourTime2 = document.getElementById("lastHourVideo2").value;
                var lastMinTime2 = document.getElementById("lastMinVideo2").value;
                var lastSecTime2 = ((document.getElementById("lastSecVideo2").value) * 0.1);
                var firstTimeVideoDuration2 = (firstHourTime2 * 3600) + (firstMinTime2 * 60) + (firstSecTime2*10);
                var lastTimeVideoDuration2 = (lastHourTime2 * 3600) + (lastMinTime2 * 60) + (lastSecTime2*10);
                var firstTimeVideo2 = firstTimeVideoDuration2;
                var lastTimeVideo2 = firstTimeVideoDuration2 + lastTimeVideoDuration2;
                if((firstTimeVideo2 < vMake.duration) && (lastTimeVideo2 < vMake.duration)) {
                    if(firstTimeVideo2 < lastTimeVideo2) {
                        vMake.src = url + "#t=" + firstTimeVideo2 + "," + lastTimeVideo2;
                    }
                    else {
                        alert("시작 시간은 끝나는 시간보다 빨라야 해요!");
                    }
                }
                else {
                    alert("시작 시간과 끝나는 시간은 총 재생 시간보다 빨라야 해요!");
                }
            });
        } else {
            document.getElementById("output").innerHTML = "영상 파일을 올려주세요.";
        }
    }

    /*-----짤 재생 설정-----*/
    //GIF 재생 속도
    speed.addEventListener('change', function(){
        var speedChange = this.value;
        delay = speedChange;
        speedrate.innerHTML = speedChange;
    }, false);
    //GIF 크기 설정
    function inputRatio() {
        //짤 비율 설정
        var prevRatio = document.getElementById("sizeVideo").value;
        ratio = prevRatio / 100;        
    }
    //동영상 재생시 이벤트 발생
    //변환되는 GIF 크기 설정
    vMake.addEventListener('play', function(){
        cw = vMake.videoWidth * ratio;
        ch = vMake.videoHeight * ratio;
        canvas.width = cw;
        canvas.height = ch;
        draw(vMake,context,cw,ch);
    },false);
    
    /*-----FRAME 지정-----*/
    /*
    //FRAME 시작*/
    start.addEventListener('click', function(){
        //순서: 동영상 시작 -> 프레임 찍음
        //프레임을 먼저 찍고 동영상을 시작하면 에러가 발생
        //프레임을 찍기 전에 동영상이 실행이 되어야 함
        if (vMake.paused) {
            vMake.play();
        }

        //동영상이 깔끔하게 실행이 되면, 딜레이없이 실행이 되면, 프레임을 찍기 시작함
        //addeventlistnener type: playing = 일시정지나 데이터 부족으로 딜레이된 후로부터 재생할 준비가 되었을 때.
        //이 기능으로 인한 버그: 처음에 frame 버튼을 눌러서 기능 작동을 시키고 새로 영상을 변환하길 원할 때,
        //단순히 재생, 멈춤을 해도 작동하더라동영상 플레이어를 눌러서 재생, 정지를 시켜도 짤이 만들어진다
        vMake.addEventListener("playing", function() {
            flag = true;
            worker.postMessage({delay:delay,w:cw,h:ch});
            document.getElementById("output").innerHTML = "영상을 캡쳐하고 있어요.";
        });

        //FRAME 끝_1: 구간 설정시 영상 재생이 끝나면 자동으로 프레임 종료와 동시에 화면 이동
        vMake.addEventListener('pause', function(){
            flag = false;
            worker.postMessage({});
            document.getElementById("output").innerHTML = "GIF 이미지를 만들고 있어요!";
            setTimeout(function() {
                window.location.href="#gif"
            }, 500);
        },false);
    },false);
    //FRAME 끝_2: end-button 클릭시 영상 재생이 끝나면서 동시에 프레임 종료와 자동으로 화면 이동
    end.addEventListener('click', function(){
        flag = false;
        worker.postMessage({});
        document.getElementById("output").innerHTML = "GIF 이미지를 만들고 있어요!";
    },false);
    //FRAME 추가??
    function draw(vMake,c,w,h) {
        //if(v.paused || v.ended)	return false;
        c.drawImage(vMake,0,0,w,h);
        if(flag == true){
            var imdata = c.getImageData(0,0,w,h);
            worker.postMessage({frame: imdata});
        }
        setTimeout(draw,delay,vMake,c,w,h);
    }

    /*-----GIF 출력-----*/
    worker.addEventListener('message', function(e) {
        document.getElementById("output").innerHTML = "GIF 이미지를 만들었어요. 잠시만 기다려주세요!";
        image.src = e.data;

        setTimeout(function() {
            window.location.href="#gifImage"
        }, 3000)
    }, false);
}