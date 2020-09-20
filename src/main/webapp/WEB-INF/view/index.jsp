<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1 style="font-family: Kristi; font-size:4vw; padding-bottom: 10px">Handmade accessories: delivery all over the
    world!</h1>
<table style="position: center">
    <tbody>
    <tr>
        <th style="width: 33%; height: inherit" id="2" class="carousel slide" data-ride="carousel"
            data-interval="7500" data-pause="false">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://i.imgur.com/lKF8ufD.png" class="d-block w-100"
                         alt="blue spiky pendant">
                </div>
                <div style="height: inherit" class="carousel-item">
                    <img src="https://i.imgur.com/BvkLWXo.png" class="d-block w-100"
                         alt="blue spiky pendant">
                </div>
            </div>
        </th>
        <th style="width: 33%; height: inherit" id="1" class="carousel slide" data-ride="carousel"
            data-interval="7500" data-pause="false">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://i.imgur.com/VucMHQz.png" class="d-block w-100"
                         alt="golden moon brooch">
                </div>
                <div class="carousel-item">
                    <img src="https://i.imgur.com/HkqU8Bu.png" class="d-block w-100"
                         alt="golden moon brooch">
                </div>
            </div>
        </th>
        <th style="width: 33%; height: inherit" id="3" class="carousel slide" data-ride="carousel"
            data-interval="7500" data-pause="false">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://i.imgur.com/Dzbm6tO.png" class="d-block w-100"
                         alt="pink jellyfish brooch">
                </div>
                <div class="carousel-item">
                    <img src="https://i.imgur.com/6KvySis.png" class="d-block w-100"
                         alt="pink jellyfish brooch">
                </div>
            </div>
        </th>
    </tr>
    </tbody>
</table>
<h3 style="text-align: center">
    <a href="${pageContext.request.contextPath}/products/all"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 2vw">See more</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/logout"
       class="btn btn-outline-danger" role="button" aria-pressed="true"
       style="font-size: 2vw">Log out</a>
</h3>
</body>
</html>
