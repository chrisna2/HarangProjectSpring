package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.SearchCriteria;

public interface BookDao {
	public void insertBook(BookDTO book);
	public void updateBook(BookDTO book);
	public void deleteBook(BookDTO book);
	
	
	public List<BookDTO> booklist(SearchCriteria cri);
	public int Bookcount(SearchCriteria cri);
	
		
	
}
