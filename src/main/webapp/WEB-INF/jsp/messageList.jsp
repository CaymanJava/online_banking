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
<h3>_</h3>
<h3 align="center"><spring:message code="my.messages"/></h3>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <table border="0" cellpadding="8" cellspacing="0" id="messageList" class="table table-striped display">
                    <thead>
                    <tr>
                        <th><spring:message code="message.date"/></th>
                        <th><spring:message code="subject"/></th>
                        <th><spring:message code="message"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${messageList}" var="message">
                        <jsp:useBean id="message" scope="page" type="com.cayman.entity.Message"/>
                        <tr class="${message.newMessage ? 'newMessage' : 'oldMessage'}">
                            <td><%=TimeUtil.toString(message.getMessageTime())%></td>
                            <td><%=message.getSubject().length() > 40 ? message.getSubject().substring(0, 40) + "..." : message.getSubject()%></td>
                            <td><%=message.getText().length() > 40 ? message.getText().substring(0, 40) + "..." : message.getText()%></td>
                            <td><a href="messages/more?messageId=${message.id}" class="btn btn-sm btn-warning menu"><spring:message code="message.more"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
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
    var messageListDataTable;
    var messageListDataTableParams;

    $(function () {
        messageListDataTable = $('#messageList');
        messageListDataTableParams = {
            "bPaginate": true,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "Date"
                },
                {
                    "mData": "Subject"
                },
                {
                    "mData": "Text"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "desc"
                ]
            ]
        };

        messageListDataTable.dataTable(messageListDataTableParams);
        editAccount();
    });
</script>
</html>
