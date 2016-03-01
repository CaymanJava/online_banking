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
        <h3><spring:message code="access.denied"/></h3>
        <h2>${msg}<spring:message code="access.denied.message"/></h2>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
