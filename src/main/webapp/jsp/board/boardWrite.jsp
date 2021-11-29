<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>게시글 등록</title>

<%@ include file="../menu/top.jsp" %>

<script type="text/javascript" src="<c:url value='/js/board.js' />" charset="UTF-8"></script>
</head>
<body>
	<div id="wrap" class="container mx-auto mt-5" align="center" style="width:700px">
	
		<h1>게시글 등록</h1>
		
		<form name="frm" class="form" method="post" action="<c:url value='/board/board_write.do' />">
		
			<input type="hidden" name="userid" value="${member.userid}" />
			
			<table class="table table-borderless mt-3">
				<tr>
					<th class="col-3 pt-4">작성자 </th>
					<td><input type="text" class="form-control my-2" name="name" 
							   value="${member.name}" readonly> </td>
				</tr>
				<tr>
					<th class="col-3 pt-4">비밀번호 <span style="color:red">(* 필수)</span></th>
					<td><input type="password" class="form-control my-2" name="pass">
					(게시물 수정 삭제시 필요합니다.)</td>
				</tr>
				<tr>
					<th class="col-3 pt-4">이메일</th>
					<td><input type="text" class="form-control my-2" name="email"
					           value="${member.email}"></td>
				</tr>
				<tr>
					<th class="col-3 pt-4">제목 <span style="color:red">(* 필수)</span></th>
					<td><input type="text" class="form-control my-2" size="70" name="title"></td>
				</tr>
				<tr>
					<th class="col-3 pt-4">내용</th>
					<td><textarea class="form-control my-2" style="resize:none" 
						   cols="70" rows="8" name="content"></textarea></td>
				</tr>
			</table>
			<br>
			<br>
				<input type="submit" class="btn btn-success mr-2" value="등록"
					onclick="return boardCheck()"> 
				<input type="reset" class="btn btn-success mr-2" value="다시 작성"> 
				<input type="button" value="목록" class="btn btn-success"
					onclick="location.href='<c:url value="/board/board_list.do" />'">
		</form>
	</div>
</body>
</html>