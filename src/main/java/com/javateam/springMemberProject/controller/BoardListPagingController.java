/**
 * 
 */
package com.javateam.springMemberProject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.springMemberProject.domain.BoardVO;
import com.javateam.springMemberProject.domain.SearchVO;
import com.javateam.springMemberProject.service.BoardService;

/**
 * 게시글 목록 보기(페이징)
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardListPagingController {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardListPagingController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("board_list.do")
	public String boardList(@RequestParam(value="page", defaultValue="1") int page,
			Model model) {
		
		log.info("게시글 목록");
		
		int limit = 10;
		
		log.info("현재 페이지 : " + page);
		
		SearchVO searchVO = new SearchVO();
		searchVO.setPage(page);
		searchVO.setLimit(limit);
		
		List<BoardVO> boardList = boardService.selectBoardsByPaging(searchVO);
		int lastPage = boardService.selectOneLastPage(limit); // 마지막 페이지

		model.addAttribute("boardList", boardList);
		model.addAttribute("page", page);
		model.addAttribute("last_page", lastPage);
		
		return "/board/boardList";
	}

}
