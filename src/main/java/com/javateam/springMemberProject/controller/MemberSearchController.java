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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.domain.PageDTO;
import com.javateam.springMemberProject.domain.SearchVO;
import com.javateam.springMemberProject.service.MemberService;

/**
 * 회원 정보 검색(페이징)
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("admin")
public class MemberSearchController {

	private static final Logger log
		= LoggerFactory.getLogger(MemberSearchController.class);
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/search_action.do")
	public String searchAction(@RequestParam(value="search_page", defaultValue="1") int searchPage,
			                   @RequestParam("search_kind") String searchKind,
			                   @RequestParam("search_word") String searchWord,
							   Model model) {
		
		log.info("회원 정보 검색(페이징)");
		
		/** 마지막(총) 페이지 */
		int lastPage = 0;
		
		/** 페이지 당 출력 인원(게시글) 수 */
		int limit = 10;
		
		SearchVO searchVO = new SearchVO();
		searchVO.setLimit(limit);
		searchVO.setPage(searchPage);
		searchVO.setSearchKind(searchKind);
		searchVO.setSearchWord(searchWord.trim()); // 공백 제거
		
		log.info("SearchVO : " + searchVO);
		
		////////////////////////////////////////////////////
		
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(searchPage);
		pageDTO.setLimit(limit);

		List<MemberVO> members = memberService.findBySearching(searchVO);
		lastPage = memberService.getSearchLastPage(searchVO);

		pageDTO.setLastPage(lastPage);
		
		// 인자 생성
		model.addAttribute("members", members);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("searchVO", searchVO); // 검색 VO
		
		log.info("######## end ########");
					   
		return "/admin/search_members_list";
	}
	
	
}