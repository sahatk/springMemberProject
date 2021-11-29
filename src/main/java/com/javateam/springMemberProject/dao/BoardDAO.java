/**
 * 
 */
package com.javateam.springMemberProject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.springMemberProject.domain.BoardVO;
import com.javateam.springMemberProject.domain.SearchVO;

/**
 * 게시판 DAO
 * 
 * @author javateam
 *
 */
@Repository
public class BoardDAO {
	
	private static final Logger log
		= LoggerFactory.getLogger(BoardDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String BOARD_NS 
		= "com.javateam.springMemberProject.mapper.Board-mapper";
	
	/**
	 * 게시글 목록 조회(페이징)
	 * 
	 * @param searchVO 검색 VO
	 * @return 페이지별 게시글 목록 
	 */
	public List<BoardVO> selectBoardsByPaging(SearchVO searchVO) {
		
		log.info("BoardDAO.selectBoardsByPaging");
		return sqlSession
				.selectList(BOARD_NS+".selectBoardsByPaging", 
						searchVO);
	} //
	
	/**
	 * 게시글 검색(페이징) : 유사검색
	 * 
	 * @param searchVO 검색 VO
	 * @return 검색 결과(게시글 목록)
	 */
	public List<BoardVO> selectBoardsBySearchPaging(SearchVO searchVO) {
		
		log.info("BoardDAO.selectBoardsBySearchPaging");
		return sqlSession
				.selectList(BOARD_NS+".selectBoardsBySearchPaging", 
						searchVO);
	} //
	
	/**
	 * 게시글 목록의 마지막 페이지 계산
	 * 
	 * @param limit 페이지 당 게시글 수
	 * @return 마지막 페이지
	 */
	public int selectOneLastPage(int limit) {
		
		log.info("BoardDAO.selectOneLastPage");
		return sqlSession
				.selectOne(BOARD_NS+".selectOneLastPage", limit);
	}

	/**
	 * 게시글 목록의 검색 마지막 페이지 계산
	 * 
	 * @param searchVO 검색 VO
	 * @return 마지막 페이지
	 */
	public int selectOneSearchLastPage(SearchVO searchVO) {
		
		log.info("BoardDAO.selectOneSearchLastPage");
		return sqlSession
				.selectOne(BOARD_NS+".selectOneSearchLastPage", 
						searchVO);
	}
	
	/**
	 * 게시글 생성
	 * 
	 * @param boardVO 게시글 VO
	 */
	public void insertBoard(BoardVO boardVO) {
		
		log.info("BoardDAO.insertBoard");
		sqlSession.insert(BOARD_NS+".insertBoard", boardVO);
	}
	
	/**
	 * 조회수 갱신
	 * 
	 * @param num 게시글 번호
	 */
	public void updateReadCount(int num) {
		
		log.info("BoardDAO.updateReadCount");
		sqlSession.update(BOARD_NS+".updateReadCount", num);
	}
	
	/**
	 * 개별 게시글 상세 내용 보기
	 * 
	 * @param num 게시글 번호
	 * @return 조회 결과(개별 게시글)
	 */
	public BoardVO selectOneBoardByNum(int num) {
		
		log.info("BoardDAO.selectOneBoardByNum");
		return sqlSession.selectOne(BOARD_NS+".selectOneBoardByNum", num);
	}
	
	/**
	 * 게시글 수정(갱신)
	 * 
	 * @param boardVO 게시글 VO
	 */
	public void updateBoard(BoardVO boardVO) {
		
		log.info("BoardDAO.updateBoard");
		sqlSession.update(BOARD_NS+".updateBoard", boardVO);
	}
	
	/**
	 * 게시글 패쓰워드 점검
	 * 
	 * @param boardVO 게시글 VO
	 * @return 게시글 VO
	 */
	public BoardVO checkPassWord(BoardVO boardVO) {
		
		log.info("BoardDAO.checkPassWord");
		return sqlSession.selectOne(BOARD_NS+".checkPassWord",
				boardVO);
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param num 게시글 번호
	 */
	public void deleteBoard(int num) {
		
		log.info("BoardDAO.deleteBoard");
		sqlSession.delete(BOARD_NS+".deleteBoard", num);
	}
	
}