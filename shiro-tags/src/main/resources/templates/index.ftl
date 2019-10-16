<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h3>首页了</h3>
<div shiro:hasPermission="user:add">用户添加跳转：<a href="add">用户添加</a></div>

<div shiro:hasPermission="user:update">用户更新跳转：<a href="update">用户更新</a></div>
<div >用户列表页跳转：<a href="userInfo" >用户列表</a></div>
<div >用户列表页跳转：<a href="userInfo2" >用户列表2</a></div>
<div >注册页跳转：<a href="register" >注册</a></div>
<div >登陆页跳转：<a href="login" >登陆</a></div>

</body>
</html>