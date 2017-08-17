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
	public List<BookDTO> booklist(SearchCriteria cri) {
	
		return sqlSession.selectList(namespace+".booklist",cri);
	}

	@Override
	public int Bookcount(SearchCriteria cri) {

		return sqlSession.selectOne(namespace+".bookcount",cri);
	}

	@Override
	public BookDTO bookDetail(String b_num) {
		return sqlSession.selectOne(namespace+".bookDetail", b_num);
	}
	@Override
	public int bookDetailBigPoint(String b_num) {
		return sqlSession.selectOne(namespace+".bookDetailBigPoint", b_num);
	}

	@Override
	public int sellInsert(BookDTO book) {
		return sqlSession.insert(namespace+".sellInsert", book);
	}

	@Override
	public int donateInsert(BookDTO book) {
		return sqlSession.insert(namespace+".donateInsert", book);
	}

	@Override
	public int bookHunting(BookDTO book) {
		return sqlSession.insert(namespace+".bookHunting", book);
	}

	@Override
	public List<BookDTO> sellList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".sellList", cri);
	}

	@Override
	public int sellListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".sellListCount", cri);
	}

	@Override
	public List<BookDTO> buyList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".buyList", cri);
	}

	@Override
	public int buyListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".buyListCount", cri);
	}

	@Override
	public List<BookDTO> donationList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".donationList", cri);
	}

	@Override
	public int donationListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".donationListCount", cri);
	}

	@Override
	public int deleteSellingBook(String b_num) {
		
		int check = 0;
		
		try {
			check = sqlSession.delete(namespace+".deleteSellingBook", b_num);
		} 
		catch (Exception e) {
			check = 0;
		}
		
		return check;
	}

	@Override
	public String maxIdFind(BookDTO book) {
		return sqlSession.selectOne(namespace+".maxIdFind", book);
	}

	@Override
	public long maxIdPointFind(BookDTO book) {
		return sqlSession.selectOne(namespace+".maxIdPointFind", book);
	}

	@Override
	public int maxTradeRecord(BookDTO book) {
		return sqlSession.update(namespace+".maxTradeRecord", book);
	}

	@Override
	public int maxTradeCheck(BookDTO book) {
		return sqlSession.update(namespace+".maxTradeCheck", book);
	}

	@Override
	public int bookTradeRecord(BookDTO book) {
		return sqlSession.update(namespace+".bookTradeRecord", book);
	}

	@Override
	public int bookTradeCheck(BookDTO book) {
		return sqlSession.update(namespace+".bookTradeCheck", book);
	}

	@Override
	public List<BookDTO> adminDonateList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".adminDonateList", cri);
	}

	@Override
	public int adminDonateListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".adminDonateListCount", cri);
	}

	@Override
	public int donateRecord(String b_num) {
		return sqlSession.insert(namespace+".donateRecord", b_num);
	}

	@Override
	public int donateCheck(String b_num) {
		return sqlSession.update(namespace+".donateCheck", b_num);
	}

	@Override
	public int donateUpdate(BookDTO book) {
		return sqlSession.update(namespace+".donateUpdate", book);
	}

	@Override
	public int sellUpdate(BookDTO book) {
		return sqlSession.update(namespace+".sellUpdate", book);
	}
	
	
	

}
