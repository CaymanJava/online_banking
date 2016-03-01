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
<h3 align="center"><spring:message code="user.title"/></h3>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add"><spring:message code="add.user"/></a>
                <a class="btn btn-sm btn-warning" href="admin/commission"><spring:message code="commission"/></a>
                <a class="btn btn-sm btn-warning" id="sendMessageToAll"><spring:message code="send.message.to.all"/></a>
    <table border="0" cellpadding="8" cellspacing="0" id="datatable" class="table table-striped display">
        <thead>
        <tr>
            <th><spring:message code="first.name"/></th>
            <th><spring:message code="last.name"/></th>
            <th><spring:message code="email"/></th>
            <th><spring:message code="user.registered"/></th>
            <th><spring:message code="user.active"/></th>
            <th><spring:message code="user.role"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" scope="page" type="com.cayman.entity.User"/>
            <tr>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                <td class="${user.enabled ? 'enable' : 'disable'}">${user.enabled ? 'Active' : 'Blocked'}</td>
                <td>${user.role eq "ROLE_USER" ? 'USER' : user.role eq "ROLE_ADMIN" ? 'ADMIN' : 'SUPER ADMIN'}</td>
                <td><a href="admin/users/settings?userId=${user.id}" class="${user.role eq "ROLE_SUPER_ADMIN" ? 'btn btn-xs btn-danger disabled' : 'btn btn-xs btn-primary edit'}"><spring:message code="user.settings"/></a></td>
            </tr>
        </c:forEach>
    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editRow">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title"><spring:message code="add.user"/></h3>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" method="post" id="detailsForm" action="/admin/users/add">
                        <input type="text" hidden="hidden" id="userId" name="userId">

                        <div class="form-group">
                            <label class="control-label col-xs-3"><spring:message code="login"/></label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" name="login" placeholder="<spring:message code="user.login"/>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="control-label col-xs-3"><spring:message code="password"/></label>

                            <div class="col-xs-9">
                                <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="user.password"/>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="firstName" class="control-label col-xs-3"><spring:message code="first.name"/></label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="<spring:message code="enter.first.name"/>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="lastName" class="control-label col-xs-3"><spring:message code="last.name"/></label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="<spring:message code="enter.last.name"/>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="control-label col-xs-3"><spring:message code="email"/></label>

                            <div class="col-xs-9">
                                <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="enter.email"/>">
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

<div class="modal fade" id="sendMessageToAllModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="send.message.to.all"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="sendMessageToAllForm" action="admin/sendMessageToAll">

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

    var ajaxUrl = 'admin/users/';
    var userListDataTable;
    var userListDataTableParams;
    $(function () {
        userListDataTable = $('#datatable');
        userListDataTableParams = {
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "firstName"
                },
                {
                    "mData": "lastName"
                },
                {
                    "mData": "email"
                },
                {
                    "mData": "registered"
                },
                {
                    "mData": "enabled"
                },
                {
                    "mData": "roles"
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

        userListDataTable.dataTable(userListDataTableParams);
        makeEditable();
    });
</script>
</html>
