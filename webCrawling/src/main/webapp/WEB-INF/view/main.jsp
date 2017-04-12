<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="inc_header.jsp"%>
	<core:if test="${not empty msg}">
		<script> alert(" <core:out value="${msg}"></core:out> "); </script>
	</core:if> 
	<h1 style="text-align: center;">주요포털 실검</h1>
	<hr>
	<div id="time_standard" style="padding-right: 15px;text-align: right;">기준 : ${curDate} </div>
	<div class="row">
		<div class="col-xs-2"></div>
		<div class="col-xs-3" id="naver_rt">
			<h3>naver</h3>

			<div id="naver_1">
				<core:forEach items="${naver_last}" begin="0" end="9" var="row" varStatus="stat">
				<div id="naver_list_${row.realtime_rank}"><a href="${row.realtime_url}" target="_blank">${row.realtime_rank}. ${row.realtime_keyword}</a></div>
				</core:forEach>
			</div>
			<div id="naver_2" style="display:none;">
				<core:forEach items="${naver_last }" begin="10" end="19" var="row">
				<div id="naver_list_${row.realtime_rank}"><a href="${row.realtime_url}" target="_blank">${row.realtime_rank}. ${row.realtime_keyword}</a></div>
				</core:forEach>
			</div>

			<button id="naver_left_btn">&lt;</button><button id="naver_right_btn">&gt;</button>
		</div>
		
		<div class="col-xs-3" id="daum_rt">
			<h3>daum</h3>
			<div>
				<core:forEach items="${daum_last}" begin="0" var="row">
				<div id="daum_list_${row.realtime_rank}"><a href="${row.realtime_url}" target="_blank">${row.realtime_rank}. ${row.realtime_keyword}</a></div>
				</core:forEach>
			</div>
		</div>
		
		<div class="col-xs-3" id="zum_rt">
			<h3>zum</h3>
			<div id="zum_1">
				<core:forEach items="${zum_last}" begin="0" end="9" var="row">
				<div id="zum_list_${row.realtime_rank}"><a href="${row.realtime_url}" target="_blank">${row.realtime_rank}. ${row.realtime_keyword}</a></div>
				</core:forEach>
			</div>
			<div id="zum_2" style="display: none">
				<core:forEach items="${zum_last}" begin="10" end="19" var="row">
				<div id="zum_list_${row.realtime_rank}"><a href="${row.realtime_url}" target="_blank">${row.realtime_rank}. ${row.realtime_keyword}</a></div>
				</core:forEach>
			</div>
			<button id="zum_left_btn">&lt;</button><button id="zum_right_btn">&gt;</button>
		</div>
	
	</div> <!-- row -->
	
	&nbsp;&nbsp;&nbsp;<button class="btn btn-default" onclick="data_reload()">갱신</button>
	
	<script>
		$(function(){
			//1분 마다 데이터 갱신
			setInterval(function(){
				data_reload();
			}, 60000);
			
			//11~20위 전환 버튼
			$("#naver_right_btn, #naver_left_btn").on("click",function(){
				//console.log($("#naver_2").css("display"));
				
				if($("#naver_2").css("display") == "none"){
					$("#naver_1").css("display","none");
					$("#naver_2").css("display","");
					$("#naver_2").show();
				}else if( $("#naver_2").css("display") == "block" ){
					$("#naver_2").css("display","none");
					$("#naver_1").css("display","");
					$("#naver_1").show();
				}
			});//naver btn
			
			$("#zum_right_btn, #zum_left_btn").on("click",function(){
				//console.log($("#zum_2").css("display"));
				
				if($("#zum_2").css("display") == "none"){
					$("#zum_1").css("display","none");
					$("#zum_2").css("display","");
					$("#zum_2").show();
				}else if( $("#zum_2").css("display") == "block" ){
					$("#zum_2").css("display","none");
					$("#zum_1").css("display","");
					$("#zum_1").show();
				}
			});//zum btn
		});
		
		function data_reload(){
			$.ajax({
				url:"data_reload_map",
				method:"POST",
				headers:{"Accept":"application/json"},
				beforeSend:function(xhr){
					xhr.setRequestHeader(header, token);
				}
			}).done(function(response_data){
				//console.log(response_data);
				var date = new Date();
				var curdatetime = date.getFullYear()+"-"+("00"+(date.getMonth()+1)).slice(-2)+"-"+date.getDate()+" "+date.getHours()+":"+("00"+date.getMinutes()).slice(-2);
				$("#time_standard").text("기준 : "+curdatetime);
				
				
				//naver갱신
				data = response_data["naver_list"];
				for(var i=0; i<data.length; i++){
					var ele = $("#naver_list_"+(i+1)+" a");	
					ele.text(data[i]["realtime_rank"]+". "+data[i]["realtime_keyword"]);	//순위 및 키워드 갱신
					ele.attr("href", data[i]["realtime_url"]);	//url 갱신
				} 
				
				//daum갱신
				data = response_data["daum_list"];
				for(var i=0; i<data.length; i++){
					var ele = $("#daum_list_"+(i+1)+" a");	
					ele.text(data[i]["realtime_rank"]+". "+data[i]["realtime_keyword"]);	//순위 및 키워드 갱신
					ele.attr("href", data[i]["realtime_url"]);	//url 갱신
				} 
				
				//zum갱신
				data = response_data["zum_list"];
				for(var i=0; i<data.length; i++){
					var ele = $("#zum_list_"+(i+1)+" a");	
					ele.text(data[i]["realtime_rank"]+". "+data[i]["realtime_keyword"]);	//순위 및 키워드 갱신
					ele.attr("href", data[i]["realtime_url"]);	//url 갱신
				} 
			});
		}
	</script>
	
</body>
</html>