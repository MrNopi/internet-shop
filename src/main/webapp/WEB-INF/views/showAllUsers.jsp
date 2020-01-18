<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="users" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Items: </h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <th><c:out value="${user.getId()}"/></th>
            <th><c:out value="${user.getLogin()}"/></th>
            <th><a href="${pageContext.request.contextPath}/Servlet/deleteUser?userId=${user.getId()}">Delete</a></th>
        </tr>

    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/Servlet/index">Back to the main</a>
</body>
</html>
