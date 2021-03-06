<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<h1 class="page-header">사용자 리스트 페이징 Tiles적용</h1>
		
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

    <script type="text/javascript">
    	$(document).ready(function(){
    		console.log("document ready");
    		
    		$(".userTr").on("click", function(){
    			console.log("userTr click");
    			var userId = $(this).data("userid");
    			
    			$("#userId").val(userId);
    			$("#frm").submit();
				
    		});
    	});
    </script>
    
  <form id="frm" action="${cp }/user/user" method="get">
  	<input type="hidden" id="userId" name="userId" />
  </form>
