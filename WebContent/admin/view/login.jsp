<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="bookmark" href="favicon.ico"/>
<link href="admin/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="admin/h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="admin/h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="admin/h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">

<script type="text/javascript" src="admin/easyui/jquery.min.js"></script> 
<script type="text/javascript" src="admin/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="admin/h-ui/lib/icheck/jquery.icheck.min.js"></script> 

<script type="text/javascript" src="admin/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function(){
		//点击图片切换验证码
		$("#vcodeImg").click(function(){
			this.src="CpachaServlet?method=loginCapcha&t="+new Date().getTime();
		});
		
		//登录
		$("#submitBtn").click(function(){
			var data = $("#form").serialize();
			$.ajax({
				type: "post",
				url: "LoginServlet?method=Login",
				data: data, 
				dataType: "text", //返回数据类型
				success: function(msg){
					 if("vcodeError" == msg){
						$.messager.alert("消息提醒", "验证码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else if("loginError" == msg){
						$.messager.alert("消息提醒", "用户名或密码错误!", "warning");
						$("#vcodeImg").click();//切换验证码
						$("input[name='vcode']").val("");//清空验证码输入框
					} else  if("loginSuccess_user" == msg){
						window.location.href = "MainServlet";
					} else if("loginSuccess_root" == msg){
						window.location.href = "SystemServlet";
					}else{
						alert(msg);
					} 
				}
				
			});
		});
		
	})
</script> 
<title>登录|沐熙论坛管理系统</title>
<meta name="keywords" content="沐熙论坛管理系统">
</head>
<body>

<div class="header" style="padding: 0;">
	<h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">沐熙论坛管理系统</h2>
</div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form id="form" class="form form-horizontal" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="" name="account" type="text" placeholder="用户名"  class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-8 col-offset-3">
          <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码"  style="width: 200px;">
          <img title="点击图片切换验证码" id="vcodeImg" src="CpachaServlet?method=loginCapcha"></div>
      </div>
      
      
      
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input id="submitBtn" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">版 权 所 有 @ 沐熙论坛管理系统 </div>


</body>
</html>