<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 04.06.13
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="myStyleSheets/mystyle.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title>New comment</title>
</head>
<body>
<div class="container-fluid">
    <div class="well">
        <div class="row-fluid">
            <div class="span12">
                <jsp:include page = "Header.jsp" flush = "true"/>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <form class="form-horizontal" method="post" action= AddComment>
                    <input type="hidden" name="ideaId" value='<c:out value="${requestScope.idea.ideaId}"/>'>
                    <input type="hidden" name="userId"  value='<c:out value="${sessionScope.user.userId}"/>'>

                    <div class="control-group">
                        <label class="control-label">Your comment:</label>
                        <div class="controls">
                            <textarea name="text"  rows="5" class="span6" id=></textarea>
                        </div>
                    </div>
                    <button class="btn btn-large btn-primary" type="reset">Reset</button>
                    <button class="btn btn-large btn-primary" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>