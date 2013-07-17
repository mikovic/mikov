<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<HTML>
<HEAD>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="myHeaderStyle.css" />
</HEAD>
<body>
<div class="row-fluid">
    <div class="span12">
        <h2>CASSIOPEYA</h2>
        <div class="row-fluid">
            <div class="span12">
                <div class="navbar">
                    <div class="navbar-inner">
                        <div class="container-fluid">
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <a class="brand" href="#">cassiopeya.com</a>
                            <div class="nav-collapse">
                                <c:choose>
                                    <c:when test = "${sessionScope.user eq null}">
                                        <ul class="nav">
                                            <li><A HREF ="Registration?action=login">Sign in</A></li>
                                            <li><A HREF ="Registration?action=signUp">Sign up</A></li>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <ul class="nav">
                                            <li class="active"><a href="StartProject?action=startProject">Start you project</a></li>
                                            <li class="dropdown">
                                                <a href="" class="dropdown-toggle" data-toggle="dropdown">Me<b class="caret"></b></a>
                                                <ul class="dropdown-menu" >
                                                    <li><a href="#" >MY ACCOUNT</a></li>
                                                    <li><a href="MyProfile?action=myProfile&page=1">My profile</a></li>

                                                    <li><a href="MyProfile?action=logOut">Log out</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                                <ul class="nav pull-right" >
                                    <li class="divider-vertical"></li>
                                    <form class="navbar-search">
                                        <div class="input-append">
                                            <input type="text"  class="search-query" placeholder="Search">
                                            <button type="submit" class="btn-primary"><i class="icon-search"></i></button>
                                        </div>
                                    </form>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>