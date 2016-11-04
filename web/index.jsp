<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Quản lý điểm - OOP L01 - AT11C KMA</title>
        <style type="text/css">
            * {
                transition: ease-in-out 0.25s;
            }
            
            html {
                height: 100%;
                width: 100%;
            }

            #feature {
                width: 960px;
                margin: 30px auto 0 auto;
                overflow: auto;
            }

            #content {
                font-family: "Segoe UI";
                font-weight: normal;
                font-size: 26px;
                color: #ffffff;
                float: left;
                width: 520px;
                margin-top: 0px;
                margin-left: 0px;
                vertical-align: middle;
            }

            #content h1 {
                font-family: "Segoe UI Light";
                color: #ffffff;
                font-weight: normal;
                font-size: 70px;
                line-height: 48pt;
                width: 800px;
            }

            #content h2 {
                font-family: "Segoe UI Light";
                color: #ffffff;
                font-weight: normal;
                font-size: 60px;
                line-height: 48pt;
                width: 800px;
            }

            p a, p a:visited, p a:active, p a:hover {
                color: #ffffff;
            }

            #content a.button {
                background: #0DBCF2;
                border: 1px solid #FFFFFF;
                color: #FFFFFF;
                display: inline-block;
                font-family: Segoe UI;
                font-size: 24px;
                line-height: 46px;
                margin-top: 10px;
                padding: 0 15px 3px;
                text-decoration: none;
            }

            #content a.button img {
                float: right;
                padding: 10px 0 0 15px;
            }

            #content a.button:hover {
                background: #1C75BC;
            }

            body {
                background-image: url('background.png');
                background-repeat: no-repeat;
                background-position: center;
                background-attachment: fixed;
                transition: ease 30s;
                animation-name: bgr;
                animation-duration: 40s;
                animation-iteration-count: infinite;
                -webkit-animation-name: bgr;
                -webkit-animation-duration: 40s;
                -webkit-animation-iteration-count: infinite;
            }
            
            @keyframes bgr {
                0% {
                    background-image: url('background.png');
                    background-position: center top;
                }
                75% {
                    background-image: url('background.png');
                    background-position: center bottom;
                }
                
                100% {
                    background-image: url('background.png');
                    background-position: center top;
                }
            }
            @-webkit-keyframes bgr {
                0% {
                    background-image: url('background.png');
                    background-position: center bottom;
                }
                75% {
                    background-image: url('background.png');
                    background-position: center top;
                }
                85% {
                    background-image: none;
                    background-position: center bottom;
                }
                100% {
                    background-image: url('background.png');
                    background-position: center bottom;
                }
            }
        </style>


    </head>
    <body bgcolor="#00abec" >
        <div id="feature">
            <div id="content">
                <p><b>Quản lý điểm cho Học sinh Phổ thông</b></p>
                <h2 style="margin-top: -24px;">Web Service</h2>
                <p>
                    Đây là một JAX-WS và RESTful API bổ trợ cho ứng dụng web và ứng dụng desktop
                    <br/><br/>
                    
                    <a href="#" onclick="alert('Người thự hiện:\n - Nguyễn Bằng Giang (Trưởng nhóm)\n - Phạm Xuân Đức\n - Lê Tiến Đạt\n - Nguyễn Phương Nam\n - Ứng Đình Hướng\n\nMôn học: Lập trình hướng đối tượng\nLớp L01 - Khóa AT11 - Năm học 2016-2017\nHọc viện Kỹ thuật Mật mã')" class="button">
                        Thông tin
                        <img border="0" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB8AAAAdCAYAAABSZrcyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYxIDY0LjE0MDk0OSwgMjAxMC8xMi8wNy0xMDo1NzowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNS4xIFdpbmRvd3MiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NERBNUVDMDBBNkE5MTFFMThGMDVCRkNEMjk1M0IwMTQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NERBNUVDMDFBNkE5MTFFMThGMDVCRkNEMjk1M0IwMTQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo0REE1RUJGRUE2QTkxMUUxOEYwNUJGQ0QyOTUzQjAxNCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0REE1RUJGRkE2QTkxMUUxOEYwNUJGQ0QyOTUzQjAxNCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PnW1aqMAAAE1SURBVHjaxFeLEYMgDEUnYARHYAS7gSM4AiN0A0dxBEbQDewG7QYUeonlqHzksHl3Oe80yQtJCMhYBrTWnZG7EaXDUKDTZfpMKvQJwlggfRG5+cCNzJ7DxYg8cgpBStBxYX3wbHLzUhjZzqwikS3rSyTJgfgJRvY5sEJYW8+XCJJDqoPKhQH4i+EhclWTOBCA+iGHFCEGVhlH/l1ybLD5RFO5wjNscPdsOzkYI3IHxHiwDXnGoEL0SD759bgwAOyrCclxMMiCWo5OM2EAIqIvHb3Piz0VFbo5ulu8En/JE2k6i1gAO2XLroGt/ZJq4NadcBXJX0ZuTdM8UjUjq7ld+QrfRAGxDVhBmt0VrwET5FjRAek+p5twpLOd/FQjPc/JbzKkdzjy2yv5vZ38j+Vf/2pvAQYAVsHorRPkS+QAAAAASUVORK5CYII%3D" />
                    </a>
                </p>
                <!--<p>
                There's nothing here yet, but Microsoft Azure makes it simple to publish content with
                <a href="http://www.windowsazure.com/en-us/documentation/articles/web-sites-deploy/#git" alt="GIT">GIT</a> and
                <a href="http://www.windowsazure.com/en-us/documentation/articles/web-sites-deploy/#ftp" alt="FTP">FTP</a>
                </p>
    
                <a href="http://go.microsoft.com/?LinkID=9844831" class="button">Tell me more<img border="0" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB8AAAAdCAYAAABSZrcyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYxIDY0LjE0MDk0OSwgMjAxMC8xMi8wNy0xMDo1NzowMSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNS4xIFdpbmRvd3MiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NERBNUVDMDBBNkE5MTFFMThGMDVCRkNEMjk1M0IwMTQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NERBNUVDMDFBNkE5MTFFMThGMDVCRkNEMjk1M0IwMTQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo0REE1RUJGRUE2QTkxMUUxOEYwNUJGQ0QyOTUzQjAxNCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo0REE1RUJGRkE2QTkxMUUxOEYwNUJGQ0QyOTUzQjAxNCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PnW1aqMAAAE1SURBVHjaxFeLEYMgDEUnYARHYAS7gSM4AiN0A0dxBEbQDewG7QYUeonlqHzksHl3Oe80yQtJCMhYBrTWnZG7EaXDUKDTZfpMKvQJwlggfRG5+cCNzJ7DxYg8cgpBStBxYX3wbHLzUhjZzqwikS3rSyTJgfgJRvY5sEJYW8+XCJJDqoPKhQH4i+EhclWTOBCA+iGHFCEGVhlH/l1ybLD5RFO5wjNscPdsOzkYI3IHxHiwDXnGoEL0SD759bgwAOyrCclxMMiCWo5OM2EAIqIvHb3Piz0VFbo5ulu8En/JE2k6i1gAO2XLroGt/ZJq4NadcBXJX0ZuTdM8UjUjq7ld+QrfRAGxDVhBmt0VrwET5FjRAek+p5twpLOd/FQjPc/JbzKkdzjy2yv5vZ38j+Vf/2pvAQYAVsHorRPkS+QAAAAASUVORK5CYII%3D" /></a>
                <br/>-->
                <font face="Segoe UI" size="4">
                <br/>
                <table width="750">
                    <tr bgcolor="rgba(255,255,255,0.8)">
                        <th align="left" width="200">    Thuộc tính Java  </th>
                        <th align="left" width="500">    Thông số  </th>
                    </tr>
                    <%@ page import="java.util.*" %>
                    <%
                        ArrayList<String> mainPageProps = new ArrayList<String>();
                        mainPageProps.add("java.version");
                        mainPageProps.add("java.vendor");
                        mainPageProps.add("os.arch");
                        mainPageProps.add("catalina.base");
                        mainPageProps.add("jetty.base");
                        mainPageProps.add("user.timezone");
                        for (String name : mainPageProps) {
                            String value = System.getProperty(name);
                            if (value != null) {
                                out.print("<tr><td>" + name);
                                out.print("</td><td>" + value);
                                out.print("</td></tr>");
                            }
                        }
                    %>
                </table>
                </font>
            </div>
        </div>
    </body>

</html>
