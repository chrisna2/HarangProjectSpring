package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.SearchCriteria;

public interface BookDao {
	public List<BookDTO> booklist(SearchCriteria cri);
	public int Bookcount(SearchCriteria cri);
	
	public BookDTO bookDetail(String b_num);
	public int bookDetailBigPoint(String b_num);
	
	public int sellInsert(BookDTO book);
	public int donateInsert(BookDTO book);
	
	public int bookHunting(BookDTO book);
	
	public List<BookDTO> sellList(SearchCriteria cri);
	public int sellListCount(SearchCriteria cri);
	public List<BookDTO> buyList(SearchCriteria cri);
	public int buyListCount(SearchCriteria cri);
	public List<BookDTO> donationList(SearchCriteria cri);
	public int donationListCount(SearchCriteria cri);
	
	public int deleteSellingBook(String b_num);
		
	public String maxIdFind(BookDTO book);
	public long maxIdPointFind(BookDTO book);
	public int maxTradeRecord(BookDTO book);
	public int maxTradeCheck(BookDTO book);
	public int bookTradeRecord(BookDTO book);
	public int bookTradeCheck(BookDTO book);
	
	public List<BookDTO> adminDonateList(SearchCriteria cri);
	public int adminDonateListCount(SearchCriteria cri);

	public int donateRecord(String b_num);
	public int donateCheck(String b_num);
	
	public int donateUpdate(BookDTO book);
	public int sellUpdate(BookDTO book);
}

