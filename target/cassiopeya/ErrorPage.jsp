<%@ page contentType="text/html;charset=UTF-8" language="java" %>


 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
<title>ErrorPage</title>
</head>
<body>
<c:out value = "${requestScope.message}"/>

 </body>
 </html>