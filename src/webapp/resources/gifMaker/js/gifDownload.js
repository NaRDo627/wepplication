function downloadFile() {
    var x = new XMLHttpRequest();
    x.open("GET", image.src, true);
    x.responseType = 'blob';
    x.onload=function(e) {
        download(x.response, "test.gif", "image/gif" );
    }
    x.send();
}


