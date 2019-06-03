window.operateEvents = {
    'click .editBtn': function (e, value, row, index) {
        layer.open({
            type: 2,
            title: '修改用户信息',
            maxmin: true,
            shadeClose: true, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: 'edit/' + row.id // iframe的url
        });
    },
    'click .removeBtn': function (e, value, row, index) {
        layer.confirm('确定要删除选中的记录？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                url: "remove",
                type: "post",
                data: {
                    'id': value
                },
                success: function (r) {
                    if (r.errorCode == 0) {
                        layer.msg(r.errorMsg);
                        $('#table').bootstrapTable('refresh')
                    } else {
                        layer.msg(r.errorMsg);
                    }
                }
            });
        })
    }
}
$(function () {
    $("#sub").click(function () {
        $('#table').bootstrapTable('refresh')
    })
    $('#table').empty();
    $('#table').bootstrapTable('destroy').bootstrapTable({
        url: 'findPage',   //url一般是请求后台的url地址,调用ajax获取数据。此处我用本地的json数据来填充表格。
        method: "get",                     //使用get请求到服务器获取数据
        dataType: "json",
        contentType: 'application/json,charset=utf-8',
        toolbar: "#toolbar",                //一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar, .toolbar.
        // uniqueId: "id",                    //每一行的唯一标识，一般为主键列
        cache: false,                       // 不缓存
        striped: true,                      // 隔行加亮
        queryParamsType: "limit",           //设置为"undefined",可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                                            //设置为"limit",符合 RESTFul 格式的参数,可以获取limit, offset, search, sort, order
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        sortable: true,                     //是否启用排序;意味着整个表格都会排序
        sortName: 'sort',                    // 设置默认排序为 name
        sortOrder: "asc",                   //排序方式
        pagination: true,                   //是否显示分页（*）
        // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        // strictSearch: true,
        // showColumns: true,                  //是否显示所有的列
        // showRefresh: true,                  //是否显示刷新按钮
        // clickToSelect: true,                //是否启用点击选中行
        pageNumber: 1,                   //初始化加载第一页，默认第一页
        pageSize: 10,                    //每页的记录行数（*）
        // dataShowPaginationSwitch: true,
        pageList: [10, 25, 50, 100],     //可供选择的每页的行数（*）
        queryParams: function (params) {
            var param = {
                pageNo: Math.ceil(params.offset / params.limit) + 1,
                pageSize: params.limit,
                order: params.order,
                orderName: params.sort,
                position: $("#position").val()
            };
            return param;
        },
        responseHandler: function (e) {
            return {"rows": e.list, "total": e.total};
        },
        onLoadSuccess: function (data) { //加载成功时执行
            console.log(data);
        },
        onLoadError: function (res) { //加载失败时执行
            console.log(res);
        },
        columns: [
            /* {
                 checkbox: true
             },*/
            {
                title: '',//标题  可不加
                align: 'center',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },
            {
                field: 'name',
                title: '图片名',
                align: 'center',
                sortable: false   //本列不可以排序
            },
            {
                field: 'url',
                title: '图片',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return '<img class="pimg" src="' + value + '" style="width: 100px;height: 100px;"/>';
                    }
                }
            },
            {
                field: 'description',
                title: '图片描述',
                align: 'center',
            },
            {
                field: 'link',
                title: '图片的链接地址',
                align: 'center',
                halign: 'center' //设置表头列居中对齐
            },
            {
                field: 'position',
                title: '图片所处位置',
                align: 'center',
                formatter: function (value, row, index) {
                    if (value == '1') {
                        return '<span class="label label-danger">轮播图</span>';
                    } else if (value == '2') {
                        return '<span class="label label-primary">首页普通图</span>';
                    } else if (value == '3') {
                        return '<span class="label label-primary">详情图</span>';
                    }
                }
            },
            {
                field: 'sort',
                title: '排序',
                align: 'center',
                sortable: true,
                sortName: 'sort'
            },
            {
                field: 'gmtCreate',
                title: '创建时间',
                align: 'center'
            },
            {
                field: 'id',
                title: '操作',
                align: 'center',
                valign: 'middle',
                events: window.operateEvents,
                formatter: function (value, row, index) {//赋予的参数
                    return [
                        '<button class="btn btn-info btn-sm rightSize editBtn" type="button"><i class="fa fa-edit"></i> 编辑</button>',
                        '<button class="btn btn-danger btn-sm rightSize removeBtn" type="button"><i class="fa fa-remove"></i> 删除</button>'
                    ].join('');
                } //自定义方法，添加操作按钮
            }
        ],
    });


})


function clean_form() {
    $(":input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");// 核心
}

function add() {
    // iframe层
    layer.open({
        type: 2,
        title: '新增图片',
        maxmin: true,
        shadeClose: true, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: 'add' // iframe的url
    });
}

function reLoad() {
    $('#table').bootstrapTable('refresh')

}