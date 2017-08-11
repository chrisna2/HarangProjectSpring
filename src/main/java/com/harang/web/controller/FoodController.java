package com.harang.web.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.FoodService;
import com.harang.web.service.PointService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private PointService pointService;
	
	private ModelAndView mav;
	
	private PageMaker pageMaker;
	
	
	// 학식 메뉴 등록 수정 페이지 관련 컨트롤러. 시작! //
	@RequestMapping(value="/Amenu",method = RequestMethod.GET)
	public ModelAndView amenuPost(){
		mav = new ModelAndView("food/a_menuList");
		return mav;
	}
	@RequestMapping(value="/AmenuUpdate",method = RequestMethod.POST)
	public ModelAndView AmenuUpdatePost(FoodDTO food){
		foodService.updateFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	@RequestMapping(value="/AmenuCreate",method = RequestMethod.POST)
	public ModelAndView AmenuCreatePost(FoodDTO food){
		foodService.insertFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	@RequestMapping(value="/AmenuDelete",method = RequestMethod.POST)
	public ModelAndView AmenuDeletePost(FoodDTO food){
		foodService.deleteFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	//ajax
	@RequestMapping(value="/amenuJson")
	public @ResponseBody List<CalanderDTO> amenuJson(){
		List<CalanderDTO> list = foodService.amenuJson();
		return list;
	}
	@RequestMapping(value="/afoodinfo")
	public @ResponseBody List<FoodDTO> afoodinfoJson(String f_num){
		List<FoodDTO> list = foodService.afoodinfoJson(f_num);
		return list;
	}
	@RequestMapping(value="/menuJson")
	public @ResponseBody List<CalanderDTO> menuJson(HttpSession session){
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		List<CalanderDTO> list = foodService.menuJson(member.getM_id());
		return list;
	}
	@RequestMapping(value="/foodinfo")
	public @ResponseBody List<FoodDTO> foodinfoJson(String f_num){
		List<FoodDTO> list = foodService.afoodinfoJson(f_num);
		return list;
	}
	// 학식 메뉴 등록 수정 페이지 관련 컨트롤러. 끝! //
	
	// 학식 메뉴 티켓 판매 확인. 시작 //
	@RequestMapping(value="/Aticket",method = RequestMethod.GET)
	public ModelAndView aticketGet(SearchCriteria cri){
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(foodService.aticketCountPaging(cri));
		
		mav = new ModelAndView("food/a_ticketList");
		
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("fmlist", foodService.aticketList(cri));
		
		return mav;
	}
	@RequestMapping(value="/Aticket",method = RequestMethod.POST)
	public ModelAndView aticketPost(SearchCriteria cri){
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(foodService.aticketCountPaging(cri));
		
		mav = new ModelAndView("food/a_ticketList");
		
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("fmlist", foodService.aticketList(cri));
		
		return mav;
	}
	
	
	@RequestMapping(value="/menu",method = RequestMethod.GET)
	public ModelAndView menuGet(){
		mav = new ModelAndView("food/menuList");
		return mav;
	}
	
	@RequestMapping(value="/ticket_proc",method = RequestMethod.POST)
	public ModelAndView ticketProc(FoodMemberDTO fm, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		
		String check = pointService.recordPointTrade("[식권 구매] "+ fm.getF_title() + " | 판매일 : " + fm.getF_selldate(), 
													 member.getM_point(), 
													 fm.getF_point(), 
													 member.getM_id(), 
													 "admin01");
		
		if("complete".equals(check)){
			
			int checknum = foodService.insertTicket(fm);
			System.out.println("checknum :"+checknum);
			
		}
		
		mav = new ModelAndView("food/buyComplete");
		mav.addObject("check", check);
		return mav;
	}
	
	@RequestMapping(value="/ticket",method = RequestMethod.GET)
	public ModelAndView ticketListGet(SearchCriteria cri, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		cri.setM_id(member.getM_id());
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(foodService.ticketListPaging(cri));
		
		mav = new ModelAndView("food/ticketList");
		
		mav.addObject("tlist", foodService.ticketList(cri));
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	@RequestMapping(value="/ticket",method = RequestMethod.POST)
	public ModelAndView ticketListPost(SearchCriteria cri, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		cri.setM_id(member.getM_id());
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(foodService.ticketListPaging(cri));
		
		mav = new ModelAndView("food/ticketList");
		
		mav.addObject("tlist", foodService.ticketList(cri));
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value="/ticketPrint",method = RequestMethod.POST)
	public ModelAndView ticketPrintPost(String f_num, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		
		System.out.println(f_num);
		//m_id, f_num
		
		FoodMemberDTO foodmember = new FoodMemberDTO();
		foodmember.setF_num(f_num);
		foodmember.setM_id(member.getM_id());
		
		foodmember = foodService.ticketPrint(foodmember);
		
		mav = new ModelAndView("food/ticketPrint");
		
		mav.addObject("food", foodmember);
		mav.addObject("thecode", f_num+"@"+member.getM_id());
		
		return mav;
	}
	
	@RequestMapping(value="/ticketUse",method = RequestMethod.POST)
	public ModelAndView ticketUsePost(FoodMemberDTO fm, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		
		fm.setM_id(member.getM_id());
		
		System.out.println(fm.getF_title()+"/"+fm.getF_num()+"/"+fm.getF_point()+"/"+fm.getM_id());
		
		int checknum = foodService.ticketUsing(fm);
		String check = "error";
		
		if(checknum == 1){
			check = "used"; 
		}
		
		mav = new ModelAndView("food/ticketResult");
		mav.addObject("result", check);
		
		return mav;
	}
	
	
	@RequestMapping(value="/ticketRefund",method = RequestMethod.POST)
	public ModelAndView ticketRefundPost(FoodMemberDTO fm, HttpSession session){
		
		LoginBean login = new LoginBean();
		MemberDTO member = (MemberDTO)login.getLoginIngfo(session);
		fm.setM_id(member.getM_id());
		
		System.out.println(fm.getF_title()+"/"+fm.getF_num()+"/"+fm.getF_point()+"/"+fm.getM_id());
		
		int checknum = foodService.ticketRefund(fm);
		String check = "error";
		
		System.out.println("checknum : " + checknum);
		
		if(checknum == 1){
			
			check = pointService.recordPointTrade("[식권 환불] "+ fm.getF_title(), 
			      								 pointService.pointInfo("admin01"), 
											     fm.getF_point(), 
												 "admin01", 
												 member.getM_id());
			if("complete".equals(check)){
				check = "refund";
			}
		}
		
		mav = new ModelAndView("food/ticketResult");
		mav.addObject("result", check);
		
		return mav;
	}
	
	
	
	
}
