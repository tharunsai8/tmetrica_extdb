<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <%@include file="include/head.jsp" %>
    <title>Tmetrica - Statistic</title>


</head>
<body>
<%@include file="include/navbar.jsp" %>
<br>
<div class="container-fluid">

    <p id="demo"></p>

    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><fmt:message bundle="${link}"
                                     key="stat.header.first"/> <b><fmt:message bundle="${link}"
                                                                               key="stat.header.second"/></b></h2>

                </div>
                <div class="col-sm-6">
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><fmt:message bundle="${link}"
                                 key="stat.table.first"/></th>
                <th><fmt:message bundle="${link}"
                                 key="stat.table.second"/></th>
                <th><fmt:message bundle="${link}"
                                 key="stat.table.third"/></th>
                <th><fmt:message bundle="${link}"
                                 key="stat.table.fourth"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="statistic" items="${stats}">
                <tr>
                    <td><c:out value="${statistic.activity.name}"/></td>
                    <c:choose>
                        <c:when test="${statistic.activity.status == 'ACTIVE'}">
                            <td><span class="badge badge-success"><c:out value="${statistic.activity.status}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${statistic.activity.status == 'SUSPENDED'}">
                            <td><span class="badge badge-warning"><c:out value="${statistic.activity.status}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-secondary"><c:out value="${statistic.activity.status}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td><span class="badge badge-info"><b><c:out
                            value="${statistic.formatDuration()}"/></b></span>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/logs">
                            <input type="hidden" name="activityId" value="${statistic.activity.id}">
                            <button class="success" type="submit"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="Show entries">&#xe8ef;</i></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <%@include file="include/pager.jsp" %>
    <br>
    <br>
</div>


<%@include file="include/footer.jsp" %>
</body>
</html>