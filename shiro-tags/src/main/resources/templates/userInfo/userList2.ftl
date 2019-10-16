<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/laydate.css">

    <link rel="stylesheet" href="static/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css" >
    <script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
    <script src="static/js/laydate.js"></script>
    <script src="static/js/bootstrap.min.js" ></script>
    <script src="static/js/bootstrap-table.min.js"></script>
    <script src="static/js/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" src="static/js/layer.js"></script>
    <script src="static/js/additional-methods.js"></script>
    <script src="static/js/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <title>用户列表</title>
</head>
<body>
<h2 align="center">用户列表页</h2>
<@shiro.user>
<div align="right" style="margin-right: 50px;margin-top: 3px">
欢迎[<@shiro.principal  property="loginName"/>]登录，<a class="btn btn-primary" href="logout" >退出</a>
</div>
</@shiro.user>

<@shiro.hasRole name="super">
<div align="right" style="margin-right: 50px;margin-top: 3px">
    有某个角色则显示：<button class="btn btn-primary" href="" style="">连接</button>
</div>
</@shiro.hasRole>

</div>
<@shiro.hasAnyRoles name="super,user,guest">
<div align="right" style="margin-right: 50px;margin-top: 3px">
    用户拥有任意角色则显示：<button class="btn btn-primary" href="" style="">测试</button>
</div>
</@shiro.hasAnyRoles>

<@shiro.guest>
<ol>
    <li>
        <h3>游客访问 <a href = "login"></a></h3>
    </li>
</ol>
</@shiro.guest>
<@shiro.hasPermission name="user:add">
<div align="right" style="margin-right: 50px;margin-top: 3px">
    拥有某个权限则显示：<button class="btn btn-primary" href="" style="">添加</button>
</div>
</@shiro.hasPermission>

<div class="form-inline row" style="margin-right: 50px">
    <div id="toolbar">
        <div style="float: left;margin-left: 30px">
            <label class="form-inline">用户名
            <input style="width: 60px" type="text" id="selecteLoginName" name="slecteLoginName">
            </label>
        </div>
        <div style="float: left;margin-left: 30px"">
            <label class="form-inline">性别
            <select id="selectGender" name="selectGender">
                <option selected="selected" value="1">男</option>
                <option value="0" >女</option>
            </select>
            </label>
        </div>
        <div style="float: left;margin-left: 30px"">
            <span class="form-inline">出生范围
            <input style="width: 100px;" type="text" id="selectBegin" name="selectBegin">
                <input style="width: 100px;" type="text" id="selectEnd" name="selectEnd">
            </span>
        </div>
        <div style="float: left;margin-left: 30px"">
            <label class="form-inline">用户状态
            <select id="selectStatus" name="selectStatus">
                <option selected="selected" value="">全部</option>
                <option value="1" >启用</option>
                <option value="0" >禁用</option>
            </select>
            </label>
        </div>
        <div style="float: left;margin-left: 30px"">
            <label class="form-inline">省份
            <input style="width: 60px;" type="text" id="selectProvince" name="selectProvince">
            </label>
        </div>
        <div style="float: left;margin-left: 30px"">
            <button type="button" class="btn btn-primary " onclick="userInfo.search()" id="">
                <i class="fa fa-search"></i>&nbsp;搜索
            </button>
        </div>
        <div style="float: left;margin-left: 30px"">
        <button type="button" class="btn btn-primary " onclick="userInfo.reset()" id="">
            <i class="fa fa-search"></i>&nbsp;重置
        </button>
        </div>
    </div>
</div><!--工具栏-->



<table id="userTable" class="table" ></table>
<form style="display: none"  id="updateform" align="center" name="updateform" method="post" action="../csair/update" >
        <input style="display: none" id="userId" name="userId">
        用户名：<input type="text" id="loginName" name="loginName"><br/>
        密  码：<input type="text" id="loginPassword" name="loginPassword"><br/>
        性  别：<select id="gender" name="gender" >
        <option selected="selected" value="1" >男</option>
        <option value="0">女</option>
    </select><br/>
        出生年月：<input type='text'  name="birthday" id='birthday' /><br/>
        邮  箱：<input id="contactEmail" name="contactEmail"  type="text" ><br/>
        真实姓名：<input id="userName" name="userName"  type="text" ><br/>
        <span style="width: 60px">联系电话：</span><input id="contactPhone" name="contactPhone"   type="text"  ><br/>
        <span style="width: 60px">联系地址：</span><input id="contactAddress" name="contactAddress"  type="text" ><br/>
        所属省份：<input id="province" name="province"  type="text" ><br/>
        邮政编号：<input id="postCode" name="postCode"  type="text" ><br/>
        是否禁用：
        <select id="userStatus" name="userStatus" >
            <option value="0">禁用</option>
            <option value="1" selected="selected">启用</option>
        </select><br/>
        <input align="center" class="submit" type="submit" onclick="closeLayer()" value="修改">
</form>
</body>
<script>
    //操作栏的格式化
    function actionFormatter(value, row, index) {
        var id = value;
        var result = "";
        result += "<@shiro.hasRole name='super'><a href='javascript:;' class='btn btn-xs blue' onclick=\"userInfo.updateform('" + row.userId + "','" + row.loginName  +
        "','" + row.gender + "','" + row.userName + "','" + row.contactAddress + "','" + row.contactEmail  +
        "','" + row.birthday + "','" + row.contactPhone + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a></@shiro.hasRole>";
        result += "<@shiro.hasRole name='super'><a href='javascript:;' class='btn btn-xs red' onclick=\"userInfo.DeleteById('" + row.userId + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a></@shiro.hasRole>";
        return result;
    }
</script>
<script type="text/javascript" src="static/js/userList/userList2.js"></script>
</html>