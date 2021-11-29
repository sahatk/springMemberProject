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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.service.BoardService;
import com.javateam.springMemberProject.service.MemberService;

/**
 * 게시글 등록폼, 게시글 수정/삭제 패쓰워드 입력폼, 게시글 수정폼
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardFormController {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardFormController.class);
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("board_write_form.do")
	public String boardWriteForm(HttpSession session, Model model) {
		
		log.info("게시글 등록폼");
		MemberVO memberVO = (MemberVO)session.getAttribute("SESS_LOGIN_INFO");
		memberVO = memberService.getMember(memberVO.getUserid()); 
		
		model.addAttribute("member", memberVO);
		
		return "/board/boardWrite";
	} //
	
	@GetMapping("board_update_form.do")
	public String boardUpdateForm(@RequestParam("num") int num,
								  Model model) {
		
		log.info("게시글 수정폼");
		
		// 기존 게시글 
		model.addAttribute("board", boardService.selectOneBoardByNum(num));
		
		return "/board/boardUpdate";
	} //
	
	@GetMapping("board_check_pass_form.do")
	public String boardCheckPassForm() {
		
		log.info("게시글 수정/삭제 패쓰워드 입력폼");
		return "/board/boardCheckPass";
	} //
	
}