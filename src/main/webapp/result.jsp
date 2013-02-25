<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<HTML>
<HEAD>
<TITLE>Welcome!</TITLE>
</HEAD>
<BODY>
<TABLE>
<TR>
<TD COLSPAN =2><jsp:include page = "Header.jsp" flush = "true"/></TD>
</TR>
<TR>
<TD><jsp:include page = "Menu.jsp" flush = "true"/></TD>
<TD VALIGN = "TOP">
<H2>WELCOME TO CASSIOPEYA!</H2>
<P><c:out value = "!{request.message}">
</TD>
</TR>
</TABLE>
</BODY>
</HTML>

