<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var name=document.getElementById("name").value;
		if(name==null||name==""){
			document.getElementById("error").innerHTML="昵称不能为空！";
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
		<div class="data_list_title">
		<img src="user/images/user_edit_icon.png"/>
		个人信息设置</div>
	<div class="row-fluid" style="padding-top: 20px;">
		<div class="span4">
			<img src="user/userImages/userhead.jpg">
		</div>
		<div class="span8">
			<form action="UserServlet?method=EditUser&action=user" method="post" >
				<input type="hidden" id="id" name="id" value="${user.id }">
				<table >
					
					<tr>
						<td>用户名：</td>
						<td><input type="text" id="username" name="username" value="${user.username}" style="margin-top:5px;height:30px;"/></td>
					</tr>
					<tr>
						<td >个性签名：</td>
						<td><input type="text" id="mood" name="mood" value="${user.mood}" style="margin-top:5px;height:30px;"/></td>
							
					</tr>
					<tr>
					<tr>
						<td >电话号码：</td>
						<td><input type="text" id="mobile" name="mobile" value="${user.mobile}" style="margin-top:5px;height:30px;"/></td>
					</tr>
					<tr>
						<td><button class="btn btn-primary" type="submit">保存</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>