<%--Используем JSTL fmt--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<h2>Users table</h2>

<%--Представляет отображение списка юзеров в табличном виде--%>
<table>
<c:forEach items="${model}" var="user">
   <tr>
      <td><a href='showuser?id=${user.name}'>${user.name}</a></td>
      <td>${user.phone}</td>
      <td>${user.email}</td>
   </tr>
</c:forEach>
</table>

</body>
</html>