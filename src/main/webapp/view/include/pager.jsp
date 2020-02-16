<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="url" value="/tmetrica_war${requestScope['javax.servlet.forward.servlet_path']}"/>

<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${url}?page=${currentPage-1}">
                    <fmt:message key="common.prev" bundle="${link}"/>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${pageCount}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active">
                        <a class="page-link"
                           href="${url}?page=${i}">
                            <c:out value="${i}"/><span class="sr-only">(current)</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="${url}?page=${i}">
                            <c:out value="${i}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt pageCount}">
            <li class="page-item">
                <a class="page-link" style="width: 120px;"
                   href="${url}?page=${currentPage+1}">
                    <fmt:message key="common.next" bundle="${link}"/>
                </a>
            </li>
        </c:if>
    </ul>
</nav>