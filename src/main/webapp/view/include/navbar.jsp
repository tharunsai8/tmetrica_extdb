<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle var="link" basename="message" scope="session"/>

<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Tmetrica</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <c:choose>
                <c:when test="${empty sessionScope.user}">

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message bundle="${link}"
                                         key="navbar.language"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/?lang=en">EN</a>

                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/?lang=ru">RU</a>
                        </div>

                    </li>
                    <form action="${pageContext.request.contextPath}/login" method="get"
                          class="form-inline my-2 my-md-0">
                        <button class="btn btn-outline-success my-2 my-sm-0" style="margin: 10px;"><fmt:message
                                bundle="${link}"
                                key="navbar.login"/></button>
                    </form>
                </c:when>
                <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logs"><fmt:message bundle="${link}"
                                                                                                     key="navbar.times"/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/activities"
                       id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message bundle="${link}"
                                     key="navbar.activities"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/activities"><fmt:message
                                bundle="${link}"
                                key="navbar.activities.all"/></a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/myactivity"><fmt:message
                                bundle="${link}"
                                key="navbar.activities.my"/></a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/statistic"><fmt:message
                            bundle="${link}"
                            key="navbar.statistic"/></a>
                </li>
                <c:if test="${admin}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/orders"
                           id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message bundle="${link}"
                                         key="navbar.orders"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/orders"><fmt:message
                                    bundle="${link}"
                                    key="navbar.orders.pending"/></a>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/reviewedorders"><fmt:message
                                    bundle="${link}"
                                    key="navbar.orders.closed"/></a>
                        </div>
                    </li>
                </c:if>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message bundle="${link}"
                                     key="navbar.language"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/?lang=en">EN</a>

                        <a class="dropdown-item"
                           href="${pageContext.request.contextPath}/?lang=ru">RU</a>
                    </div>

                </li>
            </ul>
            <form action="${pageContext.request.contextPath}/logout" method="post"
                  class="form-inline my-2 my-md-0">
                <button type="submit" class="btn btn-outline-danger my-2 my-sm-0"><fmt:message bundle="${link}"
                                                                                               key="navbar.logout"/></button>
            </form>
            </c:otherwise>
            </c:choose>


        </div>
    </div>
</nav>


