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
<h3>_</h3>
<h3 align="center"><spring:message code="account.title"/></h3>
        <div class="jumbotron">
            <div class="container">
                <div class="shadow">
                    <div class="view-box">
                        <a class="btn btn-sm btn-info" id="addAccount"><spring:message code="add.account"/></a>
                        <table border="0" cellpadding="8" cellspacing="0" id="accountList" class="table table-striped display">
        <thead>
        <tr>
            <th><spring:message code="account.name"/></th>
            <th><spring:message code="account.number"/></th>
            <th><spring:message code="account.balance"/></th>
            <th><spring:message code="currency"/></th>
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
                <td><a accountId="${account.id}" class="${account.enable ? 'btn btn-sm btn-warning menu' : 'btn btn-sm btn-danger'}">${account.enable ? 'Menu' : 'Blocked'}</a></td>
            </tr>
        </c:forEach>
    </table>
                    </div>
                </div>
            </div>
        </div>

<div class="modal fade" id="createAccount">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="add.account"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="saveAccount" action="accounts">
                    <input type="text" hidden="hidden" id="id" name="id">
                    <input type="text" hidden="hidden" id="balance" name="balance">
                    <input type="text" hidden="hidden" id="accountNumber" name="accountNumber">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="account.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" value="<spring:message code="my.new.account"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3"><spring:message code="currency"/></label>
                        <div class="col-xs-9">
                            <select name="currency" id="currency">
                                <option value="EUR" selected>EUR</option>
                                <option value="USD">USD</option>
                                <option value="PLN">PLN</option>
                                <option value="UAH">UAH</option>
                                <option value="RUB">RUB</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="back"/></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="accountMenu">
    <div class="modal-dialog">
        <div class="modal-content">
            <h3 class="modal-title"></h3>
            <div class="modal-body" align="center">
                    <input type="text" hidden="hidden" id="currentAccountId" name="currentAccountId" value="">
                    <a href="accounts/history?accountId=" class="btn btn-sm btn-primary history" id="history"><spring:message code="history"/></a>
                    <a class="btn btn-sm btn-info" id="rename"><spring:message code="rename"/></a>
                    <a class="btn btn-sm btn-warning" id="putMoney"><spring:message code="put.money"/></a>
                    <a class="btn btn-sm btn-success" id="sendMoney"><spring:message code="send.money"/></a>
                    <a href="accounts/menu/delete?accountId=" class="btn btn-sm btn-danger" id="deleteAccount"><spring:message code="delete"/></a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="renameMenu">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="rename"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="renameAccount" action="accounts/edit">
                    <input type="text" hidden="hidden" id="renameAccountId" name="renameAccountId" value="">
                    <div class="form-group">
                    <label for="newName" class="control-label col-xs-3"><spring:message code="account.name"/></label>

                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="newName" name="newName" placeholder="<spring:message code="type.account.name"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <button type="button" class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="back"/></button>
                        <button type="submit" class="btn btn-primary"><spring:message code="save"/></button>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="putMoneyMenu">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="put.money"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="putMoneyForm" action="accounts/putMoney">
                    <input type="text" hidden="hidden" id="putMoneyAccountId" name="putMoneyAccountId" value="">
                    <div class="form-group">
                        <label for="newName" class="control-label col-xs-3"><spring:message code="value"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="value" name="value" placeholder="<spring:message code="how.much.put"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="back"/></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="save"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="sendMoneyMenu">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="send.money"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="sendMoneyForm" action="accounts/createTransaction">
                    <input type="text" hidden="hidden" id="sendMoneyAccountId" name="sendMoneyAccountId" value="">
                    <div class="form-group">
                        <label for="recipientAccountNumber" class="control-label col-xs-3"><spring:message code="number.to.transfer"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="recipientAccountNumber" name="recipientAccountNumber" placeholder="<spring:message code="type.number.to.transfer"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="valueToTransfer" class="control-label col-xs-3"><spring:message code="value"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="valueToTransfer" name="valueToTransfer" placeholder="<spring:message code="how.much.to.send"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comment" class="control-label col-xs-3"><spring:message code="comment"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="comment" name="comment" placeholder="<spring:message code="your.comment"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="back"/></button>
                            <button type="submit" class="btn btn-primary"><spring:message code="send"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">
    var putMoney = "/account/putMoney";
    var accountListDataTable;
    var accountListDataTableParams;

    $(function () {
        accountListDataTable = $('#accountList');
        accountListDataTableParams = {
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "Name"
                },
                {
                    "mData": "Account Number"
                },
                {
                    "mData": "Balance"
                },
                {
                    "mData": "Currency"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        };

        accountListDataTable.dataTable(accountListDataTableParams);
        editAccount();
    });
</script>
</html>
