<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<spring:message code="hello" /><br>
<%-- <spring:message code="visitor" /> --%>

<!-- 
	select box를 change하면 해당 언어로 locale
	<fmt:setLocale "ko"/>
	spring interceptor, localeResolver 
-->
<form>
<select id="langSelect" name="language">
	<option value="ko">한국어</option>
	<option value="en">영어</option>
	<option value="ja">일본어</option>
</select><br>
<input type="submit" value="전송">
</form>

<h2>msgView.jsp갸아ㅏㅏ아아아아아앜</h2>

</body>
</html>