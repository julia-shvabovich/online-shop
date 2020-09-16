<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>
<h1>Please provide your user details</h1>
<form style="padding: 1%" method="post" action="${pageContext.request.contextPath}/login">
    <div class="form-group">
        <label for="log-name">Name</label>
        <input type="text" class="form-control" name="name" id="log-name" required>
    </div>
    <div class="form-group">
        <label for="log-login">Login</label>
        <input type="text" class="form-control" name="login" id="log-login" required>
    </div>
    <div class="form-group">
        <label for="log-password">Password</label>
        <input type="password" class="form-control" name="password" id="log-password" required>
    </div>
    <h4 style="color:red; text-align: left">${message}</h4>
    <button onclick="location.href='${pageContext.request.contextPath}/'" type="submit" class="btn btn-outline-danger"
            style="font-size:1.5vw">Log in
    </button>
</form>
</body>
</html>
