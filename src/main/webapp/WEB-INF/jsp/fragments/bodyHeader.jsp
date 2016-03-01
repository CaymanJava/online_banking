<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="/">
            <div class="navbar-header navbar-brand"><spring:message code="header.title"/></div>
        </a>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
            <form class="navbar-form navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-success" role="button" href="/admin/users"><spring:message code="admin.panel"/></a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                        <a class="btn btn-success" role="button" href="/admin/users"><spring:message code="admin.panel"/></a>
                    </sec:authorize>
                    <a class="btn btn-primary" role="button" href="messages"><spring:message code="my.messages"/></a>
                    <a class="btn btn-warning" role="button" id="sendMessage"><spring:message code="send.message"/></a>
                    <a class="btn btn-info" role="button" href="edit"><spring:message code="edit.profile"/></a>
                    <a class="btn btn-danger" role="button" href="logout"><spring:message code="logout"/></a>
                </sec:authorize>
            </form>
                </li>
                <jsp:include page="../fragments/lang.jsp"/>
            </ul>
        </div>
    </div>
</div>

<div class="modal fade" id="sendMessageModalForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title"><spring:message code="send.message.to.admin"/></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="sendMessageForm" action="sendMessage">

                    <div class="form-group">
                        <label for="subject" class="control-label col-xs-3"><spring:message code="subject"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="subject" name="subject">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mail" class="control-label col-xs-3"><spring:message code="message"/></label>

                        <div class="col-xs-9">
                            <textarea name="mail" id="mail" class="message-form"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <input action="action" type="button" value="<spring:message code="back"/>" onclick="history.go(-1);" class="btn btn-info" />
                            <button type="submit" class="btn btn-primary" id="send"><spring:message code="send"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
    var sendUrl = "/sendMessage";
    $(function () {
       sendMessage();
    });
</script>
