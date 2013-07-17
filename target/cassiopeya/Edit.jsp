<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 14.06.13
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="myStyleSheets/mystyle.css" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title></title>
</head>
<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="control-group">
            <label class="control-label">Choice category:</label>
            <div class="controls">
                <select name="categoryId">
                    <c:forEach var="category" items="${requestScope.categories}">
                        <c:choose>
                            <c:when test="${requestScope.ideaEdit.categoryId eq category.key}">
                        <option selected value='<c:out value = "${category.key}"/>'>
                            <c:out value = "${category.value}"/>
                        </option>
                            </c:when>
                        <c:otherwise>
                            <option  value='<c:out value = "${category.key}"/>'>
                                <c:out value = "${category.value}"/>
                            </option>
                        </c:otherwise>
                    </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Topic of idea</label>
            <div class="controls">
                <input type="text" name="topicIdea" class="span6" maxlength="15" value="<c:out value="${requestScope.ideaEdit.topicIdea}"/>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Describe of idea</label>
            <div class="controls">
                <textarea name="descIdea"  rows="5" class="span6" id="limitField" onkeypress="limitText(100)" value="<c:out value="${requestScope.ideaEdit.descIdea}"/>"></textarea>
            </div>
            <div class="controls">
                <input readonly type="text" name="limitCount" id="limitCount" size="3" value="100">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Budget of idea</label>
            <div class="controls">
                <input type="text" name="budget" maxlength="10" onchange="validateNumber(this.budget.value)" value="<c:out value="${requestScope.ideaEdit.budget}"/>">
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <ul class="thumbnails">
            <c:forEach var="imgId" items="${requestScope.idImgUser}">
                <li class="span4">
                    <div class="thumbnail">
                        <div class="caption">
                            <img src='DownLoadServlet?imgId=<c:out value="${imgId}"/>&load=edit'  style="width: 300px;height: 200px;" alt="300x200 You can add foto!">
                            <p><a href="IdeaView?imgId=<c:out value ="${imgId}"/>" class="btn btn-primary btn-large">Remove? »</a></p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>


</body>
</html>