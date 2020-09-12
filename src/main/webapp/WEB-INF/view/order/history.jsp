<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders history</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>User ID</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/orders/details?id=${order.id}"> Details </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/cart/products">Shopping cart</a><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a><br>
<a href="${pageContext.request.contextPath}/">Main page</a><br>
</body>
</html>

