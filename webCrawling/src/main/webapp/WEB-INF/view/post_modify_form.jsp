<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc_header.jsp" %>
	<h1 style="text-align: center;">글 수정</h1><hr>
	<form id="update_form" action="post_modify_action" method="POST">
		<div class="form-group">
			<label for="post_title">제목</label>
			<input type="text" class="form-control" id="post_title" name="title" value="${modify_post.p_title}"/>
		</div>
		<div class="form-group">
			<label for="post_content">내용</label>
			<textarea class="form-control" row="6" id="post_content" name="content">${modify_post.p_content}</textarea>
		</div>
		<input type="hidden" name="no" value="${modify_post.p_no}">
		<!-- input type="submit"  class="btn btn-default" value="확인"/-->
		<input class="btn btn-default" type="submit" value="확인"/>
		<button id="modify_cancle" class="btn btn-default" >취소</button>
		<sec:csrfInput/>
	</form>
	
	<script>
		$('#modify_cancle').click(function(e){
			e.preventDefault();
			history.back();
		});
	</script>
</body>
</html>