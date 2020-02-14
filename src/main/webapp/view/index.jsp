<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="include/head.jsp" %>
    <title>Time Tracker - Home Page</title>
</head>

<body>
<%@include file="include/navbar.jsp" %>
<header>
    <%@include file="include/header.jsp" %>
</header>

<section class="py-5">
    <div class="container">
        <h1 class="display-4"><fmt:message bundle="${link}"
                                           key="main.mainHeader"/></h1>
        <p class="lead"><fmt:message bundle="${link}"
                                     key="main.section"/>
    </div>
</section>
<%@include file="include/footer.jsp" %>
</body>
</html>
