<%--
  Created by IntelliJ IDEA.
  User: naruhodo627
  Date: 2018-11-02
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <%--<li class="sidebar-search">
                <div class="input-group custom-search-form">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                </div>
                <!-- /input-group -->
            </li>--%>
                <li>
                    <div style="height:60px;">
                            <div class="centered-outer">
                                <div class="centered-inner">
                                    <div class="centered">
                                        <a class="text-primary h4" href="/index" style="background-color:#f8f8f8;"><i class="fa fa-desktop fa-2x"></i></a>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <!-- /input-group -->
                </li>
            <li class="active">
                <span style="color:#337ab7;padding: 10px 15px;display:block;"><%--<i class="fa fa-folder-open-o fa-fw"></i>--%> <strong>미디어 편집</strong><%--<span class="fa arrow"></span>--%></span>
                <ul class="nav nav-second-level">
                    <%--<li>
                        <a href="/viewer/image_viewer">이미지</a>
                    </li>
                    <li>
                        <a href="/viewer/video_viewer">동영상</a>
                    </li>
                    <li>
                        <a href="/viewer/office_viewer">넷피스</a>
                    </li>--%>
                        <li>
                            <a href="/editor/image_editor"><i class="fa fa-image fa-fw"></i> 이미지 편집</a>
                        </li>
                        <li>
                            <a href="/editor/video_editor"><i class="fa fa-video-camera fa-fw"></i> 동영상 편집</a>
                        </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
                <li class="active">
                    <span style="color:#337ab7;padding: 10px 15px;display:block;"><%--<i class="fa fa-folder-open-o fa-fw"></i>--%> <strong>유틸리티 툴</strong><%--<span class="fa arrow"></span>--%></span>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#"><i class="fa fa-folder-open-o fa-fw"></i>암호화<span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="/crypto/encryptWord">문장 암호화</a>
                                </li>
                                <li>
                                    <a href="/crypto/encryptFile">파일 암호화</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="/viewer/ssh_terminal"><i class="fa fa-terminal fa-fw"></i> SSH 터미널</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

            <%--<li>
                <a href="/cloud/cloud"><i class="fa fa-cloud fa-fw"></i> 클라우드</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-folder-open-o fa-fw"></i>암호화<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/crypto/encryptWord">문장 암호화</a>
                    </li>
                    <li>
                        <a href="/crypto/encryptFile">파일 암호화</a>
                    </li>
                </ul>
            </li> --%>

            <%--
            <!-- Sample pages \|/ -->
            <li>
                <a href="index"><i class="fa fa-sitemap fa-fw"></i> Sample (개발 참고용)<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/sample/dashboard"> Dashboard</a>
                    </li>
                    <li>
                        <a href="#"> Charts<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="/sample/flot">Flot Charts</a>
                            </li>
                            <li>
                                <a href="/sample/morris">Morris.js Charts</a>
                            </li>
                        </ul>
                        <!-- /.nav-third-level -->
                    </li>
                    <li>
                        <a href="/sample/tables"> Tables</a>
                    </li>
                    <li>
                        <a href="/sample/forms"> Forms</a>
                    </li>
                    <li>
                        <a href="#"> UI Elements<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="/sample/panels-wells">Panels and Wells</a>
                            </li>
                            <li>
                                <a href="/sample/buttons">Buttons</a>
                            </li>
                            <li>
                                <a href="/sample/notifications">Notifications</a>
                            </li>
                            <li>
                                <a href="/sample/typography">Typography</a>
                            </li>
                            <li>
                                <a href="/sample/icons"> Icons</a>
                            </li>
                            <li>
                                <a href="/sample/grid">Grid</a>
                            </li>
                        </ul>
                        <!-- /.nav-third-level -->
                    </li>
                    <li>
                        <a href="#">Sample Pages<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/sample/blank">Blank Page</a>
                            </li>
                            <li>
                                <a href="/sample/login">Login Page</a>
                            </li>
                        </ul>
                        <!-- /.nav-third-level -->
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>

            --%>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
<script>

</script>