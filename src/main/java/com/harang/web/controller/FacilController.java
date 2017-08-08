package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harang.web.domain.MemberDTO;

import com.harang.web.service.FacilService;
import com.harang.web.utill.LoginBean;

@Controller
@RequestMapping("/facil")
public class FacilController {

	@Autowired
	private FacilService facilService;

	// 사용자 예약 내역 확인 페이지(Main) > 내역을 불러온다.
	@RequestMapping(value = "/FacilMain", method = RequestMethod.GET)
	public ModelAndView mainLoadList(HttpServletRequest req) {

		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();

		// 리퀘스트로 접속자 정보를 가져옴
		MemberDTO member = login.getLoginInfo(req);
		String m_id = member.getM_id();

		ModelAndView mav = new ModelAndView("/facil/facilities_main");

		mav.addObject("pgmlist", facilService.loadPgReserList(m_id));
		mav.addObject("srmlist", facilService.loadSrReserList(m_id));

		return mav;
	}

	// 사용자 예약 내역 확인 페이지(Main) > 선택된 예약 목록을 삭제한다.
	@RequestMapping(value = "/FacilMainDel", method = RequestMethod.POST)
	public String deleteReser(HttpServletRequest req, RedirectAttributes rttr) {
		String facilType = req.getParameter("resernum");

		System.out.println(facilType);

		// 스터디룸의 경우 예약번호 첫글자 's'로 판단해서 분기를 탐.
		if (facilType.startsWith("s")) {
			facilService.deleteReserSr(facilType);
		}
		// 운동장의 경우 예약번호 첫글자 'p'로 판단해서 분기를 탐.
		else if (facilType.startsWith("p")) {
			facilService.deleteReserPg(facilType);
		}

		// redirect시 데이터 전달을 위해 RedirectAttributes를 사용.
		rttr.addFlashAttribute("result", "true");
		
		// 삭제후 mainLoadList 재호출. 즉 리다이렉트.
		return "redirect:/facil/FacilMain";
	}

	// 운영자 예약 관리.
	@RequestMapping(value = "/AFacilManager", method = RequestMethod.GET)
	public ModelAndView aManagerLoadList(HttpServletRequest req) {

		ModelAndView mav = new ModelAndView("/facil/a_facilities_manager");
		
		// 두가지 값을 가져온다.
		mav.addObject("pglist", facilService.loadPgReserListAll());
		mav.addObject("srlist", facilService.loadSrReserListAll());
		return mav;
	}
	
	@RequestMapping(value = "/AFacilManagerDel", method = RequestMethod.POST)
	public String aManagerDelete(HttpServletRequest req, RedirectAttributes rttr){
		String facilType = req.getParameter("resernum");
		
		// .jsp 에서 취소사유 를 가지고 온다. 
		// 메세지와의 연동 기능이 없스니다. 추후 추가하세요.
		//************************************************************
		String cancel = req.getParameter("cancelMsg");
		
		// 디버그용.
		System.out.println(facilType);
		System.out.println(cancel);
		
		if (facilType.startsWith("s")) {
			facilService.deleteReserSr(facilType);
		}
		// 운동장의 경우 예약번호 첫글자 'p'로 판단해서 분기를 탐.
		else if (facilType.startsWith("p")) {
			facilService.deleteReserPg(facilType);
		}
		
		rttr.addFlashAttribute("result", "true");
		return "redirect:/facil/AFacilManager";
	}

}