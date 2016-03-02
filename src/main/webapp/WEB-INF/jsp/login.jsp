<%--<!DOCTYPE html>--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<body>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Online Bank</title>
    <link rel="stylesheet" type="text/css" href="resources/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/style3.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/animate-custom.css" />
    <link rel="shortcut icon" type="image/png" href="resources/img/bank.png">
</head>

<div class="container">
    <header>
        <h1>Cayman <span>OnlineBank</span></h1>
    </header>
    <section>
        <div id="container_demo" >
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div align="center"><b><a href="readme" class="readme">README</a></b></div>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <div>
                        <ul class="ddmenu">
                            <li><a href="#"><b>${pageContext.response.locale}</b></a>
                                <ul>
                                    <li><a onclick="showEN()">English</a></li>
                                    <li><a onclick="showRU()">Русский</a></li>
                                    <li><a onclick="showPL()">Polski</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <form  action="spring_security_check" autocomplete="on"  method="post">
                        <h1><spring:message code="enter"/></h1>
                        <p>
                            <label for="username" class="uname" data-icon="u" ><spring:message code="your.login"/></label>
                            <input id="username" name="username" required="required" type="text" placeholder="<spring:message code="type.login"/>"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p"><spring:message code="your.password"/></label>
                            <input id="password" name="password" required="required" type="password" placeholder="<spring:message code="type.password"/>" />
                        </p>
                        <c:if test="${error}">
                            <div class="error" id="error">
                                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                            </div>
                        </c:if>

                        <p class="login button">
                            <input type="submit" value="<spring:message code="log.in"/>" />
                        </p>
                        <p class="change_link">
                            <spring:message code="not.a.member"/>
                            <a href="#toregister" class="to_register" id="to_register"><spring:message code="join.us"/></a>
                        </p>
                    </form>
                </div>

                <div id="register" class="animate form">
                    <div>
                        <ul class="ddmenu">
                            <li><a href="#"><b>${pageContext.response.locale}</b></a>
                                <ul>
                                    <li><a onclick="showEN()">English</a></li>
                                    <li><a onclick="showRU()">Русский</a></li>
                                    <li><a onclick="showPL()">Polski</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <form  action="/registration" autocomplete="on" method="post">
                        <h1><spring:message code="sign.up"/></h1>

                        <p>
                            <label for="newUsername" class="uname" data-icon="u"><spring:message code="your.login"/></label>
                            <input id="newUsername" name="login" required="required" type="text" placeholder="<spring:message code="type.login"/>"/>
                        </p>

                        <p>
                            <label for="newPassword" class="youpasswd" data-icon="p"><spring:message code="your.password"/></label>
                            <input id="newPassword" name="password" required="required" type="password" placeholder="<spring:message code="type.password"/>"/>
                        </p>
                        <p>
                            <label for="email" class="youmail" data-icon="e" ><spring:message code="your.email"/></label>
                            <input id="email" name="email" required="required" type="email" placeholder="<spring:message code="type.email"/>"/>
                        </p>
                        <p>
                            <label for="firstName" class="uname" data-icon="u"><spring:message code="your.first.name"/></label>
                            <input id="firstName" name="firstName" required="required" type="text" placeholder="<spring:message code="type.first.name"/>" />
                        </p>
                        <p>
                            <label for="lastName" class="uname" data-icon="u"><spring:message code="your.last.name"/></label>
                            <input id="lastName" name="lastName" required="required" type="text" placeholder="<spring:message code="type.last.name"/>" />
                        </p>

                        <p class="signin button">
                            <input type="submit" value="<spring:message code="sign.up"/>"/>
                        </p>
                        <p class="change_link">
                            <spring:message code="already.a.member"/>
                            <a href="#tologin" class="to_register" id="to_login"> <spring:message code="go.login"/> </a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>


<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
</body>

<script type="text/javascript">
    $(document).ready(function(){
        $('.ddmenu').find('li').find('ul').slideUp();
        $('a').on('click', function(e){
            e.preventDefault();
        });
        $('.ddmenu').find('li').hover(function () {
            clearTimeout($.data(this,'timer'));
            $('ul',this).stop(true,true).slideDown(200);
        }, function () {
            $.data(this,'timer', setTimeout($.proxy(function() {
                $('ul',this).stop(true,true).slideUp(200);
            }, this), 100));
        });
        $('#to_register').click(function(){
            toRegister();
        });
        $('#to_login').click(function (){
           toLogin();
        });
        $('.readme').click(function(){
            var url = $(this).attr("href");
            window.open(url);
        })
    });

    function toRegister() {
        if (window.location.href.indexOf("#tologin") > 0) {
            window.location.href = window.location.href.replace("#tologin", "#toregister");
        } else {
            window.location.href += "#toregister"
        }
    }

    function toLogin(){
        if (window.location.href.indexOf("#toregister") > 0) {
            window.location.href = window.location.href.replace("#toregister", "#tologin");
        }
    }

    function showRU(){
        if (window.location.href.indexOf("en") > 0 || window.location.href.indexOf("ru") > 0) {
            window.location.href = window.location.href.replace("en", "ru");
        } else if (window.location.href.indexOf("pl") > 0){
            window.location.href = window.location.href.replace("pl", "ru");
        } else {
            window.location.href = window.location.href.replace("login", "login?lang=ru");
        }
    }

    function showEN() {
        if (window.location.href.indexOf("ru") > 0 || window.location.href.indexOf("en") > 0) {
            window.location.href = window.location.href.replace("ru", "en");
        } else if (window.location.href.indexOf("pl") > 0) {
            window.location.href = window.location.href.replace("pl", "en");
        } else {
            window.location.href = window.location.href.replace("login", "login?lang=en");
        }
    }

    function showPL() {
        if (window.location.href.indexOf("pl") > 0 || window.location.href.indexOf("en") > 0) {
            window.location.href = window.location.href.replace("en", "pl");
        } else if (window.location.href.indexOf("ru") > 0){
            window.location.href = window.location.href.replace("ru", "pl");
        } else {
            window.location.href = window.location.href.replace("login", "login?lang=pl");
        }
    }
</script>
</body>
</html>

