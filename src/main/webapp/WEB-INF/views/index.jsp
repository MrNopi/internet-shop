<html>
<head>
    <title>Hello mates</title>
    <style>
        h1 {
            text-align: center;
            size: 10px;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/registration">Registration</a>
<div>
    <h1>Item Adding</h1>
    <form action="${pageContext.request.contextPath}/Servlet/createItem" method="post">
        <input type="text" name="itemName">
        <input type="text" name="itemPrice">
        <button type="submit">Create Item</button>
    </form>
</div>
<form action="${pageContext.request.contextPath}/Servlet/Items">
<button type="submit">Show all items</button>
<button type="button"><a href="${pageContext.request.contextPath}/Servlet/Users">Show All Users </a></button>
</form>
<a href="${pageContext.request.contextPath}/Servlet/ShowBucketItems">My Bucket</a>
</body>
</html>
