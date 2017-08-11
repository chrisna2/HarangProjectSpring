package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.SearchCriteria;

public interface BookService {
	public List<BookDTO> booklist(SearchCriteria cri);
	public int Bookcount(SearchCriteria cri);
}
