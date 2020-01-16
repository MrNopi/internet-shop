<%--
  Created by IntelliJ IDEA.
  User: MrNopi
  Date: 15.01.2020
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <jsp:useBean id="orders" scope="request" type="java.util.List"/>

    <html>
    <head>
        <title>Items</title>
    </head>
<body>
<h1>Items: </h1>
<table border="1">
    <tr>
        <th>id</th>
        <th>Items</th>
        <th></th>
    </tr>
    <form method="Post"
    <c:forEach var="order" items="${orders}">
        <tr>
            <th><c:out value="${order.getUserId()}"/></th>
            <th><c:out value="${order.getItems()}"/></th>
            <th><a href="${pageContext.request.contextPath}/Servlet/RemoveOrder?itemId=${order.getId()}">
                Cancel Order
            </a>
            </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>

</head>
<body>

</body>
</html>
