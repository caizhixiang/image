
var fileInput = document.getElementById("file");
//选择文件
fileInput.addEventListener('change', function () {

    //如果未传入文件则中断
    if (fileInput.files[0] == undefined) {
        return;
    }

    var file = fileInput.files[0];

    //FileReader可直接将上传文件转化为二进制流
    var reader = new FileReader();
    reader.readAsDataURL(file);//转化二进制流，异步方法
    reader.onload = function () {//完成后this.result为二进制流
        //页面显示文件名
        $("#name").val(file.name);
        $("#url").val(this.result);
        $('img').attr("src", this.result);

    }


    var formData = new FormData();
    formData.append("file", $("#file")[0].files[0]);
    $.ajax({
        url: 'upload',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        success: function (msg) {
            $("#imageId").val(msg);
            debugger;
        }
    });

})


function back() {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}



function save() {



    $.ajax({
        cache: true,
        type: "POST",
        url: "saveOrUpdate",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        success: function (data) {
            if (data == "success") {

                parent.layer.msg("保存成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert("保存数据失败")
            }

        }
    });

}
