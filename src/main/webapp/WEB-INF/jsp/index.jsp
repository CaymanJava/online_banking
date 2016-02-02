<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <form method="post" action="users">
    login:
        <select name="userId">
    <option value="1" selected>Admin</option>
    <option value="2">User 1</option>
    <option value="3">User 2</option>
        </select>
    <button type="submit">select</button>
</form>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="accounts">Accounts</a></li>
</ul>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
