<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/css/demo.css">
	<script type="text/javascript" src="admin/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="admin/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="admin/easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#postList').datagrid({ 
	        title:'最新帖子(<a  href="PostServlet?method=userPostList">更多</a>)', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:true,//是否可折叠的 
	        fit: true,//自动大小 
	        fitColumns: true,
	        method: "post",
	        idField:'id', 
	        singleSelect:false,//是否单选 
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
 		        {field:'content',title:'内容',width:$(this).width() * 0.4,resizable:false,
 		        	formatter: function(value,row,index){
 		        		return row.content.replace(/<[^>]+>/g,"");
 		 			}
 		        },
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

<div class=data_list>
	<div class="row-fluid">
		<div class="span9">
		
			<div  style="margin-top: 35px;float: left;margin-left: 50px;margin-bottom:55px;width: 320px;float: left;">
					<h1>公告栏</h1>
					<div class="datas">
						<c:forEach var="overhead" items="${overheadList }">
								<li>${fn:split(overhead.publishtime, ' .')[0]}<a href="PostServlet?method=showPost&id=${overhead.id }"><span
								class="label label-success" style="margin-right:20px"></span>&nbsp;${overhead.title }</a></li>
						</c:forEach>
					
					</div>
			</div>
			<hr/>
			
			<div style="margin-top: 35px;float: right;margin-right: -100px;margin-bottom:55px;width: 300px;float: right;">
				
				<h1>最热帖子</h1>
				<div class="datas">
						<c:forEach var="hotPost" items="${hotPostList }">
								<li>${fn:split(hotPost.publishtime, ' .')[0]}<a href="PostServlet?method=showPost&id=${hotPost.id }">&nbsp;${hotPost.title }</a></li>
							</c:forEach>
					
					</div>
			</div>
		
				<div style="width:134%;height: 335px">
				<table id="postList" cellspacing="0" cellpadding="0"></table>	</div>
		</div>
		
	</div>
</div>
</body>
</html>