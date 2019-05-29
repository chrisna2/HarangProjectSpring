package com.harang.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.BambooService;
import com.harang.web.service.FacilService;
import com.harang.web.service.MyPageService;
import com.harang.web.service.ParttimeService;
import com.harang.web.utill.LoginBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private ParttimeService parttimeService;
	
	@Autowired
	private BambooService bambooService;
	
	@Autowired
	private FacilService facilService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/base/main" ,method = RequestMethod.GET)
	public ModelAndView loginMainGet(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		int tt_grade = member.getM_grade();
		int tt_term = 0;
	
		Calendar cal = Calendar.getInstance();
		
		int nowMonth = cal.get(Calendar.MONTH) + 1; 
		
			if(nowMonth>=3&&nowMonth<9){
				tt_term = 1;
			}
			else if((nowMonth>=1&&nowMonth<3)||(nowMonth>=9&&nowMonth<=12)){
				tt_term = 2;
			}
		
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setTt_grade(tt_grade);
		cri.setTt_term(tt_term);
		
		ModelAndView mav = new ModelAndView("/base/main");
		mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	@RequestMapping(value="/base/main" ,method = RequestMethod.POST)
	public ModelAndView loginMainPost(HttpServletRequest request){
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		int tt_grade = member.getM_grade();
		int tt_term = 0;
	
		Calendar cal = Calendar.getInstance();
		
		int nowMonth = cal.get(Calendar.MONTH) + 1; 
		
			if(nowMonth>=3&&nowMonth<9){
				tt_term = 1;
			}
			else if((nowMonth>=1&&nowMonth<3)||(nowMonth>=9&&nowMonth<=12)){
				tt_term = 2;
			}
		
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setTt_grade(tt_grade);
		cri.setTt_term(tt_term);
		
		ModelAndView mav = new ModelAndView("/base/main");
		mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	
	@RequestMapping(value="/base/a_main" ,method = RequestMethod.GET)
	public ModelAndView loginAmainGet(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setPerPageNum(25);
		
		ModelAndView mav = new ModelAndView("/base/a_main");
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("scPglist", facilService.scheduleToPg());
		mav.addObject("scSrlist", facilService.scheduleToSr());
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	
	@RequestMapping(value="/base/a_main" ,method = RequestMethod.POST)
	public ModelAndView loginAmainPost(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setPerPageNum(25);
			
		ModelAndView mav = new ModelAndView("/base/a_main");
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("scPglist", facilService.scheduleToPg());
		mav.addObject("scSrlist", facilService.scheduleToSr());
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	
}
