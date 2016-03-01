<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h2>_</h2>
<h3 align="center"><spring:message code="message.was.send"/></h3>
<jsp:useBean id="message" scope="request" type="com.cayman.dto.MessageTransferObject"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <div>
                    <table border="0">
                        <tr>
                            <td><b><spring:message code="message.send.to"/></b></td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr>
                        <c:forEach items="${message.userList}" var="user">
                            ${user.firstName} ${user.lastName},
                            </c:forEach>
                        </tr>
                    </table>
                    <br/>
                    <table border="0">
                        <tr>
                            <td><b><spring:message code="subject"/>:</b></td>
                        </tr>
                        <tr>
                            <td>${message.message.subject}</td>
                        </tr>
                    </table>
                    <br/>
                    <table border="0">
                        <tr>
                            <td><b><spring:message code="message"/>:</b></td>
                        </tr>
                        <tr>
                            <td>${message.message.text}</td>
                        </tr>
                    </table>
                    <br/>
                    <a href="/admin/users" class="btn btn-success"><spring:message code="admin.panel"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
