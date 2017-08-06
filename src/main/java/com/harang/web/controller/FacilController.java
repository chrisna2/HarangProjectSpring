package com.harang.web.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;

import com.harang.web.service.FacilService;
import com.harang.web.utill.LoginBean;


@Controller
@RequestMapping("/facil")
public class FacilController {

	@Autowired
	private FacilService facilService;
	
	@RequestMapping(value="/FacilMain", method=RequestMethod.GET)
	public ModelAndView mainloadList(HttpServletRequest req){
		
		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();
		
		// 리퀘스트로 접속자 정보 가져
		MemberDTO member = login.getLoginInfo(req);
		String m_id = member.getM_id();
		
		ModelAndView mav = new ModelAndView("/facil/facilities_main");
		
		mav.addObject("pgmlist",facilService.loadPgReserList(m_id));
		mav.addObject("srmlist",facilService.loadSrReserList(m_id));
		
		return mav;
	}
	
	@RequestMapping(value="/FacilMainDel", method=RequestMethod.POST)
	public ModelAndView deleteReser(HttpServletRequest req){
		String facilType = req.getParameter("resernum");
		
		System.out.println(facilType);
		
		if(facilType.startsWith("s")){
			facilService.deleteReserSr(facilType);
		}
		else if(facilType.startsWith("p")){
			facilService.deleteReserPg(facilType);
		}
		
		ModelAndView mav = new ModelAndView("/facil/facilities_main");
		return mav;
	}
}