<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title>Sign in page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span8">
            <form class="form" method="post" action="ResultLogIn">
                <c:if test = "${requestScope.message ne null}">
                    <h3><c:out value = "${requestScope.message}"/></h3>
                </c:if>
                <h2 class="form-signin-heading">Please sign in</h2>
                <input type="text" class="input-block-level"  name="login"  placeholder="Your Login">
                <input type="password" class="input-block-level" name="password"  placeholder="Password">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
                <button class="btn btn-large btn-primary" type="reset">Reset</button>
                <button class="btn btn-large btn-primary" type="submit">Sign in</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>