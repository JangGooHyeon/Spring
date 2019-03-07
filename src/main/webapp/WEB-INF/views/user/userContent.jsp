<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<h1 class="page-header">사용자 정보 조회 Tiles적용</h1>
		
		<form id="frm" class="form-horizontal" role="form" action="${cp }/user/userModifyForm" method="get">
		
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">프로필 사진</label>
				<div class="col-sm-9">
					<img src="${cp }/user/profileImg?userId=${userVo.userId }">
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">사용자 아이디</label>
				<div class="col-sm-9">
					<label class="control-label">${userVo.getUserId() }</label>
					<input type="hidden" id="userId" name="userId" value="${userVo.userId }"/>
				</div>
			</div>

			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">사용자 이름</label>
				<div class="col-sm-9">
					<label class="control-label" >${userVo.userNm }</label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">별명</label>
				<div class="col-sm-9">
					<label class="control-label">${userVo.alias }</label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">주소</label>
				<div class="col-sm-9">
					<label class="control-label">${userVo.addr1 }</label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">상세주소</label>
				<div class="col-sm-9">
					<label class="control-label">${userVo.addr2 }</label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">우편번호</label>
				<div class="col-sm-9">
					<label class="control-label">${userVo.zipcode }</label>
				</div>
			</div>

			<div class="form-group">
				<label for="userNm" class="col-sm-3 control-label">등록일시</label>
				<div class="col-sm-9">
					<label class="control-label"><fmt:formatDate value="${userVo.reg_dt }" pattern="yyyy-MM-dd"/></label>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-9">
					<button id="btn_modify" type="submit" class="btn btn-default">사용자 정보 수정</button>
				</div>
			</div>
		</form>
		
	</div>
    <script type="text/javascript">
    
    $(document).ready(function(){
    	//server side 에서 비교
        <c:if test="${msg != null }">
            alert("${msg }");
        </c:if>
    });
    
    </script>
