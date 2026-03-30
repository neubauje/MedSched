<%@ include file = "common/header.jspf" %>
<body class="HomePage">
<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="POST">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="Username">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <button type="submit" class="btn btn-default">Login</button>
</form>
</body>
<%@ include file = "common/footer.jspf" %>