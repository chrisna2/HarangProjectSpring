package com.harang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.BookService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/harangdin")
public class BookController {

	@Autowired
	private BookService bookService;
	
	private ModelAndView mav;
	
	private PageMaker pagemaker ;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public ModelAndView harangdinmainGet(SearchCriteria cri){
		
		
		
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.Bookcount(cri));
		
	
	
		
		mav = new ModelAndView("harangdin/harangdin_main");
		mav.addObject("harangdinmain",bookService.booklist(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
		
	}
	
	
	@RequestMapping(value="/main", method=RequestMethod.POST)
	public ModelAndView harangdinmainPost(SearchCriteria cri){
		
	
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.Bookcount(cri));
		
	
	
		
		mav = new ModelAndView("harangdin/harangdin_main");
		mav.addObject("harangdinmain",bookService.booklist(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
		
	}
	
	
	
	
	@RequestMapping(value="/Amain", method=RequestMethod.GET)
	public ModelAndView aharangdinmainGet(){
		
		mav = new ModelAndView("harangdin/a_harangdin_main");
		
		return mav;
		
	}
	
	
	@RequestMapping(value="/adetail", method=RequestMethod.GET)
	public ModelAndView  abookdetailpageGet(){
		
		mav = new ModelAndView("harangdin/a_book_detailpage");
		
		return mav;
	}
	
	
	@RequestMapping(value="/adonateDp", method=RequestMethod.GET)
	public ModelAndView  adonateDpGet(){
		
		mav = new ModelAndView("harangdin/a_book_donatedetailpage");
		
		return mav;
	}
	
	
	@RequestMapping(value="/adonate", method=RequestMethod.GET)
	public ModelAndView  adonateGet(){
		
		mav = new ModelAndView("harangdin/a_harangdin_donate");
		
		return mav;
	}
	
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView  detailGet(){
		
		mav = new ModelAndView("harangdin/book_detailpage");
		
		return mav;
	}
	
	
	@RequestMapping(value="/registMy", method=RequestMethod.GET)
	public ModelAndView  registMyGet(){
		
		mav = new ModelAndView("harangdin/book_regist_my");
		
		return mav;
	}
	
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public ModelAndView  registGet(){
		
		mav = new ModelAndView("harangdin/book_regist");
		
		return mav;
	}
	
	
	@RequestMapping(value="/buying", method=RequestMethod.GET)
	public ModelAndView  buyingGet(){
		
		mav = new ModelAndView("harangdin/list_buying");
		
		return mav;
	}
	
	
	@RequestMapping(value="/donation", method=RequestMethod.GET)
	public ModelAndView  donationGet(){
		
		mav = new ModelAndView("harangdin/list_donation");
		
		return mav;
	}
	
	
	@RequestMapping(value="/selling", method=RequestMethod.GET)
	public ModelAndView  sellingGet(){
		
		mav = new ModelAndView("harangdin/list_selling");
		
		return mav;
	}
	
	
	
}
