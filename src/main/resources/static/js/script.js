
function openImagePreviewDiv(url, name) {
	$("#imagePreviewId").attr("src", url);
	var title = "图片预览";
	if (name) {
		title = title + "：" + name;
	}
	parent.layer.open({
		type: 1,
		area: ['550px','490px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: $("#imagePreviewDiv").html()
	});
}

/*function checkSessionLogin(userId, loginUrl) {
	if (window != top && userId == "") {
		top.location.href = loginUrl;
	}
}*/

/**
 * 附件下载
 * 
 * @param id
 * @returns
 */
function downloadAttachment(fileUrl,fileName) {
//	fileName = fileName + "关注二维码"
	console.log(fileName)
	if (fileUrl == null || fileUrl == "") {
		return;
	}
	window.location.href = basePath + "common/sysFile/downloadFile.json?fileUrl=" + fileUrl+"&fileName="+fileName;
	/*$.ajax({
		cache : true,
		type : "GET",
		url : basePath + "common/sysFile/downloadFile.json?fileUrl="+fileUrl,
		data : {},
		async : false,
		success : function(data) {
			console.log(data);
			if (data.errorCode == "0") {
				parent.layer.msg(data.errorMsg);

			} else {
				parent.layer.alert(data.errorMsg)
			}

		}
	});*/
	
}

/**
 * word上传
 * 
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @param spanRowsId
 * @returns
 */
function uploadWord(inputFileId, inputHiddenId, inputNameId, spanRowsId) {
	uploadFile("word", inputFileId, inputHiddenId, inputNameId, null, null,null);
}

/**
 * excel上传
 * 
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @param spanRowsId
 * @returns
 */
function uploadExcel(inputFileId, inputHiddenId, inputNameId, spanRowsId,importDataType) {
	uploadFile("excel", inputFileId, inputHiddenId, inputNameId, null, spanRowsId,null,null,importDataType);
}

/**
 * 图片上传
 * 
 * @param imgId
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @returns
 */
function uploadImage(inputFileId, inputHiddenId, inputNameId, imgId) {
	uploadFile("image", inputFileId, inputHiddenId, inputNameId, imgId, null,null);
}

/**
 * 附件上传
 * 
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @returns
 */
function uploadAttachment(inputFileId, inputHiddenId, inputNameId) {
	uploadFile("file", inputFileId, inputHiddenId, inputNameId, null, null,null,null);
}
/**
 * 附件上传(返回文件名)
 *
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @param returnNameId 返回文件名input接受id
 * @param displayNameId  回显文件名span的id
 * @returns
 */
function uploadAttachment1(inputFileId, inputHiddenId, inputNameId,returnNameId,displayNameId) {
	uploadFile("file", inputFileId, inputHiddenId, inputNameId, null, null, returnNameId,displayNameId,null);
}

/**
 * 文件上传，内部使用供其他方法重载
 * 
 * @param fileType
 * @param inputFileId
 * @param inputHiddenId
 * @param inputNameId
 * @param imgId
 * @param spanRowsId
 * @returns
 */
function uploadFile(fileType, inputFileId, inputHiddenId, inputNameId, imgId, spanRowsId, returnNameId,displayNameId,importDataType) {
	var browser = getBrowserInfo();
	if (browser == "IE0" || browser == "IE7" || browser == "IE8" || browser == "IE9" || browser == "IE10") {
		layer.alert("附件上传不支持该浏览器");
		return;
	}
	var filePath = $("#" + inputFileId).val();
	if(!filePath)
		return;
	var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length);
	var loading = $("<img src='" + basePath + "static/img/ajaxLoading.gif' id='fileLoading' />");
	loading.css("position", "absolute").css("left", "50%").css("top", "45%").css("margin", "0 0 0 -32px").css("width", "64px").css("height", "64px").css("z-index", "99");
	$("#" + inputFileId).parent().append(loading);
	if (inputNameId)
		$("#" + inputNameId).html("");
	if (inputHiddenId)
		$("#" + inputHiddenId).val("");
	if (imgId)
		$("#" + imgId).attr("src", "");
	if (spanRowsId)
		$("#" + spanRowsId).html("");
	$.ajaxFileUpload({
		url : basePath + 'common/sysFile/uploadFile', // 用于文件上传的服务器端请求地址
		secureuri : false, // 是否需要安全协议，一般设置为false
		fileElementId : inputFileId, // 文件上传域的ID
		dataType : 'json', // 返回值类型 一般设置为json
		data : {
			fileType : fileType,
            importDataType : importDataType
		},
		success : function(data, textStatus) {
		    console.log(data)
		    console.log("errorCode:"+data.errorCode)
            console.log("errorMsg:"+data.errorMsg)
			console.log("textStatus:"+textStatus)
			console.log("data:"+data.errorCode)
			console.log("fileName:"+data.fileName)
			// debugger;
			$("#fileLoading").remove();
			if (textStatus == "success") {
				if (data == false) {
					layer.alert("文件上传失败");
					return;
				}
				if (data.errorCode == "1") {
					layer.alert("操作成功："+data.data.resultStr,{icon:6});
					reLoad();
					return;
				}
				if (data.errorCode == "0") {
					layer.alert("操作结果："+data.data.resultStr,{icon:6});
					reLoad();
					return;
				}
				if (data.errorCode == "-1") {
                    layer.alert("操作失败："+data.errorMsg,{icon:5});
					return;
				}
				
				/*if (inputNameId)
					$("#" + inputNameId).html(fileName);*/
				if (inputHiddenId)
					$("#" + inputHiddenId).val(data.fileUrl);
				if (imgId)
					$("#" + imgId).attr("src", data.fileUrl);
				    $("#" + imgId).show();
				if (spanRowsId)
					$("#" + spanRowsId).html(data.excelRows).parent().show();
				if (returnNameId)
					$("#" + returnNameId).val(data.fileName);
				if (displayNameId)
					$("#" + displayNameId).text(data.fileName);
			}
		},
		error : function(error,data, status, e) {
			layer.alert("上传失败，请检查模板数据");
			console.log("error status:"+ status)
			console.log("eror errorCode:"+data.errorCode)
			console.log("eror data:"+data.data)
			console.log("data:"+data)
			console.log("e："+e)
			$("#fileLoading").remove();
			
			if (inputNameId)
				$("#" + inputNameId).html("");
			if (inputHiddenId)
				$("#" + inputHiddenId).val("");
			if (imgId)
				$("#" + imgId).attr("src", "");
			if (spanRowsId)
				$("#" + spanRowsId).html("");
			
			return ;
			
		}
	});
}

/**
 * 获取浏览器信息（类型+版本）
 * 
 * @returns
 */
function getBrowserInfo() {
	var userAgent = navigator.userAgent; // 取得浏览器的userAgent字符串
	var isOpera = userAgent.indexOf("Opera") > -1; // 判断是否Opera浏览器
	var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; // 判断是否IE浏览器
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; // 判断是否IE的Edge浏览器
	var isFF = userAgent.indexOf("Firefox") > -1; // 判断是否Firefox浏览器
	var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; // 判断是否Safari浏览器
	var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; // 判断Chrome浏览器
	if (isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if (fIEVersion == 7) {
			return "IE7";
		} else if (fIEVersion == 8) {
			return "IE8";
		} else if (fIEVersion == 9) {
			return "IE9";
		} else if (fIEVersion == 10) {
			return "IE10";
		} else if (fIEVersion == 11) {
			return "IE11";
		} else {
			return "IE0"
		}// IE版本过低
	}
	if (isFF) {
		return "Firefox";
	}
	if (isOpera) {
		return "Opera";
	}
	if (isSafari) {
		return "Safari";
	}
	if (isChrome) {
		return "Chrome";
	}
	if (isEdge) {
		return "Edge";
	}
}

/**
 * 从url上获取id参数的值
 * 
 * @returns
 */
function getIdFromUrl() {
	// 获取ID
	var id = "";
	var str = location.href; // 取得整个地址栏
	var num = str.indexOf("?");
	str = str.substr(num + 1); // 取得所有参数 stringvar.substr(start [, length ]
	var arr = str.split("&"); // 各个参数放到数组里
	for (var i = 0; i < arr.length; i++) {
		num = arr[i].indexOf("=");
		if (num > 0) {
			name = arr[i].substring(0, num);
			value = arr[i].substr(num + 1);
			if (name == "id") {
				id = value;
			}
		}
	}
	return id;
}

function parseContent(content, max) {
	if (max == undefined) {
		max = 10;
	}
	if (content.length > max) {
		return '<span title="' + content + '">' + content.substring(0, max) + '...</span>';
	}
	return content;
}
