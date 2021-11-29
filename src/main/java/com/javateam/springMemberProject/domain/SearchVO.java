/**
 * 
 */
package com.javateam.springMemberProject.domain;

/**
 * 검색 VO
 * 
 * @author javateam
 *
 */
public class SearchVO {
	
	private String searchKind;
	private String searchWord;
	private int page;
	private int limit;
	
	public String getSearchKind() {
		return searchKind;
	}
	public void setSearchKind(String searchKind) {
		this.searchKind = searchKind;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
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
	
	@Override
	public String toString() {
		return "SearchVO [searchKind=" + searchKind + ", searchWord=" + searchWord + ", page=" + page + ", limit="
				+ limit + "]";
	}
	
}
