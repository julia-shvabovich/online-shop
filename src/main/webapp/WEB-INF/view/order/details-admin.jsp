<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order details</title>
</head>
<body>
<%@include file="../header-admin.jsp"%>
<table border="1">
    <tr>
        <th><h4>ID</h4></th>
        <th><h4>Name</h4></th>
        <th><h4>Price</h4></th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <h4><c:out value="${product.id}"/></h4>
            </td>
            <td>
                <h4><c:out value="${product.name}"/></h4>
            </td>
            <td>
                <h4><c:out value="${product.price}"/></h4>
            </td>
        </tr>
    </c:forEach>
</table>
<h3><a href="${pageContext.request.contextPath}/admin/orders/all">All orders</a></h3>
</body>
</html>
