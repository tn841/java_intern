<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc_header.jsp" %>
	<h1>새 글 작성</h1><hr>
	<form id="write_form" action="post_insert_action" method="POST">
		<div class="form-group">
			<label for="post_title">제목</label>
			<input type="text" class="form-control" id="post_title" name="title"/>
		</div>
		<div class="form-group">
			<label for="post_content">내용</label>
			<textarea class="form-control" row="6" id="post_content" name="content"></textarea>
		</div>
		<input type="hidden" id="writer" name='writer' value="<sec:authentication property="principal.username"/>">
		<!-- input type="submit"  class="btn btn-default" value="확인"/-->
		<sec:csrfInput/>
		<button id="post_write" class="btn btn-default">확인</button>
		<button id="post_cancle" class="btn btn-default">취소</button>
	</form>
	
		<script>
			$(function(){
				$('#post_cancle').click(function(event){
					event.preventDefault();
					window.location = "/test/post";					
				});//cancle
				
				$('#post_write').click(function(event){
					event.preventDefault();
					var f = document.getElementById('write_form');
					f.submit();
				});
	
			});
		</script>
</body>
</html>