<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <title>Registration page</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="8">
            <c:if test = "${requestScope.message ne null}">
                <h3><c:out value = "${requestScope.message}"/></h3>
            </c:if>
            <div class="row-fluid">
                <div class="span4 offset4" id="sign">
                    <form method="post" class="form-signin" action="ResultSignUp">
                        <h2 class="form-signin-heading">Please enter the user details</h2>
                        <input type="text" class="input-block-level" name="login" placeholder="Your Login">
                        <input type="password" class="input-block-level" name="password" placeholder="Password">
                        <input type="password" class="input-block-level" name="re_password" placeholder="Re-Password">
                        <input type="text" class="input-block-level" name="email" placeholder="Your email adress">
                        <label class="checkbox">
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                        <button class="btn btn-large btn-primary" type="reset">Reset</button>
                        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

