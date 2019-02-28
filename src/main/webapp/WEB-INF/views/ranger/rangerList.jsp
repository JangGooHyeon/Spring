<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>rangerList.jsp 구아ㅏㅏ아아아아ㅏㅏㅏ아앍</h2>
	
	<table border="1">
		<thead>
			<tr>
				<td>이름</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rangers }" var="ranger">
				<tr>
					<td>${ranger }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>게시판 구분 : ${boardGb }</h2>
	<h2>게시판 구분2: ${boardGb2 }</h2>
	<h2>게시판 구분3(SessionScope) : ${sessionScope.boardGb2 }</h2>
	
</body>
</html>