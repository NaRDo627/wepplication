function downloadFile() {
    var date = new Date();
    var currentDate = date.getFullYear() + "." + ( date.getMonth() + 1) + "." + date.getDate() + " - ";
    var currentTime = date.getHours() + "시" + date.getMinutes() + "분" + date.getSeconds() + "초";
    var fileName = "TEAM_시차_" + currentDate + currentTime + ".gif";
    var x = new XMLHttpRequest();
    x.open("GET", image.src, true);
    x.responseType = 'blob';
    x.onload=function(e) {
        download(x.response, fileName, "image/gif" );
    }
    x.send();
}


