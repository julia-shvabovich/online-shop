<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remove</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/cart/delete?id=${product.id}">Remove</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/orders/create" target="_self">
    <button>Place an order</button>
</form>
<a href="${pageContext.request.contextPath}/products/all">All products</a><br>
<a href="${pageContext.request.contextPath}/orders/history">Order history</a><br>
<a href="${pageContext.request.contextPath}/">Main page</a><br>
</body>
</html>
