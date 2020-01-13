<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bucket" scope="request" type="java.util.List"/>
<jsp:useBean id="user" scope="request" type="mate.academy.internetshop.models.User"/>
<%--
  Created by IntelliJ IDEA.
  User: MrNopi
  Date: 12.01.2020
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket for ${user.name}</title>
</head>
<body>
<table border="1">
<c:forEach var="item" items="${bucket}">
    <tr>
        <th><c:out value="${item.getName()}"/></th>
        <th><c:out value="${item.getPrice()}"/></th>
        <th><a href="${pageContext.request.contextPath}/deleteFromBucket?item=${item.getId()}">
            Remove From Bucket
        </a></th>
    </tr>
</c:forEach>
</table>
<a href="${pageContext.request.contextPath}/index">Back to the main page</a>
</body>
</html>