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
	
	// 페이지에 처음 접근할 때.
	@RequestMapping(value="/pointList",method = RequestMethod.GET)
	public ModelAndView pointListGet(HttpSession session,PagingDto page){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		
		RecordDTO record = new RecordDTO();
		record.setM_id(mdto.getM_id());
		
		System.out.println(mdto.getM_id());
		
		List<RecordDTO> plist = new ArrayList<>();
		
		
			plist = myPageService.pointList(record);
		
			
		// 페이징 관련 parameter 받아오기 - 반복됨..
		int nowPage=0, nowBlock=0;
		if(page.getNowPage() != 0){nowPage = page.getNowPage();}
		if(page.getNowBlock() != 0){nowBlock = page.getNowBlock();}
		// DB 연동 함수를 쓰기 위해 인스턴스 생성
		PagingBean pbean = new PagingBean();
		// 페이징 관련 정보 셋팅 , 두번째 parameter는 한페이지에 들어갈 글의 개수!!
		PagingDto paging = pbean.Paging(plist.size(),10, nowPage,10, nowBlock);
		
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("pList", plist);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	// 페이지 안에 페이징 버튼 또는 검색버튼을 눌렀을 경우.
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
		
		// 페이징 관련 parameter 받아오기 - 반복됨..
		int nowPage=0, nowBlock=0;
			if(page.getNowPage() != 0){nowPage = page.getNowPage();}
			if(page.getNowBlock() != 0){nowBlock = page.getNowBlock();}
		// DB 연동 함수를 쓰기 위해 인스턴스 생성
		PagingBean pbean = new PagingBean();
		// 페이징 관련 정보 셋팅 , 두번째 parameter는 한페이지에 들어갈 글의 개수!!
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
		
		//초기 시간표 설정값
		int tt_grade = member.getM_grade();
		int tt_term = 0;
	
		//현재 학기 구하기
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
