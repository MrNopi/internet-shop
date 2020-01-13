<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Items: </h1>
<form action="${pageContext.request.contextPath}/createItem">
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <th><c:out value="${user.getId()}"/></th>
            <th><c:out value="${user.getName()}"/></th>
            <th><button type="submit">Add item to bucket</button></th>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>
