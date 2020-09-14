<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h1>Please provide your user details</h1>
<h4 style="color:red">${message}</h4>
<form style="padding-left: 30px" method="post" action="${pageContext.request.contextPath}/login">
    Name <input type="text" name="name" required>
    <br><br>
    Login <input type="text" name="login" required>
    <br><br>
    Password <input type="password" name="password" required>
    <br><br>
    <button type="submit">Log in</button>
    <br><br>
</form>
</body>
</html>
