<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<HEAD>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <TITLE>Viewing of categories</TITLE>
</HEAD>
<body>
<div class="container-fluid well">
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
            <div class="row-fluid">
            <c:choose>
                <c:when test="${requestScope.ideasInCategory ne null}">
                    <ul class="thumbnails">
                        <c:forEach var="idea" items="${requestScope.ideasInCategory}">
                            <li class="span4">
                                <div class="thumbnail">
                                    <div class="caption">
                                        <img src='DownLoadServlet?ideaId=<c:out value="${idea.ideaId}"/>&load=viewCateg'  style="width: 300px;height: 200px;" alt="300x200 You can add foto!">
                                        <h3><c:out value = "${idea.topicIdea}"/></h3>
                                        <p><span class="badge badge-important"><c:out value = "${idea.budget}"/>&nbsp$</span></p>
                                        <p> <c:out  value ="${idea.descIdea}"/></p>
                                        <p><a href="IdeaView?ideaId=<c:out value ="${idea.ideaId}"/>" class="btn btn-primary btn-large">Learn more »</a></p>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>NOTHING TO WATCH. NOT IDEAS!</c:otherwise>
            </c:choose>
            </div>
            <div class="row-fluid">
                <div class="pagination">
                    <ul>
                        <li><a href='ViewCategories?categoryId=<c:out value ="${requestScope.categoryId}"/>&pagin=prev&startPagin=<c:out value ="${requestScope.startPagin}"/>'>
                            Prev</a></li>
                        <c:forEach var="i" begin ="${requestScope.startPagin}" end= "${requestScope.endPagin}">
                        <li><a href= 'ViewCategories?categoryId=<c:out value ="${requestScope.categoryId}"/>&page=<c:out value="${i}"/>&startPagin=<c:out value ="${requestScope.startPagin}"/>'>
                                <c:out value="${i}"/></a></li>
                        </c:forEach>
                        <li><a href='ViewCategories?categoryId=<c:out value ="${requestScope.categoryId}"/>&pagin=next&startPagin=<c:out value ="${requestScope.startPagin}"/>'>
                            Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>