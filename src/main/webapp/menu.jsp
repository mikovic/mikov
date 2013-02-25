 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<TABLE CELLSPACING = "0" CELLPADDING = "5" WIDTH ="150" BORDER ="0">
 <TR VALIGN ="TOP">
  <TD BGCOLOR = "F6F6F6">
<c:forEach var="category" items="${categories.entrySet}">
      <A HREF = /ViewCategories?categoryId = <c:out value = $ {category.key}> >
            <c:out value = $ {category.value}></A><BR>
  </c:forEach>

  </TD>
 </TR>
</TABLE>