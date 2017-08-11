package com.harang.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.Criteria;
import com.harang.web.domain.D_ApplyDTO;
import com.harang.web.domain.DaetaDTO;
import com.harang.web.domain.DaetaReplyDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.P_ApplyDTO;
import com.harang.web.domain.ParttimeDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.MessageService;
import com.harang.web.service.ParttimeService;
import com.harang.web.utill.DateBean;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;


@Controller
@RequestMapping("/parttime")
public class ParttimeController {
	
	@Autowired
	private ParttimeService parttimeService;
	
	@Autowired
	private MessageService messageService;
	
	private LoginBean loginBean = new LoginBean();
	private DateBean dateBean = new DateBean();
	private PageMaker pageMaker;
	
	/** 알바모집 */
	
	/**
	 * 알바모집 게시판에 모집글 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @param cri SearchCriteria 검색&페이징 DTO
	 * @return mav ModelAndView
	 */
	@RequestMapping("/PMAIN")
	public ModelAndView parttimeList(HttpSession session, SearchCriteria cri){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_main";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_parttime_main";}
	
		ModelAndView mav = new ModelAndView(url);
		List<ParttimeDTO> list = parttimeService.getParttimeList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(list.size());
		
		// 마감일이 지나면 [마감]으로 말머리 변경
		afterDeadline(list, "p");	

		// 추가정보 저장=> 1.글번호 2.해당 글에 지원한 지원자
		for (int i = 0; i < list.size(); i++) {
			ParttimeDTO dto = list.get(i);
			dto.setList_num(list.size() - i); // 글번호
			dto.setCnt_apply(parttimeService.getParttimeCnt_apply(dto.getP_num())); // 지원자수
			if (dto.getM_id().equals("admin02")) {
				dto.setM_name("관리자");
			} else {
				dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name()); // 이름을  저장
			}
			list.set(i, dto);
		}
		mav.addObject("list", list);
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 마감일이 지나면 자동으로 말머리가 [마감]으로 변경되는 메서드.
	 * @param list 리스트
	 * @param pORd 알바게시판이면 "p" 대타게시판이면 "d"
	 */
	public void afterDeadline(List list, String pORd){
		if(pORd.equals("p")){
			for(int i=0; i<list.size(); i++){
				ParttimeDTO dto = parttimeService.getParttime(((ParttimeDTO)list.get(i)).getP_num());
				if(dateBean.checkDeadline(dto.getP_deadline()) && !dto.getP_header().equals("[마감]")){
					dto.setP_header("[마감]");
					parttimeService.updateParttime(dto);
				}
			}
		}else if(pORd.equals("d")){
			for(int i=0; i<list.size(); i++){
				DaetaDTO dto = parttimeService.getDaeta(((DaetaDTO)list.get(i)).getD_num());
				if(dateBean.checkDeadline(dto.getD_deadline()) && !dto.getD_header().equals("[마감]")){
					dto.setD_header("[마감]");
					parttimeService.updateDaeta(dto);
				}
			}
		}
	}
	
	/**
	 * 알바 모집글을 추가하는 메서드
	 * @param session 세션
	 * @param parttime ParttimeDTO
	 * @return mav ModelAndView
	 */
	@RequestMapping("insertParttime")
	public ModelAndView insertParttime(HttpSession session, ParttimeDTO parttime){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "redirect:/parttime/PMAIN";
		ModelAndView mav = new ModelAndView(url);
		parttime.setP_daycode(transCode(parttime.getP_daycode()));
		parttime.setM_id(login.getM_id());
		parttimeService.insertParttime(parttime);
		return mav;
	}
	
	/**
	 * 체크된 요일을 매개변수로 받아서 daycode로 변환하는 메서드.
	 */
	public String transCode(String p_daycode) {
		String daycode = "";
		String[] day = { "월", "화", "수", "목", "금", "토", "일" };
		String[] arr2 = {"0","0","0","0","0","0","0"};
		
		if(p_daycode != null){
			String arr[] = p_daycode.split(",");
			for (int i = 0; i < 7; i++) {
				for(int j=0; j<arr.length;j++){
					// 해당 요일이 있으면 1로 변경
					if(arr[j].equals(day[i])){
						arr2[i] = "1";
					}
				}
				daycode += arr2[i];
			}
		}
		return daycode;
	}
	
	/**
	 * 모집글을 삭제하는 메서드.
	 * @param p_num
	 * @return mav ModelAndView
	 */
	@RequestMapping("/deleteParttime")
	public ModelAndView deleteParttime(String p_num, String tab){
		String url = null;
		if(tab.equals("PMAIN")){
			url = "redirect:/parttime/PMAIN";
		}else if(tab.equals("MYPAGE")){
			url = "redirect:/parttime/MYPAGE";
		}
		ModelAndView mav = new ModelAndView(url);
		parttimeService.deleteParttime(p_num);
		return mav;
	}	
	
	/**
	 * 알바모집 글쓰기 페이지로 이동하는 메서드.
	 * @return mav ModelAndView
	 */
	@RequestMapping("/PPOST")
	public ModelAndView parttimePost(){
		ModelAndView mav = new ModelAndView("parttime/parttime_post");
		String[] day = {"월","화","수","목","금","토","일"};
		mav.addObject("day", day);
		return mav;
	}
	
	/**
	 * 모집글에 지원을 최소하는 메서드. (취소 후 모집자에게 메시지 보내는 기능 포함)
	 * @param session 세션
	 * @param p_num 알바모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/cancelApply")
	public ModelAndView cancelApply(HttpSession session, String p_num){
		ModelAndView mav = new ModelAndView("redirect:parttime/PREAD");
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", member.getM_id());
		params.put("p_num", p_num);
		
		parttimeService.deleteParttimeApply(params); // 지원 정보를 DB에서 삭제
		ParttimeDTO dto = parttimeService.getParttime(p_num); // 해당 글 정보
		MemberDTO writer = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보

		// 지원 취소 메시지 보내기 : 관리자 -> 글 게시자
		String title = member.getM_name() + "님이 지원을 취소하였습니다.";
		String content = member.getM_name() + "님이 \"" + dto.getP_title() + "\"글에 대한 지원을 취소하였습니다."
				+ "\n해당 글을 확인해주세요.";
		MessageDTO message = new MessageDTO();
		message.setT_title(title);
		message.setT_content(content);
		message.setM_sender("admin02");
		message.setM_reader(member.getM_id());
		messageService.postMessage(message);
		
		return mav;
	}
	
	/**
	 * 알바모집 글을 수정하는 메서드.
	 * @param session 세션
	 * @param parttime ParttimeDTO 
	 * @return mav ModelAndView
	 */
	@RequestMapping("/updateParttime")
	public ModelAndView updateParttime(HttpSession session, ParttimeDTO parttime){
		MemberDTO login = loginBean.getLoginIngfo(session);
		ModelAndView mav = new ModelAndView("redirect:/parttime/PREAD");
		parttimeService.updateParttime(parttime);
		
		/** 해당 글의 지원자들에게 글의 내용이 수정되었음을 알리는 메시지를 보낸다.*/
		List list = parttimeService.getParttimeApplyList(parttime.getP_num()); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			P_ApplyDTO dto1 = (P_ApplyDTO) list.get(i);
			MemberDTO applicant = parttimeService.getMember(dto1.getM_id()); // 지원자의 회원정보
			
			String title = applicant.getM_name()+"님이 지원한 글의 내용이 수정되었습니다.";
			String content =applicant.getM_name()+"님이 지원한 알바 모집 글의 작성자 " + login.getM_name() + "님이 \"" + parttime.getP_title() + "\"글의 내용을 수정하였습니다."
							+ "\n해당 글을 반드시 확인해주세요.";
						
			messageService.postMessage(title, content, "admin02", dto1.getM_id());
		}
		mav.addObject("p_num", parttime.getP_num());
		return mav;
	}
	
	/**
	 * 글번호로 글정보를 조회하여 글읽기 페이지로 이동하는 메서드.
	 * @param session 세션
	 * @param p_num 알바모집 글번호
	 * @param tab 목록으로 돌아갈 때 어느 페이지에서 이동해왔는지 구별하는 변수
	 * @return mav ModelAndView
	 */
	@RequestMapping("/PREAD")
	public ModelAndView readParttime(HttpSession session, String p_num, String tab,String read, Criteria cri){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_read";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_parttime_read";}
		ModelAndView mav = new ModelAndView(url);
		
		/** 글 정보 */
		char[] day = { '월', '화', '수', '목', '금', '토', '일' };
		ParttimeDTO parttime = parttimeService.getParttime(p_num);
		isApply(mav, login.getM_id(), p_num, "p"); // 이 글에 지원했는지 안했는지
		
		/** 조회수 증가 */
		counterUp(p_num, read, "p");
		
		/** 지원자 정보 */
		List list = parttimeService.getParttimeApplyList(p_num); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			P_ApplyDTO dto = (P_ApplyDTO) list.get(i);
			dto.setList_num(i + 1); // 글번호 설정
			dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name());// 지원자의 회원번호로 검색하여 지원자이름을 받아온다.
			list.set(i, dto);
		}
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(5);
		pageMaker.setTotalCount(list.size());
		
		mav.addObject("a_pageMaker", pageMaker);
		mav.addObject("m_id", login.getM_id());
		mav.addObject("resume", list);
		mav.addObject("info", parttime);
		mav.addObject("daycode", splitCode(parttime.getP_daycode()));
		mav.addObject("day", day);
		return mav;
	}
	
	/**
	 * String으로 저장된 요일코드를 배열로 쪼개서 반환하는 메서드.
	 * @param code 디비에 저장된 요일코드(String)
	 * @return 요일코드를 쪼개어 놓은 배열
	 */
	public String[] splitCode(String code) {
		String[] daycode = null;
		if(code != null){
			// String을 한글자씩 쪼개서 저장
			char[] _daycode = code.toCharArray();
	
			// char배열을 String배열로 변환
			daycode = new String[7];
			for (int i = 0; i < _daycode.length; i++) {
				daycode[i] = Character.toString(_daycode[i]);
			}
		}
		return daycode;
	}
	
	/**
	 * 알바 모집글에 지원하는 메서드. (모집자에게 지원 확인 메시지 보내는 기능 포함)
	 * @param session 세션
	 * @param apply P_ApplyDTO
	 * @return 이동할 페이지
	 */
	@RequestMapping("/applyParttime")
	public ModelAndView applyParttime(HttpSession session, P_ApplyDTO apply){
		MemberDTO login = loginBean.getLoginIngfo(session);
		ParttimeDTO dto = parttimeService.getParttime(apply.getP_num()); // 해당 글 정보
		MemberDTO writer = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보

		parttimeService.createParttimeResume(apply);

		// 지원 확인 메시지 보내기 : 관리자 -> 글쓴이
		String title = "\"" + dto.getP_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다.";
		String content = "\"" + dto.getP_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다. 해당 글에서 이력서를 확인해주세요.";
		messageService.postMessage(title, content, "admin02", login.getM_id());
		
		ModelAndView mav = new ModelAndView("redirect:parttime/PREAD");
		mav.addObject("p_num", apply.getP_num());
		return mav;
	}
		
	/**
	 * 알바 모집글 작성자가 지원자 중 한명을 채용했을 때 처리하는 메서드. (채용확인메시지 포함)
	 * @param session 세션
	 * @param choice_id 채용된 회원번호
	 * @param p_num 알바모집 글번호
	 * @return 이동할 페이지 정보
	 */
	@RequestMapping("/choiceParttime")
	public ModelAndView choiceP_apply(HttpSession session, String choice_id, String p_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", choice_id);
		params.put("p_num", p_num);
		
		parttimeService.updateParttimeChoice(params); // 채용 정보를 DB에 저장
		ParttimeDTO dto = parttimeService.getParttime(p_num); // 해당 글 정보
		MemberDTO member = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보
		MemberDTO applicant = parttimeService.getMember(choice_id);

		// 채용 확인 메시지 보내기 : 채용한 사람 -> 지원자
		String title = "축하드립니다. 알바 모집에 채용되셨습니다.";
		String content = "축하드립니다. 알바 모집 게시판의 \"" + dto.getP_title() + "\"에 채용되셨습니다." + "\n채용담당자와 직접 연락하세요. 연락처는 "
				+ dto.getP_tel() + " 입니다.";
		messageService.postMessage(title, content, login.getM_id(), choice_id);

		// 채용 확인 메시지 보내기 : 관리자 -> 채용한 사람
		String title2 = "축하드립니다. 알바 채용에 성공하였습니다.";
		String content2 = member.getM_name() + "님이 알바 모집 게시판에 작성한 \"" + dto.getP_title()
				+ "\" 글에 알바를 채용하셨습니다. \n채용하신 " + applicant.getM_name() + "님과 직접 연락하세요. 연락처는 " + applicant.getM_tel()
				+ " 입니다.";
		messageService.postMessage(title2, content2, "admin02", login.getM_id());
		
		ModelAndView mav = new ModelAndView("redirect:parttime/PREAD");
		mav.addObject("p_num", p_num);
		return mav;
	}

	/**
	 * 알바모집 지원서를 읽어오는 메서드.
	 * @param session 세션
	 * @param m_id 회원번호
	 * @param p_num 알바모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/PRESUME")
	public ModelAndView getParttimeResume(HttpSession session, String m_id, String p_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_resume";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_parttime_resume";
		}
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", m_id);
		params.put("p_num", p_num);
		
		List<P_ApplyDTO> list = parttimeService.getParttimeApply(params);
		P_ApplyDTO resume = list.get(0);
		
		MemberDTO applicant = parttimeService.getMember(m_id);
		applicant.setM_age(getAge(applicant.getM_birth()));
		
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("resume", resume);
		mav.addObject("applicant", applicant);
		return mav;
	}
		
	/**
	 * 알바 모집글을 수정하는 페이지로 이동하는 메서드. 글번호로 글 정보를 검색. 
	 * @param session 세션
	 * @param p_num 알바 모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/PUPDATE")
	public ModelAndView updateParttime(HttpSession session, String p_num, String tab){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_update";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_parttime_update";
		}
		
		char[] day = { '월', '화', '수', '목', '금', '토', '일' };
		ParttimeDTO parttime = parttimeService.getParttime(p_num);

		ModelAndView mav = new ModelAndView(url);
		mav.addObject("info", parttime);
		//mav.addObject("daycode", splitCode(parttime.getP_daycode()));
		mav.addObject("day", day);
		return mav;
	}
	
	/**
	 * 이력서를 삭제하는 메서드.
	 * @param session 세션
	 * @param p_num 대타모집 글번호
	 * @param read 조회수를 올릴지 말지 결정하는 변수
	 * @return mav ModelAndView
	 */
	@RequestMapping("/delParttimeResume")
	public ModelAndView deleteParttimeResume(HttpSession session, String p_num, String read){
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", login.getM_id());
		params.put("p_num", p_num);
		
		parttimeService.deleteParttimeApply(params);
		
		ModelAndView mav = new ModelAndView("redirect:/parttime/PREAD");
		mav.addObject("p_num", p_num);
		mav.addObject("read", read);
		return mav;
	}
	
	/** 알바 모집 끝*/
	
	
	/** 대타 모집 */
	
	/**
	 * 대타모집 게시판에 모집글 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @param cri SearchCriteria 검색&페이징 DTO
	 * @return mav ModelAndView
	 */
	@RequestMapping("/DMAIN")
	public ModelAndView daetaList(HttpSession session, SearchCriteria cri){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/daeta_main";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_daeta_main";}
	
		ModelAndView mav = new ModelAndView(url);
		List<DaetaDTO> list = parttimeService.getDaetaList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(list.size());
		
		// 마감일이 지나면 [마감]으로 말머리 변경
		afterDeadline(list, "d");	

		// 추가정보 저장=> 1.글번호 2.해당 글에 지원한 지원자
		for (int i = 0; i < list.size(); i++) {
			DaetaDTO dto = list.get(i);
			dto.setList_num(list.size() - i); // 글번호
			dto.setCnt_apply(parttimeService.getDaetaCnt_apply(dto.getD_num())); // 지원자수
			if (dto.getM_id().equals("admin02")) {
				dto.setM_name("관리자");
			} else {
				dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name()); // 이름을  저장
			}
			list.set(i, dto);
		}
		mav.addObject("list", list);
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 대타모집 글쓰기 페이지로 이동하는 메서드.
	 * @return mav ModelAndView
	 */
	@RequestMapping("/DPOST")
	public ModelAndView daetaPost(){
		ModelAndView mav = new ModelAndView("parttime/daeta_post");
		String[] day = {"월","화","수","목","금","토","일"};
		mav.addObject("day", day);
		return mav;
	}
	
	/**
	 * 대타 모집글을 추가하는 메서드
	 * @param session 세션
	 * @param daeta DaetaDTO
	 * @return mav ModelAndView
	 */
	@RequestMapping("insertDaeta")
	public ModelAndView insertDaeta(HttpSession session, DaetaDTO daeta){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "redirect:/parttime/DMAIN";
		ModelAndView mav = new ModelAndView(url);
		daeta.setM_id(login.getM_id());
		parttimeService.insertDaeta(daeta);
		return mav;
	}
	
	/**
	 * 글번호로 글정보를 조회하여 글읽기 페이지로 이동하는 메서드.
	 * @param session 세션
	 * @param d_num 알바모집 글번호
	 * @param tab 목록으로 돌아갈 때 어느 페이지에서 이동해왔는지 구별하는 변수
	 * @return mav ModelAndView
	 */
	@RequestMapping("/DREAD")
	public ModelAndView readDaeta(HttpSession session, String d_num, String tab, String read, Criteria cri){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/daeta_read";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_daeta_read";}
		ModelAndView mav = new ModelAndView(url);
		
		/** 글 정보 */
		DaetaDTO daeta = parttimeService.getDaeta(d_num);
		isApply(mav, login.getM_id(), d_num, "d"); // 이 글에 지원했는지 안했는지
		
		/** 조회수 증가 */
		counterUp(d_num, read, "d");
		
		/** 지원자 정보 */
		List<D_ApplyDTO> list = parttimeService.getDaetaApplyList(d_num); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			D_ApplyDTO dto = (D_ApplyDTO) list.get(i);
			dto.setList_num(i + 1); // 글번호 설정
			dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name());// 지원자의 회원번호로 검색하여 지원자이름을 받아온다.
			list.set(i, dto);
		}
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setDisplayPageNum(5);
		pageMaker.setTotalCount(list.size());
		
		mav.addObject("m_id", login.getM_id());
		mav.addObject("resume", list);
		mav.addObject("info", daeta);
		mav.addObject("a_pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 대타 모집글을 삭제하는 메서드.
	 * @param d_num 대타 모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/deleteDaeta")
	public ModelAndView deleteDaeta(String d_num, String tab){
		String url = "redirect:/parttime/DMAIN";
		if(tab.equals("MYPAGE")){
			url = "redirect:/parttime/MYPAGE";
		}
		ModelAndView mav = new ModelAndView(url);
		parttimeService.deleteDaeta(d_num);
		return mav;
	}	
	
	/**
	 * 대타 모집글을 수정하는 페이지로 이동하는 메서드. 글번호로 글 정보를 검색. 
	 * @param session 세션
	 * @param d_num 대타 모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/DUPDATE")
	public ModelAndView updateDaeta(HttpSession session, String d_num, String tab){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/daeta_update";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_daeta_update";
		}
		
		DaetaDTO daeta = parttimeService.getDaeta(d_num);

		ModelAndView mav = new ModelAndView(url);
		mav.addObject("info", daeta);
		return mav;
	}
	
	/**
	 * 대타모집 글을 수정하는 메서드.
	 * @param session 세션
	 * @param daeta DaetaDTO 
	 * @return mav ModelAndView
	 */
	@RequestMapping("/updateDaeta")
	public ModelAndView updateDaeta(HttpSession session, DaetaDTO daeta){
		MemberDTO login = loginBean.getLoginIngfo(session);
		ModelAndView mav = new ModelAndView("redirect:/parttime/DREAD");
		parttimeService.updateDaeta(daeta);
		
		/** 해당 글의 지원자들에게 글의 내용이 수정되었음을 알리는 메시지를 보낸다.*/
		List list = parttimeService.getParttimeApplyList(daeta.getD_num()); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			D_ApplyDTO dto1 = (D_ApplyDTO) list.get(i);
			MemberDTO applicant = parttimeService.getMember(dto1.getM_id()); // 지원자의 회원정보
			
			String title = applicant.getM_name()+"님이 지원한 글의 내용이 수정되었습니다.";
			String content =applicant.getM_name()+"님이 지원한 알바 모집 글의 작성자 " + login.getM_name() + "님이 \"" + daeta.getD_title() + "\"글의 내용을 수정하였습니다."
							+ "\n해당 글을 반드시 확인해주세요.";
						
			messageService.postMessage(title, content, "admin02", dto1.getM_id());
		}
		mav.addObject("d_num", daeta.getD_num());
		return mav;
	}
	
	/**
	 * 대타 모집글에 지원하는 메서드. (모집자에게 지원 확인 메시지 보내는 기능 포함)
	 * @param session 세션
	 * @param apply D_ApplyDTO
	 * @return 이동할 페이지
	 */
	@RequestMapping("/applyDaeta")
	public ModelAndView applyDaeta(HttpSession session, D_ApplyDTO apply){
		MemberDTO login = loginBean.getLoginIngfo(session);
		DaetaDTO dto = parttimeService.getDaeta(apply.getD_num()); // 해당 글 정보
		MemberDTO writer = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보

		parttimeService.createDaetaResume(apply);

		// 지원 확인 메시지 보내기 : 관리자 -> 글쓴이
		String title = "\"" + dto.getD_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다.";
		String content = "\"" + dto.getD_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다. 해당 글에서 이력서를 확인해주세요.";
		messageService.postMessage(title, content, "admin02", login.getM_id());
		
		ModelAndView mav = new ModelAndView("redirect:parttime/PREAD");
		mav.addObject("p_num", apply.getD_num());
		return mav;
	}
	
	/**
	 * 대타 모집글 작성자가 지원자 중 한명을 채용했을 때 처리하는 메서드. (채용확인메시지 포함)
	 * @param session 세션
	 * @param choice_id 채용된 회원번호
	 * @param d_num 대타모집 글번호
	 * @return 이동할 페이지 정보
	 */
	@RequestMapping("/choiceDaeta")
	public ModelAndView choiceD_apply(HttpSession session, String choice_id, String d_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", choice_id);
		params.put("d_num", d_num);
		
		parttimeService.updateDaetaChoice(params); // 채용 정보를 DB에 저장
		DaetaDTO dto = parttimeService.getDaeta(d_num); // 해당 글 정보
		MemberDTO member = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보
		MemberDTO applicant = parttimeService.getMember(choice_id);

		// 채용 확인 메시지 보내기 : 채용한 사람 -> 지원자
		String title = "축하드립니다. 대타 모집에 채용되셨습니다.";
		String content = "축하드립니다. 대타 모집 게시판의 \"" + dto.getD_title() + "\"에 채용되셨습니다." + "\n채용담당자와 직접 연락하세요. 연락처는 "
				+ dto.getD_tel() + " 입니다.";
		messageService.postMessage(title, content, login.getM_id(), choice_id);

		// 채용 확인 메시지 보내기 : 관리자 -> 채용한 사람
		String title2 = "축하드립니다. 대타 채용에 성공하였습니다.";
		String content2 = member.getM_name() + "님이 대타 모집 게시판에 작성한 \"" + dto.getD_title()
				+ "\" 글에 대타를 채용하셨습니다. \n채용하신 " + applicant.getM_name() + "님과 직접 연락하세요. 연락처는 " + applicant.getM_tel()
				+ " 입니다.";
		messageService.postMessage(title2, content2, "admin02", login.getM_id());
		
		ModelAndView mav = new ModelAndView("redirect:parttime/DREAD");
		mav.addObject("d_num", d_num);
		return mav;
	}
	
	/**
	 * 대타모집 지원서를 읽어오는 메서드.
	 * @param session 세션
	 * @param m_id 회원번호
	 * @param d_num 알바모집 글번호
	 * @return mav ModelAndView
	 */
	@RequestMapping("/DRESUME")
	public ModelAndView getDaetaResume(HttpSession session, String m_id, String d_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/daeta_resume";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_daeta_resume";
		}
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", m_id);
		params.put("d_num", d_num);
		
		List<D_ApplyDTO> list = parttimeService.getDaetaApply(params);
		D_ApplyDTO resume = list.get(0);
		
		MemberDTO applicant = parttimeService.getMember(m_id);
		applicant.setM_age(getAge(applicant.getM_birth()));
		
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("resume", resume);
		mav.addObject("applicant", applicant);
		mav.addObject("d_num", d_num);
		return mav;
	}
	
	@RequestMapping("/dReply")
	public @ResponseBody List<DaetaReplyDTO> nameAjax(HttpServletRequest req) throws UnsupportedEncodingException{
		String d_num = req.getParameter("d_num");
		List<DaetaReplyDTO> drlist = parttimeService.getDaetaReplyList(d_num);
		for(int i=0; i<drlist.size();i++){
			DaetaReplyDTO dto = (DaetaReplyDTO)drlist.get(i);
			dto.setM_name((parttimeService.getMember(dto.getM_id()).getM_name())); //이름저장
		}
		return drlist;
	}
	
	
	/**
	 * 이력서를 삭제하는 메서드.
	 * @param session 세션
	 * @param d_num 대타모집 글번호
	 * @param read 조회수를 올릴지 말지 결정하는 변수
	 * @return mav ModelAndView
	 */
	@RequestMapping("/delDaetaResume")
	public ModelAndView deleteDaetaResume(HttpSession session, String d_num, String read){
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", login.getM_id());
		params.put("d_num", d_num);
		
		parttimeService.deleteDaetaApply(params);
		
		ModelAndView mav = new ModelAndView("redirect:/parttime/DREAD");
		mav.addObject("d_num", d_num);
		mav.addObject("read", read);
		return mav;
	}
	/** 대타 모집 끝 */
	
	/** 알바, 대타 공통으로 쓰는 메서드 */
	
	/**
	 * 모집글에 지원했는지 확인하는 메서드.
	 * @param mav ModelAndView
	 * @param m_id 회원번호
	 * @param p_num 알바모집 글번호
	 */
	public void isApply(ModelAndView mav, String m_id, String num, String pORd) {
		
		if(pORd.equals("p")){
			P_ApplyDTO applydto = new P_ApplyDTO();
			
			HashMap<String, Object> params = new HashMap<>();
			params.put("m_id", m_id);
			params.put("p_num", num);
			
			List<P_ApplyDTO> list = (List<P_ApplyDTO>) parttimeService.getParttimeApply(params);
			if(!list.isEmpty()){applydto = list.get(0);}
			
			if (applydto.getM_id() != null) {
				mav.addObject("applied", "Y");
			} else {
				mav.addObject("applied", "N");
			}
		}else if(pORd.equals("d")){
			D_ApplyDTO applydto = new D_ApplyDTO();
			
			HashMap<String, Object> params = new HashMap<>();
			params.put("m_id", m_id);
			params.put("d_num", num);
			
			List<D_ApplyDTO> list = (List<D_ApplyDTO>) parttimeService.getDaetaApply(params);
			if(!list.isEmpty()){applydto = list.get(0);}
			
			if (applydto.getM_id() != null) {
				mav.addObject("applied", "Y");
			} else {
				mav.addObject("applied", "N");
			}
		}
	}
	
	/**
	 * 현재 날짜와 출생일을 비교하여 나이를 계산하는 메서드.
	 * @param birth
	 * @return 나이
	 */
	public int getAge(String birth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date birthday = null;
		try {
			birthday = (Date) sdf.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthDay = new GregorianCalendar();
		birthDay.setTime(birthday);

		int factor = 0;
		if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
			factor = -1;
		}
		return today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR) + factor;
	}
	
	/** 읽은 것으로 취급하지 않는 것이 아니라면 알바인지 대타인지 구분하여 조회수를 증가시키는 메서드. */
	public void counterUp(String num,String read, String pORd){
		if(!"no".equals(read)){
			if(pORd.equals("p")){
				parttimeService.counterUp_Parttime(num);
			}else{
				parttimeService.counterUp_Daeta(num);
			}
		}
	}
	/** 알바, 대타 공통으로 쓰는 메서드 끝 */
	
	
	/** 내가 쓴 글 */
	/** 내가 쓴 글 끝 */
}
