<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <%@include file="include/head.jsp" %>
    <title>Tmetrica - Orders </title>
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
                            <c:when test="${active}">
                                <fmt:message bundle="${link}"
                                             key="orders.active.header.first"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${link}"
                                             key="orders.all.header.first"/>
                            </c:otherwise>
                        </c:choose>
                        <b><fmt:message bundle="${link}"
                                        key="orders.active.header.second"/></b></h2>
                </div>
                <div class="col-sm-6">

                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><fmt:message bundle="${link}"
                                 key="orders.table.first"/></th>
                <th><fmt:message bundle="${link}"
                                 key="orders.table.second"/></th>
                <th><fmt:message bundle="${link}"
                                 key="orders.table.third"/></th>
                <th><fmt:message bundle="${link}"
                                 key="orders.table.fourth"/></th>
                <c:if test="${active}">
                    <th><fmt:message bundle="${link}"
                                     key="orders.table.sixth"/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value="${order.activity.name}"/></td>
                    <td><c:out value="${order.user.name}"/></td>
                    <c:choose>
                        <c:when test="${order.status == 'ACCEPTED'}">
                            <td><span class="badge badge-success"><c:out value="${order.status}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${order.status == 'PENDING'}">
                            <td><span class="badge badge-warning"><c:out value="${order.status}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-danger"><c:out value="${order.status}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td><span class="badge badge-secondary"><c:out value="${order.action}"/></span>
                    </td>
                    <c:if test="${active}">
                        <td>
                            <a href="#acceptOrderModal" class="success passingIDAccept" data-id="${order.id}"
                               data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="Accept">&#xE876;</i></a>
                            <a href="#rejectOrderModel" class="delete passingIDReject" data-id="${order.id}"
                               data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="Reject">&#xE14c;</i></a>
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

<!--  Reject Modal HTML -->
<div id="rejectOrderModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/reject">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="orders.rej.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="orders.rej.body"/></p>
                    <p class="text-warning"><small><fmt:message bundle="${link}"
                                                                key="orders.rej.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="orderId" id="orderIdToReject" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                key="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<fmt:message bundle="${link}"
                key="orders.rej.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>

<!--  Accept Modal HTML -->
<div id="acceptOrderModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/accept">
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message bundle="${link}"
                                                         key="orders.ok.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><fmt:message bundle="${link}"
                                    key="orders.ok.body"/></p>
                    <p class="text-warning"><small><fmt:message bundle="${link}"
                                                                key="orders.ok.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="orderId" id="orderIdToAccept" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<fmt:message bundle="${link}"
                key="cancel"/>">
                    <input type="submit" class="btn btn-success" value="<fmt:message bundle="${link}"
                key="orders.ok.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(".passingIDAccept").click(function () {
        var ids = $(this).attr('data-id');
        console.log(ids);
        $("#orderIdToAccept").val(ids);
        $('#acceptOrderModal').modal('show');
    });
    $(".passingIDReject").click(function () {
        var ids = $(this).attr('data-id');
        console.log(ids);
        $("#orderIdToReject").val(ids);
        $('#rejectOrderModel').modal('show');
    });
</script>

<%@include file="include/footer.jsp" %>
</body>
</html>