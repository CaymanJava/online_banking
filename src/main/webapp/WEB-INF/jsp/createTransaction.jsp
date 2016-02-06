<%--<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>

    <h3>Send Money</h3>
    <jsp:useBean id="account" type="com.cayman.entity.Account" scope="request"/>
   <%-- <form method="get" action="/accounts/getForAdding">
    </form>--%>
    <form method="post" action="/accounts/createTransaction">
        <input type="hidden" name="id" value="${account.id}">
        <dl>
            <dt>Account number to transfer:</dt>
            <dd><input type="text" name="accountNumber"></dd>
        </dl>

        <dl>
            <dt>Value:</dt>
            <dd><input type="number" name="value"></dd>
        </dl>

        <dl>
            <dt>Comment:</dt>
            <dd><input type="text" name="comment"></dd>
        </dl>

        <button type="submit">Confirm</button>
       <%-- <button onclick="window.history.back()">Cancel</button>--%>
        <input action="action" type="button" value="Cancel" onclick="history.go(-1);" />
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
