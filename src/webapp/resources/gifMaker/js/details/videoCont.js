var myVideo = document.getElementById("vMake");

function playPause() {
    if (myVideo.paused)
        myVideo.play();
    else
        myVideo.pause();
}