package com.harang.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.ScheduleService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	private PageMaker pageMaker;

	
	@RequestMapping(value = "/SCH_LIST", method = RequestMethod.GET)
	public ModelAndView scheduleListbyGet(HttpServletRequest req, SearchCriteria cri) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade = 0;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_list";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}
		else {
			inMav = "schedule/u_sch_list";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}

		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.schList(m_grade));

		
		
		
		return mav;
	}
	@RequestMapping(value = "/SCH_LIST", method = RequestMethod.POST)
	public ModelAndView scheduleListbyPost(HttpServletRequest req, SearchCriteria cri) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		int m_grade = 0;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "schedule/a_sch_list";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}
		else {
			inMav = "schedule/u_sch_list";
			m_id = mdto.getM_id();
			m_grade = mdto.getM_grade();
		}

		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("schlist", scheduleService.schList(m_grade));

		
		
		
		return mav;
	}
	

}
