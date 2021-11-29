<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body{		width: 100%;
			height: 100%;
			background:url(../css/img/1.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>
<title>게시글 상세보기</title>

<%@ include file="../menu/top.jsp" %>

<script type="text/javascript" src="<c:url value='/js/board.js' />" charset="UTF-8"></script>
</head>
<body>
	<div id="wrap" class="container mx-auto mt-5" style="width:700px" align="center">
		
		<h1>게시글 상세보기</h1>
		
		<table class="table mt-5">
			<tr>
				<th>작성자</th>
				<td>${board.name}</td>
				<th>이메일</th>
				<td>${board.email}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate}" /></td>
				<th>조회수</th>
				<td>${board.readcount}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${board.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${board.content}</pre></td>
			</tr>
		</table>
		
		<br> <br> 
		
		<!-- 이전 페이지 이동 : 추가 -->
		<!-- 검색이 아닌 경우 -->		
		<c:if test="${searchYN == 'N'}">
			<input type="button" class="btn btn-primary mr-3" value="이전 페이지"
				onclick="location.href='${contextPath}/board/board_list.do?page=${page}'">
		</c:if>
		
		<!-- 검색인 경우 -->		
		<c:if test="${searchYN == 'Y'}">
			<input type="button" class="btn btn-primary mr-3" value="이전 페이지"
				onclick="location.href='${contextPath}/board/board_search_list.do?search_page=${page}&search_kind=${searchVO.searchKind}&search_word=${searchVO.searchWord}'">
		</c:if>
				
		<input type="button" class="btn btn-success mr-3" value="이전 페이지"
			onclick="location.href='${contextPath}/board/board_list.do?page=${page}'">
		<input type="button" class="btn btn-success mr-3" value="게시글 수정"
			onclick="open_win('${contextPath}/board/board_check_pass_form.do?num=${board.num}', 'update')">
		<input type="button" class="btn btn-success mr-3" value="게시글 삭제"
			onclick="open_win('${contextPath}/board/board_check_pass_form.do?num=${board.num}', 'delete')">
		<input type="button" class="btn btn-success mr-3" value="게시글 리스트"
			onclick="location.href='${contextPath}/board/board_list.do'"> 
		<input type="button" class="btn btn-success mr-3" value="게시글 등록"
			onclick="location.href='${contextPath}/board/board_write_form.do'">
	</div>
</body>
</html>