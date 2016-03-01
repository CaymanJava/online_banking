<%@ page import="com.cayman.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h2>_</h2>
<h3 align="center"><spring:message code="message"/></h3>
<jsp:useBean id="message" scope="request" type="com.cayman.entity.Message"/>
<h4 align="center"><%=TimeUtil.toString(message.getMessageTime())%></h4>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <div align="center">
                    <a class="btn btn-sm btn-info" href="message/mark?messageId=${message.id}"><spring:message code="message.mark.as.new"/></a>
                    <a class="btn btn-sm btn-warning" href="message/delete?messageId=${message.id}"><spring:message code="delete"/></a>
                    <a href="/messages" class="btn btn-sm btn-danger"><spring:message code="back"/></a>
                </div>
                <br/>
                <div>
                    <table border="0">
                        <tr>
                            <td><b><spring:message code="subject"/>:</b></td>
                        </tr>
                        <tr>
                            <td>${message.subject}</td>
                        </tr>
                    </table>
                    <br/>
                    <table border="0">
                        <tr>
                            <td><b><spring:message code="message"/>:</b></td>
                        </tr>
                        <tr>
                            <td>${message.text}</td>
                        </tr>
                    </table>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
