<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Birthdate</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${persons}">
        <tr>
            <td scope="row">${person.id}</td>
            <td>${person.name}</td>
            <td>${person.birthDate}</td>
            <td>${person.email}</td>
            <td>${person.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<a href="/">Main page</a>
