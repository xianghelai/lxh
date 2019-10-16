var userInfo = {
    id : "userInfoTable", // 表格id
    seItem : null, // 选中的条目
    table : null,
    layerIndex : -1,
    selectObjs : null
};
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
            console.log(typeof gender);
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

function PageCallback(){
    alert("dh");
}
$(function () {

    var queryData= {};
    queryData['loginName'] = $.trim($("#selecteLoginName").val());
    queryData['gender'] = $.trim($("#selectGender").val());
    queryData['beginTime'] = $.trim($("#selectBegin").val());
    queryData['endTime'] = $("#selectEnd").val();
    queryData['userStatus'] = $("#selectStatus").val();
    queryData['province'] = $.trim($("#selectProvince").val());
    console.log(queryData);
    $('#pageLimit').bootstrapPaginator({
        currentPage: 1,
        totalPages: 10,
        size:"normal",
        bootstrapMajorVersion: 3,
        alignment:"right",
        numberOfPages:5,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {//给每个页眉绑定一个事件，其实就是ajax请求，其中page变量为当前点击的页上的数字。
            $.ajax({
                url:'selectByConditions2',
                type:'post',
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify({
                    limit:10,
                    offset:page,
                    order:"",
                    conditionVo:{
                        loginName: queryData.loginName,      //页码
                        gender: queryData.gender,        //每页条数
                        beginTime: queryData.beginTime,
                        endTime: queryData.endTime,
                        userStatus: queryData.userStatus,
                        province: queryData.province,
                    }
                }),
                dataType:'JSON',
                success:function (data) {
                    console.log(data);
                    $("#userInfoTable").html('');
                    $("#userInfoTable").append('<tr>\n' +
                        '        <th>用户id</th>\n' +
                        '        <th>账号</th>\n' +
                        '        <th>密码</th>\n' +
                        '        <th>性别</th>\n' +
                        '        <th>真实姓名</th>\n' +
                        '        <th>联系地址</th>\n' +
                        '        <th>邮箱</th>\n' +
                        '        <th>出生日期</th>\n' +
                        '        <th>联系电话</th>\n' +
                        '    </tr>');
                    var rows =data.rows;
                    var total =data.total;
                    console.log(rows.length);
                    console.log(total);
                    for (i = 0; i < rows.length; i++) {
                        console.log(rows[i].userName);
                        $("#userInfoTable").append('<tr><td>'+rows[i].userId+'</td><td>'+rows[i].loginName+
                            '</td><td>'+rows[i].loginPassword+'</td><td>'+rows[i].gender+'</td><td>'+rows[i].userName+
                            '</td><td>'+rows[i].contactAddress+'</td><td>'+rows[i].contactEmail+'</td><td>'+rows[i].birthday+
                            '</td><td>'+rows[i].contactPhone+'</td></tr>');
                    }
                    $("#userInfoTable").append();
                    $("#userInfoTable").append('');
                    $("#userInfoTable").append('');
                }
            })
        }
    });
});
laydate.render({
    elem: '#birthday' ,//指定元素
    type:'date'
});