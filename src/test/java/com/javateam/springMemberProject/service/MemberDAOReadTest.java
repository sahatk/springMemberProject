/**
 * 
 */
package com.javateam.springMemberProject.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.springMemberProject.dao.MemberDAO;
import com.javateam.springMemberProject.domain.MemberVO;

/**
 * MemberService getMember 단위 테스트
 * 
 * @author javateam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
					"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
public class MemberDAOReadTest {
	
	private static final Logger log
		= LoggerFactory.getLogger(MemberDAOReadTest.class);
	
	@Autowired
	MemberDAO dao;

	int limit = 10;

	/**
	 * Test method for {@link com.javateam.springMemberProject.service.MemberService#insertMember(com.javateam.springMemberProject.domain.MemberVO)}.
	 */
	@Test
	public void testGetMember() {

		log.info("MemberDAOReadTest");
		
		assertEquals(11, dao.getLastPage(limit));
		
	} //

}
