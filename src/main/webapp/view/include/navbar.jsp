<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Tmetrica</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" href="/times"><fmt:message
                            code="navbar.times"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/activities" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message
                                code="navbar.activities"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/activities"><fmt:message
                                code="navbar.activities.all"/></a>
                        <a class="dropdown-item" href="/activities/my"><fmt:message
                                code="navbar.activities.my"/></a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/times/statistic"><fmt:message
                            code="navbar.statistic"/></a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/orders" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message
                                code="navbar.orders"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/orders/active"><fmt:message
                                code="navbar.orders.pending"/></a>
                        <a class="dropdown-item" href="/orders"><fmt:message
                                code="navbar.orders.closed"/></a>
                    </div>
                </li>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message
                                code="navbar.language"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <a class="dropdown-item"
                           href="app/language?locale=en">EN</a>

                        <a class="dropdown-item"
                           href="app/language?locale=ru">RU</a>
                    </div>

                </li>
            </ul>

            <form action="/login" method="get" class="form-inline my-2 my-md-0">
                <button class="btn btn-outline-success my-2 my-sm-0" style="margin: 10px;"><fmt:message
                        code="navbar.login"/></button>
            </form>


            <form action="/logout" method="post" class="form-inline my-2 my-md-0">
                <button type="submit" class="btn btn-outline-danger my-2 my-sm-0"><fmt:message
                        code="navbar.logout"/></button>
            </form>

        </div>
    </div>
</nav>