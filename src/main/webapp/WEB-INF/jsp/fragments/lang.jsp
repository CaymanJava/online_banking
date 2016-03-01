<%@page contentType="text/html" pageEncoding="UTF-8" %>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b class="caret"></b></a>
    <ul class="dropdown-menu">
        <li><a onclick="showEN()">English</a></li>
        <li><a onclick="showRU()">Русский</a></li>
        <li><a onclick="showPL()">Polski</a></li>
    </ul>
</li>
<script type="text/javascript">
    function showRU(){
        if (window.location.href.indexOf("en") > 0 || window.location.href.indexOf("ru") > 0) {
            window.location.href = window.location.href.replace("en", "ru");
        } else if (window.location.href.indexOf("pl") > 0) {
            window.location.href = window.location.href.replace("pl", "ru");
        } else if (window.location.href.indexOf("?") > 0) {
            window.location.href = window.location.href.replace("?", "?lang=ru&");
        } else {
            window.location.href += "?lang=ru";
        }
    }

    function showEN() {
        if (window.location.href.indexOf("ru") > 0 || window.location.href.indexOf("en") > 0) {
            window.location.href = window.location.href.replace("ru", "en");
        } else if (window.location.href.indexOf("pl") > 0) {
            window.location.href = window.location.href.replace("pl", "en");
        } else if (window.location.href.indexOf("?") > 0) {
            window.location.href = window.location.href.replace("?", "?lang=en&");
        } else {
            window.location.href += "?lang=en";
        }
    }

    function showPL() {
        if (window.location.href.indexOf("en") > 0 || window.location.href.indexOf("pl") > 0) {
            window.location.href = window.location.href.replace("en", "pl");
        } else if (window.location.href.indexOf("ru") > 0) {
            window.location.href = window.location.href.replace("ru", "pl");
        } else if (window.location.href.indexOf("?") > 0) {
            window.location.href = window.location.href.replace("?", "?lang=pl&");
        } else {
            window.location.href += "?lang=pl";
        }
    }
</script>