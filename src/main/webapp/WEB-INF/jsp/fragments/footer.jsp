<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="footer">
    <div class="container">
        <div align="center"><b><a id="readme" class="readme">README</a></b></div>
    </div>
</div>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.readme').click(function(){
            var url = $(this).attr("id");
            window.open(url);
        })
    });
</script>
