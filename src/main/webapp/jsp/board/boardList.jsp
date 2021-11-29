<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글 리스트</title>
<style>
.pagination a:hover{color:green;}
body{		width: 100%;
			height: 100%;
			background:url(../css/img/1.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>

<%@ include file="../menu/top.jsp" %>

</head>
<body>
	<div id="wrap" class="container mx-auto w-80 mt-5"><!--  align="center"> -->
		
		<div align="center">
			<h1>게시글 리스트</h1>
		</div>
		
		<div class="mx-auto my-3" align="right">
			<a class="btn btn-success" href="${contextPath}/board/board_write_form.do">게시글 등록</a>
		</div>
		
		<table class="table table-hover">
			
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			
			<c:forEach var="board" items="${boardList}">
				<tr class="record">
					<td>${board.num}</td>
					<td>
						<a href="${contextPath}/board/board_view.do?num=${board.num}&page=${page}">
							${board.title} 
						</a>
					</td>
					<td>${board.name}</td>
					<td><fmt:formatDate value="${board.writedate}" /></td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 페이징 -->
	<!-- 페이징 메뉴 정책 : 처음/마지막 페이지, 이전/다음, 현재 페이지 -->
	<div id="paging_menu">

		<!-- 페이징 -->
		<ul class="pagination pagination-sm justify-content-center">
			<li class="page-item"><a class="page-link"
				href="${contextPath}/board/board_list.do?page=1"> <span class="material-icons">
						keyboard_double_arrow_left </span>
			</a></li>
			<li class="page-item"><a class="page-link"
				href="${contextPath}/board/board_list.do?page=${page-1<=0 ? 1 : page-1}"> <span
					class="material-icons"> keyboard_arrow_left </span>
			</a></li>
			<li class="page-item active"><a class="page-link"
				href="${contextPath}/board/board_list.do?page=${page}"> <span
					style="font-size: 1.4em">${page}</span>
			</a></li>
			<li class="page-item"><a class="page-link"
				href="${contextPath}/board/board_list.do?page=${page>=last_page ? page : page+1}">
					<span class="material-icons"> keyboard_arrow_right </span>
			</a></li>
			<li class="page-item"><a class="page-link"
				href="${contextPath}/board/board_list.do?page=${last_page}"> <span
					class="material-icons"> keyboard_double_arrow_right </span>
			</a></li>
		</ul>
	</div>
	<!--// 페이징 -->
	
	<!-- 게시판 검색 : 작성자, 글제목, 글내용 -->
	<div class="container my-5" style="width:700px">
																	  
		<form method="get" class="form-inline" action="${contextPath}/board/board_search_list.do">
		
			<!-- 검색 페이지 : hidden field -->
			<input type="hidden" name="search_page" value="1" />
			<!--// 검색 페이지 -->
			
			<!-- 검색 구분 : select -->
			<select name="search_kind" class="custom-select mr-2 w-20">
				<option value="name">작성자</option>
				<option value="title" selected>글제목</option>
				<option value="content">글내용</option>
			</select>
			<!--// 검색 구분 -->
			
			<!-- 검색어 -->
			<input type="text" name="search_word" required class="form-control mr-2 w-50" />
			<!--// 검색어 -->
			
			<!-- 검색 버튼 -->
			<button type="submit" class="btn btn-success mr-3">검색</button>
			<!--// 검색 버튼 -->
			
			<!-- 게시글 목록 버튼 -->
			<button type="button" id="members_list_btn" class="btn btn-success"
				onclick="location.href='${contextPath}/board/board_list.do?page=1'">게시글 목록</button>
			<!--// 게시글 목록 버튼 -->
			
		</form>
		
	</div>		
	<!--// 회원 검색  -->
	
</body>
</html>