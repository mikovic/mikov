
function limitText(limitNum) {
    if (document.getElementById('limitField').value.length > limitNum) {
        document.getElementById('limitField').value = document.getElementById('limitField').value.substring(0, limitNum);
    } else {
        document.getElementById('limitCount').value = limitNum - document.getElementById('limitField').value.length;
    }
}
function validateNumber(str) {
    var pattern = "0123456789";
    var i =0;
    do {
        var pos = 0;
        for (var j =0; j<pattern.length; j++)
            if (str.charAt(i)==pattern.charAt(j)) {
                pos = 1;
                break;
            }
        i++
    } while (ps==1&& i<str.length)
    if (pos==0) {
        alert('Budget must have a number');
        return false;
    }

    return true;
}
function isEmpty(str) {
    if (str == null || trim(str)=="")
        return true;
    return false;
}
function validate(userLogin, password) {
    if (isEmpty(userLogin)) {
        alert('User login must have a value');
        return false;
    }
    if (isEmpty(password)) {
        alert('Password must have a value')
        return false
    }
    return true;
}
function trim(str)  {
    if (str!=null) {
        while (str.charAt(str.length - 1)==" ")
            str = str.substring(0, str.length - 1);
        while (str.charAt(0)==" ")
            str = str.substring(1,str.length);
    }
    return str;
}
function TrimStr(s) {
    s = s.replace( /^\s+/g, '');
    return s.replace( /\s+$/g, '');
}
function trimAll(str)  {
    if (str!=null){
        while (str.length > 0 &&
            "\n\r\t".indexOf(str.charAt(str.length - 1))!= -1)
            str = str.substring(0, str.length - 1);
        while (str.length > 0 &&

            "\n\r\t".indexOf(str.charAt(0))!= -1)
            str = str.substring(1, str.length);
    }
    return str;
}
function ToggleVisibility(element) {
    var element = document.getElementById(element);
    if (element.style.display = "none"){
        element.style.display = "block"
    }
    else
    {
        element.style.display = "none"
    }
}

function addInput(){
    var imgs = document.getElementById('imgs');
    var files = document.getElementById('files');
    var elementsInput = document.getElementsByClassName('multi');
    var cunt = elementsInput.length;
    var route = elementsInput[cunt-1].value;
    var name = elementsInput[cunt-1].getAttribute("name");
    var newImg = document.createElement("img");
    newImg.src = route;
    newImg.style = "width:100px;height:50px;";
    imgs.appendChilde(newImg);
    var newA =  document.createElement("a");
    newA.innerHTML ='Add file?';
    form.insertBefore(newA,imgs);
    newA.onclick = function(){
        if ( elementsInput[cunt-1].style.display=="block"){
            elementsInput[cunt-1].style.display="none"
        }
        name=name+cunt;
        var container=document.createElement("div");
        container.innerHTML='<input type="file" class="multi" style="display: block" onchange="addInput()">'
        var newInputFile=container.firstChild
        newInputFile.name=name
        files.appendChild(newInputFile);
        form.removeChild(newA);
    }

}
function addInputF(){
    var imgs = document.getElementById('imgs');
    var mes = document.getElementById('mes');
    var files = document.getElementById('files');
    var elementsInput = files.getElementsByTagName('input');
    var cunt = elementsInput.length;
    var route = elementsInput[cunt-1].value;
    var boolean = false;
    if(cunt>1){
        for (var i = 0;i<cunt-1;i++) {
            if(route==elementsInput[i].value){
                boolean= true;
                elementsInput[cunt-1].value = null;
                return;
            }

        }
    }
    if(boolean==true) return;
    var name = elementsInput[0].getAttribute("name");
    var newImg = document.createElement("img");
    newImg.src = route;
    newImg.style = "width:100px;height:50px;border:1px;margin:5px 8px 5px";
    imgs.appendChild(newImg);
    if ( elementsInput[cunt-1].style.display== "block"){
        elementsInput[cunt-1].style.display="none";
    }
    if(cunt<3) {
        var container=document.createElement("div")
        container.innerHTML='<input type="file" class="multi" onchange="addInputF()" style="display: block">'
        var newInputFile=container.firstChild;
        name=name+cunt;
        newInputFile.name=name;
        files.appendChild(newInputFile);

    }
    if(cunt==3){
        var newSpan1=document.createElement("span");
        newSpan1.innerHTML='You can choose only 3 files!';
        mes.appendChild(newSpan1);
    }
}
function removeFile(){
    var imgs = document.getElementById('imgs');
    var files = document.getElementById('files');
    var elementsImg = imgs.getElementsByTagName('img');
    var elementsInput = files.getElementsByTagName('input');
    var name = "uploadFile";
    var cunt = elementsImg.length;
    if(cunt==0){
        return
    }
    imgs.removeChild(elementsImg[cunt-1]);
    files.removeChild(elementsInput[cunt-1]);
    elementsInput= document.getElementById('files').getElementsByTagName('input');
    var cuntNewInput = elementsInput.length;

    if(1<cunt&&cunt<3){
        name=name+cuntNewInput;
        elementsInput[cuntNewInput-1].name = name;
    }
    if(cunt==1) {
        elementsInput[cuntNewInput-1].name = name;
    }
    if(cunt==3){
        var mes = document.getElementById('mes');
        var message=mes.getElementsByTagName('span');
        mes.removeChild(message[0]);
        var container=document.createElement("div");
        container.innerHTML='<input type="file" class="multi" onchange="addInputF()" style="display: block">'
        var newInputFile=container.firstChild;
        newInputFile.name=name;
        files.appendChild(newInputFile);

    }
}

function setReadOnlyText() {
    var topic = document.getElementById('topic');
    var textarea = document.getElementById('limitField');
    var budget = document.getElementById('budget');
    topic.readOnly = true;
    textarea.readOnly = true;
    budget.readOnly = true;
    var btn = document.getElementById('btn');
    var input = btn.getElementsByTagName('input')[0];
    input.style.display="none"
    var button1 = document.createElement('input');
    var button2 = document.createElement('input');
    button1.className = "btn btn-primary btn-large";
    button1.value ="CONTINUE?";
    button1.type = "submit"
    button2.className = "btn btn-primary btn-large";
    button2.value ="RETURN?";
    var span1 = document.createElement('span');
    var span2 = document.createElement('span');
    span2.style = "margin-left: 5px;"
    span1.appendChild(button1);
    span2.appendChild(button2);
    btn.appendChild(span1);
    btn.appendChild(span2);
    button2.onclick = function(){
        topic.readOnly = false;
        textarea.readOnly = false;
        budget.readOnly = false;
        btn.removeChild(span1);
        btn.removeChild(span2);
        input.style.display="block";
    }
    /*  function setReadOnlyText() {
     var topic = document.getElementById('topic');
     var textarea = document.getElementById('limitField');
     var budget = document.getElementById('budget');
     topic.readOnly = true;
     textarea.readOnly = true;
     budget.readOnly = true;
     var btn = document.getElementById('btn');
     var button = btn.getElementsByTagName('input')[0];
     var button1 = btn.getElementsByTagName('input')[1];
     var button2 = btn.getElementsByTagName('input')[2];
     button.style.display="none";
     button1.style.display="block";
     button2.style.display="block";

     }
     function cancelSetReadOnlyText() {
     var topic = document.getElementById('topic');
     var textarea = document.getElementById('limitField');
     var budget = document.getElementById('budget');
     var btn = document.getElementById('btn');
     var button = btn.getElementsByTagName('input')[0];
     var button1 = btn.getElementsByTagName('input')[1];
     var button2 = btn.getElementsByTagName('input')[2];
     topic.readOnly = false;
     textarea.readOnly = false;
     budget.readOnly = false;
     button.style.display="block";
     button1.style.display="none";
     button2.style.display="none";
     }  */
    function getXMLHttpRequest() {
        var xmlHttpReq = false;
        // to create XMLHttpRequest object in non-Microsoft browsers
        if (window.XMLHttpRequest) {
            xmlHttpReq = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            try {
                // to create XMLHttpRequest object in later versions
                // of Internet Explorer
                xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (exp1) {
                try {
                    // to create XMLHttpRequest object in older versions
                    // of Internet Explorer
                    xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (exp2) {
                    xmlHttpReq = false;
                }
            }
        }
        return xmlHttpReq;
    }
    /*
     * AJAX call starts with this function
     */
    function makeRequest() {
        var xmlHttpRequest = getXMLHttpRequest();
        var params = 'editText=' + encodeURIComponent(editText) +
            '&ideaId=' + encodeURIComponent(ideaId )+
            '&categoryId=' + encodeURIComponent(categoryId) +
            '&topicIdea=' + encodeURIComponent(topicIdea) +
            '&descIdea=' + encodeURIComponent(descIdea) +
            '&budget=' + encodeURIComponent(budget);
        xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
        xmlHttpRequest.open("POST", "EditServlet", true);
        xmlHttpRequest.setRequestHeader("Content-Type",
            "application/x-www-form-urlencoded");
        xmlHttpRequest.send(params);
    }

    /*
     * Returns a function that waits for the state change in XMLHttpRequest
     */
    function getReadyStateHandler(xmlHttpRequest) {


        return function() {
            if (xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {


                  var jsonExpression =  xmlHttpRequest.responseText;
                    var ideaEdit = JSON.parse(jsonExpression);

                /*    var ideaEdit = eval("("+jsonExpression+")");   */
                 /*   document.getElementById('ideaId').value = ideaEdit.ideaId;
                    document.getElementById('topicIdea').value = ideaEdit.topicIdea;
                    document.getElementById('limitField').value = ideaEdit.descIdea;
                    document.getElementById('categoryId').value = ideaEdit.categoryId;
                    document.getElementById(' budget').value = ideaEdit.budget;
                    var btn = document.getElementById('btn');
                    var input = btn.getElementsByTagName('input')[0];
                    var span1 = btn.getElementsByTagName('span')[0];
                    var span2 = btn.getElementsByTagName('span')[1];
                    btn.removeChild(span1);
                    btn.removeChild(span2);
                    input.style.display="block"; */

                   alert(ideaEdit);


                } else {
                    alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
                }
            }
        };
    }
}




