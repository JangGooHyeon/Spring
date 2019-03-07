<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<h1 class="page-header">사용자 리스트 페이징 AjaxTiles적용</h1>
		
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
              
              <tbody id="userListTbody"></tbody>
            </table>
            
            <form action="${cp }/user/userForm" method="get">
	            <button type="submit" class="btn btn-default">사용자등록</button>
            </form>
            

		<c:set var="lastPage" value="${Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0)) }"/>
		
		<nav style="text-align: center">
			<ul class="pagination" id="pagination">
			
			</ul>
		</nav>
	</div>

<script type="text/javascript">

   	//사용자 배열을 이요하여 사용자 리스트 HTML을 생성
   	function makeUserList(userList) {

		var html = "";
		
		for(var i=0; i<userList.length; i++){
			var user = userList[i];
			html += "<tr class='userTr' data-userId='" + user.userId + "'>";
			html += "	<td></td>";	
			html += "	<td>" + user.userId + "</td>";
			html += "	<td>" + user.userNm + "</td>";
			html += "	<td></td>";
			html += "	<td>" + user.reg_dt_fmt + "</td>";
			html += "</tr>";
		}
		
		$("#userListTbody").html(html);
	}
   	function makePagination(userCnt, pageSize, page) {
		
   		var lastPage = parseInt(userCnt/pageSize) + (userCnt%pageSize > 0 ? 1 : 0);

   		var html = "";
   		
   		if(page == 1){
   			html += "<li class='disabled'>";
   			html += "	<a aria-label='Previous'>";
   			html += "		<span aria-hidden='true'>&laquo;</span>";
   			html += "	</a>";
   			html += "</li>";
   		} else {
   			html += "<li>";
//    			html += "	<a href='${cp }/user/userPagingList' aria-label='Previous'>";
   			html += "	<a href='javascript:getUserPageList(1);' aria-label='Previous'>";
   			html += "		<span aria-hidden='true'>&laquo;</span>";
   			html += "	</a>";
   			html += "</li>";
   		}
   		
   		for(var i=1; i<=lastPage; i++){
   			var active ="";
   			if(i==page){
   				active = "active";
   			}
   			html += "<li class='" + active + "'>";
//    			html += "	<a href='${cp }/user/userPagingList?page=" + i + "'>" + i + "</a>";
   			html += "	<a href='javascript:getUserPageList(" + i + ");'>" + i + "</a>";
   			html += "</li>";
   		}
   		
   		if(page == lastPage) {
			html += "<li class='disabled'>";
			html += "   <a aria-label='Next'>";
			html += "      <span aria-hidden='true'>&raquo;</span>";
			html += "   </a>";
			html += "</li>";
   	   }else{
			html += "<li>";
			html += "   <a href='${cp }/user/userPagingList?page="+lastPage+"' aria-label='Previous'>";
			html += "       <span aria-hidden='true'>&raquo;</span>";
			html += "   </a>";
			html += "</li>";
   	   }
   		
		$("#pagination").html(html);
   		
	}
   
    function getUserPageListHtml(page) {
		$.ajax({
			url : "${cp}/user/userPagingListAjaxHtml",
			data : "page=" + page,
			success : function(data) {
				console.log(data);
				
				var htmlArr = data.split("=============seperator=============");
				
				
				$("#userListTbody").html(htmlArr[0]);
				$("#pagination").html(htmlArr[1]);
			}
		})
	}
   
   	$(document).ready(function(){
   		console.log("document ready");
   		
//    		getUserPageList(1, 10);
		getUserPageListHtml(1);
   		
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
