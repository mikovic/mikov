<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<HTML>
<HEAD>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <TITLE>Welcome to Cassiopeya!</TITLE>
</HEAD>
<BODY>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <jsp:include page = "Header.jsp" flush = "true"/>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span3">
            <jsp:include page = "Menu.jsp" flush = "true"/>
        </div>
        <div class="span9">
            <div class="hero-unit">
                <c:if test="${requestScope.message ne null}">
                    <h3><c:out value="${requestScope.message}"/></h3>
                </c:if>
                <p>Bringing creativity to life</p>
                <p><a class="btn btn-primary btn-large">Learn more »</a></p>
            </div>
        </div>
    </div>
</div>
</BODY>
</HTML>

