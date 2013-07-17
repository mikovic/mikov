
(function($){
    jQuery.fn.addInputFile = function(){
        var cunt = $("#files>input:file").length;
        var name =  $("#files>input:file:first").attr("name");
        var route = $("#files>input:file:last").val();

        $("<img>").attr({
            "src": route,
            "style":"width:100px;height:50px;",
            "border":"1px",
             "margin":"5px 5px 5px"
            }
        ).appendTo("#imgs");

        var varA = $("<a>").text("Add file?").wrap(<div></div>).insertBefore("#imgs");
            varA.on("click",function(){
            $("#files>input:file:last").hide();
            name=name+cunt;
            var $clone =  $("#files>input:file:first").clone(true).attr({"name":name,"val":"null"}).appendTo("#imgs");
        })
    };
})(jQuery);