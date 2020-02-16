<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<fmt:setBundle var="link" basename="message" scope="session"/>
<c:set var="login_error" value="${requestScope.login_error}"/>

<html>
<head>
    <%@include file="include/head.jsp" %>
    <title>TMetrica - Login Page</title>
</head>
<body>
<%@include file="include/navbar.jsp" %>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4"><fmt:message bundle="${link}"
                                                                        key="auth.login.welcome"/></h3>
                            <form action="${pageContext.request.contextPath}/login" method="post">
                                <div class="form-label-group">
                                    <input type="email" id="inputEmail" name="username" class="form-control"
                                           placeholder="Email address" required autofocus>
                                    <label for="inputEmail">Email address</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" name="password" class="form-control"
                                           placeholder="Password" required>
                                    <label for="inputPassword">Password</label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                        type="submit"><fmt:message bundle="${link}"
                                                                   key="auth.login.button"/></button>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/registration"><fmt:message
                                            bundle="${link}"
                                            key="auth.login.register"/></a>
                                </div>

                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger" script>
                                        <strong><fmt:message bundle="${link}"
                                                             key="wrong.password"/></strong>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp" %>

</body>

<script>
    window.setTimeout(function () {
        $(".alert").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 2000);
</script>
</html>