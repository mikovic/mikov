<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<HTML>
<HEAD>
<TITLE>Viewing of categories</TITLE>
</HEAD>
<BODY>
<TABLE>
<TR>
<TD COLSPAN =2><jsp:include page = "header.jsp" flush = "true"/></TD>
</TR>
<TR>
<TD><<jsp:include page = "Menu.jsp" flush = "true"/></TD>
<TD VALIGN = "TOP">
<c:if test="${request.categoryId!=null && !request.category.trim(),equals("")}">
<TABLE>
<c:forEach var="idea" items="${request.ideas}">
 <TR>
  <TD><FONT FACE="Verdana" SIZE ="2"><B><c:out value = ${idea.topic}></B></FONT></TD>
  <TD><FONT FACE="Verdana" SIZE ="2"><B><c:out value = ${idea.user}</B></FONT></TD>
 </TR>
 <P><TR>
  <TD COLSPAN =2><FONT FACE="Verdana" SIZE ="2"><c:out = ${idea.desc}</FONT></TD>
 </TR>
</c:forEach>
</TABLE>
</c:if>
</TD>
</TR>
</TABLE>
