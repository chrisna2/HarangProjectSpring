package com.harang.web.repository;

import com.harang.web.domain.BookDTO;

public interface BookDao {
	public void insertBook(BookDTO book);
	public void updateBook(BookDTO book);
	public void deleteBook(BookDTO book);
}
