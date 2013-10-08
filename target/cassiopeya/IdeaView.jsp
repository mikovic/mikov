
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="myStyleSheets/mystyle.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title>Idea</title>
</head>
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
            <div class="row-fluid" >
                <div class="media">
                    <ul class="pull-left"><img src='DownLoadServlet?imgId=<c:out value="${requestScope.firstImgId}"/>&load=ideaV' style="width: auto;height: 200px;" alt="You can add foto!"></ul>
                    <div class="media-body pull-right">
                        <h4 class="media-heading"><c:out value="${requestScope.idea.topicIdea}"/></h4>
                        <p><c:out value="${requestScope.idea.userLogin}"/></p>
                        <p><fmt:formatDate pattern="yyyy-MM-dd"
                                           value="${requestScope.idea.createDate}"/></p>
                        <p>Project budget $ <c:out value="${requestScope.idea.budget}"/></p>
                        <c:if test="${requestScope.invest ne null}">
                            <span class="label label-important"><c:out value="${requestScope.msg3}"/>
                                <c:out value="${requestScope.invest.investment}"/></span>
                        </c:if>
                        <c:if test="${sessionScope.user ne null}">
                            <c:choose>
                                <c:when test="${requestScope.myInvestInIdea ne null}">
                                    <p><span class="label label-info">
                                    <c:out value= "${requestScope.msg1}"/><c:out value= "${requestScope.myInvestInIdea}"/></span></p>
                                </c:when>
                                <c:otherwise>
                                    <p><span class="label label-info"><c:out value= "${requestScope.msg1}"/></span></p>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:choose>
                            <c:when test="${requestScope.sumInvest ne 0}">
                                <p><span class="label label-success">
                                <c:out value= "${requestScope.msg2}"/><c:out value= "${requestScope.sumInvest}"/></span></p>
                            </c:when>
                            <c:otherwise>
                                <p><span class="label label-info"><c:out value= "${requestScope.msg2}"/></span></p>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
            <div class="row-fluid" >
                <div class="span6">
                    <c:if test = "${requestScope.message ne null}">
                        <div class="alert">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h3><strong>Warning!</strong><c:out value = "${requestScope.message}"/></h3>
                        </div>
                    </c:if>
                </div>
                <div class="span3">
                    <div class="pull-right">
                        <p><a href="AddInvest?ideaId=<c:out value="${requestScope.idea.ideaId}"/>" class="btn btn-primary btn-large">You can invest the project</a></p>
                    </div>
                </div>
                <div class="span3">
                    <div class="pull-right">
                        <a href="AddComment?ideaId=<c:out value="${requestScope.idea.ideaId}"/>" class="btn btn-primary btn-large">You can leave your comment</a></p>
                    </div>
                </div>
            </div>
            <div class="row-fluid" >
                <div class="hero-unit">
                    <p><c:out value="${requestScope.idea.descIdea}"/></p>
                </div>
            </div>
            <c:if test="${requestScope.colImgId ne null}">
                <div class="row-fluid">
                    <div class="span9">
                        <ul class="thumbnails">
                            <c:forEach var="imgId" items="${requestScope.colImgId}">
                                <li>
                                    <img src='DownLoadServlet?imgId=<c:out value="${imgId}"/>&load=ideaV' style="width: auto;height: 200px;" alt="You can add foto!">
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>
            <div class="row-fluid" >
                <div class="hero-unit">
                    <h4><c:out value="${requestScope.msg4}"/></h4>
                    <c:forEach var="comment" items="${requestScope.comments}">
                        <p><c:out value="${comment.userLogin}"/></p>
                        <p><fmt:formatDate pattern="dd-MM-yyyy"
                                           value="${comment.createDate}"/></p>

                        <p><em><c:out value="${comment.text}"/></em></p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>