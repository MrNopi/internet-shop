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
<div>
    <h1>User deleting</h1>
    <form action="${pageContext.request.contextPath}/delete">
        <p3>Enter user id to remove</p3>
        <input type="number" name="id">
        <button>Delete</button>
    </form>
</div>
<div>
    <h1>Item Adding</h1>
    <form action="${pageContext.request.contextPath}/createItem" method="post">
        <input type="text" name="itemName">
        <input type="text" name="itemPrice">
        <button type="submit">Create Item</button>
    </form>
</div>
<form action="${pageContext.request.contextPath}/Items">
<button type="submit">Show all items</button>
<button type="button"><a href="${pageContext.request.contextPath}/Users">Show All Users </a></button>
</form>

</body>
</html>