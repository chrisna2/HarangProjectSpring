package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.SearchCriteria;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
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

	@Override
	public List<BookDTO> booklist(SearchCriteria cri) {
	
		return sqlSession.selectList(namespace+".booklist",cri);
	}

	@Override
	public int Bookcount(SearchCriteria cri) {

		return sqlSession.selectOne(namespace+".bookcount",cri);
	}

}
