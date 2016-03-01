<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" type="text/css" href="resources/css/transaction.css" />
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h2>_</h2>
<h3 align="center"><spring:message code="edit.profile.title"/></h3>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div class="view-box">
                <jsp:useBean id="user" type="com.cayman.entity.User" scope="request"/>
                <form method="post" action="/updateProfile">
                    <input type="hidden" class="form-control" id="userId" name="userId" value="${user.id}">
                    <input type="hidden" class="form-control" id="registered" name="registered" value="${user.registered}">
                    <input type="hidden" name="enabled" value="${user.enabled}">
                    <input type="hidden" name="role" value="${user.role}">

                    <table class="table3" align="center">
                        <tbody>
                        <tr>
                            <td scope="row" class="firstRowLeft"><spring:message code="login"/>:</td>
                            <td class="firstRowRight"><input type="text" class="form-control" name="login" value="${user.login}"></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="password"/>:</td>
                            <td><input type="password" class="form-control" id="password" name="password" value="${user.password}"></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="first.name"/>:</td>
                            <td><input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}"></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="last.name"/>:</td>
                            <td><input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}"></td>
                        </tr>
                        <tr>
                            <td scope="row"><spring:message code="email"/>:</td>
                            <td> <input type="email" class="form-control" id="email" name="email" value="${user.email}"></td>
                        </tr>
                        </tbody>
                    </table>
                    <br/>
                    <div align="center">
                        <input action="action" type="button" value="<spring:message code="back"/>" onclick="history.go(-1);" class="btn btn-lg btn-info" />
                        <button type="submit" class="btn btn-lg btn-success"><spring:message code="save"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
