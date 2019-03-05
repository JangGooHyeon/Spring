<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cp }/css/dashboard.css" rel="stylesheet">
  </head>

  <body>
	<%@include file="/WEB-INF/views/module/header.jsp"%>
	<%@include file="/WEB-INF/views/module/left.jsp"%>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">사용자 정보 조회</h1>
		
		<form id="frm" action="${cp }/user/userModifyForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
	
			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">프로필 사진</label>
				<div class="col-sm-4">
					<img src="${userVo.realFileName }"/>
					<input type="file" class="form-control" id="profile" name="profile" placeholder="프로필 사진">
				</div>
			</div>

			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="userId" name="userId" readonly="readonly">
				</div>
			</div>

			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="userNm" name="userNm" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">별명</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="alias" name="alias" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">주소</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="addr1" name="addr1" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">상세 주소</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="addr2" name="addr2" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-2 control-label">우편번호</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="zipcode" name="zipcode" readonly="readonly">
				</div>
				<div class="col-sm-4">
					<button type="button" id="zipcodeBtn" class="btn btn-default">검색</button>
				</div>
			</div>

			<div class="form-group">
				<label for="pass" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="pass" name="pass">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-9">
					<button type="button" id="regBtn" class="btn btn-default">정보 수정</button>
				</div>
			</div>
		</form>
		
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--  JQuery가 먼저 load된 후 bootstrap이 로드 된다. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
    	function initData() {
			$("#userId").val("${userVo.userId }");
			$("#userNm").val("${userVo.userNm}");
			$("#alias").val("${userVo.alias}");
			$("#addr1").val("${userVo.addr1}");
			$("#addr2").val("${userVo.addr2}");
			$("#zipcode").val("${userVo.zipcode}");
// 			$("#pass").val("${userVo.pass}");
			$("img").attr("src", "${cp}/user/profileImg?userId=${userVo.userId }");
		}
    	
    	$(document).ready(function(){
    		initData();
    		
    		$("#zipcodeBtn").on("click", function(){
			    new daum.Postcode({
			        oncomplete: function(data) {
			        	//새 우편번호 : data.zonecode
			        	//우편번호 input select .val(data.zonecode);
			        	$("#zipcode").val(data.zonecode);
			        	
			        	//기본주소(도로명주소) : data.roadAddress 
			        	//주소1 input select .val(data.roadAddress);
			        	$("#addr1").val(data.roadAddress);
			        	
			        	//상세주소 input focus
			        	$("#addr2").focus();
			        	
			        }
			    }).open();
			});
    		
    		$("#regBtn").on("click", function(){
				//사용자 아이디
				if($("#userId").val().trim() == ""){
					alert("사용자 아이디를 입력해주세요.");
					$("#userId").focus();
					return false;
				} 
				//사용자 이름
				if($("#userNm").val().trim() == ""){
					alert("사용자 이름을 입력해주세요.");
					$("#userNm").focus();
					return false;
				}
				//별명
				if($("#alias").val().trim() == ""){
					alert("별명을 입력해주세요.");
					$("#alias").focus();
					return false;
				}
				//주소
				if($("#addr1").val().trim() == ""){
					alert("주소를 입력해주세요.");
					$("#zipcodeBtn").trigger("click");
					return false;
				}
				//상세주소
				if($("#addr2").val().trim() == ""){
					alert("상세 주소를 입력해주세요.");
					$("#addr2").focus();
					return false;
				}
				//비밀번호
// 				if($("#pass").val().trim() == ""){
// 					alert("비밀번호를 입력해주세요.");
// 					$("#pass").focus();
// 					return false;
// 				}
			
				
				//정상적으로 validation 완료 --> form 전송
				$("#frm").submit();
				
			});
    		
    	});
    </script>
    
  </body>
</html>