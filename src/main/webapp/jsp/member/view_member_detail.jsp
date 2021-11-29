<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>개별 회원정보 조회</title>
<style>
body{		width: 100%;
			height: 100%;
			background:url(../css/img/1.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>
</head>
<body>
<%-- 회원 아이디 : ${member.userid}<br> --%>

	<!-- 상단 공동 메뉴 -->
	<%@ include file="../menu/top.jsp" %>
	<!--// 상단 공동 메뉴 -->

	<div style="text-align:center" class="mt-5">
		<h2>회원정보</h2><br>
	</div>
	
	<!-- 회원정보가 없을 때 -->
	<c:if test="${empty member.userid}">
		<div align="center">조회하는 회원정보가 없습니다.</div>
	</c:if>
	<!--// 회원정보가 없을 때 -->
	
	<!-- 회원정보 있을 때 -->
	<c:if test="${not empty member.userid}">
		<table style="width:600px" class="table mx-auto">
			<tr>
				<td>이름</td>
				<td><%-- <input type="text" name="name" size="20" 
					  maxlength="10" readonly value="${member.name}"> --%>
					  ${member.name}
			    </td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${member.userid}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${member.pwd}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${member.phone}</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>${member.admin == 1 ? "관리자" : "일반회원"}</td>
			</tr>
		</table>
	</c:if>
	<!--// 회원정보 있을 때 -->
	
	<br>
	<br>
	
<!-- 	<a href="join_form.jsp">회원가입</a>&nbsp;
	<a href="view_member.html">개별 회원정보 조회</a> -->
</body>
</html>