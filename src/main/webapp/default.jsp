<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<HTML>
<HEAD>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="myStyleSheets/mystyle.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <TITLE>Welcome to Cassiopeya</TITLE>
</HEAD>
<body>
<div class="container-fluid well">
    <div class="row-fluid">
        <div class="span12">
            <jsp:include page = "Header.jsp" flush = "true"/>
        </div>
    </div>
    <div class="row-fluid" >
        <div class="span3">
            <jsp:include page = "Menu.jsp" flush = "true"/>
        </div>
        <div class="span9">
            <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                    <div class="item active">
                        <div class="hero-unit">
                            <H2>WELCOME TO CASSIOPEYA!</H2>
                            <p class="lead">Bringing creativity to life</p>
                            <p><a class="btn btn-primary btn-large">Learn more »</a></p>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
            </div>
        </div>
    </div>
</div>
</body>
</HTML>
