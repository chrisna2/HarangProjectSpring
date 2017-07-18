package com.harang.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PagingDto;
import com.harang.web.domain.RecordDTO;
import com.harang.web.service.MyPageService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PagingBean;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
	
	private ModelAndView mav;
	
	@RequestMapping(value="/myInfo",method = RequestMethod.GET)
	public ModelAndView myinfoGet(){
		
		mav = new ModelAndView("myPage/myInfo");
		return mav;
	}
	
	@RequestMapping(value="/myInfo",method = RequestMethod.POST)
	public ModelAndView myinfoPost(){
		
		mav = new ModelAndView("myPage/myInfo");
		return mav;
	}
	
	// �������� ó�� ������ ��.
	@RequestMapping(value="/pointList",method = RequestMethod.GET)
	public ModelAndView pointListGet(HttpSession session,PagingDto page){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		
		RecordDTO record = new RecordDTO();
		record.setM_id(mdto.getM_id());
		
		System.out.println(mdto.getM_id());
		
		List<RecordDTO> plist = new ArrayList<>();
		
		
			plist = myPageService.pointList(record);
		
			
		int nowPage=0, nowBlock=0;
		if(page.getNowPage() != 0){nowPage = page.getNowPage();}
		if(page.getNowBlock() != 0){nowBlock = page.getNowBlock();}
		PagingBean pbean = new PagingBean();
		PagingDto paging = pbean.Paging(plist.size(),10, nowPage,10, nowBlock);
		
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("pList", plist);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	@RequestMapping(value="/pointList",method = RequestMethod.POST)
	public ModelAndView pointListPost(String keyword,String keyfield,HttpSession session,PagingDto page){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		
		RecordDTO record = new RecordDTO();
		record.setKeyfield(keyfield);
		record.setKeyword(keyword);
		record.setM_id(mdto.getM_id());
		
		List<RecordDTO> plist = new ArrayList<>();
		
		if(null == keyword || "".equals(keyword)){
			plist = myPageService.pointList(record);
		}
		else{
			plist = myPageService.pointListSearch(record);
		}
		
		int nowPage=0, nowBlock=0;
			if(page.getNowPage() != 0){nowPage = page.getNowPage();}
			if(page.getNowBlock() != 0){nowBlock = page.getNowBlock();}
		PagingBean pbean = new PagingBean();
		PagingDto paging = pbean.Paging(plist.size(),10, nowPage,10, nowBlock);
		
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("keyword", keyword);
		mav.addObject("keyfield", keyfield);
		mav.addObject("pList", plist);
		mav.addObject("paging", paging);
		
		return mav;
		
	}
	
	@RequestMapping(value="/timeTable",method = RequestMethod.GET)
	public ModelAndView timeTableGet(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		ArrayList ttlist  = new ArrayList();
		
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
		
		LessonDTO lesson = new LessonDTO();
		lesson.setTt_term(tt_term);
		lesson.setTt_grade(tt_grade);
		lesson.setM_id(member.getM_id());
		
		
		
		
		
		
		
		
		
		mav = new ModelAndView("myPage/timeTable");
		
		return mav;
	}
	
	@RequestMapping(value="/pointZero",method = RequestMethod.GET)
	public ModelAndView pointZeroGet(){
		
		mav = new ModelAndView("myPage/pointZero");
		
		return mav;
	}
	
	@RequestMapping(value="/specUp",method = RequestMethod.GET)
	public ModelAndView specUpGet(){
		
		mav = new ModelAndView("myPage/specUp");
		
		return mav;
	}
	
	@RequestMapping(value="/AmemList",method = RequestMethod.GET)
	public ModelAndView amemListGet(){
		
		mav = new ModelAndView("myPage/a_memList");
		
		return mav;
	}
	
	@RequestMapping(value="/AspecList",method = RequestMethod.GET)
	public ModelAndView aspecListGet(){
		
		mav = new ModelAndView("myPage/a_specList");
		
		return mav;
	}
	
	@RequestMapping(value="/Achallenge",method = RequestMethod.GET)
	public ModelAndView achallengeGet(){
		
		mav = new ModelAndView("myPage/a_challenge");
		
		return mav;
	}
	
	@RequestMapping(value="/Alesson",method = RequestMethod.GET)
	public ModelAndView alessonGet(){
		
		mav = new ModelAndView("myPage/a_lessonList");
		
		return mav;
	}
	
	@RequestMapping(value="/ApointCheck",method = RequestMethod.GET)
	public ModelAndView apointCheckGet(){
		
		mav = new ModelAndView("myPage/a_pointcheck");
		
		return mav;
	}
	

	
	
}
