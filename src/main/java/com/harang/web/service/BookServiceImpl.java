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


	@Override
	public BookDTO bookDetail(String b_num) {
		return bookdao.bookDetail(b_num);
	}


	@Override
	public int bookDetailBigPoint(String b_num) {
		
		int point = 0;
		
		try {
			point = bookdao.bookDetailBigPoint(b_num);
		} catch (Exception e) {
			e.printStackTrace();
			point = 0;
		}
		
		return point;
	}


	@Override
	public int sellInsert(BookDTO book) {
		return bookdao.sellInsert(book);
	}


	@Override
	public int donateInsert(BookDTO book) {
		return bookdao.donateInsert(book);
	}

	@Override
	public int bookHunting(BookDTO book) {
		return bookdao.bookHunting(book);
	}


	@Override
	public List<BookDTO> sellList(SearchCriteria cri) {
		return bookdao.sellList(cri);
	}

	@Override
	public int sellListCount(SearchCriteria cri) {
		return bookdao.sellListCount(cri);
	}


	@Override
	public List<BookDTO> buyList(SearchCriteria cri) {
		return bookdao.buyList(cri);
	}


	@Override
	public int buyListCount(SearchCriteria cri) {
		return bookdao.buyListCount(cri);
	}


	@Override
	public List<BookDTO> donationList(SearchCriteria cri) {
		return bookdao.donationList(cri);
	}


	@Override
	public int donationListCount(SearchCriteria cri) {
		return bookdao.donationListCount(cri);
	}


	@Override
	public int deleteSellingBook(String b_num) {
		return bookdao.deleteSellingBook(b_num);
	}


	@Override
	public String maxIdFind(BookDTO book) {
		return bookdao.maxIdFind(book);
	}


	@Override
	public long maxIdPointFind(BookDTO book) {
		return bookdao.maxIdPointFind(book);
	}


	@Override
	public int maxTradeRecord(BookDTO book) {
		return bookdao.maxTradeRecord(book);
	}


	@Override
	public int maxTradeCheck(BookDTO book) {
		return bookdao.maxTradeCheck(book);
	}


	@Override
	public int bookTradeRecord(BookDTO book) {
		return bookdao.bookTradeRecord(book);
	}

	@Override
	public int bookTradeCheck(BookDTO book) {
		return bookdao.bookTradeCheck(book);
	}


	@Override
	public List<BookDTO> adminDonateList(SearchCriteria cri) {
		return bookdao.adminDonateList(cri);
	}

	@Override
	public int adminDonateListCount(SearchCriteria cri) {
		return bookdao.adminDonateListCount(cri);
	}

	@Override
	public int donateRecord(String b_num) {
		return bookdao.donateRecord(b_num);
	}

	@Override
	public int donateCheck(String b_num) {
		return bookdao.donateCheck(b_num);
	}


	@Override
	public int donateUpdate(BookDTO book) {
		return bookdao.donateUpdate(book);
	}


	@Override
	public int sellUpdate(BookDTO book) {
		return bookdao.sellUpdate(book);
	}

}
