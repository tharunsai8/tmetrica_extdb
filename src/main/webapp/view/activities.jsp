<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
<head>
    <%@include file="include/head.jsp" %>
    <title>Tmetrica - Activities</title>
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
                    <h2>
                        <c:choose>
                            <c:when test="${usersList}">
                                <fmt:message bundle="${link}"
                                             key="times.header.first"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${link}"
                                             key="activities.all.header.first"/>
                            </c:otherwise>
                        </c:choose>
                        <b><fmt:message bundle="${link}"
                                        key="activities.my.header.second"/></b></h2>
                </div>
                <div class="col-sm-6">
                    <c:if test="${!usersList}">
                        <a href="#addRecordModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                            <span><fmt:message bundle="${link}"
                                               key="activity.add.new"/></span></a>
                    </c:if>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><fmt:message bundle="${link}"
                                 key="activities.all.table.first"/></th>
                <th><fmt:message bundle="${link}"
                                 key="activities.all.table.second"/></th>
                <c:if test="${usersList}">
                    <th><fmt:message bundle="${link}"
                                     key="activities.my.table.third"/></th>
                </c:if>
                <th><fmt:message bundle="${link}"
                                 key="activities.all.table.third"/></th>
                <c:choose>
                    <c:when test="${usersList}">
                        <th><fmt:message bundle="${link}"
                                         key="activities.my.table.sixth"/></th>
                        <th><fmt:message bundle="${link}"
                                         key="delete.button"/></th>
                    </c:when>
                    <c:otherwise>
                        <th><fmt:message bundle="${link}"
                                         key="activities.all.table.fifth"/></th>

                    </c:otherwise>
                </c:choose>
                <c:if test="${admin}">
                    <th><fmt:message bundle="${link}"
                                     key="user.add"/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${activities}" var="item">
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                        value="${item.openingTime}"/></td>
                    <c:if test="${usersList}">
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                            value="${item.closingTime}"/></td>
                    </c:if>
                    <c:choose>
                        <c:when test="${item.status == 'ACTIVE'}">
                            <td><span class="badge badge-success"><c:out value="${item.status}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${item.status == 'SUSPENDED'}">
                            <td><span class="badge badge-warning"><c:out value="${item.status}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-secondary"><c:out value="${item.status}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${usersList}">
                            <td>
                                <form method="get" action="${pageContext.request.contextPath}/logs">
                                    <input type="hidden" name="activityId" value="${item.id}">
                                    <button class="success" type="submit"><i
                                            class="material-icons"
                                            data-toggle="tooltip"
                                            title="Show entries">&#xe8ef;</i></button>
                                </form>
                            </td>
                            <td>
                                <a href="#deleteRecordModal" class="delete passingIDDelete" data-id="${item.id}"
                                   data-toggle="modal"><i
                                        class="material-icons"
                                        data-toggle="tooltip"
                                        title="Delete">&#xE872;</i></a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="#joinToActivModel" class="success passingID" data-id="${item.id}"
                                   data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="join">&#xe147;</i></a>
                            </td>


                        </c:otherwise>

                    </c:choose>
                    <c:if test="${admin}">
                        <td><a href="#joinUserToActivModel" class="success passingIDUser" data-id="${item.id}"
                               data-toggle="modal"><i
                                class="material-icons"
                                data-toggle="tooltip"
                                title="join">&#xe8a6;</i></a>
                        </td>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>

        </table>

    </div>
    <%@include file="include/pager.jsp" %>
    <br>
    <br>
</div>


<!-- Delete Modal HTML -->
<div id="deleteRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/deleteactivity">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="activities.remove.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="activities.remove.body"/></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="activityId" id="activityIdToDelete" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal"
                           value="<fmt:message  bundle="${link}"
                key="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<fmt:message bundle="${link}"
                key="delete.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Add Modal HTML -->
<div id="addRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/createactivity" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="activities.add.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label><fmt:message bundle="${link}"
                                            key="activities.all.table.first"/></label>
                        <input type="text" name="activityName" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-danger" data-dismiss="modal" value="<fmt:message bundle="${link}"
                key="cancel"/>">

                    <c:choose>
                        <c:when test="${admin}">
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.add.admin.ok"/>">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.add.user.ok"/>">
                        </c:otherwise>
                    </c:choose>

                </div>
            </form>
        </div>
    </div>
</div>

<!-- Join Modal HTML -->
<div id="joinToActivModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/join">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="activities.join.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="activities.join.body"/></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="activityId" id="actId" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                key="cancel"/>">

                    <c:choose>
                        <c:when test="${admin}">
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.join.title"/>">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.add.user.ok"/>">
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="joinUserToActivModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/join">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="user.join.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="user.join"/></p>
                    <label>Select user</label>
                    <select class="form-control" name="userId" exampleFormControlSelect1">
                    <c:forEach var="user" items="${users}">
                        <option value="${user.id}">${user.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="activityId" id="activId" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                key="cancel"/>">

                    <c:choose>
                        <c:when test="${admin}">
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.join.title"/>">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="activities.add.user.ok"/>">
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(".passingIDDelete").click(function () {
        var ids = $(this).attr('data-id');
        $("#activityIdToDelete").val(ids);
        $('#deleteRecordModal').modal('show');
    });
    $(".passingID").click(function () {
        var ids = $(this).attr('data-id');
        $("#actId").val(ids);
        $('#joinToActivModel').modal('show');
    });
    $(".passingIDUser").click(function () {
        var ids = $(this).attr('data-id');
        $("#activId").val(ids);
        $('#joinUserToActivModel').modal('show');
    });
</script>

</body>
<%@include file="include/footer.jsp" %>
</html>