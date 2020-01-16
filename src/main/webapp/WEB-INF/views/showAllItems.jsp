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
    <form method="Post"
<c:forEach var="item" items="${items}">
    <tr>
        <th><c:out value="${item.getName()}"/></th>
        <th><c:out value="${item.getPrice()}"/></th>
        <th><a href="${pageContext.request.contextPath}/Servlet/addToBucket?itemId=${item.getId()}">
            Add to bucket
        </a></th>
        <th><a href="${pageContext.request.contextPath}/Servlet/deleteItem?itemId=${item.getId()}">
            Remove Item
        </a>
        </th>
    </tr>
</c:forEach>
</table>
</body>
</html>
