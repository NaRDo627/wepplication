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

    <%--<!-- PaintWeb JS -->
    <script type="text/javascript" src="/resources/js/paintweb/paintweb.js"></script>
    <script type="text/javascript" src="/resources/js/paintweb/html2canvas.min.js"></script>
    <script type="text/javascript" src="/resources/js/paintweb/es6-promise.min.js"></script>
    <script type="text/javascript" src="/resources/js/paintweb/es6-promise.auto.min.js"></script>--%>
    <script src="/resources/js/bundle.js"></script>

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
                        <h1 class="page-header">이미지 편집</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="panel panel-default">
                            <input type="hidden" id="mno" value="${sessionScope.user_membership.mno}" />
                            <%--
                            <div class="panel-heading">
                                <br>
                                &lt;%&ndash;
                                <a href="javascript:fn_uploadImage('');" class="btn btn-primary">이미지 업로드</a>
                                <a href="#" class="btn btn-primary" id="image_download" style="display: none;">이미지 다운로드</a>
                                &ndash;%&gt;
                            </div>
                            --%>
                            <div class="panel-body" style="height:1000px; background: var(--background); padding: 0;">
                                <div style="border: 1px solid whitesmoke;">
                                    <div class="ddsmoothmenu" id="main_menu" style="z-index: -100;"></div>
                                    <div class="wrapper" style="height:1000px;">

                                        <div class="submenu">
                                            <%--<a class="logo" href="">miniPaint</a>--%>
                                            <div class="block attributes" id="action_attributes"></div>
                                            <div class="clear"></div>
                                        </div>

                                        <div class="sidebar_left" id="tools_container"></div>

                                        <div class="main_wrapper" id="main_wrapper" >
                                            <div class="canvas_wrapper" id="canvas_wrapper">
                                                <div id="mouse"></div>
                                                <div class="transparent-grid" id="canvas_minipaint_background"></div>
                                                <canvas id="canvas_minipaint">
                                                    <div class="trn error">
                                                        Your browser does not support canvas or JavaScript is not enabled.
                                                    </div>
                                                </canvas>
                                            </div>
                                        </div>

                                        <div class="sidebar_right">
                                            <div class="preview block">
                                                <h2 class="trn toggle" data-target="toggle_preview">Preview</h2>
                                                <div id="toggle_preview"></div>
                                            </div>

                                            <div class="colors block">
                                                <h2 class="trn toggle" data-target="toggle_colors">Colors</h2>
                                                <input
                                                        title="Click to change color"
                                                        type="color"
                                                        class="color_area"
                                                        id="main_color"
                                                        value="#0000ff"	/>
                                                <div class="content" id="toggle_colors"></div>
                                            </div>

                                            <div class="block" id="info_base">
                                                <h2 class="trn toggle toggle-full" data-target="toggle_info">Information</h2>
                                                <div class="content" id="toggle_info"></div>
                                            </div>

                                            <div class="details block" id="details_base">
                                                <h2 class="trn toggle toggle-full" data-target="toggle_details">Layer details</h2>
                                                <div class="content" id="toggle_details"></div>
                                            </div>

                                            <div class="layers block">
                                                <h2 class="trn">Layers</h2>
                                                <div class="content" id="layers_base"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mobile_menu">
                                        <button class="right_mobile_menu" id="mobile_menu_button" type="button"></button>
                                    </div>

                                    <div class="hidden" id="tmp"></div>
                                    <div id="popup"></div>
                                </div>

                            <%--
                                <div class="centered-outer" id="load_image">
                                    <div class="centered-inner">
                                        <div class="centered">
                                            <i class="fa fa-image fa-5x"></i>
                                        </div>
                                    </div>
                                </div>
                                --%>
                            </div>
                        </div>
                    </div>
                    <%--
                    <div class="col-lg-2 col-xs-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-lg-6 col-xs-1">
                                               <p class="text-center"><i class="fa fa-cut fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-hand-o-up fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-pencil fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-paint-brush fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-eraser fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-eraser fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-bookmark fa-2x"></i></p>
                                            </div>
                                            <div class="col-lg-6 col-xs-1">
                                                <p class="text-center"><i class="fa fa-spoon fa-2x"></i></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-xs-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>네비게이터</strong><br>
                                        <strong>X : </strong><br>
                                        <strong>Y : </strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>레이어</strong><br>
                                        <strong>배경</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <strong>히스토리</strong><br>
                                        <strong>새 이미지</strong>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    --%>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@include file="../assets/IncFooter.jsp"%>
<script>

    /*
    var srcImg = null;
    var srcExt = "";
    var downloadUrl = "";
    img1 = document.getElementById('editableImage');
    srcImg = img1;
    $(document).ready(function () {
        $("#image_download").click(function () {
            window.location.assign(downloadUrl);
        })
    });

    function initEditor() {
        var target = document.getElementById('PaintWebTarget'),
            pw = new PaintWeb();
        pw.config.guiPlaceholder = target;
        pw.config.configFile = 'config-example.json';
        pw.config.imageLoad = img1;

        pw.config.imageSaveURL = "/editor/saveImage"; // imageSave == image upload
        pw.config.imageDownloadURL = "/editor/fileDownload";
        pw.init();
    }

    function fn_uploadImage() {
        $("#uploadImageFile").click();
    }

    function uploadImageFileChange() {
        var formData = new FormData();
        formData.append('upfile', $('#uploadImageFile')[0].files[0]);
        var fileName = $('#uploadImageFile')[0].files[0].name;
        srcExt = fileName.substr(fileName.lastIndexOf('.')).toLowerCase();

        $.ajax({
            url: '/editor/fileUpload',
            data: formData,
            type: 'POST',
            contentType: false,
            processData: false,
            success: function (data) {
                $('#editableImage').attr("src", "/editor/fileDownload?filename=" + data);
                $("#PaintWebTarget").empty();
                $("#image_download").css("display", "");
                downloadUrl = "/editor/imageDownload?filename=" + data + srcExt;
               // $("#image_download").attr("href", "/editor/imageDownload?filename=" + data + srcExt);
                initEditor();
                img1.style.display = 'none';
            },error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        })
    }
    */
</script>
<script>
    $(document).ready(function(){

    })
</script>
</body>

</html>
