package com.javateam.springMemberProject.domain;

import java.sql.Timestamp;

/**
 * 게시판 VO(값 객체)
 * 
 * @author javateam
 *
 */
public class BoardVO {
	
	/** 게시글 번호 */
	private int num;
	
	/** 게시글 작성자 */
	private String name;
	
	/** 작성자 이메일 */
	private String email;
	
	/** 게시글 패쓰워드 */
	private String pass;
	
	/** 게시글 제목 */
	private String title;
	
	/** 게시글 내용 */
	private String content;
	
	/** 게시글 조회수 */
	private int readcount;
	
	/** 게시글 작성일 */
	private Timestamp writedate;
	
	/** 작성자 회원 아이디 */
	private String userid;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", name=" + name + ", email=" + email + ", pass=" + pass + ", title=" + title
				+ ", content=" + content + ", readcount=" + readcount + ", writedate=" + writedate + ", userid="
				+ userid + "]";
	}
	
}
