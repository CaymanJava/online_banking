<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <h3>User Settings</h3>

    <jsp:useBean id="user" scope="request" type="com.cayman.entity.User"/>

    <form method="post" action="admin/users/settings/edit">
        <input type="hidden" name="userId" value="${user.id}">
        <dl>
            <dt>Login:</dt>
            <dd><input type="text" value="${user.login}" name="login"></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="text" value="${user.password}" name="password"></dd>
        </dl>
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" value="${user.firstName}" name="firstName"></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" value="${user.lastName}" name="lastName"></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${user.email}" name="email"></dd>
        </dl>
        <dl>
            <dt>Registered:</dt>
            <dd><c:out value="${user.registered}"/></dd>
        </dl>
        <dl>
            Ban <input type="checkbox" value="true" name="enabled">
        </dl>
        <dl>
            Admin <input type="checkbox" value="true" name="admin">
        </dl>
        <button type="submit">Save</button>
        <input action="action" type="button" value="Cancel" onclick="history.go(-1);" />
    </form>
<h3>User's accounts</h3>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Account Number</th>
            <th>Balance</th>
            <th>Currency</th>
            <th>Enabled</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${accountList}" var="account">
            <jsp:useBean id="account" scope="page" type="com.cayman.entity.Account"/>
            <tr>
                <td>${account.name}</td>
                <td>${account.accountNumber}</td>
                <td>${account.balance}</td>
                <td>${account.currency}</td>
                <td>${account.enable}</td>
                <td><a href="/admin/users/settings/history?accountId=${account.id}&userId=${account.user.id}">History</a></td>
                <td><a href="/admin/users/settings/block?accountId=${account.id}&userId=${account.user.id}">Block</a></td>
                <td><a href="/admin/users/settings/unBlock?accountId=${account.id}&userId=${account.user.id}">Unblock</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
