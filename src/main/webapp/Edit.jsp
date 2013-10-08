

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="myStyleSheets/mystyle.css" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="/myfilejs/myfunction.js"></script>
    <script type="text/javascript" src="/myfilejs/myajax.js"></script>

    <title></title>

</head>
<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div id="contentBody"></div>
        <form id="text" method="post" action="EditServlet">
            <input type="hidden" name="editText" value="true">
            <input type="hidden" id="ideaId" name="ideaId" value="<c:out value="${requestScope.ideaEdit.ideaId}"/>">
            <div class="control-group">
                <label class="control-label">Choice category:</label>
                <div class="controls">
                    <select name="categoryId" id="categoryId" >
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
                    <input id="topic" type="text" name="topicIdea" class="span6" maxlength="15" value="<c:out value="${requestScope.ideaEdit.topicIdea}"/>">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Describe of idea</label>
                <div class="controls">
                    <textarea name="descIdea"  rows="5" class="span6" id="limitField" onkeypress="limitText(100)" ><c:out value="${requestScope.ideaEdit.descIdea}"/></textarea>
                </div>
                <div class="controls">
                    <input readonly type="text" name="limitCount" id="limitCount" size="3" value="100">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Budget of idea</label>
                <div class="controls">
                    <input type="text" id="budget" name="budget" maxlength="10" onchange="validateNumber(this.budget.value)" value="<c:out value="${requestScope.ideaEdit.budget}"/>">
                </div>
            </div>
            <div id="btn">
                <input id="btn0" class= "btn btn-primary btn-large" style="display: block" onclick="setReadOnlyTextAjax() " value="EDIT?">
            </div>
        </form>
    </div>
    <div class="row-fluid">
        <p>
        <div class="span3 pull-right">
            <a href="MyProfile?action=myProfile&page=<c:out value="${requestScope.page}"/>" class="btn-primary btn-large"><i class="icon-arrow-left"></i>Back</a>
        </div>
        </p>
    </div>
    <div class="row-fluid">
        <ul class="thumbnails">
            <c:forEach var="imgId" items="${requestScope.idImgUser}">
                <li class="span4">
                    <div class="thumbnail">
                        <div class="caption">
                            <div >
                                <img src="DownLoadServlet?imgId=<c:out value="${imgId}"/>&load=edit"  style="width: auto;height: 200px;" alt="300x200 You can add foto!">
                            </div>
                            <div style="display: block">
                                <button   class= "btn btn-primary btn-large"  onclick="changePicture(<c:out value ="${imgId}"/>)" >Remove?</button>
                            </div>
                            <div style="display: none">
                                <form id="<c:out value ="${imgId}"/>" class="form-horizontal" action="EditPictureServlet" method="post" enctype="multipart/form-data"  target="hiddenframe">
                                    <input type="hidden"  name="formId" value="<c:out value="${imgId}"/>">
                                    <input type="hidden"  name="ideaId" value="<c:out value="${requestScope.ideaEdit.ideaId}"/>">
                                    <input type="file"  accept="image/*,image/jpeg" >
                                    <input type="submit" name="upload"  value="Load picture?" />
                                </form>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
            <c:forEach var="count" begin="1" end="${requestScope.countImgToAdd}">
                <li class="span4">
                    <div class="thumbnail">
                        <div class="caption">
                            <div >
                                <img src=''  style="width: auto;height: 200px;" alt="You can add foto!">
                            </div>                            
                            <div style="display: block">
                                <form id="<c:out value ="${count}"/>" class="form-horizontal" action="EditPictureServlet" method="post" enctype="multipart/form-data" target="hiddenframe1">
                                    <input type="hidden"  name="formId" value="<c:out value ="${count}"/>">
                                    <input type="hidden"  name="ideaId" value="<c:out value="${requestScope.ideaEdit.ideaId}"/>">
                                    <input type="file"  accept="image/*,image/jpeg" >
                                    <input type="submit" name="upload"  value="Add picture?" />
                                </form>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <div id="mes"></div>
        <iframe id="hiddenframe" name="hiddenframe" style="width:0px; height:0px; border:0px">
            <script type="text/javascript">
                  window.parent.handleResponse(json);
            </script>
        </iframe>
        <iframe id="hiddenframe1" name="hiddenframe1" style="width:0px; height:0px; border:0px">
            <script type="text/javascript">
                window.parent.handleResponse1(json);
            </script>
        </iframe>
    </div>
</div>


</body>
</html>