<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update product list</title>
</head>
<body>
<%@include file="../header-admin.jsp"%>
<form style="padding-left: 30px; padding-top: 10px" method="post" action="${pageContext.request.contextPath}/admin/products/add">
    Name <input type="text" name="name" required>
    <br><br>
    Price <input type="text" name="price" required>
    <br><br>
    <button type="submit">Add</button>
</form>
<a href="${pageContext.request.contextPath}/admin/products"><h3>List of all products</h3></a><br>
</body>
</html>
