<%@ page import="com.cayman.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3>_</h3>
    <h3 align="center"><spring:message code="account.history.title"/></h3>
        <div class="jumbotron">
            <div class="container">
                <div class="shadow">
                    <div class="view-box">
                        <form method="post" class="form-horizontal" role="form"  id="filter" <%--action="admin/users/settings/history/filter"--%>action="admin/users/settings/history">
                            <input type="hidden" name="userId" value="${userId}">
                            <input type="hidden" name="accountId" value="${accountId}">
                            <div class="form-group" align="right">
                                <label class="control-label col-sm-3" for="startDate"><spring:message code="from.date"/></label>

                                <div class="col-sm-2" align="right">
                                    <input name="startDate" id="startDate" class="date-picker">
                                </div>

                                <label class="control-label col-sm-3" for="endDate"><spring:message code="to.date"/></label>

                                <div class="col-sm-2" align="right">
                                    <input name="endDate" id="endDate" class="date-picker">
                                </div>
                            </div>
                            <div class="form-group" align="right">
                                <label class="control-label col-sm-3" for="startTime"><spring:message code="from.time"/></label>

                                <div class="col-sm-2" align="right">
                                    <input name="startTime" id="startTime" class="time-picker">
                                </div>

                                <label class="control-label col-sm-3" for="endTime"><spring:message code="to.time"/></label>

                                <div class="col-sm-2" align="right">
                                    <input name="endTime" id="endTime" class="time-picker">
                                </div>
                            </div>

                            <div class="form-group" align="center">
                                <div class="col-sm-12" align="center">
                                    <spring:message code="option"/>
                                    <select name="option">
                                        <option value="all" selected><spring:message code="all"/></option>
                                        <option value="debit"><spring:message code="debit"/></option>
                                        <option value="credit"><spring:message code="credit"/></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group" align="center">
                                <div class="col-sm-12" align="center">
                                    <button align="center" type="submit" class="btn btn-primary pull-center"><spring:message code="filter"/></button>
                                </div>
                            </div>
                        </form>
    <table align="center" border="0" cellpadding="8" cellspacing="0" class="table table-striped display" id="accountHistory">
        <thead>
        <tr>
            <th><spring:message code="operation.type"/></th>
            <th><spring:message code="your.account"/></th>
            <th><spring:message code="contracts.account"/></th>
            <th><spring:message code="amount"/></th>
            <th><spring:message code="balance"/></th>
            <th><spring:message code="commission"/></th>
            <th><spring:message code="operation.type"/></th>
            <th><spring:message code="comment"/></th>
        </tr>
        </thead>
        <c:forEach items="${accountHistory}" var="history">
            <jsp:useBean id="history" scope="page" type="com.cayman.dto.AccountHistoryTransferObject"/>
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
                    </div>
                </div>
            </div>
        </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script>
    activateFilterScripts();
</script>
</html>
