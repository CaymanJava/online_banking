<%--<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <%--http://stackoverflow.com/questions/10327390/how-should-i-get-root-folder-path-in-jsp-page--%>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <h3>Accounts</h3>
    <%--<form method="post" action="meals/filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="startDate" value="${startDate}"></dd>
        </dl>
        <dl>
            <dt>To Date:</dt>
            <dd><input type="date" name="endDate" value="${endDate}"></dd>
        </dl>
        <dl>
            <dt>From Time:</dt>
            <dd><input type="time" name="startTime" value="${startTime}"></dd>
        </dl>
        <dl>
            <dt>To Time:</dt>
            <dd><input type="time" name="endTime" value="${endTime}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>--%>
    <hr>
    <a href="accounts/create">Add account</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Account Number</th>
            <th>Balance</th>
            <th>Currency</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${accountList}" var="account">
            <jsp:useBean id="account" scope="page" type="com.cayman.entity.Account"/>
            <tr><%--<tr class="${meal.exceed ? 'exceeded' : 'normal'}">--%>
               <%-- <td>
                   <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                   <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />
                    <%=TimeUtil.toString(meal.getDateTime())%>
                </td>--%>
                <td>${account.name}</td>
                <td>${account.accountNumber}</td>
                <td>${account.balance}</td>
                <td>${account.currency}</td>
                <td><a href="accounts/update?id=${account.id}">Menu</a></td>
                <td><a href="accounts/history?id=${account.id}">History</a></td>
            <%--</tr>--%>
                </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
