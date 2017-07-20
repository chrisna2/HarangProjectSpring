package com.harang.web.repository;

import org.apache.ibatis.session.SqlSession;

import com.harang.web.domain.BookDTO;

public class BookDaoImpl implements BookDao {
	
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.book-mapper";

	@Override
	public void insertBook(BookDTO book) {
		sqlSession.insert(namespace+".insertBook", book);
	}

	@Override
	public void updateBook(BookDTO book) {
		sqlSession.update(namespace+".updateBook", book);
	}

	@Override
	public void deleteBook(BookDTO book) {
		sqlSession.delete(namespace+".deletetBook", book);
	}

}
