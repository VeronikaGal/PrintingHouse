<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css"
          integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
</head>
<body>
<%@include file="header.jsp" %>
<c:choose>
    <c:when test="${sessionScope.user.role.name()=='ADMIN'}">
        <h1>Список услуг:</h1>
        <ul>
            <c:forEach var="service" items="${requestScope.services}">
                <li>
                    <img width="250" height="200" src="${pageContext.request.contextPath}/images/${service.image}"
                         alt="C:\Users\User\imagePH\stickers.jfif" class="img-circle"><br>
                    <p class="bg-info">
                        <a href="${pageContext.request.contextPath}/orders?serviceId=${service.id}">${service.description}</a>
                    </p>
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:when test="${sessionScope.user.role.name() == 'USER'}">
        <h1>Список услуг:</h1>
        <ul>
            <c:forEach var="service" items="${requestScope.services}">
                <li>
                    <img width="250" height="200" src="${pageContext.request.contextPath}/images/${service.image}"
                         alt="C:\Users\User\imagePH\stickers.jfif" class="img-circle"><br>
                    <a href="${pageContext.request.contextPath}/createOrder?serviceId=${service.id}">${service.description}</a>
                </li>
            </c:forEach>
        </ul>
    </c:when>
</c:choose>

<div id="report">
    <form action="${pageContext.request.contextPath}/report" method="get">
        <a href="${pageContext.request.contextPath}/report">
            <button type="button" class="btn btn-info btn-lg">Report</button>
        </a>
    </form>
</div>
</body>
</html>
