<%@page import="com.test.member.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>hello</title>
</head>
<body>
	<h1>db 연결 테스트 ${title }</h1> <hr>
	
	
	
	<ul>
	<core:forEach items="${member_list}" var="row">
		<li>번호:${row.member_no} 이름:${row.member_name} 나이:${row.member_age}</li>
	</core:forEach>
	</ul>
	
	<p>member_list2 : ${member_list}</p>
</body>
</html>