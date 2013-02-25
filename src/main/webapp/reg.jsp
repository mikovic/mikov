 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

 <html>
<head>
<title>Registration Page</title>
</head>
<body>
<center>
<br><h2>Registration Page</h2>
<br><h2>
 <c:if test = "${message!=null">
 <c:out value = "${message}"/>
 </c:if>
<br> Please enter the user details</h2>
<p><FORM METHOD=POST ACTION=result>
<table>
<tr>
 <td>Login:</td>
 <td><INPUT TYPE=TEXT NAME=login></td>
</tr>
<tr>
 <td>Password:</td>
 <td><INPUT TYPE=PASSWORD NAME=password></td>
</tr>
<c:if test = "${action = signIn}"
<tr>
 <td>Re-Password:</td>
 <td><INPUT TYPE=PASSWORD NAME=re_password></td>
</tr>
</c:if>
<tr>
 <td>Email:</td>
 <td><INPUT TYPE=TEXT NAME=email></td>
</tr>
<tr>
 <td><INPUT TYPE=RESET VALUE=RESET </td>
 <td><INPUT TYPE=SUBMIT VALUE=SUBMIT></td>
</tr>
</table>
</form>
</center>
</body>
</html>

