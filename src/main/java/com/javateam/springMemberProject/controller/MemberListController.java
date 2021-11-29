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
import com.javateam.springMemberProject.service.MemberService;

/**
 * 전체 회원 정보 조회(페이징)
 * 
 * @author javateam
 *
 */
@Controller
@RequestMapping("admin")
public class MemberListController {

	private static final Logger log
		= LoggerFactory.getLogger(MemberListController.class);
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/paging_action.do")
	public String pagingAction(@RequestParam(value="page", defaultValue="1") int page,
						Model model) {
		
		log.info("전체 회원 정보 조회 (페이징");
		
		/** 마지막(총) 페이지 */
		int lastPage = 0;
		
		/** 페이지 당 출력 인원(게시글) 수 */
		int limit = 10;
		
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(page);
		pageDTO.setLimit(limit);

		List<MemberVO> members = memberService.getMembersByPaging(pageDTO);
		lastPage = memberService.getLastPage(limit);

		pageDTO.setLastPage(lastPage);
		
		// 인자 생성
		model.addAttribute("members", members);
		model.addAttribute("pageDTO", pageDTO);
		
		log.info("######## end ########");
					   
		return "/admin/view_members_list";
	}
	
	
}