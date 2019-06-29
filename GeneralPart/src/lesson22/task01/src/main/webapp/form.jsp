<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
</head>
<body>
<h1>Sign in</h1>
<form method="post" action="${pageContext.request.contextPath}/person">
    <input type="text" name="login" placeholder="login"><br/>
    <input type="text" name="pass" placeholder="pass"><br/>
    <input type="submit"/>
</form>

</body>
</html>