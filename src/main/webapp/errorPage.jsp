<%@ page import = "java.util.*"%>
<%! String messageError = null; %>
<html>
<head>
<title>ErrorPage</title>
</head>
<body>
<center>
<br><h2>Error</h2>
<br><h3> <%String message = request.getParameter("messageEr");%></h3>
 </center>
 </body>
 </html>