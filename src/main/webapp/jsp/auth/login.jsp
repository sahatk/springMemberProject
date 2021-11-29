<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
/* 컨텐츠(로그인 박스 포함) */
div#login-content
{
	/* 화면 전체 + 중앙 정렬(요소) */
	width:100%;
	height:calc(100vh - 60px);
	/* background-color:yellow; */
	
	display:flex;
	align-items:center;
	justify-content:center;	
}

/* 로그인 박스 */
div#login-box 
{
	 background-color:white;
}

body{		width: 100%;
			height: 100%;
			background:url(../css/img/2.jpg) center no-repeat;
			background-size: cover;
			margin: 0;}
</style>
</head>
<body>

	<!-- 상단 공동 메뉴 -->
	<%@ include file="../menu/top.jsp" %>
	<!--// 상단 공동 메뉴 -->
	
	<!-- 컨텐츠 -->
	<div id="login-content">
		
		<!-- 로그인 박스 -->
		<div id="login-box" class="border border-light rounded p-5">
			
			<form action="${contextPath}/auth/login_action.do" method="post">
			
				<!-- 타이틀 : 불릿+표제 -->
				<div class="bg-light pt-2 d-flex">
					<span class="material-icons mt-1 ml-2 mr-3">login</span>
					<h3>로그인</h3>
				</div>				
				<!--// 타이틀 -->
				
				<!-- 아이디  -->
				<div class="form-row my-3">
					<label class="col-4 pt-2" id="userid">아 이 디 :</label> 
					<input class="col-8 form-control" type="text" id="userid" name="userid" size="20" 
						maxlength="10" 
						required
						pattern="[a-zA-Z]{1}\w{1,9}"
						title="아이디는 2~10자 사이로 영문/숫자 조합으로 입력하셔야 됩니다.">
				</div>
				<!--// 아이디  -->
				
				<!-- 패쓰워드 -->
				<div class="form-row my-3">
					<label class="col-4 pt-2" for="pwd">패쓰워드 :</label>
					<input class="col-8 form-control" type="text" id="pwd" name="pwd" size="20" 
					  maxlength="10"
					  required
					  pattern="(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,10}"
					  title="비밀번호 영문 대소문자 및 특수문자를 포함시켜서 6~10자 사이로 작성하십시오.">
				</div>
				<!--// 패쓰워드 -->
				
				<!-- 버튼 : 로그인/취소/회원가입 -->
				<div>
			    	<input class="btn btn-outline-secondary mr-2 px-3" type="submit" value="로그인">&nbsp;
				    <input class="btn btn-outline-secondary mr-2 px-3" type="reset" value="취소">&nbsp;
				    <!-- <input class="btn btn-outline-secondary" type="button" value="회원가입" onclick="javascript:location.href='join_form.jsp'"> -->
				    <input class="btn btn-outline-secondary" type="button" value="회원가입" 
				    	   onclick="javascript:location.href='${contextPath}/member/member_join.do'">
				</div>
				<!--// 버튼 -->
			
			</form>
		
		</div>				
		<!--// 로그인 박스 -->
		
	</div>
	<!--// 컨텐츠 -->

</body>
</html>