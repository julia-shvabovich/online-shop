<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        body {
            font-family: "Segoe UI";
            font-size: 20px;
            background-color: #ebebeb;
        }
        .header {
            padding: 50px;
            text-align: center;
            background: url("https://i.imgur.com/YYHIc5O.png") no-repeat;
            background-size: cover;
            font-size: 30px;
        }
        a {
            color: #1d2124;
        }
        h1, h2 {
            font-weight: lighter;
            padding-top: 30px;
            color: #1d2124;
            text-align: center;
        }
        h3, h5, h6 {
            font-weight: lighter;
            padding: 30px;
            color: #1d2124;
        }
        h4 {
            font-weight: lighter;
            padding: 10px;
            color: #1d2124;
            text-align: center;
        }
        button {
            font-weight: lighter;
            color: #1d2124;
        }
    </style>
</head>
<body>
<div class="header">
    <ul class="navbar-nav" style="color: Background">
        <li class="nav-item active" style="position: absolute; left: 30px; top: 15px">
            <a href="${pageContext.request.contextPath}/admin"
               class="btn btn-outline-danger" role="button" aria-pressed="true"
               style="font-size: 1.5vw">Home</a>        </li>
    </ul>
</div>
</body>
</html>
