<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	
		<h1 class="page-header">전체 사용자 리스트 Tiles적용</h1>
	
		<table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>사용자 아이디</th>
                  <th>사용자 이름</th>
                  <th>별명</th>
                  <th>등록일시</th>
                </tr>
              </thead>
              <tbody>
              
           	  <% int cnt = 1; %>
              
				<c:forEach items="${userList }" var="user">
					<tr class="userTr" data-userId="${user.userId }">
						<td><%=cnt++ %></td>
						<td>${user.userId }</td>
						<td>${user.userNm }</td>
						<td>-</td>
						<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy/MM/dd"/></td>
					</tr>
				</c:forEach>
              
              
              </tbody>
            </table>

    
    <script type="text/javascript">
    	//문서로딩이 완료된 이후 이벤트 등록
    	$(document).ready(function(){
    		console.log("document ready");
    		
    		$(".userTr").on("click", function(){
    			console.log("userTr click");
    			//클릭한 userTr 태그의 userId값을 출력
// 				console.log("data-userid : " + $(this).data("userid"));
    			var userId = $(this).data("userid");
    			
    			// /user로 이동 방법
    			// 1. document
//     			document.location = "/user?userId=" + userId
    			
    			// 2. form태그
    			$("#userId").val(userId);
//     			$("#frm").attr("action","/userAllList");
    			$("#frm").submit();
				
    		});
    		
    	});
    	
    </script>
    
  <form id="frm" action="${pageContext.servletContext.contextPath }/user" method="get">
  	<input type="hidden" id="userId" name="userId" />
  </form>
  