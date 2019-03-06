<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>textView.jsp입니다ㅏㅏㅏ아아ㅏㅡ가아아ㅏㅇ아아ㅏ앜</h2>
	
	<form action="${cp }/textReq">
		<input type="text" name="userId" value="brown"/><form:errors path="userVo.userId"/><br>
		<input type="password" name="pass" value="1234"/><form:errors path="userVo.pass"/><br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2>------------------------------------------------------------------</h2>
	
	<h2>textReqJsr303</h2>
	
	<form action="${cp }/textReqJsr303">
		<input type="text" name="userId" value="brown"/><form:errors path="userVo.userId"/><br>
		<input type="password" name="pass" value="1234"/><form:errors path="userVo.pass"/><br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2>------------------------------------------------------------------</h2>
	
	<h2>textReqValJsr303</h2>
	
	<form action="${cp }/textReqValJsr303">
		<input type="text" name="userId" value="brown"/><form:errors path="userVo.userId"/><br>
		<input type="password" name="pass" value="1234"/><form:errors path="userVo.pass"/><br>
		<input type="submit" value="전송"/>
	</form>
	
	<h2>------------------------------------------------------------------</h2>
	
	<h2><a href="${cp }/throwArith">Arithmetic예외 강제발생</a></h2>

	<h2><a href="${cp }/throwNoFileException">NoFileException 예외 강제발생</a></h2>
	
</body>
</html>