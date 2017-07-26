package com.harang.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PagingDto;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;
import com.harang.web.service.MyPageService;
import com.harang.web.service.PointService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;
import com.harang.web.utill.PagingBean;
import com.harang.web.utill.UploadBean;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private PointService pointService;
	
	private ModelAndView mav;
	
	private PageMaker pageMaker;
	
	@RequestMapping(value="/myInfo",method = RequestMethod.GET)
	public ModelAndView myinfoGet(){
		mav = new ModelAndView("myPage/myInfo");
		mav.addObject("sido",myPageService.sidoList());
		return mav;
	}
	
	@RequestMapping(value="/myInfo",method = RequestMethod.POST)
	public ModelAndView myinfoPost(MemberDTO member, HttpSession session, HttpServletRequest request, MultipartFile file) throws IOException{
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		//실제 파일 저장 메소드 호춯!
		String uploadedFileName = UploadBean.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		member.setM_photo(uploadedFileName);
		
		System.out.println(uploadedFileName);
		//일단 사진 저장...
		
		myPageService.updateMyinfo(member);
		
		mav = new ModelAndView("/myPage/myInfo");
		mav.addObject("check", "confirm");
		return mav;
	}
	
	@RequestMapping(value="/gugun")
	public @ResponseBody List<ZipDTO> gugunAjax(HttpServletRequest request) throws UnsupportedEncodingException{
		
		String sido = URLDecoder.decode(request.getParameter("sido"), "UTF-8" );
		System.out.println(sido);
		
		ZipDTO zip = new ZipDTO();
		
		zip.setSido(sido);
		
		List<ZipDTO> list = myPageService.gugunList(zip);
		
		return list;
	}
	
	@RequestMapping(value="/dong")
	public @ResponseBody List<ZipDTO> dongAjax(HttpServletRequest request) throws UnsupportedEncodingException{
		
		String sido = URLDecoder.decode(request.getParameter("sido"), "UTF-8" );
		String gugun = URLDecoder.decode(request.getParameter("gugun"), "UTF-8" );
		String dong = URLDecoder.decode(request.getParameter("dong"), "UTF-8" );
		
		ZipDTO zip = new ZipDTO();
		
		zip.setSido(sido);
		zip.setGugun(gugun);
		zip.setDong(dong);
		
		List<ZipDTO> list = myPageService.dongList(zip);
		
		return list;
	}
	
	@RequestMapping(value="/pointList",method = RequestMethod.GET)
	public ModelAndView pointListGet(HttpSession session,SearchCriteria cri){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		cri.setM_id(mdto.getM_id());
		
		List<RecordDTO> plist = myPageService.pointListSearch(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.pointPagingNum(mdto.getM_id()));
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("pList", plist);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	
	@RequestMapping(value="/pointList",method = RequestMethod.POST)
	public ModelAndView pointListPost(HttpSession session,SearchCriteria cri){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		cri.setM_id(mdto.getM_id());
		
		List<RecordDTO> plist = myPageService.pointListSearch(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.pointPagingNum(mdto.getM_id()));
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("pList", plist);
		mav.addObject("pageMaker", pageMaker);
		
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
	
	@RequestMapping(value="/pointZero",method = RequestMethod.POST)
	public ModelAndView pointZeroPost(HttpServletRequest request,long r_point){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		//System.out.println("아이디 : " + member.getM_id());
		//System.out.println("포인트 : " + r_point);
		
		String check = pointService.recordPointTrade("[학비 포인트 감면] "+r_point+"원의 학비가 감면 되었습니다.", 
													 member.getM_point(), 
													 r_point, 
													 member.getM_id(), 
													 "admin01");
		int checkpoint = 0;
		
		if("complete".equals(check)){
			
			checkpoint = myPageService.pointZero(r_point, member.getM_id());
			
		}
		
		System.out.println("처리 결과 : "+checkpoint);
		
		mav = new ModelAndView("myPage/pointZero");
		
		mav.addObject("result", check);
		
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
	public ModelAndView achallengeGet(SearchCriteria cri){
		
		//System.out.println(cri.getPageStart());
		//System.out.println(cri.getPerPageNum());
		//System.err.println(cri.getKeyfield());
		
		List<CertiMemberDTO> list = myPageService.achallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.achallengePage());
		
		mav = new ModelAndView("myPage/a_challenge");
		
		mav.addObject("cmlist", list);
		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
	}
	//검색을 위한 Post
	@RequestMapping(value="/Achallenge",method = RequestMethod.POST)
	public ModelAndView achallengePost(SearchCriteria cri){
		
		//System.out.println(cri.getPageStart());
		//System.out.println(cri.getPerPageNum());
		//System.err.println(cri.getKeyfield());
		
		List<CertiMemberDTO> list = myPageService.achallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.achallengePage());
		
		mav = new ModelAndView("myPage/a_challenge");
		
		mav.addObject("cmlist", list);
		mav.addObject("pageMaker", pageMaker);
		
		
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
