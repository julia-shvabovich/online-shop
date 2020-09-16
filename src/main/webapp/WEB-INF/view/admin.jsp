<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<%@include file="header-admin.jsp" %>
<h3>
    <a href="${pageContext.request.contextPath}/admin/users/all"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 1.5vw">All users</a>
    <br> <br>
    <a href="${pageContext.request.contextPath}/admin/products"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 1.5vw">All products</a>
    <br> <br>
    <a href="${pageContext.request.contextPath}/admin/products/add"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 1.5vw">Add products</a>
    <br> <br>
    <a href="${pageContext.request.contextPath}/admin/orders/all"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 1.5vw">All orders</a>
    <br> <br>
</h3>
</body>
</html>
