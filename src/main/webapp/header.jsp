 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<TABLE WIDTH = "740" CELLPADDING = "1"
HEIGHT = "75" CELLSPASING = "0"  border = "0">
<TR>
 <TD ALIGN = "LEFT" BGCOLOR = "F6F6F6">
  <FONT FACE = "Verdana" SIZE = "4">CASSIOPEYA</FONT>
 </TD>
 <TD ALIGN = "RIGHT" BGCOLOR = "F6F6F6">
 <c:choose>
    <c:when test = "${session.getAttribute(user) == null}">
        <A HREF = /registration?action = logIn>Log In</A>
        &nbsp; &nbsp; &nbsp;
        <A HREF = /registration?action = signIn >Sign In</A>
     </c:when>
     <c:otherwise>
        <A HREF = >Me</A>
 </TD>
</TR>
</TABLE>
