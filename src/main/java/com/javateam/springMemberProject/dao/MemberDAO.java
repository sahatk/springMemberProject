/**
 * 
 */
package com.javateam.springMemberProject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.springMemberProject.domain.MemberVO;
import com.javateam.springMemberProject.domain.PageDTO;
import com.javateam.springMemberProject.domain.SearchVO;


/**
 * 회원 관리 DAO
 * 
 * @author javateam
 *
 */
@Repository
public class MemberDAO {
	
	private static final Logger log
		= LoggerFactory.getLogger(MemberDAO.class);
	
	private static final String MAPPER_NS = "com.javateam.springMemberProject.mapper.Member-mapper";
	
	// MyBatis 객체 생성
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 회원 정보 생성
	 * 
	 * @param member 회원 정보
	 * @throws SQLException
	 */
	public void insertMember(MemberVO member) throws SQLException {
		
		log.info("MemberDAO.insertMember");
		sqlSession.insert(MAPPER_NS+".insertMember", member);
	} //

	/**
	 * 개별 회원 정보 조회
	 * 
	 * @param userid 회원 아이디
	 * @return 회원 정보
	 * @throws SQLException
	 */
	public MemberVO getMember(String userid) throws SQLException {
		
		log.info("MemberDAO.getMember");
		return sqlSession
				.selectOne(MAPPER_NS+".getMember", 
						   userid);
	} //
	
	/**
	 * 회원 정보 수정
	 * 
	 * @param member 회원 정보
	 * @throws SQLException
	 */
	public void updateMember(MemberVO member) throws SQLException {
		
		log.info("MemberDAO.updateMember");
		sqlSession
			.update(MAPPER_NS+".updateMember", 
					member);
	}
	
	/**
	 * 전체 회원 정보 조회(페이징)
	 * 
	 * @param pageDTO 페이징 DTO
	 * @return 페이지별 회원 정보들
	 */
	public List<MemberVO> getMembersByPaging(PageDTO pageDTO) {
		
		log.info("MemberDAO.getMembersByPaging");
		return sqlSession.selectList(MAPPER_NS+".getMembersByPaging"
				, pageDTO);		
	} //
	

	/**
	 * 마지막(총) 페이지 구하기(조회) : 전체 회원 조회
	 * 
	 * @param limit 페이지당 인원수(한계량)
	 * @return 마지막(총) 페이지
	 */
	public int getLastPage(int limit) {
		
		log.info("MemberDAO.getLastPage");
		return sqlSession.selectOne(MAPPER_NS+".getLastPage"
				 , limit);
	} //

	/**
	 * 마지막(총) 페이지 구하기(조회) : 회원 검색
	 * 
	 * @param searchVO 검색 VO
	 * @return 마지막(총) 페이지
	 */
	public int getSearchLastPage(SearchVO searchVO) {
		
		log.info("MemberDAO.getSearchLastPage");
		return sqlSession.selectOne(MAPPER_NS+".getSearchLastPage", searchVO);
	}
	
	
	/**
	 * 개별 회원 정보 삭제
	 * 
	 * @param userid 회원 아이디
	 * @throws SQLException
	 */
	public void deleteMember(String userid) throws SQLException {
		
		log.info("MemberDAO.deleteMember");
		sqlSession.delete(MAPPER_NS+".deleteMember"
				, userid);
	} //
	
	
	/**
	 * 로그인 인증 점검
	 * 
	 * @param id 회원 아이디
	 * @param pw 회원 패쓰워드
	 * @return 로그인 메시지 
	 *   ex) 존재하지 않는 회원입니다.
	 *   ex) 패쓰워드가 일치하지 않습니다.
	 *   ex) 로그인에 성공하였습니다.  
	 * @throws SQLException 
	 */
	public String checkLogin(String id, String pw) throws SQLException {
		
		log.info("MemberDAO.checkLogin");
		String msg = ""; // 에러 메시지
		MemberVO member = null;
		boolean flag = this.isMember(id);
		
		if (flag == true) { // 회원일 경우

			member = new MemberVO();
			member.setUserid(id);
			member.setPwd(pw);
			msg = (int)(sqlSession.selectOne(MAPPER_NS+".checkLogin", member))==1 ?
				   "로그인에 성공하였습니다" : "패쓰워드가 일치하지 않습니다";
			
		} else { // 회원이 아닐 경우
			msg = "존재하지 않는 회원입니다";
		} //
		
		return msg;		
	} //
	
	/**
	 * 회원 존재 여부 점검
	 * 
	 * @param id 회원 아이디
	 * @return 회원 존재 여부 
	 * @throws SQLException
	 */
	public boolean isMember(String id) throws SQLException {
		
		log.info("회원 존재 여부 점검");
		return (int)(sqlSession.selectOne(MAPPER_NS+".isMember", id))==1 ? true : false;
	} //
	
	/**
	 * 회원 검색 (아이디/이메일)
	 * 
	 * @param searchKind 검색구분
	 * @param searchWord 검색어
	 * @return 검색 결과(회원)
	 */
	public MemberVO getMemberByIdOrEmail(String searchKind, String searchWord) {
		
		log.info("회원 검색 (아이디/이메일)");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchKind", searchKind);
		map.put("searchWord", searchWord);
		
		return sqlSession.selectOne(MAPPER_NS+".getMemberByIdOrEmail", map);
	} //
	
	/**
	 * 회원 검색 (이름/연락처)
	 * 
	 * @param searchVO 검색 VO
	 * @return 검색 결과(회원 정보들)
	 */
	public List<MemberVO> getMembersByNameOrPhone(SearchVO searchVO) {
		
		log.info("회원 검색 (이름/연락처)");
		
		log.info("SearchVO : " + searchVO);
																			  
		return sqlSession.selectList(MAPPER_NS+".getMembersByNameOrPhone", searchVO);
	} //
}