<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		//jsonData요청
		$("#jsonreqBtn").on("click", function() {
// 			jsonView();
// 			responseBody();
			reqeustBody()
		});
	
	});
	
	function jsonView() {
		$.ajax({
			url : "${cp}/ajax/jsonView",
// 			url : "${cp}/ajax/responseBody",
			method : "post",
			success : function(data) {
				var html = "";
//					data.rangersList.forEach(function(ranger) {
//						html += ranger
//					});
//					$("#jsonRecvTbody").append(html);
				
				for(var i=0; i<data.rangersList.length; i++){
					var ranger = data.rangersList[i];
					html += "<tr><td>" + ranger + "</td></tr>";
				}
				$("#jsonRecvTbody").html(html);
			}
		});
	}
	
	function reqeustBody() {
		var data = {userId : "cogi", userNm : "코기새기"};
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
// 			data : $("#frm").serialize(),
			data : JSON.stringify(data),	//data를 json 문자열로 전송
			contentType : "application/json; charset=utf-8",	//client가 전송하는 데이터 타입
			dataType : "json",
			success : function(data) {
// 				var html = "";
				
// 				for(var i=0; i<data.length; i++){
// 					var ranger = data[i];
// 					html += "<tr><td>" + ranger + "</td></tr>";
// 				}
				
// 				$("#jsonRecvTbody").html(html);
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</td></tr>");
			}
		});
	}
	
	function responseBody() {
		$.ajax({
			url : "${cp}/ajax/responseBody",
			method : "post",
			dataType : "json",		//server에게 희망하는 리턴타입을 명시
			success : function(data) {
				var html = "";
				
				for(var i=0; i<data.length; i++){
					var ranger = data[i];
					html += "<tr><td>" + ranger + "</td></tr>";
				}
				
				$("#jsonRecvTbody").html(html);
			}
		});
	}
</script>
</head>
<body>
	
	<form id="frm">
		<input type="text" name="userId" value="brown"/>
		<input type="text" name="userNm" value="브라운"/>
	</form>
	
	<h2>ajaxView.jsp입니다ㅏㅏㅏㅏㅏ끄ㅜ아ㅏㅏ아아아아ㅏㅏㅏ아앜</h2>
	
	<h3>json 수신</h3>
	<div>
		<button id="jsonreqBtn">jsonData요청</button>
		<div id="jsonRecv">
			<table border="1">
			
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				
				<tbody id="jsonRecvTbody"></tbody>
				
			</table>
		</div>
	</div>
	
</body>
</html>
