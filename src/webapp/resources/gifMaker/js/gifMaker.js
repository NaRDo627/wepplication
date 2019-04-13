var workerPath = '../../../resources/gifMaker/js/techslidesGifMaker/w.js';
var worker = new Worker(workerPath);
var URL = window.URL || window.webkitURL;
if (!URL) {
    document.getElementById("output").innerHTML = 'Your browser is not <a href="http://caniuse.com/bloburls">supported</a>!';
} else {
    /*=====변수 선언=====*/
    /*-----GIF 및 비디오 설정-----*/
    //태그
    var speed = document.getElementById("speed");
    var speedrate = document.getElementById("speedrate");
    var vMake = document.getElementById("vMake");
    //GIF 재생 속도
    var flag = false;
    var delay = 100; //default speed
    //GIF 크기 비율
    var ratio = null;
    var cw,ch;

    /*-----GIF 생성-----*/
    //FRAME 생성
    var start = document.getElementById("start-button");
    var end = document.getElementById("end-button");
    //GIF 그리기
    var image = document.getElementById('image');
    var canvas = document.getElementById('c');
    var context = canvas.getContext('2d');

    /*-----진행 상황-----*/
    var startTime, endTime, runTime;

    /*=====함수 선언=====*/
    /*-----업로드-----*/
    // 드래그 앤 드롭
    window.ondragover = function(e) {e.preventDefault()}
    window.ondrop = function(e) {
        e.preventDefault();
        document.getElementById("output").innerHTML = "영상을 업로드하고 있어요";
        var length = e.dataTransfer.items.length;
        if(length > 1){
            document.getElementById("output").innerHTML = "1개의 영상만 올려주세요!";
        } else {
            upload(e.dataTransfer.files[0]);
        }
    }
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
    function input() {
        //짤 비율 설정
        var prevRatio = document.getElementById("sizeVideo").value;
        ratio = prevRatio / 100;

        vMake.addEventListener('play', function(){
            cw = vMake.videoWidth * ratio;
            ch = vMake.videoHeight * ratio;
            canvas.width = cw;
            canvas.height = ch;
            draw(vMake,context,cw,ch);
        },false);
    }

    /*-----FRAME 지정-----*/
    //FRAME 시작
    start.addEventListener('click', function(){
        //startTime = new Date().getTime();
        flag = true;
        worker.postMessage({delay:delay,w:cw,h:ch});
        document.getElementById("output").innerHTML = "영상을 캡쳐하고 있어요.";
        startTime = new Date().getTime();
    },false);
    //FRAME 끝
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

    /*-----진행 상황-----
    //그냥 바로 진행 바가 차버림
    function move() {
        runTime = startTime - endTime;
        var elem = document.getElementById("myBar");
        var width = 1;
        var id = setInterval(frame, runTime);
        function frame() {
            if (width >= 100) {
                clearInterval(id);
            } else {
                width++;
                elem.style.width = width + '%';
            }
        }
    }*/

    /*-----GIF 출력-----*/
    worker.addEventListener('message', function(e) {
        document.getElementById("output").innerHTML = "GIF 이미지를 만들었어요. 잠시만 기다려주세요!";
        image.src = e.data;

        endTime = new Date().getTime();

        // 한참 진행이 안되다가 마지막에 완성됐을 때, 한 번에 참,
        runTime = startTime - endTime;
        var elem = document.getElementById("myBar");
        var width = 1;
        var id = setInterval(frame, runTime);
        function frame() {
            if (width >= 100) {
                clearInterval(id);
            } else {
                width++;
                elem.style.width = width + '%';
                elem.innerHTML = width * 1;
            }
        }

        setTimeout(function() {
            window.location.href="#gifImage"
        }, 3000)

    }, false);
}