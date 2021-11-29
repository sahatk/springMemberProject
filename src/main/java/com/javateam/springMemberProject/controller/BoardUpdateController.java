/**
 * 
 */
package com.javateam.springMemberProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.springMemberProject.domain.BoardVO;
import com.javateam.springMemberProject.service.BoardService;

/**
 * 게시글 수정 처리
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardUpdateController {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardUpdateController.class);
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("board_update.do")
	public String boardUpdateProc(BoardVO boardVO, RedirectAttributes ra) {
		
		log.info("게시글 수정 처리");
		String msg = ""; // 메시지
		
		log.info("게시글 인자 : " + boardVO);
		
		if (boardService.updateBoard(boardVO)) {
			msg = "게시글 수정에 성공하였습니다.";
		} else {
			msg = "게시글 수정에 실패하였습니다.";
		}
		
		ra.addFlashAttribute("msg", msg); // 메시지
		
		// 게시글 상세보기로 이동
		return "redirect:/board/board_view.do?num="+boardVO.getNum(); 
	}

}
