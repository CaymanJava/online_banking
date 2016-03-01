<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <br>
        <h4><spring:message code="error"/></h4>
        <h2><spring:message code="${message}"/></h2>
    </div>
    <div class="container">
        <input action="action" type="button" value="<spring:message code="back"/>" onclick="history.go(-1);" />
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>