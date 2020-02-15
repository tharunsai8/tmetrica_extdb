<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <%@include file="include/head.jsp" %>
    <title>TMetrica - Registration Page</title>
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
                                    key="auth.register.welcome"/></h3>
                            <form ation="/registration" method="post">
                                <div class="form-label-group">
                                    <input type="email" id="inputEmail" name="email" class="form-control"
                                           placeholder="Email address" required autofocus>
                                    <label for="inputEmail">Email address</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="name" name="name" class="form-control"
                                           placeholder="Your name" required autofocus>
                                    <label for="inputEmail">Name</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="password" name="password" class="form-control"
                                           placeholder="Password" required>
                                    <label for="password">Password</label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="confirm_password" name="confirm_password"
                                           class="form-control" placeholder="Confirm password" required>
                                    <label for="confirm_password">Confirm password</label>
                                    <span id='message'></span>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                        type="submit"><fmt:message bundle="${link}"
                                        key="auth.register.button"/>
                                </button>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/login"><fmt:message bundle="${link}"
                                            key="auth.register.login"/></a></div>

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
    $('#password, #confirm_password').on('keyup', function () {
        if ($('#password').val() == $('#confirm_password').val()) {
            $('#message').html('Matching').css('color', 'green');
        } else
            $('#message').html('Not Matching').css('color', 'red');
    });

</script>
</html>
