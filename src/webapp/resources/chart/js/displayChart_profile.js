/*참고한 사이트Ʈ
1. https://devmg.tistory.com/49
2. https://github.com/DmitryBaranovskiy/raphael
3. https://morrisjs.github.io/morris.js/
4. https://jquery.com/
*/
/* <![CDATA[ */
$(document).ready(function () {
    var dataString =
        //값을 가져오는 형태
        //time: "년-월"(문자열), imageEditor: 횟수, videoEditor: 횟수, gifEditor: 횟수, encryption: 횟수, ssh: 횟수
        '[{"time": "2019-5", "imageEditor": 4, "videoEditor": 3, "gifEditor":  5, "encryption": 8, "ssh": 1}, ' +
        '{"time": "2019-4", "imageEditor": 3, "videoEditor": 1, "gifEditor":  0, "encryption": 1, "ssh": 9}, ' +
        '{"time": "2019-3", "imageEditor": 0, "videoEditor": 5, "gifEditor":  3, "encryption": 12, "ssh": 5}, ' +
        '{"time": "2019-2", "imageEditor": 2, "videoEditor": 4, "gifEditor":  4, "encryption": 0, "ssh": 15}]';
    var data = JSON.parse(dataString);

    Morris.Line({
        element: 'morrisLine_who2', //jsp 파일에 있는 그래프의 아이디 값 -> <div id="morrisLine_who2"></div>
        data: data, //json 데이터 받아오는 곳
        xkey: 'time', //X 축으로 설정할 값, 무조건 1개, 년-월로 설정
        ykeys: ['imageEditor', 'videoEditor', 'gifEditor', 'encryption', 'ssh'], //Y축으로 설정할 값, 1개 이상
        labels: ['imageEditor', 'videoEditor', 'gifEditor', 'encryption', 'ssh'], //네모난 박스로 설명란에 적을 값
        axes: false //X축, Y축이 보이지 않도록 설정, X축은 공간 부족, Y축은 실제 값과 맞지가 않아서 제거
    });
});
/* ]]> */