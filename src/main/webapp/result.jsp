<%@ page import = "java.util.*"%>
<%! String message = null; %>
 <html>
  <body>
  <center>
  <H1>Result Page</H1>
  <H3>
  <%String message = request.getParameter("message");
  if (!message==null) {
  %>
  WELCOME!<br>
  <%
  out.print(message);
  %>
  </H3>
  </body>
  </center>
 </html>