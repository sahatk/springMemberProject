<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입폼</title>
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

	<!-- 상단 공동 메뉴 -->
	<%@ include file="../menu/top.jsp" %>
	<!--// 상단 공동 메뉴 -->
	
	<div class="mx-auto mt-5" style="width:700px">
	
		<div class="mb-5" style="text-align:center">
			<h2>회원가입폼</h2>
		</div>
	
		<form method="post" class="form" action="<c:url value='/member/join_action.do' />">
			
			<table class="table">
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" size="20" 
						  maxlength="10"
						  class="form-control w-50" 
						  required 
						  pattern="[가-힣]{2,10}" 
						  title="이름은 한글 2~10자만 허용합니다."></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userid" size="20" 
							maxlength="10" 
							class="form-control w-50"
							required
							pattern="[a-zA-Z]{1}\w{1,9}"
							title="아이디는 2~10자 사이로 영문/숫자 조합으로 입력하셔야 됩니다."></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" size="20" 
						  maxlength="10"
						  class="form-control w-50"
						  required
						  pattern="(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,10}"
						  title="비밀번호 영문 대소문자 및 특수문자를 포함시켜서 6~10자 사이로 작성하십시오."></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" size="20" 
							maxlength="20"
							class="form-control w-50"
							required
							pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"
							title="올바른 이메일 형식으로 작성하십시오."
							></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="phone" size="11" 
							class="form-control w-50"
							maxlength="13"
							pattern="010-\d{4}-\d{4}"
							title="올바른 휴대폰 번호를 작성하십시오.">
					</td>
				</tr>
				<tr>
					<th>등급</th>
					<td>
					 	<div class="custom-control custom-radio">

							<input type="radio" name="admin" value="1" id="role_admin" 
								class="custom-control-input"> 
							<label class="custom-control-label mr-5" for="role_admin">관리자</label> 
							
							<input type="radio" name="admin" value="0" id="role_member" 
								checked="checked" class="custom-control-input"> 
							<label class="custom-control-label" for="role_member">일반회원</label>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="pt-4" style="text-align:center">
						<input type="submit" class="btn btn-success mr-3 px-3" value="가입">
						<input type="reset" class="btn btn-success px-3" value="취소">
					</td>
				</tr>
			</table>
		</form>
	
	</div>
</body>
</html>