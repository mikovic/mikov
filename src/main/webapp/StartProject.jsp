<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.cassiopeya.dto.User" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>



<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="jquery.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/thirdpartyapplicationjs/ractoon/textcounter.jqu"></script>
    <script src="/myfilejs/myfunction.js"></script>
    <script src="/jquery.addInputFile.js"></script>
     <script src="/thirdpartyapplicationjs/jquery.MultiFile.js."></script>
    <script>
        $("input:file").on("change", addInputFile());
    </script>

    <script>
        $(function() {
            $( "#datepicker" ).datepicker();
        });
    </script>
    <script>
        $("#textarea").textcounter({
            type: "word",
            max: 5,
            stopInputAtMaximum: false
        });
    </script>
    <script>
        $('.MultiFile').MultiFile({
            accept:'jpg|gif|bmp|png|rar', max:15, STRING: {
                remove:'удалить',
                selected:'Выбраны: $file',
                denied:'Неверный тип файла: $ext!',
                duplicate:'Этот файл уже выбран:\n$file!'
            }});
    </script>

    <title>My Profile</title>
</head>
<body>
<div class="container-fluid">
    <div class="well">
        <div class="row-fluid">
            <div class="span12">
                <jsp:include page = "Header.jsp" flush = "true"/>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <form class="form-horizontal" action="StartProject" method="post" enctype="multipart/form-data"  onsubmit="trimAll(this.descIdea.value)">
                    <input type="hidden" name="userId" value='<c:out value="${sessionScope.user.userId}"/>'>
                    <div class="control-group">
                        <label class="control-label">Choice category:</label>
                        <div class="controls">
                            <select name="categoryId">
                                <c:forEach var="category" items="${requestScope.categories}">
                                    <option  value='<c:out value = "${category.key}"/>'>
                                        <c:out value = "${category.value}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Topic of idea</label>
                        <div class="controls">
                            <input type="text" name="topicIdea" class="span6" maxlength="15">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Describe of idea</label>
                        <div class="controls">
                            <textarea name="descIdea"  rows="5" class="span6" id="limitField" onkeypress="limitText(100)"></textarea>
                        </div>
                        <div class="controls">
                            <input readonly type="text" name="limitCount" id="limitCount" size="3" value="100">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Budget of idea</label>
                        <div class="controls">
                            <input type="text" name="budget" maxlength="10" onchange="validateNumber(this.budget.value)">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Files to upload</label>
                        <div class="controls">
                            <div id="files">
                            <input type="file" class="multi" type='file' onchange="addInputF()" style="display: block" name="uploadFile" id="MultiFile"  accept="image/*,image/jpeg" >
                            </div>
                            <div id="mes"></div>
                            <div id="imgs"></div>
                            <input type="button"  onclick="removeFile()" value="Remove file?">
                        </div>
                    </div>
                    <button class="btn btn-large btn-primary" id="button" type="reset">Reset</button>
                    <button class="btn btn-large btn-primary" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>