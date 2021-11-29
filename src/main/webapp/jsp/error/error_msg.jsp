<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>에러 처리 페이지</title>
</head>
<body>
<script>
alert("에러 처리 페이지 : ${msg}");
location.href="${pageContext.request.contextPath}/${move_page}"; // 이동 페이지
</script>

<%-- <a href="${pageContext.request.contextPath}">홈(Home)</a> --%>
</body>
</html>