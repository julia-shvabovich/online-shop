<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h1>Please provide your user details</h1>
<form style="padding: 1%" method="post" action="${pageContext.request.contextPath}/registration">
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" name="name" id="name" required>
    </div>
    <div class="form-group">
        <label for="login">Login</label>
        <input type="text" class="form-control" name="login" id="login" required>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" id="password" required>
    </div>
    <div class="form-group">
        <label for="repeat">Repeat your password</label>
        <input type="password" class="form-control" name="repeat" id="repeat" required>
    </div>
    <h4 style="color:red; text-align: left">${message}</h4>
    <button onclick="location.href='${pageContext.request.contextPath}/'" type="submit" class="btn btn-outline-danger"
       style="font-size:1.5vw">Register</button>
</form>
</body>
</html>
