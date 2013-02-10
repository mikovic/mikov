 <html>
<head>
<title>Registration Page</title>
</head>
<body>
<center>
<br><h2>Registration Page</h2>
<br><h2>
  <%
  String mes = (String)request.getAttribute("message");
   if (mes!=null) {
   %>
  <%=mes%>
  <%
  }
  %>
<br> Please enter the user details</h2>
<br><FORM METHOD=POST ACTION=hello>
<table>
<tr>
 <td>Login:</td>
 <td><INPUT TYPE=TEXT NAME=login></td>
</tr>
<tr>
 <td>Password:</td>
 <td><INPUT TYPE=PASSWORD NAME=password></td>
</tr>
<tr>
 <td>Email:</td>
 <td><INPUT TYPE=TEXT NAME=email></td>
</tr>
<tr>
 <td><INPUT TYPE=RESET VALUE=REFRESH </td>
 <td><INPUT TYPE=SUBMIT VALUE=SUBMIT></td>
</tr>
</table>
</form>
</center>
</body>
</html>

