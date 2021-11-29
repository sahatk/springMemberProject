/**
 * 
 */
package com.javateam.springMemberProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.springMemberProject.service.BoardService;

/**
 * 게시글 삭제 처리
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardDeleteController {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardDeleteController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("board_delete.do")
	public String boardDelete(@RequestParam("num") int num,
							  RedirectAttributes ra) {
		
		log.info("게시글 삭제 처리");
		String msg = ""; // 메시지
		
		if (boardService.deleteBoard(num) == true) {
			msg = "게시글 삭제에 성공하였습니다.";
		} else {
			msg = "게시글 삭제에 실패하였습니다.";
		} //
		
		ra.addFlashAttribute("msg", msg); // 메시지
		
		// 게시글 목록으로 이동
		return "redirect:/board/board_list.do"; 
	}

}
