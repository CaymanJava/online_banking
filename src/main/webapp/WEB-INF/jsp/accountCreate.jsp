<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2><a href="">Home</a></h2>
    <h3>Account Create</h3>
    <hr>
    <jsp:useBean id="account" type="com.cayman.entity.Account" scope="request"/>
    <form method="post" action="accounts">
        <input type="hidden" name="id" value="${account.id}">
        <input type="hidden" name="accountNumber" value="${account.accountNumber}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${account.name}" name="name"></dd>
        </dl>
        <dl>
            <dt>Currency:</dt>
            <dd><select name="currency">
                <option value="EUR" selected>EUR</option>
                <option value="USD">USD</option>
                <option value="PLN">PLN</option>
                <option value="UAH">UAH</option>
                <option value="RUB">RUB</option>
            </select></dd>
        </dl>
        <input type="hidden" name="balance" value="${account.balance}">
        <dl>
            <dt>Balance:</dt>
            <dd><c:out value="${account.balance}"/></dd>
        </dl>


        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
