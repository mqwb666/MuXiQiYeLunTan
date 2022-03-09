<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var title=document.getElementById("title").value;
		var content=CKEDITOR.instances.content.getData();
		var type_id=document.getElementById("type_id").value;
		var depart_id=document.getElementById("depart_id").value;
		if(title==null||title==""){
			document.getElementById("error").innerHTML="标题不能为空！";
			return false;
		}
		if(content==null||content==""){
			document.getElementById("error").innerHTML="内容不能为空！";
			return false;
		}
		if(type_id==null||type_id==""){
			document.getElementById("error").innerHTML="请选择类别！";
			return false;
		}
		if(depart_id==null||depart_id==""){
			document.getElementById("error").innerHTML="请选择部门！";
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${diary.diary_id!=null }">
				<img src="user/images/diary_type_edit_icon.png"/>
				修改帖子</div>
			</c:when>
			<c:otherwise>
				<img src="user/images/diary_add_icon.png"/>
				写帖子</div>
			</c:otherwise>
		</c:choose>
		<form action="PostServlet?method=postSave" method="post" onsubmit="return checkForm()">
			<div>
				<div class="diary_title"><input type="text" id="title"  name="title" value="${diary.title }" class="input-xlarge"  style="margin-top:5px;height:30px;"  placeholder="帖子标题..."/></div>
				<div>
					<textarea class="ckeditor" id="content" name="content">${diary.content }</textarea>
				</div>
				<div >
					<select id="type_id" name="type_id">
						<option value="">请选择帖子类别...</option>
						<c:forEach var="type" items="${typeList }">
							<option value="${type.id }" ${type.id==post.id?'selected':'' }>${type.name }</option>
						</c:forEach>
					</select>
				
					<select id="depart_id" name="depart_id">
						<option value="">请选择部门类别...</option>
						<c:forEach var="depart" items="${departList }">
							<option value="${depart.id }" ${depart.id==post.id?'selected':'' }>${depart.name }</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<input type="hidden" id="id" name="id" value="${post.id }"/>
					<input type="submit" class="btn btn-primary" value="发布"/>
					<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					<font id="error" color="red">${error }</font>  
				</div>
			</div>
		</form>
</div>