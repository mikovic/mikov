<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="myfilejs/myfunction.js"></script>
<html>
<head>
    <link rel="stylesheet" href="myStyleSheets/mystyle.css" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title>My profile</title>
</head>
<body class="body">
<div class="container-fluid">
    <div class="well">
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
                <div class="browse_tools">
                    <div class="pages">
                     <span>Page&nbsp<c:out value="${requestScope.page}"/>&nbspof&nbsp
                         <c:out value="${requestScope.countIdeasUser}"/></span>
                        <c:choose>
                            <c:when test="${requestScope.page eq requestScope.countIdeasUser}">
                                <span class="next next_off"></span>
                                <a class="prev" href="MyProfile?action=myProfile&page=<c:out value="${requestScope.page-1}"/>"></a>
                            </c:when>
                            <c:when test="${requestScope.page eq 1}">
                                <a class="next" href="MyProfile?action=myProfile&page=<c:out value="${requestScope.page+1}"/>"></a>
                                <span class="prev prev_off"></span>
                            </c:when>
                            <c:otherwise>
                                <a class="next" href="MyProfile?action=myProfile&page=<c:out value="${requestScope.page+1}"/>"></a>
                                <a class="prev" href="MyProfile?action=myProfile&page=<c:out value="${requestScope.page-1}"/>"></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <c:choose>
                <c:when test="${requestScope.msg ne null}">
                <div class="row-fluid">
                    <div class="span9">
                        <div class="hero-unit">
                            <h4><c:out value="${requestScope.msg}"/></h4>
                            <div class="row-fluid">
                                <div class="span9">
                                    <div class="thumbnail">
                                        <div class="caption">
                                            <img src='DownLoadServlet?imgId=<c:out value="${requestScope.firstImgId}"/>&load=myProfile'  style="width: auto;height: 200px;" alt="You can add foto!">
                                        </div>
                                    </div>
                                    <h4><c:out value = "${requestScope.ideaNew.topicIdea}"/></h4>
                                    <p>Budget new project $ <c:out value = "${requestScope.ideaNew.budget}"/></p>
                                    <p><fmt:formatDate pattern="yyyy-MM-dd"
                                                       value="${requestScope.idea.createDate}"/></p>
                                    <p> <c:out  value ="${requestScope.ideaNew.descIdea}"/></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${requestScope.colImgId ne null}">
                    <div class="row-fluid">
                        <div class="span9">
                            <c:forEach var="imgId" items="${requestScope.colImgId}">
                                <img src='DownLoadServlet?imgId=<c:out value="${imgId}"/>&load=myProfile' style="width: auto;height: 200px;" alt="">
                            </c:forEach>
                        </div>
                    </div>
                </c:if>

            </div>
            </c:when>
            <c:otherwise>
                <div class="row-fluid">
                    <div class="span9">
                        <div class="media">
                            <a class="thumbnail pull-left">
                                <img class="media-object" src='DownLoadServlet?imgId=<c:out value="${requestScope.firstImgId}"/>&load=myProfile' style="width: auto;height: 200px;" alt="300x200 You can add foto">
                            </a>
                            <div class="media-body pull-right">
                                <h3 class="media-heading"><c:out value = "${requestScope.ideaUserOnPage.topicIdea}"/></h3>

                                <p><em>CREATE:</em>&nbsp <fmt:formatDate pattern="yyyy-MM-dd"
                                                                         value="${requestScope.ideaUserOnPage.createDate}"/></p>
                                <p><em>BUDGET:</em>&nbsp$<c:out value = "${requestScope.ideaUserOnPage.budget}"/></p>
                                <p><em>The project is invested for $</em><span class="badge badge-important">
                                            <c:out value = "${requestScope.infoInvestInIdea.sumInvestInIdea}"/></span></p>
                                <p><em>The maximum investment made $</em><span class="badge badge-success">
                                            <c:out value = "${requestScope.infoInvestInIdea.maxInvestInIdea}"/></span></p>
                                <p><em>The average investment made $</em><span class="badge badge-info">
                                            <c:out value = "${requestScope.infoInvestInIdea.avgInvestInIdea}"/></span></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span9">
                        <p><c:out  value ="${requestScope.ideaUserOnPage.descIdea}"/></p>
                    </div>
                </div>

                <c:if test="${requestScope.colImgId ne null}">
                    <div class="row-fluid">
                        <div class="thumbnails">
                            <c:forEach var="imgId" items="${requestScope.colImgId}">
                                <li>
                                    <div class="thumbnail">
                                        <img src='DownLoadServlet?imgId=<c:out value="${imgId}"/>&load=myProfile' style="width: auto;height: 200px;" alt="">
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <div class="row-fluid">
                    <div class="span3 pull-right">
                        <a href="EditServlet?ideaId=<c:out value="${requestScope.ideaUserOnPage.ideaId}"/>&page=<c:out value="${requestScope.page}"/>" class="btn-primary btn-large">EDIT</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span9">
                        <c:choose>
                            <c:when test="${requestScope.msg1 ne null}">
                                <span class="label label-important"><h4><c:out value="${requestScope.msg1}"/></h4></span>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="comment" items="${requestScope.comments}">
                                    <div class="row-fluid">
                                        <div class="span3">
                                            <div class="row-fluid">
                                                <c:out value="${comment.userLogin}"/>
                                            </div>
                                            <div class="row-fluid">
                                                <c:out value="${comment.createDate}"/>
                                            </div>
                                        </div>
                                        <div class="span6">
                                            <div class="row-fluid">
                                                <c:out value="${comment.text}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</div>
</body>
</html>
