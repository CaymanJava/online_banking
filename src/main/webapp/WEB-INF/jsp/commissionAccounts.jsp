<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3>_</h3>
<h3 align="center"><spring:message code="commission.account.title"/></h3>

    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <div class="view-box">
    <table text-align="center" border="0" cellpadding="8" cellspacing="0" class="table table-striped display">
        <thead>
        <tr>
            <th><spring:message code="account.name"/></th>
            <th><spring:message code="account.number"/></th>
            <th><spring:message code="balance"/></th>
            <th><spring:message code="currency"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${commissionAccounts}" var="account">
            <jsp:useBean id="account" scope="page" type="com.cayman.entity.Account"/>
            <tr>
                <td>${account.name}</td>
                <td>${account.accountNumber}</td>
                <td>${account.balance}</td>
                <td>${account.currency}</td>
                <td><a href="admin/commission/history?accountId=${account.id}" class="btn btn-sm btn-primary"><spring:message code="history"/></a></td>
            </tr>
        </c:forEach>
    </table>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
