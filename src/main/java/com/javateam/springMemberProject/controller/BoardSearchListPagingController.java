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
import com.javateam.springMemberProject.domain.PageDTO;
import com.javateam.springMemberProject.domain.SearchVO;
import com.javateam.springMemberProject.service.BoardService;

/**
 * 게시글 "검색" 목록 보기(페이징)
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("board")
public class BoardSearchListPagingController {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardSearchListPagingController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("board_search_list.do")
	public String boardList(@RequestParam(value="search_page", defaultValue="1") int searchPage,
							@RequestParam("search_kind") String searchKind,
							@RequestParam("search_word") String searchWord,
							Model model) {
		
		log.info("게시글 검색 목록");
		
		int limit = 10;
		
		log.info("현재 페이지(검색) : " + searchPage);
		
		SearchVO searchVO = new SearchVO();
		searchVO.setPage(searchPage);
		searchVO.setLimit(limit);
		searchVO.setSearchKind(searchKind);
		searchVO.setSearchWord(searchWord);  
		
		log.info("현재 페이지 : " + searchPage);
		log.info("검색 요소 : " + searchVO);
		
		List<BoardVO> boardList = boardService.selectBoardsBySearchPaging(searchVO);
		int lastPage = boardService.selectOneSearchLastPage(searchVO); // 마지막 페이지(검색)

		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(searchPage);
		pageDTO.setLimit(limit);
		pageDTO.setLastPage(lastPage);
				
		model.addAttribute("boardList", boardList);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("pageDTO", pageDTO);
		
		return "/board/boardSearchList";
	}

}
