/*=====변수 선언=====*/
//동영상 태그
var vMake = document.getElementById("vMake");

/*=====함수 선언=====*/
//종료 버튼
function pauseVideo() {
    if (!(vMake.paused)) {
        vMake.pause();
    }
}

//시간 설정 드롭 다운
function myFunction() {
    document.getElementById("dropDownForSetting_myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.dropDownForSettingBtn')) {
        var dropdowns = document.getElementsByClassName("dropDownForSetting_content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};

function videoNoneDisplay(){
    if((document.getElementById("videoTimeSetting").style.display === "none") ||(document.getElementById("videoRangeSetting").style.display === "none") ||(document.getElementById("videoTimeSetting").style.display === "inline-block") || (document.getElementById("videoRangeSetting").style.display === "inline-block")){
        document.getElementById("videoTimeSetting").style.display = "none";
        document.getElementById("videoRangeSetting").style.display = "none";
    }
}
function videoTimeDisplay(){
    if((document.getElementById("videoTimeSetting").style.display === "none") ||(document.getElementById("videoRangeSetting").style.display === "none") ||(document.getElementById("videoTimeSetting").style.display === "inline-block") || (document.getElementById("videoRangeSetting").style.display === "inline-block")){
        document.getElementById("videoTimeSetting").style.display = "inline-block";
        document.getElementById("videoRangeSetting").style.display = "none";
    }
}
function videoRangeDisplay(){
    if((document.getElementById("videoTimeSetting").style.display === "none") ||(document.getElementById("videoRangeSetting").style.display === "none") ||(document.getElementById("videoTimeSetting").style.display === "inline-block") || (document.getElementById("videoRangeSetting").style.display === "inline-block")){
        document.getElementById("videoTimeSetting").style.display = "none";
        document.getElementById("videoRangeSetting").style.display = "inline-block";
    }
}