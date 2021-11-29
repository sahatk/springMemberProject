<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>게시글 수정</title>

<%@ include file="../menu/top.jsp" %>

<script type="text/javascript" src="<c:url value='/js/board.js' />" charset="UTF-8"></script>
</head>
<body>

	<div id="wrap" class="container mx-auto mt-5" align="center" style="width:700px">
	
		<h1>게시글 수정</h1>
		
		<form name="frm" class="form" method="post" action="<c:url value='/board/board_update.do' />">
		
			<input type="hidden" name="num" value="${board.num}">
				
			<table class="table table-borderless mt-3">
				<tr>
					<th class="col-3 pt-4">작성자  <span style="color:red"></span></th>
					<td><input type="text" size="12" name="name"
						class="form-control my-2 bg-white" value="${board.name}" readonly></td>
				</tr>
				<tr>
					<th class="col-3 pt-4">비밀번호 <span style="color:red">(* 필수)</span></th>
					<td><input type="password" size="12" name="pass" class="form-control my-2">
					(게시물 수정 삭제시 필요합니다.)</td>
				</tr>
				<tr>
					<th class="col-3 pt-4">이메일</th>
					<td><input type="text" size="40" maxlength="50" name="email" 
					     class="form-control my-2" value="${board.email}"></td>
				</tr>
				<tr>
					<th class="col-3 pt-4">제목</th>
					<td><input type="text" size="70" name="title" class="form-control my-2"
						value="${board.title}"></td>
				</tr>
				<tr>
					<th class="col-3 pt-4">내용</th>
					<td><textarea cols="70" rows="8" name="content" class="form-control my-2"
						 class="form-control my-2" style="resize:none">${board.content}</textarea></td>
				</tr>
			</table>
			<br>
			<br> 
				<input type="submit" class="btn btn-success mr-3" value="등록"
					onclick="return boardCheck()"> 
				<input type="reset"	class="btn btn-success mr-3" value="다시 작성"> 
				<input type="button" class="btn btn-success" value="목록"
					onclick="location.href='<c:url value="/board/board_list.do" />'">
		</form>
	</div>
</body>
</html>