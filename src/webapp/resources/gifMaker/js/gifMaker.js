var workerPath = '../../../resources/gifMaker/js/techslidesGifMaker/w.js';
var worker = new Worker(workerPath);
var URL = window.URL || window.webkitURL;
if (!URL) {
    document.getElementById("output").innerHTML = 'Your browser is not <a href="http://caniuse.com/bloburls">supported</a>!';
} else {
    worker.addEventListener('message', function(e) {
        document.getElementById("output").innerHTML = "완성입니다. 짤 버튼을 눌러 확인하세요!";
        image.src = e.data;
    }, false);

    var start = document.getElementById("start-button");
    var end = document.getElementById("end-button");
    var image = document.getElementById('image');
    var speed = document.getElementById("speed");
    var speedrate = document.getElementById("speedrate");

    var flag = false;
    var delay = 100; //default speed

    //짤 재생 설정
    speed.addEventListener('change', function(){
        var speedChange = this.value;
        delay = speedChange;
        speedrate.innerHTML = speedChange;
    }, false);

    var vMake = document.getElementById("vMake");
    var canvas = document.getElementById('c');
    var context = canvas.getContext('2d');
    var cw,ch;

    var ratio = null;
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

    function draw(vMake,c,w,h) {
        //if(v.paused || v.ended)	return false;
        c.drawImage(vMake,0,0,w,h);
        if(flag == true){
            var imdata = c.getImageData(0,0,w,h);
            worker.postMessage({frame: imdata});
        }
        setTimeout(draw,delay,vMake,c,w,h);
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
            vMake.src = url;
        } else {
            document.getElementById("output").innerHTML = "This file does not seem to be a video.";
        }
    }

    function downloadFile() {
        var link = document.getElementById("link");
        link.download = name;
        link.href = file;
        link.click();
    }
}