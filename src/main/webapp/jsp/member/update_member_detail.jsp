<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원수정폼</title>
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
	
	<div class="mx-auto mt-5" style="width:900px">
	
		<div class="mb-5" style="text-align:center">
			<h2>회원수정폼</h2>
		</div>
	
		<form method="post" class="form" action="${contextPath}/member/update_action.do">
		
			<table class="table">
				<tr>
					<th>이름</th>
					<!-- 참고) disabled 속성 : 전송 불가 -->
					<!-- <td><input type="text" name="name" size="20" 
						            readonly value="${SESS_DEFAULT_MEMBER.name}"></td> -->
					<td>
						${SESS_DEFAULT_MEMBER.name}
						<input type="hidden" name="name" value="${SESS_DEFAULT_MEMBER.name}">
					</td>
					
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						${SESS_DEFAULT_MEMBER.userid}
						<input type="hidden" name="userid" value="${SESS_DEFAULT_MEMBER.userid}">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<!-- 기존 비밀번호 -->
						<input type="hidden" name="pwd" value="${SESS_DEFAULT_MEMBER.pwd}" />
					
						<input type="password" name="newPwd" size="20" 
							   class="form-control w-50"
							   maxlength="10"
							   pattern="(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,10}"
							   title="비밀번호 영문 대소문자 및 특수문자를 포함시켜서 6~10자 사이로 작성하십시오."></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<!-- 기존 이메일 -->
						<input type="hidden" name="email" value="${SESS_DEFAULT_MEMBER.email}" />
						
						<input type="email" name="newEmail" size="20" 
							   maxlength="20"
							   class="form-control w-50"
							   required
							   pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"
							   title="올바른 이메일 형식으로 작성하십시오."
							   value="${SESS_DEFAULT_MEMBER.email}"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<!-- 기존 전화번호 -->
						<input type="hidden" name="phone" value="${SESS_DEFAULT_MEMBER.phone}" />
						
						<input type="text" name="newPhone" size="11" 
							   maxlength="13"
							   class="form-control w-50"
							   required
							   pattern="010-\d{4}-\d{4}"
							   title="올바른 휴대폰 번호를 작성하십시오."
							   value="${SESS_DEFAULT_MEMBER.phone}"></td>
				</tr>
				<tr>
					<th>등급</th>
					<td>
						${SESS_DEFAULT_MEMBER.admin eq "1" ? "관리자" : "일반회원"}
						
						<input type="hidden" name="admin" value="${SESS_DEFAULT_MEMBER.admin}" />
				</tr>
				<tr>
					<td colspan="2" class="pt-4" style="text-align:center">
						<input type="submit" class="btn btn-success mr-3 px-3" value="수정">
						<input type="reset" class="btn btn-success px-3" value="취소">
					</td>
				</tr>
			</table>
		</form>
	
	</div>
</body>
</html>