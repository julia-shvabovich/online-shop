<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<%@include file="header-admin.jsp"%>
<h3>
    <a href="${pageContext.request.contextPath}/admin/users/all">All users</a><br>
    <a href="${pageContext.request.contextPath}/admin/products">All products</a><br>
    <a href="${pageContext.request.contextPath}/admin/products/add">Add products</a><br>
    <a href="${pageContext.request.contextPath}/admin/orders/all">All orders</a><br>
</h3>
</body>
</html>
