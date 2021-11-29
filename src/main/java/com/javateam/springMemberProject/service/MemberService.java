/**
 * 
 */
package com.javateam.springMemberProject.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.javateam.springMemberProject.dao.MemberDAO;
import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.domain.PageDTO;
import com.javateam.springMemberProject.domain.SearchVO;

/**
 * 회원 관리 서비스
 * 
 * @author javateam
 *
 */
@Service
public class MemberService {
	
	private static final Logger log
		= LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	/**
	 * 회원 정보 저장
	 * 
	 * @param member 회원 정보
	 * @return 회원 정보 저장 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertMember(MemberVO member) {
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			memberDAO.insertMember(member);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.insertMember 저장 에러");
			e.printStackTrace();
		}
		
		return flag;
	} //
	
	/**
	 * 개별 회원 정보 조회
	 * 
	 * @param userid 회원 아이디
	 * @return 회원 정보
	 */
	@Transactional(readOnly=true)
	public MemberVO getMember(String userid) {
		
		MemberVO member = null;
		
		try {
			member = memberDAO.getMember(userid);
			if (member == null) {
				throw new Exception("MemberService.getMember 조회 에러");
			}
		} catch (Exception e) {
			log.error("MemberService.getMember 조회 에러");
			e.printStackTrace();
		}
		
		return member;
	} //
	
	
//	@Transactional(readOnly=true)
//	public MemberVO getMember(String userid) throws Exception {
//		
//		MemberVO member = null;
//		
//		member = memberDAO.getMember(userid);
//		if (member == null) {
//			throw new Exception("MemberService.getMember 조회 에러");
//		} 
//		
//		return member;
//	} //
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param member 회원 정보
	 * @return 회원 정보 수정 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateMember(MemberVO member) {
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			memberDAO.updateMember(member);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.updateMember 수정 에러");
			e.printStackTrace();
		}
		
		return flag;
	} //
	
	/**
	 * 전체 회원 정보 조회(페이징)
	 * 
	 * @param pageDTO 페이징 DTO
	 * @return 페이지별 회원 정보들
	 */
	@Transactional(readOnly=true)
	public List<MemberVO> getMembersByPaging(PageDTO pageDTO) {
		
		List<MemberVO> members = null;
		
		try {
			members = memberDAO.getMembersByPaging(pageDTO);
			if (members == null) {
				throw new Exception("MemberService.getMembersByPaging 조회 에러");
			}
		} catch (Exception e) {
			log.error("MemberService.getMembersByPaging 조회 에러");
			e.printStackTrace();
		}
		
		return members;
	} //
	
	/**
	 * 마지막(총) 페이지 구하기(조회) : 전체 회원 조회
	 * 
	 * @param limit 페이지당 인원수(한계량)
	 * @return 마지막(총) 페이지
	 */
	@Transactional(readOnly=true)
	public int getLastPage(int limit) {
		
		int result = 0;
	
		try {
			result = memberDAO.getLastPage(limit);
		} catch (Exception e) {
			log.error("MemberService.getLastPage 조회 에러");
			e.printStackTrace();
		}
		
		return result;
	} //
	
	
	/**
	 * 마지막(총) 페이지 구하기(조회) : 회원 검색
	 * 
	 * @param searchVO 검색 VO
	 * @return 마지막(총) 페이지
	 */
	@Transactional(readOnly=true)
	public int getSearchLastPage(SearchVO searchVO) {
		
		int result = 0;
	
		try {
			result = memberDAO.getSearchLastPage(searchVO);
		} catch (Exception e) {
			log.error("MemberService.getSearchLastPage 조회 에러");
			e.printStackTrace();
		}
		
		return result;
	} //
	
	/**
	 * 회원 정보 삭제
	 * 
	 * @param member 회원 정보
	 * @return 회원 정보 삭제 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteMember(String userid) {
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			memberDAO.deleteMember(userid);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.deleteMember 삭제 에러");
			e.printStackTrace();
		}
		
		return flag;
	} //
	
	/**
	 * 로그인 인증 점검
	 * 
	 * @param userid 회원 아이디
	 * @param pwd 회원 패쓰워드
	 * @return 로그인 메시지 
	 *   ex) 존재하지 않는 회원입니다.
	 *   ex) 패쓰워드가 일치하지 않습니다.
	 *   ex) 로그인에 성공하였습니다.  
	 */
	@Transactional(readOnly=true)
	public String checkLogin(String userid, String pwd) {
		
		log.info("MemberService.checkLogin");
		String msg = "";
		
		try {
			msg = memberDAO.checkLogin(userid, pwd);
		} catch (Exception e) {
			log.error("MemberService.checkLogin 조회 에러");
			e.printStackTrace();
		}
		
		return msg;
	} //
	
	/**
	 * 회원 검색
	 * 
	 * @param searchVO 검색 VO ex) 검색 구분 : 아이디, 이메일 (동등검색), 이름, 연락처 (유사 검색)
	 * @return 검색 결과 (회원 정보)
	 */
	@Transactional(readOnly=true)
	public List<MemberVO> findBySearching(SearchVO searchVO) {
		
		log.info("MemberService.findBySearching");
		
		List<MemberVO> members = new ArrayList<>();
		
		// 검색 구분 : 아이디, 이메일 (동등검색), 이름, 연락처 (유사 검색)
		String searchKind = searchVO.getSearchKind();
		String searchWord = searchVO.getSearchWord();

		// 아이디, 이메일 (동등 검색)
		if (searchKind.contentEquals("userid") || searchKind.contentEquals("email")) {
			
			members.add(memberDAO.getMemberByIdOrEmail(searchKind, searchWord));
			
		} else { // 이름, 연락처 (유사 검색)
			
			members = memberDAO.getMembersByNameOrPhone(searchVO);
		} //
		
		return members;
	}
	
}