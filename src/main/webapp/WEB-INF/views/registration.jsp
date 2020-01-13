<%--
  Created by IntelliJ IDEA.
  User: MrNopi
  Date: 11.01.2020
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration page</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name">Enter your email address</label>
        <input type="text" id="name" name="name">

    <label for="surname">Enter your surname</label>
        <input type="text" id="surname" name="surname">

        <label for="password">Enter your password</label>
        <input type="password" id="password" name="password">

        <label for="password-repeat">Enter your password again</label>
        <input type="password" id="password-repeat">

    <button type="submit" name="subBtn">Register</button>

    </form>
</div>
</body>
</html>
