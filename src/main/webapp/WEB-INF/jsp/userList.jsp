<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3>UserList</h3>

    <form method="post" action="users">
        login: <select name="userId">
        <option value="1" selected>Admin</option>
        <option value="2">User 1</option>
        <option value="3">User 2</option>
    </select>
        <button type="submit">select</button>
    </form>

    <li><a href="users">Get All</a></li>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>login</th>
            <th>password</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
            <th>registered</th>
            <th>Active</th>
            <th>Roles</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" scope="page" type="com.cayman.entity.User"/>
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                <td><%=user.isEnabled()%>
                <td>${user.roles}</td>
                <td><a href="/users/update?id=${user.id}">Update</a></td>
                <td><a href="/users/delete?id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
