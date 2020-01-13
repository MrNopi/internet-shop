<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <th><c:out value="${order.getItems()}"/></th>
        </tr>
        <th><a href="${pageContext.request.contextPath}/deleteOrder?orderId=${order.getId()}">Remove order</a></th>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/index">Back to the main page</a>
</table>
</body>
</html>
