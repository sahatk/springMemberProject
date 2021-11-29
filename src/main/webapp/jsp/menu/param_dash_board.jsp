<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

  <div class="modal" id="paramDashBoard">
    <div class="modal-dialog modal-dialog-scrollable modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">인자 현황</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	<table class="table">
				<tr>
					<td class="col-5">마지막 페이지 : </td><td>${pageDTO.lastPage}</td> 
				</tr>
				<tr>	
          			<td>로그인 정보(세션) : </td><td>${SESS_LOGIN_INFO}</td>
          		</tr>
          		<tr>
          			<td>메시지 : </td><td>${msg}</td>          			
          		</tr>
          		<tr>
          			<td>세션 기존 회원 정보 : </td><td>${sessionScope.SESS_DEFAULT_MEMBER}</td>
          		</tr>
          		<tr>
          			<td>게시판 정보(크기) : </td><td>${boardList.size()}</td>
          		</tr>
          		<tr>
          			<td>게시판 페이징 (현재 페이지) : </td><td>${page}</td>
          		</tr>
          		<tr>
          			<td>게시판 페이징 (마지막 페이지) : </td><td>${last_page}</td>
          		</tr>
          	</table>          
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div> 
  
</body>
</html>