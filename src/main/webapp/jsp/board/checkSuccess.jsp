<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>체크 결과</title>
</head>
<body>

<script type="text/javascript">

	if (window.name == "update") {
		
		window.opener.parent.location.href 
			= "${pageContext.request.contextPath}/board/board_update_form.do?num=${param.num}";
	
	} else if (window.name == 'delete') {
	
		// alert('삭제되었습니다.');
		window.opener.parent.location.href 
			= "${pageContext.request.contextPath}/board/board_delete.do?num=${param.num}";
	}
	
	window.close(); // 창닫기
</script>
</body>
</html>