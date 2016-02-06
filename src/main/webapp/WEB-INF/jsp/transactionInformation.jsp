<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2><a href="">Home</a></h2>
    <h3>Transaction Information</h3>
    <hr>
    <jsp:useBean id="dto" type="com.cayman.dto.TransactionTransferObject" scope="request"/>
    <form method="post" action="/accounts/sendMoney">
        <input type="hidden" name="senderId" value="${dto.senderAccount.user.id}">
        <input type="hidden" name="senderAccountId" value="${dto.senderAccount.id}">

        <input type="hidden" name="recipientId" value="${dto.recipientAccount.user.id}">
        <input type="hidden" name="recipientAccountId" value="${dto.recipientAccount.id}">

        <input type="hidden" name="commissionCurrency" value="${dto.senderAccount.currency}">

        <input type="hidden" name="transferAmount" value="${dto.amount}">
        <input type="hidden" name="commission" value="${dto.commission}">
        <input type="hidden" name="amountForReceive" value="${dto.amountInRecipientCurrency}">
        <input type="hidden" name="comment" value="${dto.comment}">

        <dl>
            <dt>Your account:</dt>
            <dd><c:out value="${dto.senderAccount.accountNumber}"/></dd>
        </dl>

        <dl>
            <dt>Recipient account:</dt>
            <dd><c:out value="${dto.recipientAccount.accountNumber}"/></dd>
        </dl>

        <dl>
            <dt>Transfer amount:</dt>
            <dd><c:out value="${dto.amount}"/> <c:out value="${dto.senderAccount.currency}"/></dd>
        </dl>

        <dl>
            <dt>Commission:</dt>
            <dd><c:out value="${dto.commission}"/> <c:out value="${dto.senderAccount.currency}"/></dd>
        </dl>

        <dl>
            <dt>Amount for receive:</dt>
            <dd><c:out value="${dto.amountInRecipientCurrency}"/> <c:out value="${dto.recipientAccount.currency}"/></dd>
        </dl>

        <dl>
            <dt>Comment:</dt>
            <dd><c:out value="${dto.comment}"/></dd>
        </dl>

        <button type="submit">Confirm</button>
        <%--<button onclick="window.history.back()">Cancel</button>--%>
        <input action="action" type="button" value="Back" onclick="history.go(-1);" />
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
