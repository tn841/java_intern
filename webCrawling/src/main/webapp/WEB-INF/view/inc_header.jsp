<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta id="_csrf" name="_csrf" th:content="${_csrf.token}"/>
<meta id="_csrf_header" name="${_csrf.headerName}" th:content="${_csrf.headerName}"/>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Insert title here</title>
<script type="text/javascript">
	//csrf 변수
	var token = $("meta[name='_csrf']").attr("th:content");
	var header = $("meta[name='X-CSRF-TOKEN']").attr("th:content");

	//상단 메뉴 활성화 여부
	$(function(){
		//console.log(location.pathname);
		var path = location.pathname;
		if(path.includes("main")){
			$("#main").addClass("active");
			$("#dash_board").removeClass("active");
			$("#bbs").removeClass("active");
		}else if(path.includes("dash_board")){
			$("#main").removeClass("active");
			$("#dash_board").addClass("active");
			$("#bbs").removeClass("active");
		}else if(path.includes("post")){
			$("#main").removeClass("active");
			$("#dash_board").removeClass("active");
			$("#bbs").addClass("active");
		}
	});
</script>
</head>
<body >

	

 	<script>
		function loginSubmit(){
			var f = document.getElementById("log_form");
			f.action = "/test/login";
			f.submit();
		}
	</script>

 

 

	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#"></a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li id="main" class=""><a href="/test/main">홈</a></li>
	      <li id="dash_board"><a href="/test/dash_board_1">대시보드</a></li>
	      <li id="bbs"><a href="/test/post">게시판</a></li>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	    	<li>
	    		<sec:authorize access="isAnonymous()">
					<div class="navbar-form navbar-right" >
						<form action="login" method="post" id="login_f">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
				    	<span style="color: gray;" ><h5> 로그인 하세요. 
				        <a href = "javascript:document.getElementById('login_f').submit();"> 로그인 </a> </h5></span>
			   		</div>
			   		
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<div class="navbar-form navbar-right" >
						<form action="logout" method="post" id="logout_f">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
				    	<span style="color: gray;" ><h5> <sec:authentication property="principal.username"/> 님 반갑습니다.
				        <a href="javascript:document.getElementById('logout_f').submit();" > Logout</a> </h5></span>    
			   		</div>
				</sec:authorize>
	    	</li>
	    	<li>&nbsp;&nbsp;&nbsp;</li>
	    </ul>
	  </div>
	</nav>
	
	<!------------------------     navigation   ------------------------>
	
    
	
	<hr>