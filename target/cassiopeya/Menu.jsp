<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="myHeaderStyle.css" />
</head>
<body>
<div class="row-fluid">
    <div class="span3">
            <em>Categories</em>
        <div class="span3">
            <c:forEach var="category" items="${requestScope.categories}">
                <p><a HREF ='ViewCategories?categoryId=<c:out value ="${category.key}"/>&page=1&startPagin=1'>
                    <c:out value = "${category.value}"/>
                </a></p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
