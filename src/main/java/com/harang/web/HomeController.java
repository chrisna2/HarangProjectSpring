package com.harang.web;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.BambooService;
import com.harang.web.service.FacilService;
import com.harang.web.service.LoginService;
import com.harang.web.service.MyPageService;
import com.harang.web.service.ParttimeService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.UploadBean;

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
	
	@Autowired
	private LoginService loginService;
	
	
	private String uploadPath;	
	
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
	
	

	@RequestMapping(value="/base/regform" ,method = RequestMethod.GET)
	public ModelAndView regformGet(){
		ModelAndView mav = new ModelAndView("/base/regform");
		mav.addObject("sido", myPageService.sidoList());
		return mav;
	}
	
	
	
	@RequestMapping(value="/base/regform" ,method = RequestMethod.POST)
	public ModelAndView regformPost(MemberDTO member, HttpSession session, HttpServletRequest request, MultipartFile file) throws IOException{
		
		//파일업로드는 이제 이렇게 간단하게 가능하다.
		//웹서버의 고정 경로 찾기 : 실제 도메인 관련
		uploadPath = request.getSession().getServletContext().getRealPath("/");
		//현재 웹서버 저장 경로 : C:\NahyunKee\FrameWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\HarangProjectSpring\

		//실제 파일 저장 메소드 호춯!
		String uploadedFileName = UploadBean.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
		member.setM_photo(uploadedFileName);
		
		loginService.register(member);
			
		System.out.println(uploadedFileName);
		
		ModelAndView mav = new ModelAndView("/login/loginPost");
		
		session.invalidate();
		
		mav.addObject("loginType", "confirm");
		
		return mav;
	}
}
