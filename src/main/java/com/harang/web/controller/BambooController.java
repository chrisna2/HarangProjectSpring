package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.BambooDTO;
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

	@RequestMapping(value = "/BB_LIST", method = RequestMethod.GET)
	public ModelAndView bambooListbyGet(SearchCriteria cri) {

		ModelAndView mav = new ModelAndView("bamboo/u_bb_list");

		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);

		return mav;

	}

	@RequestMapping(value = "/BB_LIST", method = RequestMethod.POST)
	public ModelAndView bambooListbyPost(SearchCriteria cri) {

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
	public ModelAndView bambooContentbyGet(HttpServletRequest req, String bb_num, SearchCriteria cri) {

		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		String inMav = null;

		if (null != session.getAttribute("member")) {

			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			inMav = "bamboo/u_bb_content";

		} else {
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			inMav = "bamboo/a_bb_content";
		}

		ModelAndView mav = new ModelAndView(inMav);

		System.out.println("BambooController에서 Test 시작");
		System.out.println("접속한 사람의 아이디 : " + m_id);
		System.out.println("bb_num : " + bb_num);
		System.out.println("BambooController에서 Test 종료");

		/*if (session.getAttribute(bb_num) != "read") {

			bambooService.bbUpdateCnt(bb_num);
			session.setAttribute(bb_num, "read");
		}*/

		bambooService.bbUpdateCnt(bb_num);
		
		mav.addObject("bbcon", bambooService.bbCon(bb_num));
		mav.addObject("bblcnt", bambooService.bbLCnt(bb_num));
		mav.addObject("bbdlcnt", bambooService.bbDLCnt(bb_num));
		mav.addObject("brlist", bambooService.bbRList(bb_num));
		
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);
		
		
		
		

		return mav;
	}

	@RequestMapping(value = "/BB_CON", method = RequestMethod.POST)
	public ModelAndView bambooContentbyPost(HttpServletRequest req, String bb_num, SearchCriteria cri) {

		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		String inMav = null;

		if (null != session.getAttribute("member")) {

			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			inMav = "bamboo/u_bb_content";

		} else {
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			inMav = "bamboo/a_bb_content";
		}

		ModelAndView mav = new ModelAndView(inMav);

		System.out.println("BambooController에서 Test 시작");
		System.out.println("접속한 사람의 아이디 : " + m_id);
		System.out.println("bb_num : " + bb_num);
		System.out.println("BambooController에서 Test 종료");

		/*if (session.getAttribute(bb_num) != "read") {

			bambooService.bbUpdateCnt(bb_num);
			session.setAttribute(bb_num, "read");
		}*/
		
		bambooService.bbUpdateCnt(bb_num);

		mav.addObject("bbcon", bambooService.bbCon(bb_num));
		mav.addObject("bblcnt", bambooService.bbLCnt(bb_num));
		mav.addObject("bbdlcnt", bambooService.bbDLCnt(bb_num));
		mav.addObject("bbrlist", bambooService.bbRList(bb_num));	
		
		
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);
		
		
		
		
		return mav;
	}

	@RequestMapping(value = "/BB_POST", method = RequestMethod.GET)
	public String bambooPostbyGET(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		String inMav = null;

		if (null != session.getAttribute("member")) {

			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			inMav = "bamboo/u_bb_post";

		} else {
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			inMav = "bamboo/a_bb_post";
		}
		

		return inMav;

	}
	
	@RequestMapping(value = "/BB_POST", method = RequestMethod.POST)
	public ModelAndView bambooPostbyPOST(HttpServletRequest req, BambooDTO bambooDTO, SearchCriteria cri) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		String goTo = null;

		if (null != session.getAttribute("member")) {

			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			goTo = "bamboo/u_bb_list";

		} else {
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			goTo = "bamboo/a_bb_list";
		}
		

		
		bambooService.bbPost(bambooDTO);

		ModelAndView mav = new ModelAndView(goTo);
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;

	}
	
	@RequestMapping(value = "/BB_DEL", method = RequestMethod.POST)
	public ModelAndView bambooDeletebyPOST(HttpServletRequest req, String bb_num, SearchCriteria cri){
		
		bambooService.bbDelete(bb_num);
		
		HttpSession session = req.getSession();
		MemberDTO mdto;
		String m_id = null;
		String goTo = null;

		if (null != session.getAttribute("member")) {

			mdto = (MemberDTO) session.getAttribute("member");
			m_id = mdto.getM_id();
			goTo = "bamboo/u_bb_list";

		} else {
			mdto = (MemberDTO) session.getAttribute("admin");
			m_id = mdto.getM_id();
			goTo = "bamboo/a_bb_list";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);
		
		
		return mav;
		
	}
	

}
