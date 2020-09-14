<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Welcome to the online shop!</h1>
<h3>
    <a href="${pageContext.request.contextPath}/products/all">All products</a><br>
    <a href="${pageContext.request.contextPath}/orders/history">Orders history</a><br>
</h3>
</body>
</html>
