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
import com.harang.web.domain.DlikeDTO;
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
	public ModelAndView bambooListbyGet(HttpServletRequest req, SearchCriteria cri) {
		
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_list";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_list";
			m_id = mdto.getM_id();
		}

		ModelAndView mav = new ModelAndView(inMav);

		mav.addObject("bblist", bambooService.bbList(cri));
		mav.addObject("bbNList", bambooService.bbNList());

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(bambooService.bbListCount(cri));

		mav.addObject("pageMaker", pageMaker);

		return mav;

	}

	@RequestMapping(value = "/BB_LIST", method = RequestMethod.POST)
	public ModelAndView bambooListbyPost(HttpServletRequest req, SearchCriteria cri) {

		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_list";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_list";
			m_id = mdto.getM_id();
		}


		ModelAndView mav = new ModelAndView(inMav);

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
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}

		

		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}
		
		ModelAndView mav = new ModelAndView(inMav);

		System.out.println("BambooController에서 Test 시작");
		System.out.println(inMav);
		System.out.println(mdto.getM_dept());
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
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}

		ModelAndView mav = new ModelAndView(inMav);

		System.out.println("BambooController에서 Test 시작");
		System.out.println(inMav);
		System.out.println(mdto.getM_dept());
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
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_post";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_post";
			m_id = mdto.getM_id();
		}

		

		return inMav;

	}
	
	@RequestMapping(value = "/BB_POST", method = RequestMethod.POST)
	public ModelAndView bambooPostbyPOST(HttpServletRequest req, BambooDTO bambooDTO, SearchCriteria cri) {
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_list";
			m_id = mdto.getM_id();
			
			bambooService.abbPost(bambooDTO);
		}
		else {
			inMav = "bamboo/u_bb_list";
			m_id = mdto.getM_id();
			
			bambooService.bbPost(bambooDTO);
		}

		

		
		

		ModelAndView mav = new ModelAndView(inMav);
		
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
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_list";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_list";
			m_id = mdto.getM_id();
		}
		
		
		ModelAndView mav = new ModelAndView(inMav);
		
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
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_con_update";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_con_update";
			m_id = mdto.getM_id();
		}
		
		
		
		ModelAndView mav = new ModelAndView(inMav);

		mav.addObject("bbcon", bambooService.bbCon(bb_num));
		
		return mav;
		
		
	}
	
	@RequestMapping(value = "/BB_UPDATE", method = RequestMethod.POST)
	public ModelAndView bambooUpdateByPOST(HttpServletRequest req, BambooDTO bambooDTO, SearchCriteria cri){
		
		
		bambooService.bbUpdate(bambooDTO);
		
		
		String bb_num = req.getParameter("bb_num");

		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		
		
		ModelAndView mav = new ModelAndView(inMav);
		
		mav.addObject("bbcon", bambooService.bbCon(bb_num));
		mav.addObject("bblcnt", bambooService.bbLCnt(bb_num));
		mav.addObject("bbdlcnt", bambooService.bbDLCnt(bb_num));
		mav.addObject("brlist", bambooService.bbRList(bb_num));	
		
		System.out.println(bb_num);
		
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
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		
		ModelAndView mav = new ModelAndView(inMav);
		
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
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		
		ModelAndView mav = new ModelAndView(inMav);
		
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
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}

		ModelAndView mav = new ModelAndView(inMav);


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
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}

		ModelAndView mav = new ModelAndView(inMav);


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
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}

		ModelAndView mav = new ModelAndView(inMav);


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
	@RequestMapping(value = "BB_DLIKE_CANCLE", method = RequestMethod.POST)
	public ModelAndView bambooDLikeCancleByPOST(HttpServletRequest req,
			LikeDTO likeDTO, String bb_num, SearchCriteria cri){
		
		bambooService.bbDLikeCancle(likeDTO);
		
		HttpSession session = req.getSession();
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		String m_id = null;
		String inMav = null;
		
		if(mdto == null) {
			mdto = (MemberDTO)session.getAttribute("admin");
		}
		
		if(mdto.getM_dept().equals("관리자")) {
			inMav = "bamboo/a_bb_content";
			m_id = mdto.getM_id();
		}
		else {
			inMav = "bamboo/u_bb_content";
			m_id = mdto.getM_id();
		}
		
		List<LikeDTO> bblList = (List)bambooService.bbLCnt(bb_num);
		List<DlikeDTO> bbdlList = (List)bambooService.bbDLCnt(bb_num);
		
		for(int i =0 ; i<bblList.size(); i++){
			
			if (m_id.equals(bblList.get(i).getM_id())){
				
				req.setAttribute("islike", "y");
			}
			
		}
		for(int i = 0; i<bbdlList.size(); i++){
			
			if (m_id.equals(bbdlList.get(i).getM_id())){
				
				req.setAttribute("isdlike", "y");
			}
		}

		ModelAndView mav = new ModelAndView(inMav);


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
