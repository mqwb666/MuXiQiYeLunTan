<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function postDelete(diary_id){
		if(confirm("您确定要删除这个帖子吗？")){
			var url= "PostServlet?method=DeletePost&id="+${post.id}
			$.post(url,function(msg){
				if("success" == msg){
					window.location.href = "MainServlet";
				}});
		}
	}
</script>
<div class="data_list">
		<div class="data_list_title">
		<img src="user/images/diary_show_icon.png"/>
		帖子信息</div>
		<div>
		<div class="diary_title">
				<h3>${post.title }</h3>
			</div>
			<div class="diary_title"><h3>${diary.title }</h3></div>
			<div class="diary_info">
				发布时间：『${post.publishtime }』&nbsp;&nbsp;帖子类别：
						<c:forEach var="depart" items="${departList}">
							<c:if test="${depart.id==post.type_id}">${depart.name}</c:if>
						</c:forEach>
			</div>
			
			<div class="diary_content">
				${post.content }
			</div>
			<div class="diary_action">
			<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
			
			<c:if test="${user.id==post.user_id}"><button class="btn btn-danger" type="button" onclick="postDelete(${post.id})">删除帖子</button></c:if>
			
				
			</div>
		</div>
</div>