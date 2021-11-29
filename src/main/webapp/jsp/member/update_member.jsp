<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>개별 회원정보 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body{		width: 100%;
			height: 100%;
			background:url(../css/img/1.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>
</head>
<body>

	<form action="${pageContext.request.contextPath}/member/view_action.do" method="get">
	
		<label id="userid">조회할 회원 아이디 : </label>
		
		<input type="text" id="userid" name="userid" size="20" 
			maxlength="10"
			required
			pattern="[a-zA-Z]{1}\w{1,9}"
			title="아이디는 2~10자 사이로 영문/숫자 조합으로 입력하셔야 됩니다.">
			
		<!-- 추가 필드 : update(수정) 모드 -->
		<!-- <input type="hidden" name="mode" value="update" /> -->
		<input type="text" name="mode" value="update" readonly />
			
		<input type="submit" value="회원정보 조회" />
		
	</form>
	
</body>
</html>