/**
 * 
 */
package com.javateam.springMemberProject.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javateam.springMemberProject.domain.MemberVO;

/**
 * 관리자 인증(auth) 인터셉터
 * 
 * @author javateam
 *
 */
@Service
public class AuthAdminInterceptorService extends HandlerInterceptorAdapter {

	private static final Logger log
		= LoggerFactory.getLogger(AuthAdminInterceptorService.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("admin 경로 진입");
		boolean result = true;
		String contextPath = request.getContextPath();
		 
		HttpSession session = request.getSession();
		String msg = session.getAttribute("SESS_LOGIN_INFO")==null ? "로그인 안됨" : "로그인";
		log.info("로그인 여부 : " + msg);
		
		if (msg.equals("로그인 안됨")) {
			
			response.sendRedirect(contextPath+"/auth/login.do");
			result = false;
			
		} else { // 로그인 상태
			
			MemberVO sessMember = (MemberVO)session.getAttribute("SESS_LOGIN_INFO");
			log.info("로그인 아이디 : " 
					+ sessMember.getUserid());
			
			// 관리자 여부 점검
			if (sessMember.getAdmin() == 1) {
				log.info(sessMember.getUserid() + "는 관리자입니다.");
			} else {
				log.info(sessMember.getUserid() + "는 일반회원입니다.");
				// myPage로 이동
				// response.sendRedirect(contextPath+"/member/my_page.do");
				
				// 에러 처리 페이지 이동
				request.setAttribute("msg", "관리자만 들어갈 수 있는 페이지입니다.");
				request.setAttribute("move_page", "member/my_page.do");
				
				RequestDispatcher rd 
					= request.getRequestDispatcher("/jsp/error/error_msg.jsp");
				rd.forward(request, response);
				
				result = false;
			} //
			
		} //
		
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("admin 경로 퇴거");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.info("view 종료");
	}
	
	
}