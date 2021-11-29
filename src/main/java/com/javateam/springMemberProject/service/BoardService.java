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

import com.javateam.springMemberProject.dao.BoardDAO;
import com.javateam.springMemberProject.domain.BoardVO;
import com.javateam.springMemberProject.domain.SearchVO;

/**
 * 게시판 관리 서비스
 * 
 * @author javateam
 *
 */
@Service
public class BoardService {

	private static final Logger log 
		= LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	/**
	 * 게시글 목록 조회(페이징)
	 * 
	 * @param searchVO 검색 VO
	 * @return 페이지별 게시글 목록 
	 */
	@Transactional(readOnly=true)
	public List<BoardVO> selectBoardsByPaging(SearchVO searchVO) {
		
		log.info("BoardDAO.selectBoardsByPaging");
		
		List<BoardVO> boardList = new ArrayList<>(); 
		
		try {
			boardList = boardDAO.selectBoardsByPaging(searchVO);
		} catch (Exception e) {
			log.error("MemberService.selectBoardsByPaging 조회 에러");
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	/**
	 * 게시글 검색(페이징) : 유사검색
	 * 
	 * @param searchVO 검색 VO
	 * @return 검색 결과(게시글 목록)
	 */
	@Transactional(readOnly=true)
	public List<BoardVO> selectBoardsBySearchPaging(SearchVO searchVO) {
		
		log.info("BoardDAO.selectBoardsBySearchPaging");
		List<BoardVO> boardList = new ArrayList<>(); 
		
		try {
			boardList = boardDAO.selectBoardsBySearchPaging(searchVO);
		} catch (Exception e) {
			log.error("MemberService.selectBoardsBySearchPaging 조회 에러");
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	/**
	 * 게시글 목록의 마지막 페이지 계산
	 * 
	 * @param limit 페이지 당 게시글 수
	 * @return 마지막 페이지
	 */
	@Transactional(readOnly=true)
	public int selectOneLastPage(int limit) {
	
		log.info("BoardService.selectOneLastPage");
		int result = 0;
		
		try {
			result = boardDAO.selectOneLastPage(limit);
		} catch (Exception e) {
			log.error("BoardService.selectOneLastPage 조회 에러");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 게시글 목록의 검색 마지막 페이지 계산
	 * 
	 * @param searchVO 검색 VO
	 * @return 마지막 페이지
	 */
	@Transactional(readOnly=true)
	public int selectOneSearchLastPage(SearchVO searchVO) {
		
		log.info("BoardService.selectOneSearchLastPage");
		int result = 0;
		
		try {
			result = boardDAO.selectOneSearchLastPage(searchVO);
		} catch (Exception e) {
			log.error("BoardService.selectOneSearchLastPage 조회 에러");
			e.printStackTrace();
		}
		
		return result;
	}		
	
	/**
	 * 게시글 생성
	 * 
	 * @param boardVO 게시글 VO
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertBoard(BoardVO boardVO) {
	
		log.info("BoardService.insertBoard");
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			boardDAO.insertBoard(boardVO);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.insertBoard 저장 에러");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 조회수 갱신
	 * 
	 * @param num 게시글 번호
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateReadCount(int num) {
		
		log.info("BoardService.updateReadCount");
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			boardDAO.updateReadCount(num);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.updateReadCount 저장 에러");
			e.printStackTrace();
		}
		
		return flag;
		
	}
	
	/**
	 * 개별 게시글 상세 내용 보기
	 * 
	 * @param num 게시글 번호
	 * @return 조회 결과(개별 게시글)
	 */
	@Transactional(readOnly=true)
	public BoardVO selectOneBoardByNum(int num) {
		
		log.info("BoardService.selectOneBoardByNum");
		
		BoardVO boardVO = null;
		
		try {
			boardVO = boardDAO.selectOneBoardByNum(num);
		} catch (Exception e) {
			log.error("BoardService.selectOneBoardByNum 조회 에러");
			e.printStackTrace();
		}
		
		return boardVO;
	}
	
	/**
	 * 게시글 수정(갱신)
	 * 
	 * @param boardVO 게시글 VO
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateBoard(BoardVO boardVO) {
		
		log.info("BoardService.updateBoard");
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			boardDAO.updateBoard(boardVO);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.updateBoard 저장 에러");
			e.printStackTrace();
		}
		
		return flag;
	}	
	
	/**
	 * 게시글 패쓰워드 점검
	 * 
	 * @param boardVO 게시글 VO
	 * @return 게시글 VO
	 */
	@Transactional(readOnly=true)
	public BoardVO checkPassWord(BoardVO boardVO) {
		
		log.info("BoardService.checkPassWord");
		BoardVO resultBoardVO = null;
		
		try {
			resultBoardVO = boardDAO.checkPassWord(resultBoardVO); 
		} catch (Exception e) {
			log.error("BoardService.checkPassWord 조회 에러");
			e.printStackTrace();
		}
		
		return resultBoardVO;
	}
	
	/**
	 * 게시글 삭제
	 * 
	 * @param num 게시글 번호
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteBoard(int num) {
		
		log.info("BoardService.deleteBoard");
		
		boolean flag = false;
		TransactionStatus txStatus
			= transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			boardDAO.deleteBoard(num);
			transactionManager.commit(txStatus);
			flag = true;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);	
			log.error("MemberService.deleteBoard 삭제 에러");
			e.printStackTrace();
		}
		
		return flag;
	}
	
}