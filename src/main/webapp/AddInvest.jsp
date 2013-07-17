<%--
  Created by IntelliJ IDEA.
  User: Masha
  Date: 06.06.13
  Time: 10:03
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
    <title>Your invest</title>
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
                <form class="form-horizontal" method="post" action= AddInvest>
                    <input type="hidden" name="ideaId" value='<c:out value="${requestScope.idea.ideaId}"/>'>
                    <input type="hidden" name="userId"  value='<c:out value="${sessionScope.user.userId}"/>'>
                    <div class="control-group">
                        <label class="control-label">Your invest:</label>
                        <div class="controls">
                            <input type="text" name="investment" maxlength="11"/>
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