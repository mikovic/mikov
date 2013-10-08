function setReadOnlyTextAjax() {

    $('#topic').attr('readOnly','true');
    $('#limitField').attr('readOnly','true');
    $('#budget').attr('readOnly','true');
    $('#btn').find('input').css("display","none");
    $('<input id="btn1" class = "btn btn-primary btn-large" value ="CONTINUE?" onclick="send()" >').appendTo('#btn');
    $('<input id="btn2" class="btn btn-primary btn-large" value = "RETURN?" onclick="cancelSetReadOnlyTextAjax()">').css("margin-left", "5px").appendTo('#btn');

}
function cancelSetReadOnlyTextAjax(){

    $('#topic').removeAttr('readOnly');
    $('#limitField').removeAttr('readOnly');
    $('#budget').removeAttr('readOnly');
    $('#btn').find('#btn1').remove();
    $('#btn').find('#btn2').remove();
    $('#btn').find('#btn0').css("display","block");

}

function send()

{


    var dataString = $('#text').serialize();

    $.ajax({

        type: "POST",
        url: "/EditServlet",
        data: dataString,
        dataType: "json" ,
        success: function(jsonexpression) {
            $('#ideaId').attr('value',jsonexpression.ideaId);
            $('#categoryId').attr('value',jsonexpression.categoryId);
            $('#topic').attr('value',jsonexpression.topic);
            $('#limitField').attr('value',jsonexpression.descIdea);
            $('#budget').attr('value',jsonexpression.budget);
            $("#contentBody").text("You successfully added changes in your idea!");

        }

    });
    return false

}

function changePicture(id){

    var dataString = {imgId: id};

    $.ajax ({
        type: "GET",
        url: "/EditPictureServlet",
        data : dataString,
        dataType :"html",
        success : function(html) {
            var form = $('#'+ id).parent();
            var button = $(form).prev();
            $(form).css("display","block");
            $(button).css("display","none")
            var img = $(button).prev().cildren().eq(0);
            $(img).attr("src", "");
            $("#contentBody").text(html);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }



    });
}
function handleResponse(json) {
    var obj = jQuery.parseJSON(json);
    var formId = obj[0];
    var imgId = obj[1];
    var form = $('#'+ formId).parent();
    $(form).css("display","none");
    var button = $(form).prev();
    $(button).css("display","block");
    var img = $(button).prev().cildren().eq(0);
    $(img).attr("src", "DownLoadEditPicture?imgId="+imgId);

}
function handleResponse1(json) {
    var obj = jQuery.parseJSON(json);
    if(obj.length==2){
    var formId = obj[0];
    var imgId = obj[1];
    var form = $('#'+ formId).parent();
    $(form).css("display","none");
    var img = $(form).prev().cildren().eq(0);
    $(img).attr("src", "DownLoadEditPicture?imgId="+imgId);
    }
     else   {
        $('#mes').text(obj)
    }

}


