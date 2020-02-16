<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>

    <%@include file="include/head.jsp" %>
    <title>Tmetrica - Logs</title>
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

                        <fmt:message bundle="${link}"
                                     key="stat.header.first"/>


                        <b><fmt:message bundle="${link}"
                                        key="times.header.second"/></b></h2>
                </div>
                <div class="col-sm-6">

                    <a href="#addRecordModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                        <span><fmt:message bundle="${link}"
                                           key="times.add"/></span></a>

                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><fmt:message bundle="${link}"
                                 key="times.table.first"/></th>
                <th><fmt:message bundle="${link}"
                                 key="times.table.second"/></th>
                <th><fmt:message bundle="${link}"
                                 key="times.table.third"/></th>
                <th><fmt:message bundle="${link}"
                                 key="times.table.fourth"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${logs}">
                <tr>
                    <td><c:out value="${log.activity.name}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${log.startTime}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${log.endTime}"/></td>
                    <td>
                        <a href="#editRecordModel" class="edit passingIDEdit" data-id="${log.id}" data-toggle="modal"><i
                                class="material-icons"
                                title="Edit">&#xE254;</i></a>
                        <a href="#deleteRecordModal" class="delete passingID" data-id="${log.id}" data-toggle="modal"><i
                                class="material-icons"
                                data-toggle="tooltip"
                                title="Delete">&#xE872;</i></a>
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

<!-- Add Modal HTML -->
<div id="addRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/addlog" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="times.add.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Start Time</label>
                        <input type="datetime-local" id="startDateAdd" min="2020-01-01T00:00" max="2100-01-00T00:00"
                               name="startDate"
                               class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label>End Date</label>
                        <input type="datetime-local" id="endDateAdd" name="endDate" class="form-control" required>
                    </div>
                    <label>Activity</label>
                    <select class="form-control" name="activityId" exampleFormControlSelect1">
                    <c:forEach var="activity" items="${activities}">
                        <option value="${activity.id}">${activity.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                        key="cancel"/>">
                    <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                        key="times.add.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editRecordModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/editlog" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="times.edit.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Start Time</label>
                        <input type="datetime-local" min="2020-01-01T00:00" max="2100-01-00T00:00" name="startDate"
                               class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>End Date</label>
                        <input type="datetime-local" name="endDate" class="form-control" required>
                    </div>
                    <label>Activity</label>
                    <select class="form-control" name="activityId" exampleFormControlSelect1">
                    <c:forEach var="activity" items="${activities}">
                        <option value="${activity.id}">${activity.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="logId" id="logId" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                        key="cancel"/>">
                    <input type="submit" class="btn btn-info" value="<fmt:message bundle="${link}"
                        key="times.edit.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/deletelog">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="times.delete.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="times.delete.body"/></p>
                    <p class="text-warning"><small><fmt:message bundle="${link}"
                                                                key="delete.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="logId" id="logIdToDelete" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                        key="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<fmt:message bundle="${link}"
                        key="delete.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(".passingID").click(function () {
        var ids = $(this).attr('data-id');
        $("#logIdToDelete").val(ids);
        $('#deleteRecordModal').modal('show');
    });
    $(".passingIDEdit").click(function () {
        var ids = $(this).attr('data-id');
        $("#logId").val(ids);
        $('#editRecordModel').modal('show');
    });
</script>
<%@include file="include/footer.jsp" %>
</body>
</html>