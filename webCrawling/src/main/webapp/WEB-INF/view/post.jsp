<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="inc_header.jsp" %>
	
	<h1 style="text-align: center;">게시판</h1><hr>
	<!-- div class="row">
	<div class="col-xs-10"></div>
	<div class="col-xs-2">
	<select class="form-control" id="sel1">
	    <option>4</option>
	    <option>8</option>
		<option>16</option>
		<option>20</option>
	</select>
	</div>
	</div-->
	<table class="table table-hover">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
		<core:forEach items="${post_list}" varStatus="stat" var="row">
		
		<tr>
			<td>${1+stat.index +((cur_page-1)*per_page)}</td>
			<td><a href="post_view?no=${row.p_no}">${row.p_title}</a></td>
			<td>${row.p_writer}</td>
			<td><fmt:formatDate value="${row.p_date}" pattern="yy-MM-dd HH:mm"/> </td>
		</tr>

		</core:forEach>
	</table>
	
	<div style="text-align: center;">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	<core:if test="${cur_page != 1}">
	    <li>
	      <a href="post?cur_page=${(cur_page - 1)}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    </core:if>
	    <core:forEach begin="1" end="${total_page_cnt}" varStatus="stat">
	    	<core:choose >
	    		<core:when test="${cur_page == stat.index}">
	    			<li class="active" id="page_nation" no="${total_page_cnt}"><a href="post?cur_page=${stat.index}">${stat.index}</a></li>
	    		</core:when>
	    		<core:otherwise>
	    			<li id="page_nation" no="${total_page_cnt}"><a href="post?cur_page=${stat.index}">${stat.index}</a></li>
	    		</core:otherwise>
	    	</core:choose>
	   		
	    </core:forEach>
	    <core:if test="${cur_page != total_page_cnt }">
	    <li>
	      <a href="post?cur_page=${(cur_page + 1)}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    </core:if>
	  </ul>
</nav>
</div>
	
	<button id="insert_post" class="btn btn-default">글작성</button>
	<script>
		$(function(){
			$('#insert_post').click(function(){
				console.log("insert");
				window.location = "/test/post_write_form";
			});//글 쓰기
			
			var path = location.pathname;
			console.log($('#page_nation').attr("no"));
			if(path.includes($('#page_nation').attr("no"))){
				$('#page_nation').addClass("active");
			}
		});
		

	</script>
</body>
</html>