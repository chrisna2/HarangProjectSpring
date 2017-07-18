package com.harang.web.utill;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.harang.web.domain.Criteria;

public class PageMaker {

	private int totalCount, startPage, endPage;
	private boolean prev, next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
		
	}
	
	private void calcData(){
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum)+1;
		
		int finalEndPage = (int)(Math.ceil((totalCount)/(double)cri.getPerPageNum()));
		
		if(endPage > finalEndPage){
			
			endPage = finalEndPage;
		}
		
		prev = startPage ==1? false : true;
		next = endPage*cri.getPerPageNum() >= totalCount ? false:true;
		
		
	}
	

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	public String makeQuery(int page){
		
		UriComponents uri = UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("perPageNum", cri.getPerPageNum()).build();
			
		return uri.toString();
		
	}
	
	
	
	
}
