<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="inc_header.jsp" %>
	<h1 style="text-align: center;">게시글 보기</h1><hr>
	<table class="table table-bordered">
		<tr>
			<td>제목</td>
			<td colspan="3">${find_post.p_title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${find_post.p_writer}</td>
			<td>작성일</td>
			<td><fmt:formatDate value="${find_post.p_date}" pattern="yy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td colspan="4">내용</td>
		</tr>
		<tr>
			<td colspan="4">${find_post.p_content}</td>
		</tr>
	</table>
	<button class="btn btn-default" onclick="go_list()">목록으로</button>
	
	<sec:authentication property="principal.username" var="login_username"/>
	<c:if test='${login_username eq find_post.p_writer && find_post.p_writer ne "admin"}'>
		<button class="btn btn-default" onclick="post_delete(${find_post.p_no})">삭제</button>
		<button class="btn btn-default" onclick="post_modify(${find_post.p_no})">수정</button>
		<form action="post_modify" id="modify_F" method="post">
			<input type="hidden" name="no" value="${find_post.p_no}">
		<sec:csrfInput/>
	</form>
	</c:if>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button class="btn btn-default" onclick="post_delete(${find_post.p_no})">삭제</button>
		<button class="btn btn-default" onclick="post_modify(${find_post.p_no})">수정</button>
		<form action="post_modify" id="modify_F" method="post">
			<input type="hidden" name="no" value="${find_post.p_no}">
		<sec:csrfInput/>
	</form>
	</sec:authorize>
	
	
	
	<script>
		function go_list(){
			window.history.back();
		}
		
		function post_delete(no){
			console.log("삭제"+no);
			$.ajax({
				url:"post_delete",
				method:"POST",
				data:{"no":no},
				beforeSend:function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success:function(data){
					if(data == "del_ok"){
						alert("삭제 되었습니다.");
						location = "post";
					}
					else{
						alert("삭제 오류 발생. 관리자 문의");
					}
				}
			});
		}
		
		function post_modify(no){
			document.getElementById('modify_F').submit();
		}
	</script>
</body>
</html>