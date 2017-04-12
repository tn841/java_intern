<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="core"  uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="inc_header.jsp" %>
<!-- google chart -->
<script src="https://www.gstatic.com/charts/loader.js"></script>

	<h1 style="text-align: center;">Dash board2</h1><hr>
	
	<div class="row">
		<div class="col-xs-3">
			<div class="list-group">
				<a href="dash_board_1" class="list-group-item">검색어 순위 추이</a>
				<a href="dash_board_2" class="list-group-item active">많이 언급된 검색어</a>
				<a href="#" class="list-group-item">...</a>
			</div>
		</div>
		
		<div class="col-xs-7">
		
			<div class="panel-body">
			    [기준]
			</div>
			<form action="javascript:chart_data()" name="chart_form" >
				포털 :
				<select id="portal">
					<option value="naver">naver</option>
					<option value="daum">daum</option>
					<option value="zum">zum</option>
				</select><br>
				시간 : 현재 시각 [<span id="cur_date">${cur_date}</span>] 기준 <span id="range">1시간 00분</span> 전 (15분 단위)
				<br>
				<input type="range" min="15" max="1440" value="60" step="15" oninput="range_change(this.value)"/> <br>
			</form>
			
		
			
			<div id="barchart_values" style="width: 700px; height: 250px;"></div>
			
			 <div class="panel-body" style="position: absolute;top: 520px;">
			    [키워드 언급 순위]
			 </div>
			<div id="rt_data" style="position: absolute;top: 560px;"></div>
			
		</div>
		
			

	</div>
	
	
	<script>
	
		function show_chart(){
			range_change(15);
		}
		
		function range_change(value){
			var hour = Math.floor(value/60);
			var min = value%60;
			$('#range').html(hour+" 시간 "+min+" 분");
			chart_data(value);
		}

		function chart_data(value){
			$('form[name=chart_form]').attr("method", "POST");

			$.ajax({
				url:"chart_json",
				method:"POST",
				data:{	period:value,
						portal:$('#portal').val(),
						e_date:$('#cur_date').text()
					},
				headers:{
							"Accept":"application/json"
						},
				beforeSend:function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success:function(data){					
					//차트 다시 그리기
					drawChart(data);
					
					//리스트 다시 그리기
					if(data == ""){
						$("#rt_data").html("데이터가 없습니다.");
						return;
					}
					var html = "<ol>";
					for(var i=0; i<data.length; i++){
						html += "<li>"+data[i]["r_keyword"]+" : "+data[i]["c"]+"회</li>";
					}
					html += "</ol>";
					$("#rt_data").html(html);
					//console.log(html);
				}
			}); 
		}
		

	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(show_chart);
    function drawChart(r_data) {
    	//json data를 google chart가 원하는 array형식으로 만들기
    	var chartData = [];
    	chartData.push( ["검색어", "검색 횟수", { role: "style" } ]);
    	for(var key in r_data){
    		var item = r_data[key];
    		//console.log(item);
    		chartData.push([item['r_keyword'], item['c'], "silver"]);
    	}
	
      var data = google.visualization.arrayToDataTable(chartData);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "많이 검색된 키워드",
        width: 600,
        height: 400,
        bar: {groupWidth: "50%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
      chart.draw(view, options);
  }
	</script>
</body>
</html>