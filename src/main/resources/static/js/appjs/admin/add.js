var fileInput = document.getElementById("file");
//选择文件
fileInput.addEventListener('change', function () {

    //如果未传入文件则中断
    if (fileInput.files[0] == undefined) {
        return;
    }

    var file = fileInput.files[0];
    $("#name").val(file.name);


    var formData = new FormData();
    formData.append("file", $("#file")[0].files[0]);
    $.ajax({
        url: 'upload',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.errorCode == 0) {
                $('#imgg').attr("src", data.data);
                $("#image").val(data.data);
            }
        }
    });

})

var thum = document.getElementById("thum");
//选择文件
thum.addEventListener('change', function () {

    //如果未传入文件则中断
    if (thum.files[0] == undefined) {
        return;
    }

    var formData = new FormData();
    formData.append("file", thum.files[0]);
    $.ajax({
        url: 'upload',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.errorCode == 0) {
                $('#thumImg').attr("src", data.data);
                $('#thumUrl').val(data.data);
            }
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

            if (data.errorCode == 0) {
                parent.layer.msg(data.errorMsg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert("保存数据失败")
            }

        }
    });

}
