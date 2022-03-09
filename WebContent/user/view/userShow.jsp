<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>沐熙论坛</title>
	<link href="user/style/diary.css" rel="stylesheet">
	<link href="user/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="user/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script src="user/bootstrap/js/jQuery.js"></script>
	<script src="user/bootstrap/js/bootstrap.js"></script>
	<script src="user/js/ckeditor/ckeditor.js"></script>

	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/css/demo.css">
	<script type="text/javascript" src="admin/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="admin/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="admin/easyui/js/validateExtends.js"></script>
	
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#postuserList').datagrid({ 
	        title:'个人帖子', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:true,//是否可折叠的 
	        fit: true,//自动大小 
	        fitColumns: true,
	        method: "post",
	        url:"PostServlet?method=PostList&user_id="+${user.id},
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        sortName:'publishtime',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
 		        {field:'id',title:'ID',width:$(this).width() * 0.05,align:'right',resizable:false},    
 		        {field:'title',title:'标题',width:$(this).width() * 0.2,resizable:false,
 		        	formatter: function(value,row,index){
 		        		return "<a href='PostServlet?method=showPost&id="+row.id+"'>"+row.title+"</a>";
 		 			}	
 		        
 		        },
 		        {field:'content',title:'内容',width:$(this).width() * 0.4,resizable:false},
 		        {field:'publishtime',title:'发布时间',width:$(this).width() * 0.2,resizable:false}, 
 		       {field:'pageview',title:'阅读量',width:$(this).width() * 0.1,resizable:false},
	
	 		]], 
	 		
	        toolbar: "#toolbar",
	       
	    }); 
	    //设置分页控件 
	    var p = $('#postList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 


	});
	
	</script>
	
	
	
	<style type="text/css">
	  body {
			padding-top: 60px;
			padding-bottom: 40px;
       }
		hr {
			width: 2px;
			height: 200px;
			background-color: #0088D8;
			float: left;
			margin-left: 10px;
		}
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#">沐熙论坛</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="MainServlet"><i class="icon-home"></i>&nbsp;主页</a></li>
              <li class="active"><a href="PostServlet?method=preSave"><i class="icon-pencil"></i>&nbsp;写帖子</a></li>
              <li class="active"><a href="UserServlet?method=preSave"><i class="icon-user"></i>&nbsp;信息修改</a></li>
              <c:if test="${user.is_root==1 }"> <li class="active"><a href="SystemServlet"><i class="icon-list"></i>&nbsp;管理界面</a></li></c:if>
            </ul>
          </div>
          <form class="navbar-form pull-right" method="post" action="LoginServlet?method=logout">
			  <button type="submit" class="btn" >&nbsp;退出登录</button>
		  </form>
        </div>
      </div>
</div>

<div class="container">
	<div class="row-fluid">
		<div class="span9">

			<div class=data_list>
			<div class="data_list_title">
					<img src="user/images/user_edit_icon.png"/>
					个人中心</div>
				<div class="row-fluid" style="padding-top: 20px;">
					<div class="span4">
						<img width="90%" src="user/userImages/userhead.jpg">
					</div>
					<div class="span8">
						<table >
							<tr>
								<td>用户名：</td>
								<td><input type="text" id="mobile" name="mobile" value="${user.username}" style="margin-top:5px;height:30px;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td>性别：</td>
								<td><input type="text" id="mobile" name="mobile" value="${user.sex==1?"男":"女"}" style="margin-top:5px;height:30px;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td >电话号码：</td>
								<td><input type="text" id="mobile" name="mobile" value="${user.mobile}" style="margin-top:5px;height:30px;" readonly="readonly"/></td>	
							</tr>
							<tr>
								<td >用户级别：</td>
								<td><input type="text" id="mobile" name="mobile" value="${user.is_root==1?'管理员':'普通用户'}" style="margin-top:5px;height:30px;" readonly="readonly"/></td>	
							</tr>
							<tr>
								<td >所属部门：</td>
								<td>
								<c:forEach var="depart" items="${departList}">
									<c:if test="${depart.id==user.id}"><input type="text" id="mobile" name="mobile" value="${depart.name}" style="margin-top:5px;height:30px;" readonly="readonly"/></c:if>
								</c:forEach>
								</td>	
							</tr>
							
						</table>
					</div>
				</div>
				<div style="width:100%;height: 335px"><table id="postuserList" cellspacing="0" cellpadding="0"></table> 	</div>
			</div>
					
		</div>
		
		<div class="span3">
			<div class="data_list">
				<div class="data_list_title">
					个人中心
				</div>
				<div class="user_image">
					<a href="UserServlet?method=userShow"><img src="user/userImages/userhead.jpg"/></a>
				</div>
				<div class="nickName">${user.username }</div>
				<div class="userSign">(${user.mood })</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					按帖子类别
				</div>
				<div class="datas">
					<ul>
						<c:forEach var="type" items="${typeList}">
							<span><a href="">${type.name}</a></span>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					按所属部门
				</div>
				<div class="datas" >
					<ul>
						<c:forEach var="depart" items="${departList}">
							<span><a href="">${depart.name}</a></span>
						</c:forEach>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
</div>
	
</body>
</html>