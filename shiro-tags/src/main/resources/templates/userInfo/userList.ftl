<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/laydate.css">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/js/laydate.js"></script>
    <link rel="stylesheet" href="static/css/bootstrap-table.min.css">
    <script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="static/js/layer.js"></script>
    <title>用户列表</title>
</head>
<body>
<div align="center" >
<h2 style="margin-left: 30px">用户列表页</h2>
</div>
<div align="center" >
      <label >用户名
          <input style="width: 60px" type="text" id="selecteLoginName" name="slecteLoginName">
      </label>
     <label >性别
      <select id="selectGender" name="selectGender">
                <option  value="" selected="selected">请选择</option>
                <option  value="1">男</option>
                <option value="0" >女</option>
            </select>
        </label>
    <span class="form-inline">出生范围
            <input style="width: 100px;" type="text" id="selectBegin" name="selectBegin">
                <input style="width: 100px;" type="text" id="selectEnd" name="selecteEnd">
            </span>
    <label class="form-inline">用户状态
    <select id="selectStatus" name="selectStatus">
        <option selected="selected" value="">全部</option>
        <option value="1" >启用</option>
        <option value="0" >禁用</option>
    </select>
</label>
    <label class="form-inline">省份
        <input style="width: 60px;" type="text" id="selectProvince" name="selectProvince">
    </label>
    <button type="button" class="btn btn-primary " onclick="userInfo.search()" id="">
    <i class="fa fa-search"></i>&nbsp;搜索
</button>
    <button type="button" class="btn btn-primary " onclick="userInfo.reset()" id="">
    <i class="fa fa-search"></i>&nbsp;重置
</button>
</div>

<table align="center" id="userInfoTable">
    <tr>
        <th>用户id</th>
        <th>账号</th>
        <th>密码</th>
        <th>性别</th>
        <th>真实姓名</th>
        <th>联系地址</th>
        <th>邮箱</th>
        <th>出生日期</th>
        <th>联系电话</th>
    </tr>
<#list list as item>
<div >
    <tr>
        <#if item.userId??><td >${item.userId}</td><#else ><td></td></#if>
        <#if item.loginName??><td>${item.loginName}</td><#else ><td></td></#if>
        <#if item.loginPassword??><td>${item.loginPassword}</td><#else ><td></td></#if>
        <#if item.gender??><td>${item.gender}</td><#else ><td></td></#if>
        <#if item.userName??><td>${item.userName}</td><#else ><td></td></#if>
        <#if item.contactAddress??><td>${item.contactAddress}</td><#else ><td></td></#if>
        <#if item.contactEmail??><td>${item.contactEmail}</td><#else ><td></td></#if>
        <#if item.birthday??><td>${item.birthday?string("yyyy-MM-dd")}</td><#else ><td></td></#if>
        <#if item.contactPhone??><td>${item.contactPhone}</td><#else ><td></td></#if>
        <#if item.userId??><td><button onclick="userInfo.updateform(
           '<#if item.userId??> ${item.userId}<#else> </#if>',
           '<#if item.loginName??> ${item.loginName}<#else> </#if>',
           '<#if item.gender??> ${item.gender}<#else> </#if>',
           '<#if item.userName??> ${item.userName}<#else> </#if>',
           '<#if item.contactAddress??> ${item.contactAddress}<#else> </#if>',
           '<#if item.contactEmail??> ${item.contactEmail}<#else> </#if>',
           '<#if item.birthday??> ${item.birthday?string("yyyy-MM-dd")}<#else> </#if>',
           '<#if item.contactPhone??> ${item.contactPhone}<#else> </#if>')"  >修改</button></td><#else ><td></td></#if>
        <#if item.userId??><td><button onclick="userInfo.delform()">删除</button></td><#else ><td></td></#if>
    </tr><br/>
</div>
</#list>
</table>
<div id="Paginator" align="center"> <ul id="pageLimit"></ul> </div>

<form align="center" style="display: none"  id="updateform"  name="updateform" method="post" action="../csair/update" >
        <input style="display: none" id="userId" name="userId">
        用户名：<input type="text" id="loginName" name="loginName"><br/>
        密  码：<input type="text" id="loginPassword" name="loginName"><br/>
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
        <input class="submit" type="submit" value="注册"><br/>
</form>
</body>
<script src="static/js/userList/userInfo.js"></script>
</html>