<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>denglu</title>
</head>
<body>
<h1>登陆页</h1>
<h3 ><#if name??>${name}</#if></h3>
<h3 color="red"  style="color: red">欢迎登陆</h3>
<form action="dologin" method="post">
<div>
<input type="text" name="loginName" />
</div>
<div><input type="text" name="loginPassword" /></div>
<div><input type="submit"  value="提交"/></div>
</form>
<br/>

</body>
</html>