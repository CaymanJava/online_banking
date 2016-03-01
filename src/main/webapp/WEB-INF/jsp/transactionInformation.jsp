<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" type="text/css" href="resources/css/transaction.css" />
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
    <h2>_</h2>
    <h3 align="center"><spring:message code="transaction.information.title"/></h3>

    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <div class="view-box">
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

                    <table class="table3" align="center">
                        <tbody>
                        <tr>
                            <td scope="row" class="firstRowLeft"><spring:message code="transaction.from"/></td>
                            <td class="firstRowRight"><c:out value="${dto.senderAccount.accountNumber}"/></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="transaction.to"/></td>
                            <td><c:out value="${dto.recipientAccount.accountNumber}"/></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="transfer.amount"/></td>
                            <td><c:out value="${dto.amount}"/> <c:out value="${dto.senderAccount.currency}"/></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="receive.amount"/></td>
                            <td><c:out value="${dto.amountInRecipientCurrency}"/> <c:out value="${dto.recipientAccount.currency}"/></td>
                        </tr>
                        <tr>
                            <td scope="row" class="lastRowLeft"><spring:message code="transaction.comment"/></td>
                            <td><c:out value="${dto.comment}"/></td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td scope="row"><spring:message code="transaction.commission"/></td>
                            <td><c:out value="${dto.commission}"/> <c:out value="${dto.senderAccount.currency}"/></td>
                        </tr>
                        </tfoot>
                    </table>
                    <br/>
                    <div align="center">
                        <input action="action" type="button" value="<spring:message code="back"/>" onclick="history.go(-1);" class="btn btn-lg btn-info" />
                        <button type="submit" class="btn btn-lg btn-success"><spring:message code="confirm"/></button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
