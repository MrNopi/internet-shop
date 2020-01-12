<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="items" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Items</title>
</head>
<body>
<h1>Items: </h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add</th>
    </tr>
<c:forEach var="item" items="${items}">
    <tr>
        <th><c:out value="${item.getName()}"/></th>
        <th><c:out value="${item.getPrice()}"/></th>
        <th><a href="${pageContext.request.contextPath}/createItem">
            <c:import url="/createItem" var="item"/>
            Add item to bucket
        </a></th>
    </tr>
</c:forEach>
</table>
</body>
</html>
