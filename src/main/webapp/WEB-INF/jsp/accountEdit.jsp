<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2><a href="">Home</a></h2>
    <h3>Account Edit</h3>
    <hr>
    <jsp:useBean id="account" type="com.cayman.entity.Account" scope="request"/>


    <form method="post" action="accounts">

      <%--  <dl>
            <a href="accounts/update/delete?id=${account.id}">Delete</a>
        </dl>

        <dl>
            <a href="accounts/getForAdding?id=${account.id}">Put Money</a>
        </dl>

        <dl>
            <a href="accounts/getForSend?id=${account.id}">Send Money</a>
        </dl>--%>
          <a href="accounts/menu/delete?id=${account.id}">Delete</a>
          <a href="accounts/getForAdding?id=${account.id}">Put Money</a>
          <a href="accounts/getForSend?id=${account.id}">Send Money</a>

        <input type="hidden" name="id" value="${account.id}">
        <input type="hidden" name="accountNumber" value="${account.accountNumber}">
          <dl>
              <dt>Status:</dt>
              <dd class="${account.enable ? 'enable' : 'disable'}"><c:out value="${account.enable ? 'active' : 'blocked'}"/></dd>
          </dl>

          <dl>
              <dt>Name:</dt>
              <dd><input type="text" value="${account.name}" name="name"></dd>
          </dl>
          <dl>

              <dt>Currency:</dt>
              <dd><c:out value="${account.currency}"/></dd>
          </dl>
        <input type="hidden" name="currency" value="${account.currency}">
        <dl>
            <dt>Balance:</dt>
            <dd><c:out value="${account.balance}"/></dd>
        </dl>
        <input type="hidden" name="balance" value="${account.balance}">

        <button type="submit">Save</button>
        <input action="action" type="button" value="Cancel" onclick="history.go(-1);" />
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
