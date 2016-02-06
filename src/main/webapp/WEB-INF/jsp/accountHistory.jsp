<%@ page import="com.cayman.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <%--http://stackoverflow.com/questions/10327390/how-should-i-get-root-folder-path-in-jsp-page--%>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <h3>AccountHistory</h3>
        <form method="post" action="/accounts/history/filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value=""></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value=""></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value=""></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value=""></dd>
        </dl>
        options:
        <select name="option">
            <option value="all" selected>All</option>
            <option value="debit">Debit</option>
            <option value="credit">Credit</option>
        </select>
        <button type="submit">Filter</button>
    <%--</form>--%>
        <hr>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Type</th>
            <th>Your account</th>
            <th>Contract's account</th>
            <th>Amount</th>
            <th>Balance</th>
            <th>Commission</th>
            <th>Operation time</th>
            <th>Comment</th>
        </tr>
        </thead>
        <c:forEach items="${accountHistory}" var="history">
            <jsp:useBean id="history" scope="page" type="com.cayman.dto.AccountHistoryTransferObject"/>
            <input type="hidden" name="id" value="${history.accountId}">
            <tr align="center" class="${history.debit ? 'debit' : 'credit'}">
                <td>${history.debit ? "Debit" : "Credit"}</td>
                <td>${history.userAccountNumber}</td>
                <td><%= history.getContractorAccountNumber().equals(" ") ? "from outside" : history.getContractorAccountNumber()%></td>
                <td>${history.amount} ${history.currency}</td>
                <td>${history.amountAfterOperation} ${history.currency}</td>
                <td>${history.commission} ${history.currency}</td>
                <td><%=TimeUtil.toString(history.getOperationTime())%></td>
                <td><%= history.getComment().equals(" ") ? "-" : history.getComment()%></td>
            </tr>
        </c:forEach>
        <input action="action" type="button" value="Back" onclick="history.go(-1);" />
    </table>
        </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
