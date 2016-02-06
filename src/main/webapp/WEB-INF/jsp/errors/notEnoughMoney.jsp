<%--<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<section>
    <%--http://stackoverflow.com/questions/10327390/how-should-i-get-root-folder-path-in-jsp-page--%>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <b>Sorry, but you have not enough money for that transaction</b>
    <br/><button onclick="window.history.back()">Cancel</button>
</section>

</body>
</html>
