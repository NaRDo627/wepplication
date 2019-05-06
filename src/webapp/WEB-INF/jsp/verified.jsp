<%@ page import="org.codehaus.jettison.json.JSONObject" %>
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

    <%@include file="assets/IncAsset.jsp" %>

</head>

<body>
<div id="wrapper">

    <%@include file="assets/IncHeader.jsp" %>

</div>
<%@include file="assets/IncFooter.jsp" %>
<script>
    $(document).ready(function () {
        alert("이메일 인증에 성공하였습니다");

        if(${sessionScope.users ne null}) {
            location.href = "/index";
        }
        else
            location.href = "/login";
    });
</script>
</body>

</html>
