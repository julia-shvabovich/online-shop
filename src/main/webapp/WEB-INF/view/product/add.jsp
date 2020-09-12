<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update product list</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/products/add">
    Name <input type="text" name="name" required>
    Price <input type="text" name="price" required>
    <button type="submit">Add</button>
</form>
<a href="${pageContext.request.contextPath}/products/admin">All products</a><br>
<a href="${pageContext.request.contextPath}/admin">Main page</a><br>
</body>
</html>
