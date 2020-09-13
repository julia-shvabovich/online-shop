<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th><h4>ID</h4></th>
        <th><h4>Name</h4></th>
        <th><h4>Price</h4></th>
        <th><h4>Remove</h4></th>
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
            <td>
                <h4><a href="${pageContext.request.contextPath}/cart/delete?id=${product.id}">Remove</a></h4>
            </td>
        </tr>
    </c:forEach>
</table>
<form style="padding: 10px" action="${pageContext.request.contextPath}/orders/create" target="_self">
    <button>Place an order</button>
</form>
<h3>
    <a href="${pageContext.request.contextPath}/products/all">All products</a><br>
    <a href="${pageContext.request.contextPath}/orders/history">Order history</a>
</h3>
</body>
</html>
