<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <%@include file="../include/head.jsp" %>
    <link href="${pageContext.request.contextPath}/view/css/error.css" rel="stylesheet">
    <title>Tmetrica - 500 ERROR</title>
</head>

<body>
<%@include file="../include/navbar.jsp" %>
<div id="notfound">
    <div class="notfound">


        <div class="notfound-404">
            <h1>&#x1F4BB;</h1>
        </div>
        <h2>500 - Internal Server Error</h2>
        <p>Unexpected Error:(<br/>Please
            go back to the previous page to continue browsing.</p>
        <a href="${pageContext.request.contextPath}/">home page</a>


    </div>
</div>
<%@include file="../include/footer.jsp" %>
</body>
</html>
