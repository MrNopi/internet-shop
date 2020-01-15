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
<div>
    <a href="${pageContext.request.contextPath}/login">Already have an account? Sign in!</a>
</div>
</body>
</html>
