

function back(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
}


function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "../save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		success : function(data) {
			if (data.errorCode == "1") {

				parent.layer.msg(data.errorMsg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.errorMsg)
			}

		}
	});

}
