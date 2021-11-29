/**
 * 
 */
package com.javateam.springMemberProject.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.service.MemberService;

/**
 * 로그인 인증 
 * 
 * @author javateam
 */
@Controller
@RequestMapping("auth")
public class AuthController {

	private static final Logger log 
		= LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login.do")
	public String loginForm(HttpSession session) {
		
		log.info("로그인 페이지");

		// 로그인 여부 점검(인증 세션 여부 점검)		
		return session.getAttribute("SESS_LOGIN_INFO") != null ? 
				"/member/my_page" :	"/auth/login";
	} //
	
	@PostMapping("/login_action.do")
	public String loginAction(@RequestParam("userid") String userid,
							  @RequestParam("pwd") String pwd,
							  HttpSession session,
							  Model model) {
		
		log.info("로그인 처리");
		
		log.info("아이디 : " + userid);
		log.info("패쓰워드 : " + pwd);
		
		String msg = ""; // 인증 (에러) 메시지
		MemberVO member = null; // 기존 회원 정보
		String forwardPage = ""; // 이동 페이지/주소
		
		// 인증 정보 점검 : MemberService checkLogin
		// 인증 성공 => 세션(SESS_LOGIN_INFO) 생성
		// 인증 실패 => 페이지 이동 : 메시징 + 로그인
		msg = memberService.checkLogin(userid, pwd);
		
		log.info("인증 메시지 : " + msg);
		
		if (msg.equals("로그인에 성공하였습니다")) {
		
			// 세션 생성
			if (session.getAttribute("SESS_LOGIN_INFO") == null) { // 세션 생성 여부 점검
				
				MemberVO sessionVO = new MemberVO();
				member = memberService.getMember(userid);
				
				// 세션 : 아이디/관리자여부/이름
				sessionVO.setUserid(userid);			
				sessionVO.setAdmin(member.getAdmin());
				sessionVO.setName(member.getName());
				session.setAttribute("SESS_LOGIN_INFO", sessionVO);
				
				// 관리자
				if (member.getAdmin() == 1) {
					forwardPage = "redirect:/admin/paging_action.do";
				} else { // 일반인
					forwardPage = "redirect:/member/my_page.do";
				} //
				
			} //
		
		} else { // 비회원, 비밀번호 불일치
			forwardPage = "/error/error_msg";
			model.addAttribute("msg", msg);
			model.addAttribute("move_page", "auth/login.do");
		} //
		
		return forwardPage;
	} //
	
	/**
	 * 로그아웃 처리
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/logout.do")
	public String logout(HttpSession session, Model model) {
		
		log.info("로그아웃 처리");
		
		String userid = ((MemberVO)session.getAttribute("SESS_LOGIN_INFO")).getUserid();
		model.addAttribute("userid", userid);
		
		session.invalidate();
		
		return "/auth/logout";
	}
	
} //