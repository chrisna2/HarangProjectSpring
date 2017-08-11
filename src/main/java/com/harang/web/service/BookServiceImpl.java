package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.BookDao;


@Service
public class BookServiceImpl implements BookService {
	
	
	@Autowired
	private BookDao bookdao;
	

	@Override
	public List<BookDTO> booklist(SearchCriteria cri) {
		
		return bookdao.booklist(cri);
	}


	@Override
	public int Bookcount(SearchCriteria cri) {
	
		return bookdao.Bookcount(cri);
	}

}
