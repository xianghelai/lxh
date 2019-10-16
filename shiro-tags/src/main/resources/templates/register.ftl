<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/laydate.css">
    <script src="static/js/laydate.js"></script>
    <script src="static/js/jquery.js"></script>
    <script src="static/js/additional-methods.js"></script>
    <script src="static/js/jquery.validate.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <style>
        .error{
            color:red;
        }
    </style>

    <title>注册页</title>
</head>
<body>
<@shiro.guest>
<ol>
    <li>
        <h3>游客访问 <a href = "login"></a></h3>
    </li>
</ol>
</@shiro.guest>
<form  id="userForm"  method="post" action="../csair/register" >
        <div align="center" style="margin: 20px;margin-top: 50px">
            <label for="loginName"><span style="color: red">*</span>账号名</label>
            <input id="loginName" name="loginName"  type="text"  >
        </div>
        <div align="center" style="margin: 10px">
            <label for="loginPassword"><span style="color: red">*</span>密码</label>
            <input id="loginPassword" name="loginPassword" minlength="5" type="password"  >
        </div>
        <div align="center" style="margin: 10px">
            <label for="checkPassword"><span style="color: red">*</span>确认密码</label>
            <input id="checkPassword" name="checkPassword" minlength="5" type="password"  >
        </div>
        <div align="center" style="margin:10px">
            <label for="gender"><span style="color: red">*</span>性别</label>
            <select id="gender" name="gender" >
                <option selected="selected" value="1" >男</option>
                <option value="0">女</option>
            </select>
        </div>
        <div align="center" style="margin:10px">
                <label for="birthday">出生日期</label>
                <input type='text' class="form-control" name="birthday" id='birthday' />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
        </div>
        <div align="center" style="margin: 10px">
            <label for="contactEmail">email</label>
            <input id="contactEmail" name="contactEmail"  type="text" >
        </div>
        <div align="center" style="margin: 10px">
            <label for="userName"><span style="color: red">*</span>真实姓名</label>
            <input id="userName" name="userName"  type="text" >
        </div>
        <div style="display: none">
            <label for="userStatus">用户状态</label>
            <input id="userStatus" type="text" name="userStatus" value="1">
        </div>
        <div align="center" style="margin: 10px">
            <label for="contactPhone"><span style="color: red">*</span>联系电话</label>
            <input id="contactPhone" name="contactPhone"   type="text"  >
        </div>
        <div align="center" style="margin: 10px">
            <label for="contactAdddress">联系地址</label>
            <input id="contactAdddress" name="contactAdddress"  type="text" >
        </div>
        <div align="center" style="margin: 10px">
            <label for="province">所属省份</label>
            <input id="province" name="province"  type="text" >
        </div>
        <div align="center" style="margin: 10px">
            <label for="postCode">邮编编号</label>
            <input id="postCode" name="postCode"  type="text" >
        </div>
        <div align="center" style="margin: 10px">
            <input class="submit" type="submit" value="注册">
        </div>
</form>
</body>
<script >
    /*$.validator.setDefaults({
        submitHandler: function(form) {
        }
    });*/
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        /*var nowday=new Date(Date.parse("2001-10-09").replace("-", "/"));*/
        //var nowday=Date.parse("2001-10-09");
        //console.log(nowday);
        $("#userForm").validate({
            rules: {
                loginName:{
                    required: true,
                    rangelength:[3,9]
                },
                loginPassword:{
                    required: true,
                    rangelength:[6,12]
                },
                checkPassword:{
                    required: true,
                    equalTo: "#loginPassword"
                },
                contactEmail: "email",
                userName:{
                    required:true,
                    minlength: 2
                },
                contactPhone:{
                    required:true,
                    phone:true
                },
                postCode: {
                    isZipCode:true
                },
                birthday:{
                    birth:true
                }
            },
            messages: {
                loginName:{
                    required: "请输入用户名",
                    rangelength:"用户名必须大于3且小于9个字符"
                },
                loginPassword:{
                    required: "请输入密码",
                    rangelength:"密码必须大于6且小于12个字符"
                },
                checkPassword:{
                    required: "请输入密码",
                    equalTo: "两次输入的密码不一致"
                },
                contactEmail: "请填写正确的邮箱",
                userName:{
                    required: "请填写您的真实姓名",
                    minlength:"用户名不小于2字符"
                },
                contactPhone:{
                    required:"请填写联系号码",
                    phone:"请填写正确的联系号码"
                },
                postCode:{
                    isZipCode:"请填写正确的邮政编号"
                },
                birthday:{
                    birth:"年龄未满18"
                }
            },
            onsubmit:true// 是否在提交时验证
        });
    });
    // 邮政编码验证
        jQuery.validator.addMethod("isZipCode", function(value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的邮政编码");
    //手机号验证
    jQuery.validator.addMethod("phone", function(value, element) {
        var length = value.length;
        var regPhone = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || ( length == 11 && regPhone.test( value ) );
    }, "请正确填写您的手机号码");
    //日期验证，年龄大于18
    jQuery.validator.addMethod("birth", function(value, element) {
        /*var birthday = jQuery(param).val();*/
        var birthday = $("#birthday").val();
        console.log(value);
        birthday = Date.parse(birthday);
        var nowday=Date.parse("2001-10-10");
        console.log(nowday);
        console.log(birthday)
        if(birthday>nowday){
            return false;
        }else{
            return true;
        }
    }, "您的年龄不足18");
    laydate.render({
        elem: '#birthday' ,//指定元素
        type:'date'
    });
</script>
</html>