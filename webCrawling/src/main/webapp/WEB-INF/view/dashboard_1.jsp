<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="core"  uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="inc_header.jsp" %>
<!-- google chart -->
<script src="https://www.gstatic.com/charts/loader.js"></script>

	<h1 style="text-align: center;">Dash board1</h1><hr>
	
	<div class="row">
		<div class="col-xs-3">
			<div class="list-group">
				<a href="dash_board_1" class="list-group-item active">검색어 순위 추이</a>
				<a href="dash_board_2" class="list-group-item ">많이 언급된 검색어</a>
				<a href="#" class="list-group-item">...</a>
			</div>
		</div><!-- col 3 -->
		
		<div class="col-xs-7">
		
			<div class="panel-body">
			    [기준]
			</div>
			<form action="javascript:chart_data()" name="chart_form" >
				포털 :
				<select id="portal" onchange="select_change(this.value)">
					<option value="naver">naver</option>
					<option value="daum">daum</option>
					<option value="zum">zum</option>
				</select>
				&nbsp;&nbsp;
				검색어: 
				<select id="keyword" onchange="keyword_select_change(this.value)">
					<core:forEach items="${r_list}" var="row">
						<option value="${row.realtime_keyword}">${row.realtime_rank}. ${row.realtime_keyword}</option>
					</core:forEach>
				</select>
				<br>
		
			</form>
		
			 <div class="panel-body">
			    [키워드 순위 추이]
			 </div>
			 <div id="lineChart"></div>
		</div><!-- col 7 -->
	</div><!-- row -->
	
	
	<script>
	//페이지 로드 시
	$(function(){
		google.charts.setOnLoadCallback(page_load);
	});
	function page_load(){
		var keyword = $('#keyword option:selected').val();		
		keyword_select_change(keyword);
	}
	
	//포탈 선택박스 이벤트 처리
	function select_change(val){
		console.log(val);
		$.ajax({
			url:"portal_select",
			method:"POST",
			data:{	portal:val },
			headers:{
					"Accept":"application/json"
				},
			beforeSend:function(xhr){
				//console.log(header+":"+token);
				xhr.setRequestHeader(header, token);
			},
			success:function(data){
				console.log(data);
				//검색어 선택박스 갱신
				var $options = $('#keyword').children();
		
				if(val == 'daum'){
					for(var i=10; i<20; i++){
						$($options[i]).hide();
					}
				}else{
					for(var i=10; i<20; i++){
						$($options[i]).show();
					}
				}
				for(var i=0; i<$options.length; i++){
					if(data[i] == null){
						break;
					}
					var $option = $options[i];
					var keyword = data[i]['realtime_keyword'];
					var rank = data[i]['realtime_rank'];
					$($option).attr('value', keyword);
					$($option).text(rank+". "+keyword);
				}//for
			}
		});
	}//select_change
	
	//검색어 선택박스 이벤트 처리
	function keyword_select_change(value){
		//console.log("검색어:"+value+", 포털:"+$('#portal option:selected').text());
		var portal = $('#portal option:selected').text();
		$.ajax({
			url:"keyword_select",
			method:"POST",
			headers:{"Accept":"application/json"},
			data:{portal:portal, keyword:value},
			beforeSend:function(xhr){
				xhr.setRequestHeader(header, token);
			},
			success:function(data){
				//console.log(data);
				drawChart(data);
			}
		});
	}
	
	
	//chart
 	google.charts.load('current', {packages: ['line', 'corechart']});
   

	function drawChart(r_data) {
		var data = new google.visualization.DataTable();
	    data.addColumn('datetime', 'x');	//열 제목
	    data.addColumn('number', r_data[0]['realtime_keyword']);
		
	    //json 데이터를 array형식으로 
	    var dataArr = [];
	    for(var key in r_data){
	    	var item = r_data[key];
	    	//console.log("date : "+new Date(item['realtime_timestamp']));
	    	dataArr.push([
	    			new Date(item['realtime_timestamp']), 
	    			item['realtime_rank']
	    	]);
	    }
	    
	    data.addRows(dataArr);
	
	    var options = {
	    	title:'순위 변동 추이',	
   			hAxis: {
   	          title: '시간'
   	        },	
	   		vAxis: {
	   			title:'순위',
	   			direction:-1,
	   		},
	        wdith:600,
	        height:350
		};
	
		var chart = new google.visualization.LineChart(document.getElementById('lineChart'));
		chart.draw(data, options);
	   }//dataChart
	</script>
</body>
</html>