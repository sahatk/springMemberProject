<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<style>
div#logout-content
{
	/* 화면 전체 + 중앙 정렬(요소) */
	width:100%;
	height:calc(100vh - 60px);
	
	display:flex;
	align-items:center;
	justify-content:center;	
}
body{		width: 100%;
			height: 100%;
			background:url(../css/img/2.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
h2 {
	 background-color:white;
}
</style>
</head>
<body>

<!-- 상단 공동 메뉴 -->
<%@ include file="../menu/top.jsp" %>
<!--// 상단 공동 메뉴 -->

<div id="logout-content" align="center">
	<h2>${userid} 님 로그아웃되었습니다.</h2><br>
</div>

</body>
</html>