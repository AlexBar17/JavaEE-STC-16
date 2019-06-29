<%@ page import="ru.inno.stc14.entity.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Пользователи</title>
    <meta charset="UTF-8"/>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Pass</th>
    </tr>
    <% List<Person> list = (List<Person>) request.getAttribute("persons");
        for (Person person : list) { %>
    <tr>
        <td><%=person.getId()%></td>
        <td><%=person.getLogin()%></td>
        <td><%=person.getPass()%></td>
    </tr>
    <br>
    <% } %>
</table>
<br>
<a href="/students_war">Main page</a>
</body>
</html>
