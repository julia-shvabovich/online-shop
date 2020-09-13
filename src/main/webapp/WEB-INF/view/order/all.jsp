<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<%@include file="../header-admin.jsp"%>
<table border="1">
    <tr>
        <th><h4>ID</h4></th>
        <th><h4>User ID</h4></th>
        <th><h4>Details</h4></th>
        <th><h4>Delete</h4></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <h4><c:out value="${order.id}"/></h4>
            </td>
            <td>
                <h4><c:out value="${order.userId}"/></h4>
            </td>
            <td>
                <h4><a href = "${pageContext.request.contextPath}/admin/orders/details?id=${order.id}">Details</a></h4>
            </td>
            <td>
                <h4><a href = "${pageContext.request.contextPath}/admin/orders/delete?id=${order.id}">Delete</a></h4>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
