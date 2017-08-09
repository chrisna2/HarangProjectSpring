package com.harang.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
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
	
	// 관리자 예약 취소(삭제) 
	@RequestMapping(value = "/AFacilManagerDel", method = RequestMethod.POST)
	public String aManagerDelete(HttpServletRequest req, RedirectAttributes rttr){
		String facilType = req.getParameter("resernum");
		
		// .jsp 에서 취소사유 를 가지고 온다. 
		// 메세지와의 연동 기능이 없스니다. 추후 추가하세요.
		//************************************************************
		String cancel = req.getParameter("cancelMsg");
				
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
	
	// 관리자 운동장 / 일정관리
	// ajax 사용을 위해서 ResponseBody 붙여줌.
	@ResponseBody
	@RequestMapping(value="/AFacilPG")
	public ModelAndView aPgscheduleLoadList(HttpServletRequest req) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView("/facil/a_facilities_pg_schedule");
		
		// 운동장 시설 리스트.
		mav.addObject("pglist",facilService.schedulePgList());
		
		// 일정 리스트.
		mav.addObject("sclist",facilService.scheduleToPg());
		
		// Ajax를 위해서.
		mav.addObject("ajaxtypelist",facilService.schPgTypeAjax());
		
		// 이동.
		return mav;
	}
	
	// pg_type을 바탕으로 pg_name을 찾는 AJAX
	// ResoponseBody는 여기에 붙여도 되고 retrun 값 앞에도 붙일수 있다.
	@ResponseBody
	@RequestMapping(value="/AFacilPgNameAjax")
	public List<PlaygroundDTO> ajaxPgName(HttpServletRequest req) throws UnsupportedEncodingException{
			String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
			System.out.println("ajax 시작.");
			List<PlaygroundDTO> list = facilService.schPgNameAjax(pg_type);
			
			/*
			// 디버깅용.
			for(int i=0; i<list.size() ;i++){
				System.out.println(list.get(i).getPg_name());
			}
			*/
			
			return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/AFacilPgNumAjax")
	public List<PlaygroundDTO> ajaxPgLoadNum(HttpServletRequest req) throws UnsupportedEncodingException{
			String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
			String pg_name = URLDecoder.decode(req.getParameter("pg_name"), "UTF-8");
			
			PlaygroundDTO pgdto = new PlaygroundDTO();
			pgdto.setPg_type(pg_type);
			pgdto.setPg_name(pg_name);
			
			List<PlaygroundDTO> list = facilService.schPgNumAjax(pgdto);
			
			return list;
	}
		
	// 관리자 운동장  / 일정관리 / 일정취소
	@RequestMapping(value = "/AFacilPgDel", method = RequestMethod.POST)
	public String aPgscheduleLoadListDel(HttpServletRequest req, RedirectAttributes rttr){
		String pgm_num = req.getParameter("pgm_num");
		
		// 일정 / 삭제
		facilService.deleteReserPg(pgm_num);
		
		rttr.addFlashAttribute("result", "true");
		
		return "redirect:/facil/AFacilPG";
	}
	
	@RequestMapping(value="/AFacilPgAdd")
	public String aFacilPgAdd(HttpServletRequest req, RedirectAttributes rttr){
		
		String pgm_date = req.getParameter("addpgm_date");
		
		PgMemberDTO pgmdto = new PgMemberDTO();
		
		// 학사일정의 경우 운영자[admin03/시설관리자]로 입력된다.
		pgmdto.setM_id("admin03");
		pgmdto.setPgm_date(req.getParameter("addpgm_date"));
		
		// 학사일정으로 인한 예약의 경우 하루 전체를 빌린다.
		pgmdto.setPgm_timecode("A1111111111111");
		pgmdto.setPgm_issue(req.getParameter("addpgm_issue"));
		pgmdto.setPg_num(req.getParameter("addpg_num"));
		
		facilService.schPgAdd(pgmdto);
		
		// 일정 추가 완료. return값이 없음.
		rttr.addFlashAttribute("result", "addok");
		
		return "redirect:/facil/AFacilPG";
	}

}