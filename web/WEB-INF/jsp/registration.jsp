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
<form class="form-horizontal">
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Name:
                <div class="col-sm-10">
                    <input type="text" name="name" id="name">
                </div>
            </label><br>
        </div>
        <div class="form-group">
            <label for="phoneNumber" class="col-sm-2 control-label">Phone Number:
                <div class="col-sm-10">
                    <input type="text" name="phoneNumber" id="phoneNumber">
                </div>
            </label><br>
        </div>
        <div class="form-group">
            <label for="addressId" class="col-sm-2 control-label">Address:
                <div class="col-sm-10">
                    <input type="text" name="address" id="addressId">
                </div>
            </label><br>
        </div>
        <div class="form-group">
            <label for="emailId" class="col-sm-2 control-label">Email:
                <div class="col-sm-10">
                    <input type="text" name="email" id="emailId">
                </div>
            </label><br>
        </div>
        <div class="form-group">
            <label for="passwordId" class="col-sm-2 control-label">Password:
                <div class="col-sm-10">
                    <input type="password" name="password" id="passwordId">
                </div>
            </label><br>
        </div>
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select><br>
        <button type="submit" class="btn btn-info btn-lg">Send</button>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}<br></span>
                </c:forEach>
            </div>
        </c:if>
    </form>
</form>
</body>
</html>
