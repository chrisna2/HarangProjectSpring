package com.harang.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.BookDTO;
import com.harang.web.domain.HunterDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.BookService;
import com.harang.web.service.PointService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;
import com.harang.web.utill.UploadBean;

@Controller
@RequestMapping("/harangdin")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private PointService pointService;
	
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
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public ModelAndView registMyPost(String b_num){
		
		mav = new ModelAndView("harangdin/book_regist");
		
		return mav;
	}
	
	
	@RequestMapping(value="/regist_proc", method=RequestMethod.POST)
	public ModelAndView harangdinRegistProcPost(HttpServletRequest request, MultipartFile file) throws IOException{
		
		BookDTO book = new BookDTO();
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		book.setM_id(request.getParameter("m_id"));
		book.setB_choice(request.getParameter("b_choice"));
		book.setB_want(Integer.parseInt(request.getParameter("b_want")));
		book.setB_want(Integer.parseInt(request.getParameter("b_stock")));
		book.setB_name(request.getParameter("b_name"));
		book.setB_writer(request.getParameter("b_writer"));
		book.setB_pub(request.getParameter("b_pub"));
		book.setB_content(request.getParameter("b_content"));	
		
		//파일의 값이 있으면
		if(!"".equals(file.getOriginalFilename()) || null != file.getOriginalFilename()){
			
			//새로운 파일 저장
			String uploadedFileName = 
					UploadBean.uploadFile(uploadPath, 
							file.getOriginalFilename(), file.getBytes());
			book.setB_photo(uploadedFileName);
		}
		
		int check = 0;
		String result = "";
		
		if("판매".equals(book.getB_choice())){
			check = bookService.sellInsert(book);
			if(1 == check){
				result = "sell_complete";
			}
			else{
				result = "sell_fail";
			}
		}
		else if("기부".equals(book.getB_choice())){
			check = bookService.donateInsert(book);
			if(1 == check){
				result = "donate_complete";
			}
			else{
				result = "donate_fail";
			}
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		mav.addObject("result", result);
		return mav;
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView  updatePost(String b_num){
		
		mav = new ModelAndView("harangdin/book_update");
		
		mav.addObject("i", bookService.bookDetail(b_num));
		
		return mav;
	}
	
	@RequestMapping(value="/update_proc", method=RequestMethod.POST)
	public ModelAndView updateProcPost(HttpServletRequest request, MultipartFile file) throws IOException{
		
		BookDTO book = new BookDTO();
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		book.setM_id(request.getParameter("m_id"));
		book.setB_choice(request.getParameter("b_choice"));
		book.setB_want(Integer.parseInt(request.getParameter("b_want")));
		book.setB_want(Integer.parseInt(request.getParameter("b_stock")));
		book.setB_name(request.getParameter("b_name"));
		book.setB_writer(request.getParameter("b_writer"));
		book.setB_pub(request.getParameter("b_pub"));
		book.setB_content(request.getParameter("b_content"));	
		book.setB_num(request.getParameter("b_num"));
		book.setB_photo(request.getParameter("b_photo"));
		
		
		//파일의 값이 있으면
		if(!"".equals(file.getOriginalFilename()) || null != file.getOriginalFilename()){
			
			//이전에 파일을 올린 경우
			if(!"".equals(book.getB_photo())||null!=book.getB_photo()){
				//이전 파일 삭제
				UploadBean.deleteFile(book.getB_photo(), uploadPath);
			}
			//새로운 파일 저장
			String uploadedFileName = 
					UploadBean.uploadFile(uploadPath, 
							file.getOriginalFilename(), file.getBytes());
			book.setB_photo(uploadedFileName);
		}
		
		int check = 0;
		String result = "";
		
		if("판매".equals(book.getB_choice())){
			check = bookService.sellUpdate(book);
			if(1 == check){
				result = "sell_update_complete";
			}
			else{
				result = "sell_update_fail";
			}
		}
		else if("기부".equals(book.getB_choice())){
			check = bookService.donateUpdate(book);
			if(1 == check){
				result = "donate_update_complete";
			}
			else{
				result = "donate_update_fail";
			}
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		mav.addObject("result", result);
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/bdetail", method=RequestMethod.POST)
	public ModelAndView  ubookdetailpagePost(String b_num){
		mav = new ModelAndView("harangdin/book_detailpage");
		
		mav.addObject("i", bookService.bookDetail(b_num));
		mav.addObject("max_point", bookService.bookDetailBigPoint(b_num));
		
		return mav;
	}
	
	@RequestMapping(value="/b_hunter", method=RequestMethod.POST)
	public ModelAndView harangdinHunterProc(BookDTO book,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		book.setM_id(member.getM_id());
		
		System.out.println(book.toString());
		
		int check = bookService.bookHunting(book);
		
		String result = "";
		
		if(check == 1){
			result = "hunt_complete";
		}
		else{
			result = "hunt_fail";
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/sellList", method=RequestMethod.GET)
	public ModelAndView  sellingGet(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.sellListCount(cri));
		
		mav = new ModelAndView("harangdin/list_selling");
		
		mav.addObject("harangdinmain", bookService.sellList(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/sellList", method=RequestMethod.POST)
	public ModelAndView  sellingPost(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.sellListCount(cri));
		
		mav = new ModelAndView("harangdin/list_selling");
		
		mav.addObject("harangdinmain", bookService.sellList(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/buyList", method=RequestMethod.GET)
	public ModelAndView  buyListGet(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.buyListCount(cri));
		
		mav = new ModelAndView("harangdin/list_buying");

		mav.addObject("harangdinmain", bookService.buyList(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/buyList", method=RequestMethod.POST)
	public ModelAndView  buyListPost(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.buyListCount(cri));
		
		mav = new ModelAndView("harangdin/list_buying");
		
		mav.addObject("harangdinmain", bookService.buyList(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	
	@RequestMapping(value="/donationList", method=RequestMethod.GET)
	public ModelAndView  donationListGet(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.donationListCount(cri));
		
		mav = new ModelAndView("harangdin/list_donation");
		
		mav.addObject("harangdinmain", bookService.donationList(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/donationList", method=RequestMethod.POST)
	public ModelAndView  donationListPost(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		cri.setM_id(member.getM_id());
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.donationListCount(cri));
		
		mav = new ModelAndView("harangdin/list_donation");
		
		mav.addObject("harangdinmain", bookService.donationListCount(cri));
		mav.addObject("pageMaker", pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView deleteProcPost(String b_num){
		
		int check = bookService.deleteSellingBook(b_num);
		
		String result = "";
		
		if(check == 1){
			result ="delete_complete";
		}
		else{
			result ="delete_fail";
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/trade", method=RequestMethod.POST)
	public ModelAndView tradeFirstPost(HttpServletRequest request, HttpServletResponse response){
		
		BookDTO book = new BookDTO();
		
		String b_name = request.getParameter("b_name");
		String b_num = request.getParameter("b_num");
		int r_point = Integer.parseInt(request.getParameter("max_point"));
		
		book.setB_name(b_name);
		book.setB_num(b_num);
		book.setBh_want(r_point);
		
		String giver_id = bookService.maxIdFind(book);
		
		book.setM_id(giver_id);
		
		long giver_point = bookService.maxIdPointFind(book);
		String r_content = "[판매 의사 전달]" + b_name + "의 해당 도서 거래 " +r_point+" 포인트 회수"; 
		
		String result = "trade1_fail";

		//1단계 포인트 거래
		String step1 = pointService.recordPointTrade(r_content, giver_point, r_point, giver_id, "admin05");
		if("complete".equals(step1)){
			//2단계 거래 기록 hunt
			int step2 = bookService.maxTradeRecord(book);
			if(step2 == 1){
				//3단계 거래 체크
				int step3 = bookService.maxTradeCheck(book);
				if(step3 == 1){
					result = "trade1_complete";
				}
			}
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/okay", method=RequestMethod.POST)
	public ModelAndView tradeSecondPost(HttpServletRequest request, HttpServletResponse response){
		
		BookDTO book = new BookDTO();
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		String buyer_id = member.getM_id();
		String b_num = request.getParameter("b_num");
		String b_name = request.getParameter("b_name");
		String seller_id = request.getParameter("m_id");
		int bh_want = Integer.parseInt(request.getParameter("bh_want"));
		long giver_point = pointService.pointInfo("admin05");
		String r_content = "구매자가 " + b_name + "의 해당 도서를 수령확인을 하였습니다.";
		
		String result = "trade2_fail";
		
		book.setB_num(b_num);
		book.setM_id(buyer_id);
		book.setBh_want(bh_want);
		
		//step1
		String step1 = pointService.recordPointTrade(r_content, giver_point, bh_want, "admin05", seller_id);
		if("complete".equals(step1)){
			//2단계 거래 기록 hunt
			int step2 = bookService.bookTradeRecord(book);
			if(step2 == 1){
				//3단계 거래 체크
				int step3 = bookService.bookTradeCheck(book);
				if(step3 == 1){
					result = "trade2_complete";
				}
			}
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public ModelAndView aharangdinmainGet(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.Bookcount(cri));
		
		mav = new ModelAndView("harangdin/a_harangdin_main");
		
		mav.addObject("mharangdinmain",bookService.booklist(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
		
	}
	
	@RequestMapping(value="/adminMain", method=RequestMethod.POST)
	public ModelAndView aharangdinmainPost(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.Bookcount(cri));
		
		mav = new ModelAndView("harangdin/a_harangdin_main");
		
		mav.addObject("mharangdinmain",bookService.booklist(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
		
	}
		
	@RequestMapping(value="/adminDonate", method=RequestMethod.GET)
	public ModelAndView adonateGet(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.adminDonateListCount(cri));
		
		mav = new ModelAndView("harangdin/a_harangdin_donate");
		
		mav.addObject("dlist",bookService.adminDonateList(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/adminDonate", method=RequestMethod.POST)
	public ModelAndView adonatePost(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(bookService.adminDonateListCount(cri));
		
		mav = new ModelAndView("harangdin/a_harangdin_donate");
		
		mav.addObject("dlist",bookService.adminDonateList(cri));
		mav.addObject("pageMaker",pagemaker);
		
		return mav;
	}
	
	@RequestMapping(value="/adminDonateDetail", method=RequestMethod.POST)
	public ModelAndView adonateDpPost(String b_num){
		
		mav = new ModelAndView("harangdin/a_book_donatedetailpage");
		mav.addObject("i", bookService.bookDetail(b_num));
		mav.addObject("max_point", bookService.bookDetailBigPoint(b_num));
		
		return mav;
	}
	
	@RequestMapping(value="/adminBdetail", method=RequestMethod.POST)
	public ModelAndView  abookdetailpagePost(String b_num){
		mav = new ModelAndView("harangdin/a_book_detailpage");
		mav.addObject("i", bookService.bookDetail(b_num));
		mav.addObject("max_point", bookService.bookDetailBigPoint(b_num));
		return mav;
	}
	
	@RequestMapping(value="/adminDelete", method=RequestMethod.POST)
	public ModelAndView adminDeleteProcPost(String b_num){
		
		int check = bookService.deleteSellingBook(b_num);
		
		String result = "";
		
		if(check == 1){
			result ="admin_delete_complete";
		}
		else{
			result ="admin_delete_fail";
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/adminDonateDelete", method=RequestMethod.POST)
	public ModelAndView adminDonateDeleteProcPost(String b_num){
		
		int check = bookService.deleteSellingBook(b_num);
		
		String result = "";
		
		if(check == 1){
			result ="admin_donate_delete_complete";
		}
		else{
			result ="admin_donate_delete_fail";
		}
		
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/donateComplete", method=RequestMethod.POST)
	public ModelAndView donateCompletePost(HttpServletRequest request, HttpServletResponse response){
		
		LoginBean login = new LoginBean();
		MemberDTO admin = login.getLoginInfo(request);
		
		String r_content = "[도서기부]" + request.getParameter("b_name") + " " + request.getParameter("b_regdate");
		String b_num = request.getParameter("b_num");
		String haver_id = request.getParameter("m_id");
		
		String result = "donate_fail";
		
		//step1
		String step1 = pointService.recordPointTrade(r_content, admin.getM_point(), 5000, admin.getM_id(), haver_id);
		if("complete".equals(step1)){
			//2단계 거래 기록 hunt
			int step2 = bookService.donateRecord(b_num);
			if(step2 == 1){
				//3단계 거래 체크
				int step3 = bookService.donateCheck(b_num);
				if(step3 == 1){
					result = "donate_complete";
				}
			}
		}
		mav = new ModelAndView("harangdin/regist_result");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	
	
}
