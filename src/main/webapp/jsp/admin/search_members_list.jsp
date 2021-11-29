<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 검색 현황</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
/* 회원 부재시  */
div#msg_empty_members {
	width: 100%;
	height: 80vh;
	display: flex;
	align-items: center;
	justify-content: center
}

/* 페이징 */
div#paging_menu {
	width: 100%;
	height: 100px;
	display: flex;
	align-items: center;
	justify-content: center
}
.pagination a:hover{color:green;}
body{		width: 100%;
			height: 100%;
			background:url(../css/img/1.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>
</head>
<body>

	<!-- 페이지 현황 : ${pageDTO}<br>
	검색 현황 : ${members}
	<hr> -->
	 
	<!-- 상단 공동 메뉴 -->
	<%@ include file="../menu/top.jsp" %>
	<!--// 상단 공동 메뉴 -->
		
	<script>
	function checkDelete(delete_id) {
		
		if (confirm("정말 삭제하시겠습니까")) {
			console.log("삭제");
			console.log("주소 : " + "${contextPath}/member/delete_member.do?userid="+delete_id+"&admin=1");
			location.href="${contextPath}/member/delete_member.do?userid="+delete_id+"&admin=1";
			
		} else {
			console.log("취소");
			
		} //
	}

	
	$(function() {
		
		// 전체 회원 목록으로 이동
		$("#members_list_btn").click(function(e){
			location.href = '${contextPath}/admin/paging_action.do';
		});
	});
	</script> 

	<div align="center" class="mt-5">
		<h3>검색 회원 현황</h3>
	</div>

	<!-- 회원정보 존재하지 않을 때 -->
	<c:if test="${empty members}">
		<div id="msg_empty_members">회원정보가 존재하지 않습니다.</div>
	</c:if>
	<!--// 회원정보 존재하지 않을 때 -->

	<!-- 회원정보 존재시 -->
	<c:if test="${not empty members}">

		<!-- 회원 목록 출력 -->
		<table class="table table-hover mx-auto w-75 mt-5 mb-5">
			<!-- <border="1" width="80%" align="center"> -->
			<!-- 제목 -->
			<tr class="table-dark">
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>패쓰워드</th>
				<th>이메일</th>
				<th>연락처</th>
				<th>관리자</th>
				<th>메뉴</th>
			</tr>
			<!-- 내용 -->
			<c:forEach var="member" items="${members}" varStatus="vs">
				<tr>
					<!-- 번호 : (현재 페이지-1)*10 + vs.count -->
					<td>${(pageDTO.page-1)*pageDTO.limit+vs.count}</td>
					<td>${member.name}</td>
					<td>${member.userid}</td>
					<td>${member.pwd}</td>
					<td>${member.email}</td>
					<td>${member.phone}</td>
					<td>${member.admin==1 ? "관리자" : "사용자"}</td>
					
					<!-- 메뉴 : 회원정보 수정/삭제 -->
					<td>
						<div class="btn-group btn-group-sm">
						
						    <button type="button" class="btn btn-success"
						        onclick="location.href='<c:url value='/member/view_action.do?userid=${member.userid}&mode=update&admin=1' />'">
						    	수정
					    	</button>		
					    												 
					    	<button type="button" onclick="checkDelete('${member.userid}')" class="btn btn-success">
						    	삭제
					    	</button>
					    	
					    </div>
					</td>
					<!--// 메뉴 : 회원정보 수정/삭제 -->
				</tr>
			</c:forEach>
		</table>
		<!--// 회원 목록 출력 -->

		<!-- 페이징 메뉴 -->
		<!-- 페이징 메뉴 정책 : 처음/마지막 페이지, 이전/다음, 현재 페이지 -->

		<div id="paging_menu">

			<!-- 페이징 -->
			<ul class="pagination pagination-sm justify-content-center">
				<li class="page-item"><a class="page-link"
					href="${contextPath}/admin/search_action.do?search_page=1&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}"> <span class="material-icons">
							keyboard_double_arrow_left </span>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="${contextPath}/admin/search_action.do?search_page=${pageDTO.page-1<=0 ? 1 : pageDTO.page-1}&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}"> <span
						class="material-icons">keyboard_arrow_left</span>
				</a></li>
				<li class="page-item active"><a class="page-link"
					href="${contextPath}/admin/search_action.do?search_page=${pageDTO.page}&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}"> <span
						style="font-size: 1.4em">&nbsp;${pageDTO.page}&nbsp;</span>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="${contextPath}/admin/search_action.do?search_page=${pageDTO.page>=pageDTO.lastPage ? pageDTO.page : pageDTO.page+1}&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}">
						<span class="material-icons">keyboard_arrow_right</span>
				</a></li>
				<li class="page-item"><a class="page-link"
					href="${contextPath}/admin/search_action.do?search_page=${pageDTO.lastPage}&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}"> <span
						class="material-icons">keyboard_double_arrow_right</span>
				</a></li>
			</ul>
			<!-- // 페이징  -->
			
		</div>
		<!--// 페이징 메뉴 -->
		
	</c:if>
	<!--// 회원정보 존재시 -->
	
	<!-- 회원 검색 : 이름, 아이디, 이메일, 연락처 -->
	<div class="container mb-5" style="width:700px">
	
		<form method="get" class="form-inline" action="${contextPath}/admin/search_action.do">
		
			<!-- 검색 페이지 : hidden field -->
			<%-- <input type="text" name="search_page" class="form-control mr-2 w-5" value="${empty searchVO.page ? 1 : searchVO.page}" /> --%>
			<input type="hidden" name="search_page" value="${empty searchVO.page ? 1 : searchVO.page}" />
			<!--// 검색 페이지 -->
			
			<!-- 검색 구분 : select -->
			<select name="search_kind" class="custom-select mr-2 w-20">
				<option value="name" selected>이름</option>
				<option value="userid">아이디</option>
				<option value="email">이메일</option>
				<option value="phone">휴대폰</option>
			</select>
			<!--// 검색 구분 -->
			
			<!-- 검색어 -->
			<input type="text" name="search_word" class="form-control mr-2 w-50" />
			<!--// 검색어 -->
			
			<!-- 검색 버튼 -->
			<button type="submit" class="btn btn-success mr-3">검색</button>
			<!--// 검색 버튼 -->
			
			<!-- 전체 회원 목록 버튼 -->
			<button type="button" id="members_list_btn" class="btn btn-success">전체 회원 목록</button>
			<!--// 전체 회원 목록 버튼 -->
			
		</form>
		
	</div>		
	<!--// 회원 검색  -->
		
</body>
</html>