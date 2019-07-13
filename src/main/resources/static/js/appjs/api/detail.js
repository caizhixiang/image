$(function () {

    if ($("#code").val()) {
        // $('#summernote').summernote('code', $("#code").val());
        $("#summernote").append($("#code").val());
    }
})
$(function () {
    var imglist=$("#summernote img");//获取ID为div里面的所有img
    for(var i=0;i<imglist.length;i++){ //循环为每个img设置
        var maxWidth=$(window).width();
        let width = imglist[i].width;
        if (width > maxWidth) {
            $(imglist[i]).removeAttr("width");
            $(imglist[i]).css({"width":"100%"});
        }

    }
})
