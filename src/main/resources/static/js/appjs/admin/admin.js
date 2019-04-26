$(function() {
    load();
    $("#sub").click(function () {
        reLoad();
    })
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or
                // post
                url : "findPage", // 服务器数据的加载地址
                // showRefresh : true,
                // showToggle : true,
                showColumns : true,
                cache : false,
                iconSize : 'outline',
                // toolbar : '#toolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                sortOrder : "desc", // 排序方式
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType :
                // "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client"//
                paginationPreText : "上一页",
                paginationNextText : "下一页",
                paginationFirstText : "第一页",
                paginationLastText : "最后一页",
                queryParamsType : "",
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        pageSize: params.pageSize,   //每页多少条数据
                        pageNo: params.pageNumber, // 页码

                        position : $('#position').val(),

                    };
                },
                responseHandler : function (res) {
                    if (res.errorMsg){
                        layer.msg(res.errorMsg);
                        return {"total":0,"rows":[]};
                    }
                    if (res.total==0){
                        return {"total":0,"rows":[]}
                    }else {
                        return {
                            "rows": res.list,
                            "total": res.total
                        }
                    }
                },
                columns : [
                   /* {
                        checkbox : true
                    },*/

                    {
                        field : 'name',
                        title : '图片名',
                        align : 'center'
                    },
                    {
                        field : 'url',
                        title : '图片',
                        align : 'center',
                        formatter:function (value,row,index) {

                            if (value) {
                                value = "data:image/jpeg;base64," + value;
                                return "<img class='pimg' data-action=\"zoom\" src='"+value+"' style='width: 100px;height: 100px;'/>";
                            }
                        }
                    },
                    {
                        field : 'description',
                        title : '图片描述',
                        align : 'center'
                    },
                    {
                        field : 'link',
                        title : '图片的链接地址',
                        align : 'center'
                    },
                    {
                        field : 'position',
                        title : '图片所处位置',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value =='21001') {
                                return '<span class="label label-danger">案场负责人</span>';
                            } else if (value =='21002') {
                                return '<span class="label label-primary">吧台服务员</span>';
                            }else if (value =='21003') {
                                return '<span class="label label-primary">投诉/报修服务员</span>';
                            }
                        }
                    },
                    {
                        field : 'sort',
                        title : '排序'
                    },
 {
                        field : 'gmtCreate',
                        title : '创建时间'
                    },

                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var d = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit ">编辑</i></a> ';

                            var e = '<a class="btn btn-warning btn-sm " href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove">删除</i></a> ';
                            return d + e;
                        }
                    }

                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function clean_form(){
    $(":input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");// 核心
}

function add() {
    // iframe层
    layer.open({
        type : 2,
        title : '添加用户',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '420px' ],
        content : 'add' // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.errorCode == 1) {
                    layer.msg(r.errorMsg);
                    reLoad();
                } else {
                    layer.msg(r.errorMsg);
                }
            }
        });
    })

}

/*编辑*/
function edit(id) {
    layer.open({
        type : 2,
        title : '修改用户信息',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '400px' ],
        content : 'edit/' + id // iframe的url
    });
}

function exportsAll() {
    if ($("td").hasClass("dataTables_empty")) {
        layer.msg("列表无数据");
        return;
    }
    window.top.layer.open({
        content : '是否导出所有数据?',
        btn : [ '确认', '取消' ],
        closeBtn : false,
        shadeClose : true,
        yes : function(index, layero) {
            window.location.href = "liable/exportData?companyId=" + $('#companyName').val() + "&buildingId=" + $('#projectName').val() + "&position="
                + $('#position').val() + "&phone=" + $('#phone').val()
            window.parent.layer.close(index);
        }
    });
}


    function click11(o){
        var large_image = '<img src= ' + o.src + '></img>';
        $('#dialog_large_image').html($(large_image));

    }


function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}