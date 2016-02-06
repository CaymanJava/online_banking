<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
        <li><a href="admin/users">Users</a></li>
        <li><a href="admin/commission">Commission</a></li>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
