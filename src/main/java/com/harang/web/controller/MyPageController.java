 package com.harang.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.harang.web.domain.CertiDTO;
import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;
import com.harang.web.service.MyPageService;
import com.harang.web.service.PointService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;
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
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/myInfo",method = RequestMethod.GET)
	public ModelAndView myinfoGet(){
		mav = new ModelAndView("myPage/myInfo");
		mav.addObject("sido",myPageService.sidoList());
		return mav;
	}
	
	@RequestMapping(value="/myInfo",method = RequestMethod.POST)
	public ModelAndView myinfoPost(MemberDTO member, HttpServletRequest request, MultipartFile file) throws IOException{
		
		//웹에 올린 경로를 그대로 받아옴
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		//파일의 값이 있으면
		if(!"".equals(file.getOriginalFilename())){
			
			//이전에 파일을 올린 경우
			if(!"".equals(member.getM_photo())||null!=member.getM_photo()){
				//이전 파일 삭제
				UploadBean.deleteFile(member.getM_photo(), uploadPath);
			}
		
			//새로운 파일 저장
			String uploadedFileName = UploadBean.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			member.setM_photo(uploadedFileName);
		}
		
		//반대로 파일의 값이 없으면 그냥 히든으로 숨긴 파일의 경로를 저장 한다.
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
	
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public @ResponseBody List<MemberDTO> getUserList(){
    	
    	return myPageService.userList();
	}
	
    @RequestMapping(value = "/AmemList",method = RequestMethod.GET)
    public ModelAndView memberListGet(SearchCriteria cri){
    	
    	pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.memberListCount(cri));
    	
    	mav = new ModelAndView("myPage/a_memList");
    	
    	mav.addObject("memList", myPageService.memberList(cri));
    	mav.addObject("pageMaker", pageMaker);		
    			
    	return mav;
    }
    
    @RequestMapping(value = "/AmemList",method = RequestMethod.POST)
    public ModelAndView memberListPost(SearchCriteria cri){
    	
    	pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(myPageService.memberListCount(cri));
    	
    	mav = new ModelAndView("myPage/a_memList");
    	
    	mav.addObject("memList", myPageService.memberList(cri));
    	mav.addObject("pageMaker", pageMaker);		
    	
    	return mav;
    }
  
    
    @RequestMapping(value = "/Anewmem",method = RequestMethod.POST)
    public ModelAndView newMember(){
    	
    	mav = new ModelAndView("myPage/a_newMember");
    	
    	return mav;
    }
    
    @RequestMapping(value = "/addMember",method = RequestMethod.POST)
    public ModelAndView addMemberInsert(HttpServletRequest request, HttpServletResponse response){
    	
    	
    	MemberDTO member = new MemberDTO();
    	
    	member.setM_id(request.getParameter("m_id"));
    	member.setM_name(request.getParameter("m_name"));
    	member.setM_dept(request.getParameter("m_dept"));
    	member.setM_birth(request.getParameter("m_birth"));
    	member.setM_pw(request.getParameter("m_birth"));
    	
    	int result = myPageService.addMemberInsert(member);
    	
    	System.out.println(result);

    	String msg = "error";
    	
    	if(1 == result){
    		msg = "complete";
    	}
    	
    	mav = new ModelAndView("myPage/a_memResult");
    	
    	mav.addObject("msg", msg);
    	
    	return mav;
    }
    
    @RequestMapping(value = "/membernum")
    public @ResponseBody String newMemberNum(String num4){
    	
    	System.out.println(num4);
    	
    	String num3 = myPageService.newMemberNum(num4);
    	String num5 = num3.substring(6, 9);
    	int num6 = Integer.parseInt(num5)+1000+1;
    	String num7 = Integer.toString(num6);
    	String num8 = num7.substring(1);
    	
    	System.out.println(num8);
    	
    	return num8;
    }
    
    @RequestMapping(value = "/userData")
    public @ResponseBody MemberDTO getUserData(String m_id){
    	
    	MemberDTO member = myPageService.memberData(m_id);
    	
    	System.out.println(member.toString());
    	
    	return member;
	}
	
	
	@RequestMapping(value="/pointList",method = RequestMethod.GET)
	public ModelAndView pointListGet(HttpSession session,SearchCriteria cri){
		
		MemberDTO mdto  = (MemberDTO)session.getAttribute("member");
		cri.setM_id(mdto.getM_id());
		
		List<RecordDTO> plist = myPageService.pointListSearch(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.pointPagingNum(cri));
		
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
		pageMaker.setTotalCount(myPageService.pointPagingNum(cri));
		
		mav = new ModelAndView("myPage/pointList");
		mav.addObject("pList", plist);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value="/ApointCheck",method = RequestMethod.GET)
	public ModelAndView aPointListGet(SearchCriteria cri){
		
		List<MemberDTO> mlist = myPageService.apointMember(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.apointMemberCount(cri));
		
		mav = new ModelAndView("myPage/a_pointcheck");
		
		mav.addObject("mList", mlist);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value="/ApointCheck",method = RequestMethod.POST)
	public ModelAndView aPointListPost(SearchCriteria cri){
		
		List<MemberDTO> mlist = myPageService.apointMember(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.apointMemberCount(cri));
		
		mav = new ModelAndView("myPage/a_pointcheck");
		
		mav.addObject("mList", mlist);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	@RequestMapping(value="/Applist",method = RequestMethod.GET)
	public ModelAndView aPersonalPointListGet(String check_id, SearchCriteria cri){
		
		cri.setM_id(check_id);
		
		List<RecordDTO> plist = myPageService.pointListSearch(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.pointPagingNum(cri));
		
		mav = new ModelAndView("myPage/a_pplist");
		mav.addObject("pList", plist);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("check", myPageService.pointPersonInfo(check_id));
		
		return mav;
	}
	
	@RequestMapping(value="/Applist",method = RequestMethod.POST)
	public ModelAndView aPersonalPointListPost(String check_id, SearchCriteria cri){
		
		cri.setM_id(check_id);
		
		List<RecordDTO> plist = myPageService.pointListSearch(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.pointPagingNum(cri));
		
		mav = new ModelAndView("myPage/a_pplist");
		mav.addObject("pList", plist);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("check", myPageService.pointPersonInfo(check_id));
		
		return mav;
	}
	
	@RequestMapping(value="/updatepoint",method = RequestMethod.POST)
	public ModelAndView updatepoint(HttpServletRequest request, HttpServletResponse response){

		String check = request.getParameter("check");
		
		long m_point = Long.parseLong(request.getParameter("m_point"));
		long a_point = Long.parseLong(request.getParameter("a_point"));
		String r_content = request.getParameter("r_content");
		int r_point = Integer.parseInt(request.getParameter("r_point"));
		String member_id = request.getParameter("member_id");
		String admin_id = request.getParameter("admin_id");
		
		String result = "";
		
		if("plus".equals(check)){
			
			result = pointService.recordPointTrade(r_content, 
													a_point, 
													r_point, 
													admin_id, 
													member_id);
			if("complete".equals(result)){
				result = "plusgo";
			}
			
		}
		else if("minus".equals(check)){
			
			result = pointService.recordPointTrade(r_content, 
													m_point, 
													r_point, 
													member_id,
													admin_id);
			if("complete".equals(result)){
				result = "minusgo";
			}
		}
		
		mav = new ModelAndView("myPage/a_pplist");
		mav.addObject("result", result);
		mav.addObject("check", myPageService.pointPersonInfo(member_id));
		
		return mav;
	}
	
	
	/**
	 * 시간표 처음 접근 할때 하는 메소드
	 * @param request
	 * @param cri
	 * @return
	 */
	@RequestMapping(value="/timeTable",method = RequestMethod.GET)
	public ModelAndView timeTableGet(HttpServletRequest request, SearchCriteria cri){
		
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
		
		cri.setM_id(member.getM_id());
		cri.setTt_grade(tt_grade);
		cri.setTt_term(tt_term);
		
		/////////////////////////////////////////////////////////////////////////
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.lessonCount(cri));
		
		mav = new ModelAndView("myPage/timeTable");
		
			mav.addObject("tt_grade", tt_grade);
			mav.addObject("tt_term", tt_term);
			mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
			mav.addObject("ttname", tt_grade+"학년 "+tt_term+"학기");
			mav.addObject("llist", myPageService.lessonList(cri));
			mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	/**
	 * 시간표 검색 및 페이징
	 * @param request
	 * @param cri
	 * @return
	 */
	@RequestMapping(value="/timeTable",method = RequestMethod.POST)
	public ModelAndView timeTablePost(HttpServletRequest request, SearchCriteria cri){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		int tt_grade = cri.getTt_grade();
		int tt_term = cri.getTt_term();
		cri.setM_id(member.getM_id());
		
		//String check = request.getParameter("check");
		
		/////////////////////////////////////////////////////////////////////////
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.lessonCount(cri));
		
		mav = new ModelAndView("myPage/timeTable");
		
			mav.addObject("tt_grade", tt_grade);
			mav.addObject("tt_term", tt_term);
			mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
			mav.addObject("ttname", tt_grade+"학년 "+tt_term+"학기");
			mav.addObject("llist", myPageService.lessonList(cri));
			mav.addObject("pageMaker", pageMaker);
			
		
		return mav;
	}
	
	
	@RequestMapping(value="/aLessonList",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> aLessonListJson(@RequestParam(value = "page", required=false) int page,
													    	@RequestParam(value = "rows", required=false) int rows,
													    	@RequestParam(value = "sidx", required=false) String sidx,
													    	@RequestParam(value = "sord", required=false) String sord,
													    	@RequestParam(value = "searchField", required=false) String searchField,
													        @RequestParam(value = "searchString", required=false) String searchString) throws JsonProcessingException{
															//무조건 이 방식대로 가야 함...써글. jqgrid 내장 함수 명임
		
		//SearchCriteria 사용 가능!!!
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page); // page : 현재 보이는 페이지
		cri.setPerPageNum(rows); //rows : 페이지 당 등록된 줄의 숫자
		cri.setKeyfield(searchField);
		cri.setKeyword(searchString);
		
		List<LessonDTO> list = myPageService.aLessonList(cri);
		
		//페이징..
		int totCount = myPageService.aLessonListCount(cri);
		double totalPage = (double)totCount/rows;
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("total",(int)Math.ceil(totalPage));
		modelMap.put("record",totCount);
		modelMap.put("page", page);
		modelMap.put("rows", list);
		
		return modelMap;
		
	}
		
	@RequestMapping(value="/aLessonListEdit",method = RequestMethod.POST)
	public @ResponseBody String aLessonListEdit(HttpServletRequest request, HttpServletResponse response){
		
		String oper = request.getParameter("oper");
		
		LessonDTO lesson = new LessonDTO();
		
		lesson.setCellName(request.getParameter("cellName"));
		String cellValue = request.getParameter("cellValue");//의미 없음...
		lesson.setL_num(request.getParameter("id"));
		lesson.setL_name(request.getParameter("l_name"));
		lesson.setL_ismust(request.getParameter("l_ismust"));
		lesson.setL_dept(request.getParameter("l_dept"));
		lesson.setL_teacher(request.getParameter("l_teacher"));
		lesson.setL_room(request.getParameter("l_room"));
		lesson.setL_time(request.getParameter("l_time"));
		lesson.setL_day(request.getParameter("l_day"));
		lesson.setL_grade(request.getParameter("l_grade"));
		lesson.setL_term(request.getParameter("l_term"));
		if(null == request.getParameter("l_credit")||"".equals(request.getParameter("l_credit"))){
			lesson.setL_credit(0);
		}
		else{
			lesson.setL_credit(Integer.parseInt(request.getParameter("l_credit")));
		}
		
		int check = 0;
		
		if("add".equals(oper)){
			check = myPageService.aLessonInsert(lesson);
		}
		else if("edit".equals(oper)){
			check = myPageService.aLessonUpdateCell(lesson);
		}
		else if("del".equals(oper)){
			check = myPageService.aLessonDelete(lesson);
		}
		else{
			check = 0;
		}
		
		String result = null;
		if(check == 1){
			result="success";
		}else{
			result="fail";
		}
		
		return result;
	}
	
	@RequestMapping(value="/Anewlesson",method = RequestMethod.POST)
	public ModelAndView aNewLesson(){
		
		mav = new ModelAndView("myPage/a_lessonplus");
		
		mav.addObject("dlist", myPageService.l_deptList());
		mav.addObject("tlist", myPageService.l_teacherList());
		mav.addObject("rlist", myPageService.l_roomList());
		
		return mav;
				
	}
	
	@RequestMapping(value="/findtt")
	public @ResponseBody List<LessonDTO> findTimetable(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		LessonDTO lesson = new LessonDTO();
		
		String check  = request.getParameter("check");
		lesson.setL_term(request.getParameter("l_term"));
		
		List<LessonDTO> list = new ArrayList<>();
		
		if("room".equals(check)){
			
			lesson.setL_room(URLDecoder.decode(request.getParameter("l_room"), "UTF-8"));
			list = myPageService.findTimetableRoom(lesson);
			
		}
		else if("teacher".equals(check)){
			
			lesson.setL_teacher(URLDecoder.decode(request.getParameter("l_teacher"), "UTF-8"));
			list =  myPageService.findTimetableTeacher(lesson);
		}
		
		return list;
		
	}
	
	
	@RequestMapping(value="/Alesson",method = RequestMethod.GET)
	public ModelAndView alessonGet(){
		
		mav = new ModelAndView("myPage/a_lessonList");
		
		return mav;
	}
	
	
	@RequestMapping(value="/aLessonPlus",method = RequestMethod.POST)
	public ModelAndView aLessonPlus(LessonDTO lesson){
		
		int check = myPageService.aLessonInsert(lesson);
		
		String result = "";
		
		if(check == 1){
			result = "insert_complete";
		}
		else{
			result = "insert_fail";
		}
		
		mav = new ModelAndView("myPage/a_lessonList");
		
		mav.addObject("result", result);
		
		return mav;
		
	}
	
	/**
	 * 시간표 등록 ajax 등록..
	 * @param cri
	 * @param lesson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/timeTable/enroll",method = RequestMethod.POST)
	public ResponseEntity<String> enrollLesson(@RequestBody LessonDTO lesson){
		
		ResponseEntity<String> entity = null;
		
		try{
			String result = myPageService.enrollCheck(lesson);
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
			
		}catch (Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/**
	 * 시간표 등록 ajax 삭제..
	 * @param cri
	 * @param lesson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/timeTable/delete",method = RequestMethod.POST)
	public ResponseEntity<String> deleteLesson(@RequestBody LessonDTO lesson){
		
		ResponseEntity<String> entity = null;
		
		try{
			String result = myPageService.deleteCheck(lesson);
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
			
		}catch (Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
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
	
	@RequestMapping(value="/AspecList",method = RequestMethod.GET)
	public ModelAndView aspecListGet(SearchCriteria cri){
		
		mav = new ModelAndView("myPage/a_specList");
		
		return mav;
	}
	
	@RequestMapping(value="/AchallengeComplete",method = RequestMethod.POST)
	public ModelAndView challengeComplete(HttpServletRequest request,HttpServletResponse response){
		
		LoginBean login = new LoginBean();
		MemberDTO admin = login.getLoginInfo(request);
		
		String r_content = "[스펙 업 성공!] "+ request.getParameter("c_name") +" : "
							+ request.getParameter("c_point") + " 포인트 지급";
		int c_point = Integer.parseInt(request.getParameter("c_point"));
		String member_id = request.getParameter("m_id");
		String c_num = request.getParameter("c_num");
		
		CertiMemberDTO certi = new CertiMemberDTO();
		certi.setM_id(member_id);
		certi.setC_num(c_num);
		
		int check = myPageService.challengeComplete(certi);
		
		String result = "";
		
		if(check == 1){
			result = pointService.recordPointTrade(r_content, 
												   admin.getM_point(), 
												   c_point, 
												   admin.getM_id(),
												   member_id);
		}
		
		mav = new ModelAndView("myPage/a_challengeComplete");

		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/AchallengeReturn",method = RequestMethod.POST)
	public ModelAndView challengeReturn(HttpServletRequest request,HttpServletResponse response){
		
		String member_id = request.getParameter("m_id");
		String c_num = request.getParameter("c_num");
		
		CertiMemberDTO certi = new CertiMemberDTO();
		certi.setM_id(member_id);
		certi.setC_num(c_num);		
		
		int check = myPageService.challengeReturn(certi);
		
		String result = "";
		
		if(check == 1){
			result = "return";
		}
		
		mav = new ModelAndView("myPage/a_challengeComplete");
		
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/specList",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> specListJson(	@RequestParam(value = "page", required=false) int page,
													    	@RequestParam(value = "rows", required=false) int rows,
													    	@RequestParam(value = "sidx", required=false) String sidx,
													    	@RequestParam(value = "sord", required=false) String sord,
													    	@RequestParam(value = "searchField", required=false) String searchField,
													        @RequestParam(value = "searchString", required=false) String searchString) throws JsonProcessingException{
															//무조건 이 방식대로 가야 함...써글.
		
		//SearchCriteria 사용 가능!!!
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(page); // page : 현재 보이는 페이지
		cri.setPerPageNum(rows); //rows : 페이지 당 등록된 줄의 숫자
		cri.setKeyfield(searchField);
		cri.setKeyword(searchString);
		
		
		List<CertiMemberDTO> list = myPageService.specListJson(cri);
		
		//페이징..
		int totCount = myPageService.specListJsonCount(cri);
		double totalPage = (double)totCount/rows;
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("total",(int)Math.ceil(totalPage));
		modelMap.put("record",totCount);
		modelMap.put("page", page);
		modelMap.put("rows", list);
	
		return modelMap;
	}
	
	@RequestMapping(value="/specListEdit",method = RequestMethod.POST)
	public @ResponseBody String specListJsonEdit(HttpServletRequest request, HttpServletResponse response){
		
		String oper = request.getParameter("oper");
		
		CertiMemberDTO certi = new CertiMemberDTO();
		
		String cellname = request.getParameter("cellName");
		String cellvalue = request.getParameter("cellValue");
		certi.setC_name(request.getParameter("c_name"));
		certi.setC_agency(request.getParameter("c_agency"));
		certi.setC_num(request.getParameter("id"));
		//request.getParameter("c_num")으로는 'c_num'을 받아 오지 못한다.
		
		int c_point = 0;
		
			if(null == request.getParameter("c_point")||"".equals(request.getParameter("c_point"))){
				c_point = 0;
			}
			else{
				c_point = Integer.parseInt(request.getParameter("c_point"));
			}
		
		certi.setC_point(c_point);
		
		System.out.println("기관 : "+certi.getC_agency()+
						"\n이름 : "+certi.getC_name()+
						"\n번호 : "+certi.getC_num()+
						"\n포인트 : "+certi.getC_point()+
						"\n오퍼(구분자) : "+oper+
						"\n선택한 셀의 항목 이름 : "+cellname+
						"\n변경된 셀의 항목 데이터 : "+cellvalue);
		
		String result = null;
		
		if("add".equals(oper)){
			
			result = myPageService.specInsert(certi);
			
			//이슈 1] 어떻게 result 값을 jsp페이지 밖으로 리보내줄까? -> 해결 
			//이슈 2] 업데이트, 삭제에 필요한 pk 값은 어떻게 받아와야 하나? //해결.. 했는데.. 꺼림찍 -> 해결 
			//이슈 3] 입력이 완료되고 자동 생성된 pk 값을 어떻게 받아 올까? (트리거로 생성된 값) || JQGRID에 입력후 새로고침을 AJAX안에서 어떻게 실행 할까? -> 미해결
			
		}
		else if("edit".equals(oper)){
			
			if("c_name".equals(cellname)){
				result = myPageService.specUpdateName(certi);
			}
			else if("c_agency".equals(cellname)){
				result = myPageService.specUpdateAgency(certi);
			}
			else if("c_point".equals(cellname)){
				result = myPageService.specUpdatePoint(certi);
			}
			else if("".equals(cellname)||null == cellname){
				result = myPageService.specUpdateAll(certi);
			}
		}
		else if("del".equals(oper)){
			result = myPageService.specDelete(certi);
			System.out.println(result);
		}
		else{
			System.out.println("잘못된 접근입니다.");
			result ="fail";
		}
		return result;
	}
	
	//@RequestMapping(value="/myPage/specList/excel",method = RequestMethod.POST)
	
	
	
	@RequestMapping(value="/Achallenge",method = RequestMethod.GET)
	public ModelAndView achallengeGet(SearchCriteria cri){
		
		List<CertiMemberDTO> list = myPageService.achallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.achallengePage(cri));
		
		mav = new ModelAndView("myPage/a_challenge");
		
		mav.addObject("cmlist", list);
		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
	}
	//검색을 위한 Post
	@RequestMapping(value="/Achallenge",method = RequestMethod.POST)
	public ModelAndView achallengePost(SearchCriteria cri){
		
		List<CertiMemberDTO> list = myPageService.achallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.achallengePage(cri));
		
		mav = new ModelAndView("myPage/a_challenge");
		
		mav.addObject("cmlist", list);
		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
	}
	
	@RequestMapping(value="/specUp",method = RequestMethod.GET)
	public ModelAndView specUpGet(SearchCriteria cri, HttpServletRequest req){
	
		//이전 꺼 활용
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(req);
		cri.setM_id(member.getM_id());
		
		List<CertiMemberDTO> list = myPageService.uchallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.uchallengePage(cri));
		
		mav = new ModelAndView("myPage/specUp");
		
		mav.addObject("aspeclist", list);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value="/specUp",method = RequestMethod.POST)
	public ModelAndView specUpPost(SearchCriteria cri, HttpServletRequest req){
		
		//이전 꺼 활용
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(req);
		cri.setM_id(member.getM_id());
		
		List<CertiMemberDTO> list = myPageService.uchallengeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(myPageService.uchallengePage(cri));
		
		mav = new ModelAndView("myPage/specUp");
		
		mav.addObject("aspeclist", list);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value="/specUpProc", method = RequestMethod.POST)
	public ModelAndView specUpProcPost(CertiMemberDTO certi, HttpServletRequest request, MultipartFile file) throws IOException{
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		//파일의 값이 있으면
		if(!"".equals(file.getOriginalFilename())){
			if(!"".equals(certi.getCm_image())||null!=certi.getCm_image()){
				//이전 파일 삭제
				UploadBean.deleteFile(certi.getCm_image(), uploadPath);
			}
			//새로운 파일 저장
			String uploadedFileName = 
					UploadBean.uploadFile(uploadPath, 
							file.getOriginalFilename(), file.getBytes());
			certi.setCm_image(uploadedFileName);
		}

		mav = new ModelAndView("myPage/specUpComplete");
		String check = request.getParameter("check");
		
		if(check.equals("insert")){
			myPageService.uchallenge_challenge(certi);
			mav.addObject("msg", "challenge_success");
			
		}else if(check.equals("update")){
			myPageService.uchallenge_rechallenge(certi);
			mav.addObject("msg", "rechallenge_success");
		}
		
		return mav;
	}
	

	
	
}
