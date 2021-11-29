<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
a:hover{background : #7CD696;}
</style>
<title>상단부(메뉴)</title>
	
  <!-- google icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  
  <!-- bootstrap(css) -->  
  <link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap.min.css' />">
  
  <!-- jquery -->
  <script src="<c:url value='/jquery/jquery.min.js' />"></script>
  
  <!-- popper.js --> 
  <script src="<c:url value='/popper.js/umd/popper.min.js' />"></script>
  
  <!-- bootstrap(js) -->
  <script src="<c:url value='/bootstrap/js/bootstrap.min.js' />"></script>
</head>
<body>

<!-- 인자 현황 팝업 -->
<%@ include file="param_dash_board.jsp" %>
<!--// 인자 현황 팝업 -->

<!-- 메시징 -->
<c:if test="${not empty msg}">
<script>
	alert("${msg}");
</script>
</c:if>
<!-- // 메시징 -->

<!-- context Path -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- 홈, 로그인/로그아웃, 마이 페이지 등등 -->

<nav class="navbar navbar-expand-sm bg-success navbar-dark">

  <!-- Brand/logo -->
  <a class="navbar-brand text-dark" href="<c:url value='/' />">javateam</a>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link text-dark" href="<c:url value='/' />">홈</a>
    </li>
    
    <!-- 인증 상태에 따라 로그인/로그아웃 -->
	<c:choose>
	
		<%-- 로그인 : 로그아웃 상태 --%>
		<c:when test="${empty SESS_LOGIN_INFO}">
			<li class="nav-item">
				<a class="nav-link text-dark" href="<c:url value='/member/member_join.do' />">회원가입</a>
			</li>
		
		    <li class="nav-item">
		      <a class="nav-link text-dark" href="<c:url value='/auth/login.do' />">로그인</a>
		    </li>
		</c:when>
		
		<%-- 로그아웃 : 로그인 상태 --%>
		<c:otherwise>
			<li class="pt-2 pl-3 pr-1" style="color:#fff">
				${SESS_LOGIN_INFO.userid}님 로그인하셨습니다&nbsp;
			</li>
			<li class="nav-item">
		      <a class="nav-link text-dark" href="<c:url value='/auth/logout.do' />">로그아웃</a>
		    </li>
		</c:otherwise>
	</c:choose>
	<!--// 인증 상태에 따라 로그인/로그아웃 -->
    
    <!-- 마이페이지/회원 정보 보기/수정/삭제 -->
    <c:if test="${not empty SESS_LOGIN_INFO}">
        
        <!-- 마이 페이지 --> 
        <li class="nav-item">
	    	<a class="nav-link text-dark" href="<c:url value='/member/my_page.do' />">My Page</a>
	    </li>
	    
	    <!-- 회원 게시판 -->
	    <li class="nav-item">
	    	<a class="nav-link mx-3 text-dark" href="<c:url value='/board/board_list.do' />">게시판</a>
	    </li>
	    
	    <!-- 회원 정보 보기 -->
	    <li class="nav-item">
	    	<a class="nav-link text-dark" href="<c:url value='/member/view_action.do?userid=${sessionScope.SESS_LOGIN_INFO.userid}&mode=view' />">회원정보 보기</a>
	    </li>
	    
	    <!-- 회원 정보 수정 -->
	    <li class="nav-item">
	    	<%-- <a class="nav-link" href="<c:url value='/member/view_member.do?userid=${sessionScope.SESS_LOGIN_INFO.userid}&mode=update' />">회원정보 수정</a> --%>
	    	<a class="nav-link text-dark" href="<c:url value='/member/view_action.do?userid=${sessionScope.SESS_LOGIN_INFO.userid}&mode=update' />">회원정보 수정</a>
	    </li>
	    
	    <!-- 회원 정보 삭제 -->
	    <li class="nav-item">
	    	<%-- <a class="nav-link" href="<c:url value='/member/view_member.do?userid=${sessionScope.SESS_LOGIN_INFO.userid}&mode=delete' />">회원정보 삭제</a> --%>
	    	<a class="nav-link text-dark" href="<c:url value='/member/delete_member.do?userid=${sessionScope.SESS_LOGIN_INFO.userid}&mode=delete' />">회원정보 삭제</a>
	    </li>
	    
    </c:if>
    
    <!-- 관리자 메뉴 : 전체 회원 정보 조회 -->
    <c:if test="${SESS_LOGIN_INFO.admin == 1}">
     	<li class="nav-item">
	    	<a class="nav-link text-dark" href="<c:url value='/admin/paging_action.do' />">전체 회원 정보 조회</a>
	    </li>
    </c:if>
    <!--// 관리자 메뉴 : 전체 회원 정보 조회 -->
    
    <!-- 인자 현황 대시(dash) 보드 : 팝업(모달) -->
   		<li class="nav-item ml-5">
			<button type="button" class="btn btn-sm btn-danger mt-1" data-toggle="modal" data-target="#paramDashBoard">
			  인자 현황
			</button>
	    </li>
    <!--// 인자 현황 대시(dash) 보드 : 팝업(모달) -->
        
  </ul>
</nav>
</body>
</html>