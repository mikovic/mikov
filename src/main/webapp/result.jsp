
 <html>
  <body>
  <center>
  <H1>Result Page</H1>
  <H3>
  WELCOME!
  <br>
  <%
  String mes = (String)request.getAttribute("message");
  if (mes!=null) {
  %>
  <%=mes%>
  <%
  }
  %>
  </H3>
  </body>
  </center>
 </html>