$(function() {
    load();
    $("#sub").click(function () {
        reLoad();
    })
});

function load() {
    $('#exampleTable').bootstrapTable()
    $('#exampleTable').bootstrapTable(
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
                        title: '',//标题  可不加
                        align: 'center',
                        formatter: function (value, row, index) {
                            return index+1;
                        }
                    },
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
                                return "<img class='pimg' src='"+value+"' style='width: 100px;height: 100px;'/>";
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
                            if (value =='1') {
                                return '<span class="label label-danger">轮播图</span>';
                            } else if (value =='2') {
                                return '<span class="label label-primary">首页普通图</span>';
                            }else if (value =='3') {
                                return '<span class="label label-primary">详情图</span>';
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
                if (r.errorCode == 0) {
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

