<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is login page</title>
</head>
<body>
<div>
    ${errorMsg}
</div>
<div>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="name">Enter your email address</label>
        <input type="text" id="name" name="login">

        <label for="password">Enter your password</label>
        <input type="password" id="password" name="password">

        <button type="submit" name="subBtn">Login</button>
    </form>
    <p>Don't have an account,<a href="${pageContext.request.contextPath}/registration"> Sign Up</a></p>
</div>
<a href="${pageContext.request.contextPath}/Servlet/index">Back to main menu</a>
</body>
</html>
