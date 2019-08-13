<%--
  Created by IntelliJ IDEA.
  User: i.gavrilov
  Date: 26.06.2019
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3>Login Page</h3>
    <p style="color: red;">${errorString}</p>

    <form method="POST" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <table border="0">
            <tr>
                <td>User Name</td>
                <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>

            <tr>
                <td colspan ="2">
                    <input type="submit" value= "Submit" />
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
