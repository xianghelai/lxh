var tableiInit=function () {
    $('#userTable').bootstrapTable({
        //data:tableData,
        url:'selectByConditions',             //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,
        dataType:"json",
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        /*queryParams: 'queryParams',*/         //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        /* pageList: [10, 25, 50, 100],  */      //可供选择的每页的行数（*）
        smartDisplay:false,
        search: false,                      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                 //是否显示所有的列
        showRefresh: false,                 //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "userId",
        dataField: "rows",
        showToggle:false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,
        responseHandler: function(res) {
            return {
                "total": res.total,//总页数
                "rows": res.rows   //数据
            };
        },
        columns: [
            { field: 'userId', title: 'id', align: 'center', valign: 'middle', sortable: true },
            { field: 'loginName', title: '登陆名', align: 'center', valign: 'middle', sortable: true },
            { field: 'loginPassword', title: '登陆密码', align: 'center', valign: 'middle', sortable: true},
            { field: 'gender', title: '性别', align: 'center', valign: 'middle', sortable: true},
            { field: 'contactPhone', title: '联系电话', align: 'center', valign: 'middle', sortable: true},
            { field: 'contactEmail', title: '邮箱', align: 'center', valign: 'middle', sortable: true},
            { field: 'userName', title: '真实姓名', align: 'center', valign: 'middle', sortable: true},
            { field: 'birthday', title: '出生日期', align: 'center', valign: 'middle', sortable: true},
            { field: 'contactAddress', title: '地址', align: 'center', valign: 'middle', sortable: true},
            { field: 'postCode', title: '邮编', align: 'center', valign: 'middle', sortable: true},
            { field: 'userStatus', title: '用户状态', align: 'center', valign: 'middle', sortable: true},
            {field: 'userStatus', title: '操作', align: 'center', valign: 'middle', formatter: actionFormatter }
        ]

    });
}
var userInfo = {
    id : "userTable", // 表格id
    seItem : null, // 选中的条目
    table : null,
    layerIndex : -1,
    selectObjs : null
};

userInfo.search =function () {
    var queryData= {};
    queryData['loginName'] = $.trim($("#selecteLoginName").val());
    queryData['gender'] = $.trim($("#selectGender").val());
    queryData['beginTime'] = $.trim($("#selectBegin").val());
    queryData['endTime'] = $("#selectEnd").val();
    queryData['userStatus'] = $("#selectStatus").val();
    queryData['province'] = $.trim($("#selectProvince").val());
    console.log(queryData);
    $('#userTable').bootstrapTable('refresh', {
        query:
            {
                conditionVo:{
                    loginName: queryData.loginName,      //页码
                    gender: queryData.gender,        //每页条数
                    beginTime: queryData.beginTime,
                    endTime: queryData.endTime,
                    userStatus: queryData.userStatus,
                    province: queryData.province,
                }
            }
    });
}

userInfo.closeLayer=function () {
    layer.close(userInfo.layerIndex);
}
userInfo.updateform = function (userId,loginName,gender,userName,contactAddress,contactEmail,birthday,contactPhone) {
    var index=layer.open({
        type:1,
        title: '修改信息',
        area: ['400px', '550px'], //宽高
        fix: false, //不固定
        content:$('#updateform'),
        success:function () {
            $("#userId").val(userId);
            $("#loginName").val(loginName);
            //$("#loginPassword").val(loginPassword);
            console.log(userId+"  "+loginName);
            $("#gender").val(gender);
            $("#birthday").val(birthday);
            $("#contactEmail").val(contactEmail);
            $("#userName").val(userName);
            $("#contactPhone").val(contactPhone);
            $("#contactAddress").val(contactAddress);
            //$("#gender").val(1);
        }
    });
    this.layerIndex=index;
}
search =function () {
    
}
$(function () {
    $('#userTable').bootstrapTable({
        //data:tableData,
        url:'selectByConditions',             //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,
        dataType:"json",
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        /*queryParams: 'queryParams',*/         //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        /*pageNumber:1, */                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        /* pageList: [10, 25, 50, 100],  */      //可供选择的每页的行数（*）
        smartDisplay:false,
        search: false,                      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                 //是否显示所有的列
        showRefresh: false,                 //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "userId",
        dataField: "rows",
        showToggle:false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,
        responseHandler: function(res) {
            return {
                "total": res.total,//总页数
                "rows": res.rows   //数据
            };
        },
        columns: [
            {field: 'selectItem', radio: true},
            { field: 'userId', title: 'id', align: 'center', valign: 'middle', sortable: true },
            { field: 'loginName', title: '登陆名', align: 'center', valign: 'middle', sortable: true },
            { field: 'loginPassword', title: '登陆密码', align: 'center', valign: 'middle', sortable: true},
            { field: 'gender', title: '性别', align: 'center', valign: 'middle', sortable: true,
                formatter: function (value, row, index) {
                if( value =="1"){
                    return "男";
                }else if( value =="0"){
                    return "女";
                    }
                }
                },
            { field: 'contactPhone', title: '联系电话', align: 'center', valign: 'middle', sortable: true},
            { field: 'contactEmail', title: '邮箱', align: 'center', valign: 'middle', sortable: true},
            { field: 'userName', title: '真实姓名', align: 'center', valign: 'middle', sortable: true},
            { field: 'birthday', title: '出生日期', align: 'center', valign: 'middle', sortable: true},
            { field: 'contactAddress', title: '地址', align: 'center', valign: 'middle', sortable: true},
            { field: 'postCode', title: '邮编', align: 'center', valign: 'middle', sortable: true},
            { field: 'userStatus', title: '用户状态', align: 'center', valign: 'middle', sortable: true},
            { field: 'province', title: '省份', align: 'center', valign: 'middle', sortable: true},
            {field: '', title: '操作', align: 'center', valign: 'middle', formatter:  actionFormatter

    }
        ]

    });

    $("#inputForm").validate({
        rules: {
            test:{
                required:true
            }
            /*selectBegin:{
                required: false
            },
            selectEnd:{
                required: false,
                compareDate:"#selectBegin"
            }*/
        },
        messages: {
            test:{
                required:"请输入内容"
            }
            /*selectBegin:{
                required: "请选择开始时间",
            },
            selectEnd:{
                required: "请选择结束时间",
                rangelength:"结束时间不能小于开始时间"
            }*/
        },
        onsubmit:true// 是否在提交时验证
    });

});

EditViewById =function (id) {
    var index = layer.open({
        type: 1,
        title: '修改',
        area: ['600px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: $("#updateForm")
    });
    index();
}
DeleteByIds =function (id) {
    
}
laydate.render({
    elem: '#birthday' ,//指定元素
    type:'date'
});
laydate.render({
    elem: '#selectBegin' ,//指定元素
    type:'date'
});
laydate.render({
    elem: '#selectEnd' ,//指定元素
    type:'date'
});

//搜索框重置
userInfo.reset =function () {
    $("#selecteLoginName").val("");
    $("#selectBegin").val("");
    $("#selectEnd").val("");
    $("#selectStatus").val("");
    $("#selectProvince").val("")
}
//删除事件
userInfo.DeleteById =function (userId) {
    $.ajax({
        //请求方式
        type : "post",
        //请求的媒体类型
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",

        url : "delUser",
        //数据，json字符串
        data : {
            "userId":userId
        },
        dataType: "JSON",
        //请求成功
        success : function(result) {
            if(result.msg=="error"){
                window.location.href="error/用户未禁用无法删除";
            }else{
                window.location.href="userInfo2";
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
            console.log("ajax请求错误");
            window.location.href="error/";

        }
    });
}

jQuery.validator.addMethod("compareDate", function(value, element,param) {
    console.log(typeof value);
    console.log(typeof param);
    return false;

},"结束时间不能小于开始时间");