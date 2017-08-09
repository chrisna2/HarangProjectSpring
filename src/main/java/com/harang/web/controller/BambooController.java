package com.harang.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.BbreplyDTO;
import com.harang.web.domain.LikeDTO;
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
	
	
	@RequestMapping(value = "/BB_UPDATE", method = RequestMethod.GET)
	public ModelAndView bambooUpdateByGET(HttpServletRequest req, String bb_num){
		
		HttpSession session = req.getSession();
		
		String inMav = null;

		if (null != session.getAttribute("member")) {

			inMav = "bamboo/u_bb_con_update";

		} else {
			
			inMav = "bamboo/a_bb_con_update";
		}
		
		ModelAndView mav = new ModelAndView(inMav);

		mav.addObject("bbcon", bambooService.bbCon(bb_num));
		
		return mav;
		
		
	}
	
	@RequestMapping(value = "/BB_UPDATE", method = RequestMethod.POST)
	public ModelAndView bambooUpdateByPOST(HttpServletRequest req, BambooDTO bambooDTO, SearchCriteria cri){
		
		
		bambooService.bbUpdate(bambooDTO);
		
		
		String bb_num = req.getParameter("bb_num");

		String goTo = null;
		
		HttpSession session = req.getSession();

		if (null != session.getAttribute("member")) {

			goTo = "bamboo/u_bb_content";

		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	
	
	@RequestMapping(value = "BR_POST", method = RequestMethod.POST)
	public ModelAndView bambooReplyPostByPOST(HttpServletRequest req,
			String bb_num, SearchCriteria cri, BbreplyDTO bbreplyDTO){
		
		bambooService.bbrpost(bbreplyDTO);
		
		String goTo = null;
		
		HttpSession session = req.getSession();

		if (null != session.getAttribute("member")) {

			goTo = "bamboo/u_bb_content";

		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	
	
	@RequestMapping(value = "BR_DELETE", method = RequestMethod.POST)
	public ModelAndView bambooReplyDeleteByPOST(HttpServletRequest req,
			String br_num, String bb_num, SearchCriteria cri){
		
		bambooService.bbrdelete(br_num);
		
		String goTo = null;
		
		HttpSession session = req.getSession();

		if (null != session.getAttribute("member")) {

			goTo = "bamboo/u_bb_content";

		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	@RequestMapping(value = "BB_LIKE", method = RequestMethod.POST)
	public ModelAndView bambooLikeByPOST(HttpServletRequest req,
			LikeDTO likeDTO, String bb_num, SearchCriteria cri){
		
		bambooService.bbLike(likeDTO);
		
		String goTo = null;
		
		HttpSession session = req.getSession();
		
		if (null != session.getAttribute("member")) {
			
			goTo = "bamboo/u_bb_content";
			
		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	@RequestMapping(value = "BB_DLIKE", method = RequestMethod.POST)
	public ModelAndView bambooDLikeByPOST(HttpServletRequest req,
			LikeDTO likeDTO, String bb_num, SearchCriteria cri){
		
		bambooService.bbDLike(likeDTO);
		
		String goTo = null;
		
		HttpSession session = req.getSession();
		
		if (null != session.getAttribute("member")) {
			
			goTo = "bamboo/u_bb_content";
			
		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	@RequestMapping(value = "BB_LIKE_CANCLE", method = RequestMethod.POST)
	public ModelAndView bambooLikeCancleByPOST(HttpServletRequest req,
			LikeDTO likeDTO, String bb_num, SearchCriteria cri){
		
		bambooService.bbLikeCancle(likeDTO);
		
		String goTo = null;
		
		HttpSession session = req.getSession();
		
		if (null != session.getAttribute("member")) {
			
			goTo = "bamboo/u_bb_content";
			
		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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
	@RequestMapping(value = "BB_DLIKE_Cancle", method = RequestMethod.POST)
	public ModelAndView bambooDLikeCancleByPOST(HttpServletRequest req,
			LikeDTO likeDTO, String bb_num, SearchCriteria cri){
		
		bambooService.bbDLikeCancle(likeDTO);
		
		String goTo = null;
		
		HttpSession session = req.getSession();
		
		if (null != session.getAttribute("member")) {
			
			goTo = "bamboo/u_bb_content";
			
		} else {
			
			goTo = "bamboo/a_bb_content";
		}
		
		
		ModelAndView mav = new ModelAndView(goTo);
		
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

}
