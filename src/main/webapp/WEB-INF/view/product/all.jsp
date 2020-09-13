<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<%@include file="../header.jsp"%>
<table border="1">
    <tr>
        <th><h4>ID</h4></th>
        <th><h4>Name</h4></th>
        <th><h4>Price</h4></th>
        <th><h4>Buy</h4></th>
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
                <h4><a href="${pageContext.request.contextPath}/cart/add-product?id=${product.id}">Buy</a></h4>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
