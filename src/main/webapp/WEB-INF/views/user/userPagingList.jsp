<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
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
    <link href="${pageContext.servletContext.contextPath }/css/dashboard.css" rel="stylesheet">
  </head>

  <body>
	<%@include file="/WEB-INF/views/module/header.jsp"%>
	<%@include file="/WEB-INF/views/module/left.jsp"%>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">전체 사용자 리스트</h1>
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
              
           	  <% List<UserVo> userList = (List<UserVo>)request.getAttribute("userList"); %>
           	  <% int cnt = 1; %>
              
				<c:forEach items="${userList }" var="user">
					<tr class="userTr" data-userId="${user.userId }">
						<td><%=cnt++ %></td>
						<td>${user.userId }</td>
						<td>${user.getUserId() }</td>
						
						<td>${user.userNm }</td>
						<td>-</td>
						<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy/MM/dd"/></td>
					</tr>
				</c:forEach>
              
              
              </tbody>
            </table>
            
            <form action="${cp }/user/userForm" method="get">
	            <button type="submit" class="btn btn-default">사용자등록</button>
            </form>
            
            <%
            	int userCnt  = (Integer)request.getAttribute("userCnt");
            	int pageSize = (Integer)request.getAttribute("pageSize");
            	int cPage = (Integer)request.getAttribute("page");
            	String cp = request.getContextPath();
            %>

		<c:set var="lastPage" value="${Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0)) }"/>
		
		<nav style="text-align: center">
			<ul class="pagination">
			
				<c:choose>
					<c:when test="${page == 1 }">
						<li class="disabled">
							<a aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.servletContext.contextPath }/user/userPagingList" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
							
				<c:forEach begin="1" end="${lastPage }" var="i">
					<c:set var="active" value=""/>
					<c:if test="${i == page }">
						<c:set var="active" value="active"/>
					</c:if> 
					
					<li class="${active }">
						<a href="${pageContext.servletContext.contextPath }/user/userPagingList?page=${i }">${i }</a>
					</li>
				</c:forEach>
				
				<c:choose>
					<c:when test="${page == lastPage }">
						<li class="disabled">
							<a aria-label="Next"> 
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.servletContext.contextPath }/user/userPagingList?page=${lastPage }" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--  JQuery가 먼저 load된 후 bootstrap이 로드 된다. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
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
    
  <form id="frm" action="${cp }/user/user" method="get">
  	<input type="hidden" id="userId" name="userId" />
  </form>
  </body>
</html>