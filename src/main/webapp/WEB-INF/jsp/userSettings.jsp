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
<h3 align="center">${user.firstName} ${user.lastName} <spring:message code="user.settings.title"/></h3>
    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3 align="center" id="userRole"><spring:message code="user.role"/>: <b>${user.role eq "ROLE_USER" ? 'USER' : 'ADMIN'}</b></h3>
                <h3 align="center" class="${user.enabled ? 'enable' : 'disable'}" id="userStatus">${user.enabled ? 'Active' : 'Blocked'}</h3>
                <div class="view-box">
                    <div align="center">
                        <a class="btn btn-sm btn-primary" id="edit"><spring:message code="edit.user"/></a>
                        <a class="btn btn-sm btn-info" id="sendMessageToUser"><spring:message code="send.message"/></a>
                        <a class="btn btn-sm btn-warning changeUserStatus" userId="${user.id}" id="block" ><spring:message code="block.user"/></a>
                        <a class="btn btn-sm btn-success changeUserStatus" userId="${user.id}" id="unblock"><spring:message code="unblock.user"/></a>
                        <a class="btn btn-sm btn-success changeAdminRole" id="admin" userId="${user.id}"><spring:message code="enable.admin"/></a>
                        <a class="btn btn-sm btn-warning changeAdminRole" id="user" userId="${user.id}"><spring:message code="disble.admin"/></a>
                        <a href="admin/users/delete?userId=${user.id}" class="btn btn-sm btn-danger deleteUser"><spring:message code="delete.user"/></a>
                    </div>
                    <br/>
    <table border="0" cellpadding="8" cellspacing="0" id="userSettings" class="table table-striped dataTables_scrollBody">
        <thead>
        <tr>
            <th><spring:message code="account.name"/></th>
            <th><spring:message code="account.number"/></th>
            <th><spring:message code="balance"/></th>
            <th><spring:message code="currency"/></th>
            <th><spring:message code="account.enabled"/></th>
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
                <td class="${account.enable ? 'enable' : 'disable'}">${account.enable ? "Active" : "Block"}</td>
                <td><a href="/admin/users/settings/history?accountId=${account.id}&userId=${account.user.id}"
                       class="btn btn-xs btn-primary information"><spring:message code="history"/></a></td>
                <td><a accountId="${account.id}" userId="${account.user.id}"
                       class="${account.enable ? 'btn btn-xs btn-danger changeAccountStatus' : 'btn btn-xs btn-success changeAccountStatus'}" >${account.enable ? 'Block' : 'Unblock'}</a></td>
            </tr>
        </c:forEach>
    </table>
                </div>
            </div>
        </div>
    </div>

<div class="modal fade" id="editUserForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="edit.user"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="userForm" action="admin/users/settings/edit">
                    <input type="hidden" class="form-control" id="userId" name="userId" value="${user.id}">
                    <input type="hidden" class="form-control" id="registered" name="registered" value="${user.registered}">
                    <input type="hidden" name="enabled" value="${user.enabled}">
                    <input type="hidden" name="role" value="${user.role}">

                    <div class="form-group">
                        <label class="control-label col-xs-3"><spring:message code="login"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" name="login" value="${user.login}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" value="${user.password}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstName" class="control-label col-xs-3"><spring:message code="first.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastName" class="control-label col-xs-3"><spring:message code="last.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
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

<div class="modal fade" id="sendMessageToUserModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="send.message.to.user"/> ${user.firstName} ${user.lastName}</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="sendMessageToUserForm" action="admin/sendMessageToUser">
                    <input type="text" hidden="hidden" id="userId" name="userId" value="${user.id}">
                    <div class="form-group">
                        <label for="subject" class="control-label col-xs-3"><spring:message code="subject"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="subject" name="subject">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="message" class="control-label col-xs-3"><spring:message code="message"/></label>

                        <div class="col-xs-9">
                            <textarea name="message" id="message" class="message-form"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" class="btn btn-info" data-dismiss="modal" aria-hidden="true"><spring:message code="back"/></button>
                            <button type="submit" class="btn btn-primary" id="send"><spring:message code="send"/></button>
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

    var accountStatus = "admin/users/settings/changeAccountStatus?";
    var userStatus = "admin/users/settings/changeUserStatus?";
    var userSetting = "admin/users/settings?userId=";
    var adminRole = "admin/users/settings/changeUserRole?";
    var userAccountsTable;
    var userAccountsTable_params;

    $(function () {

        if ($("#userStatus").html() == "Active") {
            $('#block').show();
            $('#unblock').hide();
        } else {
            $('#unblock').show();
            $('#block').hide();
        }

        if ($('#userRole').html().indexOf("USER") > 0) {
            $('#admin').show();
            $('#user').hide();
        } else {
            $('#user').show();
            $('#admin').hide();
        }

        userAccountsTable = $('#userSettings');
        userAccountsTable_params = {
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "name"
                },
                {
                    "mData": "accountNumber"
                },
                {
                    "mData": "Balance"
                },
                {
                    "mData": "Currency"
                },
                {
                    "mData": "Enabled"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    2,
                    "asc"
                ]
            ]
        };

        userAccountsTable.dataTable(userAccountsTable_params);
        editUser();
    });
</script>
</html>
