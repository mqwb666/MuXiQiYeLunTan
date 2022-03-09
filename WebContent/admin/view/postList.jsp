<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>帖子列表</title>
	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="admin/easyui/css/demo.css">
	<script type="text/javascript" src="admin/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="admin/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="admin/easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'帖子列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"PostServlet?method=PostList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        remoteSort: false,
	 		columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},    
 		        {field:'title',title:'标题',width:200, sortable: true},
 		        {field:'content',title:'内容',width:200},
 		        {field:'publishtime',title:'发布时间',width:100, sortable: true},
 		        {field:'type_id',title:'类型',width:150, sortable: true,
 		        	formatter: function(value,row,index){
 						if (row.type_id){
 							var typeList = $(".typeList").combobox("getData");
 							for(var i=0;i<typeList.length;i++ ){
 								if(row.type_id == typeList[i].id)return typeList[i].name;
 							}
 							return row.type_id;
 						} else {
 							return 'not found';
 						}
 		 			}
 		        },
 		        {field:'depart_id',title:'所属部门',width:150, sortable: true,
 		        	formatter: function(value,row,index){
 						if (row.depart_id){
 							var departList = $(".departList").combobox("getData");
 							for(var i=0;i<departList.length;i++ ){
 								if(row.depart_id == departList[i].id)return departList[i].name;
 							}
 							return row.depart_id;
 						} else {
 							return 'not found';
 						}
 					}		
 		        
 		        
 		        },
 		        
 		       {field:'pageview',title:'阅读量',width:150, sortable: true,},
		        {field:'user_id',title:'用户',width:150, sortable: true,
 		    	  formatter: function(value,row,index){
						if (row.user_id){
							var userList = $("#userList").combobox("getData");
							for(var i=0;i<userList.length;i++ ){
								if(row.user_id == userList[i].id)return userList[i].username;
							}
							return row.user_id;
						} else {
							return 'not found';
						}
					}		
		        
		        },
		        {field:'is_overhead',title:'顶置',width:150, sortable: true,
		        	formatter: function(value,row,index){
		        		if(row.is_overhead)
							return "是";
		        		return "否"
						
					}	
		        },
 		       
 		        
	 		]], 
	 		
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
	        	try{
	        		$(".typeList").combobox("getData");
	        		$(".departList").combobox("getData");
	        		$("#userList").combobox("getData");
	        	}catch(err){
	        		preLoadType();
	        		preLoadDepart();
	        		preLoadUser();
	        	}
	        }
	    }); 
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	   
	    
	  //修改按钮监听事件
	  	$("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	  
	  //设置编辑帖子窗口
	    $("#editDialog").dialog({
	    	title: "编辑帖子",
	    	width: 500,
	    	height: 400,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'确定修改',
					plain: true,
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							//var gradeid = $("#add_gradeList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "PostServlet?method=EditPost",
								data: $("#editForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","修改成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_name").textbox('setValue', "");
										$("#edit_info").val("");
										//重新刷新页面数据
							  			//$('#gradeList').combobox("setValue", gradeid);
							  			$('#dataList').datagrid("reload");
										
									} else{
										$.messager.alert("消息提醒","修改失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						var selectRow = $("#dataList").datagrid("getSelected");
						//设置值
						$("#edit_type_id").textbox('setValue', function(){
		 						if (selectRow.type_id){
		 							var typeList = $(".typeList").combobox("getData");
		 							for(var i=0;i<typeList.length;i++ ){
		 								if(selectRow.type_id == typeList[i].id)return typeList[i].name;
		 							}
		 							return selectRow.type_id;
		 						}
		 		 			});
						$("#edit_depart_id").textbox('setValue', function(){
		 						if (selectRow.type_id){
		 							var departList = $(".departList").combobox("getData");
		 							for(var i=0;i<departList.length;i++ ){
		 								if(selectRow.depart_id == departList[i].id)return departList[i].name;
		 							}
		 							return selectRow.depart_id;
		 						}
		 		 			});
						
						 $('input:radio').eq(selectRow.is_overhead==0 ? 1:0).attr('checked', 'true');
						 
						$("#edit-id").val(selectRow.id);
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_type_id").textbox('setValue', function(){
 						if (selectRow.type_id){
 							var typeList = $(".typeList").combobox("getData");
 							for(var i=0;i<typeList.length;i++ ){
 								if(selectRow.type_id == typeList[i].id)return typeList[i].name;
 							}
 							return selectRow.type_id;
 						}
 		 			});
				$("#edit_depart_id").textbox('setValue', function(){
 						if (selectRow.type_id){
 							var departList = $(".departList").combobox("getData");
 							for(var i=0;i<departList.length;i++ ){
 								if(selectRow.depart_id == departList[i].id)return departList[i].name;
 							}
 							return selectRow.depart_id;
 						}
 		 			});
				
				 $('input:radio').eq(selectRow.is_overhead==0 ? 1:0).attr('checked', 'true');
				 
				$("#edit-id").val(selectRow.id);
			}
	    });
	  
	
		
	  //删除
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var numbers = [];
            	$(selectRows).each(function(i, row){
            		numbers[i] = row.sn;
            	});
            	var ids = [];
            	$(selectRows).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除选中的数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "PostServlet?method=DeletePost",
							data: {sns: numbers, ids: ids},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	    
	    function preLoadType(){
	  		$(".typeList").combobox({
		  		width: "100",
		  		height: "25",
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: true, //编辑
		  		method: "post",
		  		url: "TypeServlet?method=TypeList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  			//加载帖子下的学生
		  			//$('#dataList').datagrid("options").queryParams = {clazzid: newValue};
		  			//$('#dataList').datagrid("reload");
		  		}
		  	});
	  	}
	    
	    
	    function preLoadDepart(){
	  		$(".departList").combobox({
		  		width: "100",
		  		height: "25",
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: true, //不可编辑
		  		method: "post",
		  		url: "DepartServlet?method=DepartList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  		}
		  	});
	  	}
	    function preLoadUser(){
	  		$("#userList").combobox({
		  		width: "100",
		  		height: "25",
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: true, //不可编辑
		  		method: "post",
		  		url: "UserServlet?method=UserList&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  		}
		  	});
	  	}
	    
	 
	  
	  //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			postTitle: $('#search_post_title').val(),
	  			postContent: $('#search_post_content').val(),
	  			type_id: $(".typeList").combobox('getValue') == '' ? -1 : $(".typeList").combobox('getValue'),
	  			depart_id: $(".departList").combobox('getValue') == '' ? -1 : $(".departList").combobox('getValue'),
	  					
	  		});
	  	});
	});
	
	</script>
</head>
<body>
	<!-- 帖子列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;margin-top:4px;" class="datagrid-btn-separator" >&nbsp;&nbsp;标题：<input id="search_post_title" class="easyui-textbox" name="search_post_title" />&nbsp;&nbsp;内容：<input id="search_post_content" class="easyui-textbox" name="search_post_content" /></div>
		
		<div style="margin-left: 10px;margin-top:4px;" >
			&nbsp;类型：<input class="typeList" class="easyui-textbox" name="type" />
			&nbsp;所属部门：<input class="departList" class="easyui-textbox" name="depart" />
			&nbsp;<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>			
	</div>
	<span id="userList"  ></span>
	
	<!-- 编辑窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    	<input type="hidden" id="edit-id" name="id">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>帖子类型</td>
	    			<td><input id="edit_type_id" class="typeList" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="type_id"  data-options="required:true, missingMessage:'不能为空'" />&nbsp;(请从下拉列表中选择)</td>
	    		</tr>
	    		<tr>
	    			<td>所属部门:</td>
	    			<td><input id="edit_depart_id" class="departList" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="depart_id"  data-options="required:true, missingMessage:'不能为空'" />&nbsp;(请从下拉列表中选择)</td>
	    		</tr>
	    		<tr>
	    			<td>顶置：</td>
	    			<td>是<input type="radio" name="is_overhead" value="1" />否<input type="radio" name="is_overhead" value="0" /></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
	
	
<!-- 提交表单处理iframe框架 -->
	<iframe id="photo_target" name="photo_target"></iframe>  
</body>
</html>