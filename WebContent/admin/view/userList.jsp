<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
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
	        title:'用户列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"UserServlet?method=UserList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        //sortOrder:'DESC', 
	        remoteSort: false,
	 		columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},    
 		        {field:'username',title:'用户名',width:200, sortable: true},
 		        {field:'sex',title:'性别',width:200, sortable: true,
 		        	formatter: function(value,row,index){
 		        		if(row.sex)
 							return "男";
 		        		return "女"
 					}	
 		        
 		        },
 		        {field:'mobile',title:'电话号码',width:100},
 		        {field:'mood',title:'个性签名',width:150},
 		        {field:'depart_id',title:'所属部门',width:150	, sortable: true,
 		        	formatter: function(value,row,index){
 						if (row.depart_id){
 							var departList = $("#departList").combobox("getData");
 							for(var i=0;i<departList.length;i++ ){
 								if(row.depart_id == departList[i].id)return departList[i].name;
 							}
 							return row.depart_id;
 						} else {
 							return 'not found';
 						}
 					}		
 		        },
 		        {field:'is_root',title:'管理员',width:150, sortable: true,
 		        	formatter: function(value,row,index){
 		        		if(row.is_root)
 							return "是";
 		        		return "否"
 					}	
 		        },
 		        
	 		]], 
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
	        	try{
	        		$("#departList").combobox("getData")
	        	}catch(err){
	        		preLoadDepart();
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
	  
	  //设置编辑用户窗口
	    $("#editDialog").dialog({
	    	title: "编辑用户",
	    	width: 250,
	    	height: 150,
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
								url: "UserServlet?method=EditUser",
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
						$('input:radio').eq(selectRow.is_root==0 ? 1:0).attr('checked', 'true');
						$("#edit-id").val(selectRow.id);
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				$('input:radio').eq(selectRow.is_root==0 ? 1:0).attr('checked', 'true');
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
							url: "UserServlet?method=DeleteUser",
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
	    
	    
	    function preLoadDepart(){
	  		$("#departList").combobox({
		  		width: "150",
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
	  	
	  //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			username: $('#search_user_name').val(),
	  			depart_id: $("#departList").combobox('getValue') == '' ? -1 : $("#departList").combobox('getValue')
	  		});
	  	});
	});
	
	</script>
</head>
<body>
	<!-- 用户列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;margin-top:4px;" class="datagrid-btn-separator" >&nbsp;&nbsp;用户名：<input id="search_user_name" class="easyui-textbox" name="search_user_name" /></div>
		<div style="margin-left: 10px;margin-top:4px;" >
			&nbsp;所属部门：<input id="departList" class="easyui-textbox" name="depart" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>

		<!-- 编辑窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    	<input type="hidden" id="edit-id" name="id">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>设置管理员：</td>
	    			<td>是<input type="radio" name="is_root" value="1" />否<input type="radio" name="is_root" value="0" /></td>
	    		</tr>
	    		
	    	</table>
	    </form>
	</div>
	
<!-- 提交表单处理iframe框架 -->
	<iframe id="photo_target" name="photo_target"></iframe>  
</body>
</html>