<%--@elvariable id="services" type="com.sun.org.apache.xml.internal.security.signature.Manifest"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="${pageContext.request.contextPath}/createOrder?serviceId=${requestScope.serviceId}" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="serviceId" value="${requestScope.serviceId}"/>
    <label for="file">File:
        <input type="file" name="file" id="file">
    </label><br>
    <select name="paperType" id="paperType">
        <c:forEach var="paperType" items="${requestScope.paperTypes}">
            <option value="${paperType}">${paperType}</option>
        </c:forEach>
    </select><br>
    <label for="quantity">Quantity:
        <input type="number" name="quantity" id="quantity">
    </label><br>
    <c:forEach var="delivery" items="${requestScope.deliveries}">
        <input type="radio" name="delivery" value="${delivery}"> ${delivery}
        <br>
    </c:forEach>
    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}<br></span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
