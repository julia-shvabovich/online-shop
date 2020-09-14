<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<%@include file="../header-admin.jsp" %>
<table border="1">
    <tr>
        <th><h4>ID</h4></th>
        <th><h4>Name</h4></th>
        <th><h4>Login</h4></th>
        <th><h4>Delete</h4></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <h4><c:out value="${user.id}"/></h4>
            </td>
            <td>
                <h4><c:out value="${user.name}"/></h4>
            </td>
            <td>
                <h4><c:out value="${user.login}"/></h4>
            </td>
            <td>
                <h4><a href="${pageContext.request.contextPath}/admin/user/delete?id=${user.id}">Delete</a></h4>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
