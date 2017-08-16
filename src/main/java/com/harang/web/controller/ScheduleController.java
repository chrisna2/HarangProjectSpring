package com.harang.web.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SearchCriteria2;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.service.ScheduleService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	private PageMaker pageMaker;

	
	@RequestMapping(value = "/SCH_LIST", method = RequestMethod.GET)
	public ModelAndView scheduleListbyGet(HttpServletRequest req, SearchCriteria2 cri) {
		

		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade = 0;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
			
			ModelAndView mav = new ModelAndView(inMav);
			mav.addObject("schlist", scheduleService.aschList(cri));
			
			pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(scheduleService.schListCount(cri));
			System.out.println("1111111111");
			System.out.println(cri.getKeyfield());
			System.out.println(cri.getKeyword());

			mav.addObject("pageMaker", pageMaker);
			
			return mav;
			
		}
		else {
			inMav = "schedule/u_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
			ModelAndView mav = new ModelAndView(inMav);
			mav.addObject("schlist", scheduleService.schList(cri));
			
			pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(scheduleService.schListCount(cri));

			mav.addObject("pageMaker", pageMaker);
			
			return mav;
		}
	}
	@RequestMapping(value = "/SCH_LIST", method = RequestMethod.POST)
	public ModelAndView scheduleListbyPost(HttpServletRequest req, SearchCriteria2 cri) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade = 0;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
			
			ModelAndView mav = new ModelAndView(inMav);
			mav.addObject("schlist", scheduleService.aschList(cri));
			
			pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(scheduleService.schListCount(cri));
			System.out.println("1111111111");
			System.out.println(cri.getKeyfield());
			System.out.println(cri.getKeyword());

			mav.addObject("pageMaker", pageMaker);
			
			return mav;
			
		}
		else {
			inMav = "schedule/u_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
			ModelAndView mav = new ModelAndView(inMav);
			mav.addObject("schlist", scheduleService.schList(cri));
			
			pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(scheduleService.schListCount(cri));

			mav.addObject("pageMaker", pageMaker);
			
			return mav;
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/schRead")
	public ScheduleDTO ajaxSchRead(HttpServletRequest req, String s_num) throws UnsupportedEncodingException{
			
			return scheduleService.schRead(s_num);
	}
	
	@ResponseBody
	@RequestMapping(value="/schCal")
	public List<CalanderDTO> ajaxSchCal(HttpServletRequest req, SearchCriteria2 cri) throws UnsupportedEncodingException{
		
		
		List<CalanderDTO> schconlist = new ArrayList<CalanderDTO>();
		
		List<CalanderDTO> schcallist1 = scheduleService.schCal(cri, req);
		
		List<CalanderDTO> schcallist2 = scheduleService.schCal2();
		
		schconlist.addAll(schcallist1);
		schconlist.addAll(schcallist2);
		
		
		return schconlist;
	}
	
	
	@RequestMapping(value="/SCH_JOIN")
	public ModelAndView ajaxSchJoin(HttpServletRequest req, SearchCriteria2 cri)throws UnsupportedEncodingException{
		
		
		scheduleService.schJoin(cri);
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade ;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}
		else {
			inMav = "schedule/u_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}

		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.schList(cri));

		
		
		
		return mav;
		
	}
	
	
	@RequestMapping(value="/SCH_JOIN_CANCLE")
	public ModelAndView ajaxSchJoinCancle(HttpServletRequest req, SearchCriteria2 cri)throws UnsupportedEncodingException{
		
		
		scheduleService.schJoinCancle(cri);
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade ;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}
		else {
			inMav = "schedule/u_sch_main";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}

		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.schList(cri));

		
		
		
		return mav;
	}
	
	@RequestMapping(value="/SCH_POST", method = RequestMethod.GET)
	public ModelAndView schedulePostbyGet(){
		
		ModelAndView mav = new ModelAndView("schedule/a_sch_post");
		
		mav.addObject("srlist", scheduleService.srList());
		mav.addObject("pglist", scheduleService.pgList());
		
		
		return mav;
	}
	
	@RequestMapping(value="/SCH_POST", method = RequestMethod.POST)
	public ModelAndView schedulePostByPost(HttpServletRequest req, ScheduleDTO scheduleDTO, SearchCriteria2 cri){
		
		
		String gr1 = req.getParameter("gr1");
		String gr2 = req.getParameter("gr2");
		String gr3 = req.getParameter("gr3");
		String gr4 = req.getParameter("gr4");	
		
		String s_grade = gr1+gr2+gr3+gr4; 
		
		
		
		s_grade = s_grade.replaceAll("null", "");
		
		if("".equals(s_grade)||null==s_grade){
			s_grade = "1234";
		}
		
		scheduleDTO.setS_grade(s_grade);
		
		
		String s_location = "[ "+ req.getParameter("fselect")+ " ] [ " + req.getParameter("pg_type") + " ] [ " + req.getParameter("pg_name") + " ]"; 
		
		if("시설을 선택하세요.".equals(req.getParameter("pg_type"))){
			s_location = null;
		}
		
		scheduleDTO.setS_location(s_location);
		
		
		
		
		
		scheduleService.schPost(scheduleDTO);
		
		
		
		ModelAndView mav = new ModelAndView("schedule/a_sch_main");
		
		mav.addObject("schlist", scheduleService.schList(cri));
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(scheduleService.schListCount(cri));

		mav.addObject("pageMaker", pageMaker);
		
		
		
		return mav;
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/selectPg")
	public List<PgMemberDTO> ajaxSelectPgName(HttpServletRequest req, String pg_type) throws UnsupportedEncodingException{
		
	
		pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
		System.out.println(pg_type);
		
		List<PgMemberDTO> pglist = scheduleService.pgName(pg_type);	
		
		return pglist;
	}
	
	@ResponseBody
	@RequestMapping(value="/selectSr")
	public List<SrMemberDTO> ajaxSelectSrName(HttpServletRequest req, String sr_type) throws UnsupportedEncodingException{
		
		sr_type = URLDecoder.decode(req.getParameter("sr_type"), "UTF-8");
		List<SrMemberDTO> srlist = scheduleService.srName(sr_type);
		
		return srlist;
	}
	
	@RequestMapping(value = "/SCH_DELETE")
	public ModelAndView schDelete(String s_num, SearchCriteria2 cri){
		
		scheduleService.schDelete(s_num);
		
		String inMav = "schedule/a_sch_main";
		
		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.aschList(cri));
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(scheduleService.schListCount(cri));
		

		mav.addObject("pageMaker", pageMaker);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/SCH_UPDATE", method = RequestMethod.GET)
	public ModelAndView schUpdatebyGet(String s_num){
		
		String inMav = "schedule/a_sch_update";
		
		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schcon", scheduleService.schRead(s_num));
		
		return mav;
		
	}

	@RequestMapping(value = "/SCH_UPDATE", method = RequestMethod.POST)
	public ModelAndView schUpdatebyPost(HttpServletRequest req, SearchCriteria2 cri, ScheduleDTO scheduleDTO){
		
		
		String gr1 = req.getParameter("gr1");
		String gr2 = req.getParameter("gr2");
		String gr3 = req.getParameter("gr3");
		String gr4 = req.getParameter("gr4");	
		
		String s_grade = gr1+gr2+gr3+gr4; 
		
		
		
		s_grade = s_grade.replaceAll("null", "");
		
		if("".equals(s_grade)||null==s_grade){
			s_grade = "1234";
		}
		
		scheduleDTO.setS_grade(s_grade);
		
		
		String s_location = "[ "+ req.getParameter("fselect")+ " ] [ " + req.getParameter("pg_type") + " ] [ " + req.getParameter("pg_name") + " ]"; 
		
		if("시설을 선택하세요.".equals(req.getParameter("pg_type"))){
			s_location = null;
		}
		
		scheduleDTO.setS_location(s_location);
		
		if(null == req.getParameter("s_ispoint")){
			
			scheduleDTO.setS_ispoint("N");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		scheduleService.schUpdate(scheduleDTO);
		
		String inMav = "schedule/a_sch_main";
		
		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.aschList(cri));
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(scheduleService.schListCount(cri));
		

		mav.addObject("pageMaker", pageMaker);
		
		return mav;
		
	}
}
