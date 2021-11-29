/**
 * 
 */
package com.javateam.springMemberProject.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.springMemberProject.domain.MemberUpdateVO;
import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.service.MemberService;


/**
 * 회원 관리 controller
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("member") // 공통 경로
public class MemberController {
	
	private static final Logger log
		= LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	/**
	 * 회원 가입 폼
	 * 
	 * @return 회원가입 페이지
	 */
	// @RequestMapping("/member/member_join.do")
	@RequestMapping("/member_join.do")
	public String memberJoinForm() {
		
		log.info("회원가입 폼");
		
		return "/member/join_form";
	} //
	
	/**
	 * 회원 가입 처리
	 * 
	 * @param member 회원 정보 객체
	 * @param ra 리다이렉트 인자 객체
	 * @return
	 */
	@RequestMapping(value="/join_action.do", method=RequestMethod.POST)
	public String joinAction(MemberVO member, 
							 RedirectAttributes ra) {
		
		log.info("회원가입 처리");
		
		log.info("회원 가입정보 : " + member);
		
		// 회원 정보 저장
		boolean flag = memberService.insertMember(member);
		String msg = flag == true ? "회원 정보 저장에 성공하였습니다" 
				: "회원 정보 저장에 실패하였습니다";
		
		log.info("msg : {}", msg);
		
		// 인자 전송
		ra.addFlashAttribute("msg", msg);
		
		// return "redirect:/"; // home
		return "redirect:/member/member_join.do"; // 회원 가입 페이지
	}

	/**
	 * 개별 회원 정보 조회 폼
	 * @return
	 */
	@RequestMapping("/view_member.do")
	public String viewMemberForm() {
		
		log.info("개별 회원 정보 조회폼");
		
		return "/member/view_member";
	} //
	
	
	@RequestMapping(value="/view_action.do", method=RequestMethod.GET)
	// public String viewAction(HttpServletRequest request) {
	public String viewAction(@RequestParam("userid") String userid,
						     @RequestParam(value="mode", defaultValue="view") String mode,
						     @RequestParam(value="admin", defaultValue="0") int admin,
						     Model model,
						     HttpSession session) {
		
		log.info("개별 회원 정보 조회 처리");
		
		log.info("userid : {}", userid);
		log.info("mode : {}", mode);
		
		// 이동 페이지 : 회원 정보 조회/수정
		String forwardPage = mode.contentEquals("view") ? "view_member_detail" :
            				"update_member_detail";   
		
		// 회원 정보 조회
		MemberVO member = memberService.getMember(userid);
		
		log.info("조회된 회원 정보 : " + member);
		
		// 회원 정보 => 인자화 : model(조회)/session(수정)
		if (mode.equals("view")) { // 조회
			
			model.addAttribute("member", member);
			
		} else { // 수정
			
			// 관리자 여부 점검 => 관리자(admin=1) = 세션 교체(수정), 
			// 관리자(admin=0) = 세션 교체(X)
			if (admin == 0) { // 일반 회원
				if (session.getAttribute("SESS_DEFAULT_MEMBER")==null) {
					session.setAttribute("SESS_DEFAULT_MEMBER", member);
				}	
			} else { // admin == 1 (관리자)
				session.setAttribute("SESS_DEFAULT_MEMBER", member);
			} //
			
		} //
		
		return "/member/" + forwardPage;
	} //
	
	/**
	 * 개별 회원 정보 수정 폼
	 * @return
	 */
	@RequestMapping("/update_member.do")
	public String updateMemberForm() {
		
		log.info("개별 회원 정보 수정폼");
		
		return "/member/update_member";
	} //
	
	/**
	 * 회원 정보 수정 처리
	 * 
	 * @return
	 */
	@RequestMapping(value="/update_action.do", method=RequestMethod.POST)
	public String updateAction(MemberUpdateVO memberUpdateVO, 
							   HttpSession session,
							   Model model) {
		
		log.info("회원 정보 수정 처리");
		
		// 수정 회원정보 준비 : MemberVO
		MemberVO member = new MemberVO();
		
		// 이동할 페이지
		String forwardPage = "";
				
		// 기존 회원 정보
		MemberVO defaultMember = (MemberVO)session.getAttribute("SESS_DEFAULT_MEMBER");
		
		log.info("수정된 회원 정보 : " + memberUpdateVO);
		log.info("기존의 회원 정보 : " + defaultMember);
		
		member = defaultMember;
		
		// - 패쓰워드
		if (memberUpdateVO.getNewPwd().equals("") == false) {
			member.setPwd(memberUpdateVO.getNewPwd());
		}
		
		// - 이메일
		if (memberUpdateVO.getNewEmail().equals("") == false) {
			member.setEmail(memberUpdateVO.getNewEmail());
		}
		
		// - 연락처(전화번호) : 기존 정보와 신규 정보 동등 여부 점검 => 선택적 수정
		if (memberUpdateVO.getNewPhone().equals("") == false) {
			member.setPhone(memberUpdateVO.getNewPhone());
		}
		
		log.info("수정될 회원 정보 : " + member);
		
		// 회원 정보 수정
		boolean flag = memberService.updateMember(member);
		
		String msg = flag == true ? "회원 정보 수정에 성공하였습니다" 
				: "회원 정보 수정에 실패하였습니다";
		
		log.info("msg : " + msg);
		
		// 로그인 인증(authentication:auth) 정보
		// MemberVO loginInfo = (MemberVO)session.getAttribute("SESS_LOGIN_INFO");
		MemberVO loginInfo = member;
		
		if (msg.contentEquals("회원 정보 수정에 성공하였습니다")) {
			
			// 사용자 : 관리자(1) => 전체 회원 조회 페이지,
			// 사용자 : 일반회원(0) => MyPage
			if (loginInfo.getAdmin()==1) { // 관리자
				forwardPage = "/admin/paging_action.do?page=1"; // 관리자
			} else { // 사용자
				// forwardPage = "/member/view_action.do?userid="+loginInfo.getUserid()+"&mode=update";
				forwardPage = "/member/view_action.do?userid="+loginInfo.getUserid()+"&mode=view";
			} //
			
			forwardPage = "redirect:/" + forwardPage;   
			
		} else { // 실패
			
			log.info("############ 실패");
			
			forwardPage = "/error/error_msg";
			model.addAttribute("msg", msg);
			model.addAttribute("move_page", ""); // 에러 메시징 후 이동 페이지 (홈)
		} //
		
		log.info("##### 페이지 이동 : " + forwardPage);
		
		return forwardPage; 
	} //
	
	/**
	 * 회원 정보 삭제 처리
	 * 
	 * @param userid 회원 아이디
	 * @param admin 관리자 여부 ex) 관리자 : 1, 일반회원 : 0
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete_member.do")	
	public String deleteMember(@RequestParam("userid") String userid,
							   @RequestParam(value="admin", defaultValue="0") int admin,
							   HttpSession session,
							   Model model) {
		
		log.info("회원 정보 삭제 처리");
		
		log.info("회원 아이디 : " + userid);
		log.info("관리자 여부 : " + admin);
		
		// 회원 정보 삭제
		boolean flag = memberService.deleteMember(userid);
		
		String forwardPage = ""; // 이동 페이지 (성공/실패)
		String movePageError = ""; // 이동 페이지 (실패:에러 처리 페이지)
		String msg = ""; // 메시지
		
		if (flag == true) { // 삭제 성공
			
			log.info("회원 정보 삭제 성공");
			msg = "회원 정보 삭제 성공";
			
			if (admin == 1) { // 관리자
				
				forwardPage = "redirect:/admin/paging_action.do?page=1";
				
			} else { // 일반 회원
				
				// 세션 삭제 : 탈퇴 회원
				session.invalidate();
				forwardPage = "redirect:/";
			} //
			
		} else { // 삭제 실패
			
			log.info("회원 정보 삭제 실패");
			msg = "회원 정보 삭제 실패";
			
			if (admin == 1) { // 관리자
				movePageError = "admin/paging_action.do?page=1";
			} else { // 일반 회원
				movePageError = "member/my_page.do"; // myPage.jsp
			} //
			
			forwardPage = "/error/error_msg";
			model.addAttribute("msg", msg);
			model.addAttribute("move_page", movePageError);
		} //
		
		return forwardPage;
	} //
	
	/**
	 * 마이 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/my_page.do")
	public String myPage() {
		
		log.info("마이 페이지");
		
		return "/member/my_page";
	}
	
} //