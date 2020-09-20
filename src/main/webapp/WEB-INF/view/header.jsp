<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script crossorigin="anonymous"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
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
            <a href="${pageContext.request.contextPath}/"
               class="btn btn-outline-danger" role="button" aria-pressed="true"
               style="font-size: 1.5vw">Home</a>
        </li>
        <li class="nav-item active" style="position: absolute; right: 180px; top: 15px">
            <a href="${pageContext.request.contextPath}/registration"
               class="btn btn-outline-danger" role="button" aria-pressed="true"
               style="font-size: 1.5vw">Sign up</a>
        </li>
        <li class="nav-item active" style="position: absolute; right: 30px; top: 15px">
            <a href="${pageContext.request.contextPath}/cart/products"
               class="btn btn-outline-danger" role="button" aria-pressed="true"
               style="font-size: 1.5vw">Basket</a>
        </li>
    </ul>
</div>
</body>
</html>
