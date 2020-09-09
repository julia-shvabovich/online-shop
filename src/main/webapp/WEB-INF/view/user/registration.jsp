<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Please provide your user details</h1>

<h4 style="color:red">${message}</h4>

<form method="post" action="${pageContext.request.contextPath}/registration">
    Name <input type="text" name="name" required>
    Login <input type="text" name="login" required>
    Password <input type="password" name="password" required>
    Repeat your password <input type="password" name="repeat">
    <button type="submit">Register</button>
</form>
</body>
</html>
