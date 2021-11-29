/**
 * 
 */
package com.javateam.springMemberProject.domain;

/**
 * 페이징 DTO
 * 
 * @author javateam
 *
 */
public class PageDTO {

	/** 현재 페이지 */
	private int page;
	
	/** 페이지 당 게시글 수 */
	private int limit;
	
	/** 마지막 페이지 */
	private int lastPage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PageDTO [page=" + page + ", limit=" + limit + ", lastPage=" + lastPage + "]";
	}
	
}
