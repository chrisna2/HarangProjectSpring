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
import com.harang.web.service.BambooService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/bamboo")
public class BambooController {

	@Autowired
	private BambooService bambooService;
	
	private PageMaker pageMaker;
	
	
	@RequestMapping(value="/BB_LIST",method = RequestMethod.GET)
	public ModelAndView bambooListGet(SearchCriteria cri){
		
		ModelAndView mav = new ModelAndView("bamboo/u_bb_list");
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));
		
		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
		
	}
	@RequestMapping(value="/BB_LIST",method = RequestMethod.POST)
	public ModelAndView bambooListPost(SearchCriteria cri){
		
		ModelAndView mav = new ModelAndView("bamboo/u_bb_list");
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));
		
		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
		
	}
	
	@RequestMapping(value = "/BB_CON", method = RequestMethod.GET)
	public ModelAndView bambooContentGet(HttpServletRequest req, String bb_num){
		
		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		ModelAndView mav;
		
		
		if(null != session.getAttribute("member")){
			
			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			mav = new ModelAndView("bamboo/u_bb_con");
		}
		else{
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			mav = new ModelAndView("bamboo/a_bb_con");
		}
		
		System.out.println("BambooController에서 Test 시작");
		System.out.println("접속한 사람의 아이디 : " + m_id);
		System.out.println("bb_num : " + bb_num);
		System.out.println("BambooController에서 Test 종료");
		
		
		
		
		return null;
	}
	
	
	
	
	
}
