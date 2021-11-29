/**
 * 
 */
package com.javateam.springMemberProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.springMemberProject.domain.BoardVO;
import com.javateam.springMemberProject.service.BoardService;

/**
 *  게시글 수정/삭제 패쓰워드 입력폼 처리
 *  
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardCheckPassController {

	private static final Logger log
		= LoggerFactory.getLogger(BoardCheckPassController.class);
	
	@Autowired
	BoardService boardService;
				  
	@GetMapping("/board_check_pass.do")
	public String boardCheckPass(@RequestParam("num") int num,
								 @RequestParam("pass") String pass,
								 Model model) {
		
		log.info("게시글 수정/삭제 패쓰워드 입력폼 처리");
		String forwardPage = "";
		
		BoardVO boardVO = boardService.selectOneBoardByNum(num);
		
		// 패쓰워드 점검
		if (boardVO.getPass().equals(pass)) { // 패쓰워드 일치
			
			forwardPage = "/board/checkSuccess";
			
		} else { // 패쓰워드 불일치
			
			forwardPage = "/board/boardCheckPass";
			model.addAttribute("message", "비밀번호가 틀렸습니다.");
		} //
		
		return forwardPage;
	}
	
}
