package com.harang.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harang.web.HomeController;
import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;
import com.harang.web.service.FacilService;
import com.harang.web.service.MyPageService;
import com.harang.web.service.PointService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/facil")
public class FacilController {

	static final String FACIL_ADMIN = "admin03";
	
	@Autowired
	private FacilService facilService;
	
	@Autowired
	private PointService pointService;
	
	private static final Logger logger = LoggerFactory.getLogger(FacilController.class);
	
	private PageMaker pageMakerPg;
	private PageMaker pageMakerSr;
	

	// 사용자 메인 / 전체 불러오기
	// 사용자 예약 내역 확인 페이지(Main) > 내역을 불러온다.
	@RequestMapping(value = "/FacilMain", method = RequestMethod.GET)
	public ModelAndView mainLoadList(SearchCriteria cri, HttpServletRequest req) {

		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();

		// 리퀘스트로 접속자 정보를 가져옴
		MemberDTO member = login.getLoginInfo(req);
		String m_id = member.getM_id();
		
		cri.setM_id(m_id);

    	pageMakerPg = new PageMaker();
    	pageMakerPg.setCri(cri);
    	pageMakerPg.setTotalCount(facilService.reserPgListCount(cri));
    	
    	pageMakerSr = new PageMaker();
    	pageMakerSr.setCri(cri);
    	pageMakerSr.setTotalCount(facilService.reserSrListCount(cri));
    	    	
		ModelAndView mav = new ModelAndView("/facil/facilities_main");

		mav.addObject("pgmlist", facilService.loadPgReserList(cri));
		mav.addObject("pageMakerPg",pageMakerPg);
		mav.addObject("srmlist", facilService.loadSrReserList(cri));
		mav.addObject("pageMakerSr",pageMakerSr);

		return mav;
	}
	
	// 검색을 위한 Post page
	@RequestMapping(value = "/FacilMain", method = RequestMethod.POST)
	public ModelAndView mainSerchLoadList(SearchCriteria cri, HttpServletRequest req) {

		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();

		// 리퀘스트로 접속자 정보를 가져옴
		MemberDTO member = login.getLoginInfo(req);
		
		String m_id = member.getM_id();
		
		// cri에 id값 입력.
		cri.setM_id(m_id);
		
		System.out.println(cri.getKeyfield());
		System.out.println(cri.getKeyword());
		
		pageMakerPg = new PageMaker();
		
		ModelAndView mav = new ModelAndView("/facil/facilities_main");
		
		mav.addObject("pgmlist", facilService.loadPgReserList(cri));
		mav.addObject("srmlist", facilService.loadSrReserList(cri));
		

		return mav;
	}

	// 사용자 예약 내역 확인 페이지(Main) > 선택된 예약 목록을 삭제한다.
	@RequestMapping(value = "/FacilMainDel", method = RequestMethod.POST)
	public String deleteReser(HttpServletRequest req, RedirectAttributes rttr) {
		String facilSwitch = req.getParameter("facilSwitch");
		String resernum = req.getParameter("resernum"); 
		Long backpoint = Long.parseLong(req.getParameter("backpoint"));
		Long adminPoint = pointService.pointInfo(FACIL_ADMIN);
		MemberDTO member = (MemberDTO)req.getSession().getAttribute("member");
		
		String check = "";
		
		
		// 스터디룸의 경우
		if (facilSwitch.startsWith("s")) {
			check = pointService.recordPointTrade("[스터디룸 예약 취소] "+backpoint+"포인트가 환불되었습니다.", 
												  adminPoint, 
												  backpoint, 
												  FACIL_ADMIN,
												  member.getM_id());
			if("complete".equals(check)) {
				facilService.deleteReserSr(resernum);
				// redirect시 데이터 전달을 위해 RedirectAttributes를 사용.
				rttr.addFlashAttribute("result", "true");
			}
			else {
				// redirect시 데이터 전달을 위해 RedirectAttributes를 사용.
				rttr.addFlashAttribute("result", "overpoint");
			}
		}
		// 운동장의 경우
		else if (facilSwitch.startsWith("p")) {
			check = pointService.recordPointTrade("[운동시설 예약 취소] "+backpoint+"포인트가 환불되었습니다.", 
												  adminPoint, 
												  backpoint, 
												  FACIL_ADMIN,
												  member.getM_id());
			
			if("complete".equals(check)) {
				facilService.deleteReserPg(resernum);
				// redirect시 데이터 전달을 위해 RedirectAttributes를 사용.
				rttr.addFlashAttribute("result", "true");
			}
			else {
				// redirect시 데이터 전달을 위해 RedirectAttributes를 사용.
				rttr.addFlashAttribute("result", "overpoint");
			}
		}
		// 삭제후 mainLoadList 재호출. 즉 리다이렉트.
		return "redirect:/facil/FacilMain";
	}

	// 운영자 예약 관리.
	@RequestMapping(value = "/AFacilManager", method = RequestMethod.GET)
	public ModelAndView aManagerLoadList(HttpServletRequest req, SearchCriteria cri, String n) {

		ModelAndView mav = new ModelAndView("/facil/a_facilities_manager");
		
		SearchCriteria _cri = new SearchCriteria();
		
		cri.setPerPageNum(5);
		_cri.setPerPageNum(5);
		
		aManagerLoadListPgPaging(_cri, mav);
		aManagerLoadListSrPaging(_cri, mav);
		
		if( n != null){
			switch(n){
				case "1":
					aManagerLoadListPgPaging(cri, mav);
					break;
				case "2":	
					aManagerLoadListSrPaging(cri, mav);
					break;
			}
		}
	
		return mav;
	}
	
	// 운영자 예약 관리 / 검색을 위한 POST 방식
	@RequestMapping(value = "/AFacilManager", method = RequestMethod.POST)
	public ModelAndView aManagerSearchLoadList(HttpServletRequest req, SearchCriteria cri, String n) {
		ModelAndView mav = new ModelAndView("/facil/a_facilities_manager");
		
		SearchCriteria _cri = new SearchCriteria();
		
		cri.setPerPageNum(5);
		_cri.setPerPageNum(5);
		
		aManagerLoadListPgPaging(_cri, mav);
		aManagerLoadListSrPaging(_cri, mav);
		
		if( n != null){
			switch(n){
				case "1":
					aManagerLoadListPgPaging(cri, mav);
					break;
				case "2":	
					aManagerLoadListSrPaging(cri, mav);
					break;
			}
		}
	
		return mav;
	}
	
	
	
	// 
	public void aManagerLoadListPgPaging(SearchCriteria cri, ModelAndView mav){

		
		pageMakerPg = new PageMaker();
		pageMakerPg.setCri(cri);
		pageMakerPg.setTotalCount(facilService.reserPgListAllCount(cri));
		mav.addObject("pageMaker1",pageMakerPg);
		mav.addObject("pglist", facilService.loadPgReserListAll(cri));

	}
	
	public void aManagerLoadListSrPaging(SearchCriteria cri, ModelAndView mav){

		pageMakerSr = new PageMaker();
		pageMakerSr.setCri(cri);
		pageMakerSr.setTotalCount(facilService.reserSrListAllCount(cri));
		mav.addObject("pageMaker2",pageMakerSr);
		mav.addObject("srlist", facilService.loadSrReserListAll(cri));

	}
	
	// 관리자 예약 취소(삭제)
	@RequestMapping(value = "/AFacilManagerDel", method = RequestMethod.POST)
	public String aManagerDelete(HttpServletRequest req, RedirectAttributes rttr) {
		String facilType = req.getParameter("resernum");

		// .jsp 에서 취소사유 를 가지고 온다.
		// 메세지와의 연동 기능이 없스니다. 추후 추가하세요.
		// ************************************************************
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

	// ******************************* 시설 추가
	// ************************************
	// 시설 추가,수정,삭제 / 메인
	@RequestMapping(value = "/AFacilAddDel", method = RequestMethod.GET)
	public ModelAndView aFacilAddDelLoadList(HttpServletRequest req, SearchCriteria cri) {
		ModelAndView mav = new ModelAndView("/facil/a_facilities_adddel");
		mav.addObject("srlist", facilService.loadSrList(cri));
		mav.addObject("pglist", facilService.loadPgList(cri));

		return mav;
	}
	
	// 검색을 위한 POST방식 접
	@RequestMapping(value = "/AFacilAddDel", method = RequestMethod.POST)
	public ModelAndView aFacilAddDelSearchLoadList(HttpServletRequest req, SearchCriteria cri) {
		
		System.out.println(cri.getKeyfield());
		System.out.println(cri.getKeyword());
		
		ModelAndView mav = new ModelAndView("/facil/a_facilities_adddel");
		mav.addObject("srlist", facilService.loadSrList(cri));
		mav.addObject("pglist", facilService.loadPgList(cri));

		return mav;
	}

	// 시설 추가,수정,삭제 / 수정
	@RequestMapping(value = "/AFacilAddDel_mod")
	public String aFacilAddDel_modify(HttpServletRequest req, RedirectAttributes rttr) {
		// 시설 종류 판단.
		String choiceType = req.getParameter("modi_num");
		String type = req.getParameter("endmodi_type");
		String name = req.getParameter("endmodi_name");
		String content = req.getParameter("endmodi_content");

		PlaygroundDTO pgdto = new PlaygroundDTO();
		StudyroomDTO srdto = new StudyroomDTO();

		System.out.println(choiceType);

		// 스터디룸의 경우 시설번호 첫번째 's'로 판단.
		if (choiceType.startsWith("s")) {
			srdto.setSr_num(choiceType);
			srdto.setSr_type(type);
			srdto.setSr_name(name);
			srdto.setSr_content(content);

			facilService.facilSrmodi(srdto);
		}

		// 운동장의 경우 시설번호 첫번째 'p'로 판단.
		else if (choiceType.startsWith("p")) {
			pgdto.setPg_num(choiceType);
			pgdto.setPg_type(type);
			pgdto.setPg_name(name);
			pgdto.setPg_content(content);

			facilService.facilPgmodi(pgdto);
		}

		// 수정 성공 메세지
		rttr.addFlashAttribute("result", "mod");
		return "redirect:/facil/AFacilAddDel";
	}

	// 시설 추가,수정,삭제 / 삭제
	@RequestMapping(value = "/AFacilAddDel_del")
	public String aFacilAddDel_del(HttpServletRequest req, RedirectAttributes rttr) {
		// 시설 종류 판단.
		String type = req.getParameter("del_num");
		System.out.println(type);

		// 스터디룸의 경우 시설번호 첫번째 's'로 판단.
		if (type.startsWith("s")) {
			facilService.facilSrDel(type);
		}

		// 운동장의 경우 시설번호 첫번째 'p'로 판단.
		else if (type.startsWith("p")) {
			facilService.facilPgDel(type);
		}

		// 삭제성공 메세지
		rttr.addFlashAttribute("result", "del");
		return "redirect:/facil/AFacilAddDel";
	}

	// 시설 추가,수정,삭제 / 추가
	@RequestMapping(value = "/AFacilAddDel_add")
	public String aFacilAddDel_add(HttpServletRequest req, RedirectAttributes rttr) {
		// 시설 종류 판단.
		String choiceType = req.getParameter("selectfacil");
		String facilType = req.getParameter("facil_type");
		String name = req.getParameter("facil_name");
		String content = req.getParameter("facil_content");

		System.out.println(choiceType);

		PlaygroundDTO pgdto = new PlaygroundDTO();
		StudyroomDTO srdto = new StudyroomDTO();

		// 스터디룸의 경우 시설번호 첫번째 's'로 판단.
		if (choiceType.equals("스터디룸")) {
			System.out.println("스터디룸 접근");
			System.out.println(facilType + ":" + name + ":" + content);

			srdto.setSr_type(facilType);
			srdto.setSr_name(name);
			srdto.setSr_content(content);

			// 입력.
			facilService.facilSrAdd(srdto);
		}

		// 운동장의 경우 시설번호 첫번째 'p'로 판단.
		else if (choiceType.equals("운동장")) {
			System.out.println("운동장 접근");
			System.out.println(facilType + ":" + name + ":" + content);

			pgdto.setPg_type(facilType);
			pgdto.setPg_name(name);
			pgdto.setPg_content(content);

			// 입력.
			facilService.facilPgAdd(pgdto);
		}

		// 추가성공 메세지
		rttr.addFlashAttribute("result", "add");
		return "redirect:/facil/AFacilAddDel";
	}

	// ******************************* 스터디룸 일정관리
	// ************************************
	// 관리자 운동장 / 일정관리
	// ajax 사용을 위해서 ResponseBody 붙여줌.
	@ResponseBody
	@RequestMapping(value = "/AFacilPG")
	public ModelAndView aPgscheduleLoadList(HttpServletRequest req) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/facil/a_facilities_pg_schedule");

		// 운동장 시설 리스트.
		mav.addObject("pglist", facilService.schedulePgList());

		// 일정 리스트.
		mav.addObject("sclist", facilService.scheduleToPg());

		// Ajax를 위해서.
		mav.addObject("ajaxtypelist", facilService.schPgTypeAjax());

		// 이동.
		return mav;
	}

	// pg_type을 바탕으로 pg_name을 찾는 AJAX
	// ResoponseBody는 여기에 붙여도 되고 retrun 값 앞에도 붙일수 있다.
	@ResponseBody
	@RequestMapping(value = "/AFacilPgNameAjax")
	public List<PlaygroundDTO> ajaxPgName(HttpServletRequest req) throws UnsupportedEncodingException {
		String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
		System.out.println("ajax 시작.");
		List<PlaygroundDTO> list = facilService.schPgNameAjax(pg_type);

		/*
		 * // 디버깅용. for(int i=0; i<list.size() ;i++){
		 * System.out.println(list.get(i).getPg_name()); }
		 */

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/AFacilPgNumAjax")
	public List<PlaygroundDTO> ajaxPgLoadNum(HttpServletRequest req) throws UnsupportedEncodingException {
		String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
		String pg_name = URLDecoder.decode(req.getParameter("pg_name"), "UTF-8");

		PlaygroundDTO pgdto = new PlaygroundDTO();
		pgdto.setPg_type(pg_type);
		pgdto.setPg_name(pg_name);

		List<PlaygroundDTO> list = facilService.schPgNumAjax(pgdto);

		return list;
	}

	// 관리자 운동장 / 일정관리 / 일정취소
	@RequestMapping(value = "/AFacilPgDel", method = RequestMethod.POST)
	public String aPgscheduleLoadListDel(HttpServletRequest req, RedirectAttributes rttr) {
		String pgm_num = req.getParameter("pgm_num");

		// 일정 / 삭제
		facilService.deleteReserPg(pgm_num);

		rttr.addFlashAttribute("result", "true");

		return "redirect:/facil/AFacilPG";
	}

	@RequestMapping(value = "/AFacilPgAdd")
	public String aFacilPgAdd(HttpServletRequest req, RedirectAttributes rttr) {
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

	// ******************************* 스터디룸 일정관리
	// ************************************
	// 관리자 스터디룸 / 일정관리
	// ajax 사용을 위해서 ResponseBody 붙여줌.
	@ResponseBody
	@RequestMapping(value = "/AFacilSR")
	public ModelAndView aSrscheduleLoadList(HttpServletRequest req) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("/facil/a_facilities_sr_schedule");

		// 운동장 시설 리스트.
		mav.addObject("srlist", facilService.scheduleSrList());

		// 일정 리스트.
		mav.addObject("sclist", facilService.scheduleToSr());

		// Ajax를 위해서.
		mav.addObject("ajaxtypelist", facilService.schSrTypeAjax());

		// 이동.
		return mav;
	}

	// sr_type을 바탕으로 sr_name을 찾는 AJAX
	// ResoponseBody는 여기에 붙여도 되고 retrun 값 앞에도 붙일수 있다.
	@ResponseBody
	@RequestMapping(value = "/AFacilSrNameAjax")
	public List<StudyroomDTO> ajaxSrName(HttpServletRequest req) throws UnsupportedEncodingException {
		String sr_type = URLDecoder.decode(req.getParameter("sr_type"), "UTF-8");
		System.out.println("ajax 시작.");
		List<StudyroomDTO> list = facilService.schSrNameAjax(sr_type);

		/*
		 * // 디버깅용. for(int i=0; i<list.size() ;i++){
		 * System.out.println(list.get(i).getPg_name()); }
		 */

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/AFacilSrNumAjax")
	public List<StudyroomDTO> ajaxSrLoadNum(HttpServletRequest req) throws UnsupportedEncodingException {
		String sr_type = URLDecoder.decode(req.getParameter("sr_type"), "UTF-8");
		String sr_name = URLDecoder.decode(req.getParameter("sr_name"), "UTF-8");

		StudyroomDTO srdto = new StudyroomDTO();
		srdto.setSr_type(sr_type);
		srdto.setSr_name(sr_name);

		List<StudyroomDTO> list = facilService.schSrNumAjax(srdto);

		return list;
	}

	// 관리자 운동장 / 일정관리 / 일정취소
	@RequestMapping(value = "/AFacilSrDel", method = RequestMethod.POST)
	public String aSrscheduleLoadListDel(HttpServletRequest req, RedirectAttributes rttr) {
		String srm_num = req.getParameter("srm_num");

		// 일정 / 삭제
		facilService.deleteReserSr(srm_num);

		rttr.addFlashAttribute("result", "true");

		return "redirect:/facil/AFacilSR";
	}

	@RequestMapping(value = "/AFacilSrAdd")
	public String aFacilSrAdd(HttpServletRequest req, RedirectAttributes rttr) {

		SrMemberDTO srmdto = new SrMemberDTO();

		// 학사일정의 경우 운영자[admin03/시설관리자]로 입력된다.
		srmdto.setM_id("admin03");
		srmdto.setSrm_date(req.getParameter("addsrm_date"));

		// 학사일정으로 인한 예약의 경우 하루 전체를 빌린다.
		srmdto.setSrm_timecode("A1111111111111");
		srmdto.setSrm_issue(req.getParameter("addsrm_issue"));
		srmdto.setSr_num(req.getParameter("addsr_num"));

		facilService.schSrAdd(srmdto);

		// 일정 추가 완료. return값이 없음.
		rttr.addFlashAttribute("result", "addok");

		return "redirect:/facil/AFacilSR";
	}

	// ****************************[사용자]**********************************




	// 사용자메인 / 예약 취소
	@RequestMapping(value = "/FacilMainDel")
	public String userReserDel(HttpServletRequest req, RedirectAttributes rttr) {

		String type = req.getParameter("값넣으세요.");

		// 스터디룸의 경우 시설번호 첫번째 's'로 판단.
		if (type.startsWith("s")) {
			facilService.deleteReserSr(type);
		}

		// 운동장의 경우 시설번호 첫번째 'p'로 판단.
		else if (type.startsWith("p")) {
			facilService.deleteReserPg(type);
		}

		rttr.addFlashAttribute("result", "addok");

		return "redirect:/facil/facilities_main";
	}

	// ****************************************************************
	// 사용자 운동장 예약 / 메인.
	@RequestMapping(value = "/FacilPGreserv")
	public ModelAndView userPgMain() {
		ModelAndView mav = new ModelAndView("/facil/facilities_playground");
		mav.addObject("ajaxtypelist", facilService.schPgTypeAjax());
		return mav;
	}

	// 사용자 운동장 예약 / 예약 셀렉트문[facil type] 불러오기..
	@ResponseBody
	@RequestMapping(value = "/FacilPgNameAjax")
	public List<PlaygroundDTO> userPgNameAjax(HttpServletRequest req) throws UnsupportedEncodingException {
		String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");

		PlaygroundDTO pgdto = new PlaygroundDTO();
		pgdto.setPg_type(pg_type);

		List<PlaygroundDTO> list = facilService.schPgNameAjax(pg_type);
		return list;
	}

	// [Ajax]사용자 운동장 예약 / 예약 [facil name] 불러오기.
	@ResponseBody
	@RequestMapping(value = "/FacilPgNumAjax")
	public List<PlaygroundDTO> userPgNumAjax(HttpServletRequest req) throws UnsupportedEncodingException {
		String pg_type = URLDecoder.decode(req.getParameter("pg_type"), "UTF-8");
		String pg_name = URLDecoder.decode(req.getParameter("pg_name"), "UTF-8");

		PlaygroundDTO pgdto = new PlaygroundDTO();
		pgdto.setPg_type(pg_type);
		pgdto.setPg_name(pg_name);

		List<PlaygroundDTO> list = facilService.schPgNumAjax(pgdto);

		return list;
	}

	// [Ajax] 사용자 운동장 예약 / Front의 Date값과 위에서 구한 facil_num과 type으로 timecode출력.
	@ResponseBody
	@RequestMapping(value = "/FacilPgTimecodeAjax")
	public PgMemberDTO userPgList(HttpServletRequest req, PgMemberDTO pgmdto) throws UnsupportedEncodingException {
		String timecode = facilService.loadPgTimecodeAjax(pgmdto);
		pgmdto.setPgm_timecode(timecode);

		return pgmdto;
	}

	// 사용자 운동장 예약 / 예약
	@RequestMapping(value = "/FacilPgReser")
	public ModelAndView userPgReser(HttpServletRequest req, PgMemberDTO pgmdto) {
		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();

		// 리퀘스트로 접속자 정보를 가져옴
		MemberDTO member = login.getLoginInfo(req);
		String m_id = member.getM_id();

		// 포인트 차감을 위한 reques 나중에 수정해줄것!
		long minusPoint = Long.parseLong(req.getParameter("minuspoint"));

		// JSP 설계 실수로 이렇게 받아서 진행한다.. ㅠ
		pgmdto.setM_id(m_id);
		pgmdto.setPg_num(req.getParameter("spg_num"));
		pgmdto.setPgm_timecode(req.getParameter("spgm_timecode"));
		pgmdto.setPgm_date(req.getParameter("spgm_date"));

		String check = "";
		
		check = pointService.recordPointTrade("[운동시설 예약] "+minusPoint+"포인트가 차감되었습니다.", 
				  member.getM_point(), 
				  minusPoint, 
				  m_id,
				  FACIL_ADMIN);
		
		if("complete".equals(check)) {
			facilService.userReserPg(pgmdto);
			// 결제가 성공적으로 이뤄졌다 포인트 연계후 if으로 나눌것.
			req.setAttribute("tradecheck", "complete");
		}
		else {
			// 결제가 실패했다.
			req.setAttribute("tradecheck", "overpoint");
		}
		
		ModelAndView mav = new ModelAndView("/facil/facilresult");

		return mav;
	}

	// ****************************************************************
	// 사용자 스터디룸 예약 / 메인.
	// 사용자 운동장 예약 / 메인.
	@RequestMapping(value = "/FacilSRreserv")
	public ModelAndView useSrMain() {
		ModelAndView mav = new ModelAndView("/facil/facilities_studyroom");
		mav.addObject("ajaxtypelist", facilService.schSrTypeAjax());
		return mav;
	}

	// 사용자 운동장 예약 / 예약 셀렉트문[facil type] 불러오기..
	@ResponseBody
	@RequestMapping(value = "/FacilSrNameAjax")
	public List<StudyroomDTO> userSrNameAjax(HttpServletRequest req) throws UnsupportedEncodingException {
		String sr_type = URLDecoder.decode(req.getParameter("sr_type"), "UTF-8");

		StudyroomDTO srdto = new StudyroomDTO();
		srdto.setSr_type(sr_type);

		List<StudyroomDTO> list = facilService.schSrNameAjax(sr_type);
		return list;
	}

	// [Ajax]사용자 운동장 예약 / 예약 [facil name] 불러오기.
	@ResponseBody
	@RequestMapping(value = "/FacilSrNumAjax")
	public List<StudyroomDTO> userSrNumAjax(HttpServletRequest req) throws UnsupportedEncodingException {
		String sr_type = URLDecoder.decode(req.getParameter("sr_type"), "UTF-8");
		String sr_name = URLDecoder.decode(req.getParameter("sr_name"), "UTF-8");

		StudyroomDTO srdto = new StudyroomDTO();
		srdto.setSr_type(sr_type);
		srdto.setSr_name(sr_name);

		List<StudyroomDTO> list = facilService.schSrNumAjax(srdto);

		return list;
	}

	// [Ajax] 사용자 운동장 예약 / Front의 Date값과 위에서 구한 facil_num과 type으로 timecode출력.
	@ResponseBody
	@RequestMapping(value = "/FacilSrTimecodeAjax")
	public SrMemberDTO userSrList(HttpServletRequest req, SrMemberDTO srmdto) throws UnsupportedEncodingException {
		String timecode = facilService.loadSrTimecodeAjax(srmdto);
		srmdto.setSrm_timecode(timecode);

		return srmdto;
	}

	// 사용자 운동장 예약 / 예약
	@RequestMapping(value = "/FacilSrReser")
	public ModelAndView userSRReser(HttpServletRequest req, SrMemberDTO srmdto) {
		// 로그인 빈을 통해서 ID 검
		LoginBean login = new LoginBean();

		// 리퀘스트로 접속자 정보를 가져옴
		MemberDTO member = login.getLoginInfo(req);
		String m_id = member.getM_id();

		// 포인트 차감을 위한 reques 나중에 수정해줄것!
		long minusPoint = Long.parseLong(req.getParameter("minuspoint"));
		
		// JSP 설계 실수로 이렇게 받아서 진행한다.. ㅠ
		srmdto.setM_id(m_id);
		srmdto.setSr_num(req.getParameter("ssr_num"));
		srmdto.setSrm_timecode(req.getParameter("ssrm_timecode"));
		srmdto.setSrm_date(req.getParameter("ssrm_date"));

		String check = "";
		
		check = pointService.recordPointTrade("[스터디룸 예약] "+minusPoint+"포인트가 차감되었습니다.", 
				  member.getM_point(), 
				  minusPoint, 
				  m_id,
				  FACIL_ADMIN);
		
		if("complete".equals(check)) {
			facilService.userReserSr(srmdto);
			// 결제가 성공적으로 이뤄졌다 포인트 연계후 if으로 나눌것.
			req.setAttribute("tradecheck", "complete");
		}
		else {
			// 결제가 실패했다.
			req.setAttribute("tradecheck", "overpoint");
		}

		ModelAndView mav = new ModelAndView("/facil/facilresult");

		return mav;
	}
	// 운동장 예약 내역 조회
	@ResponseBody
	@RequestMapping(value = "/pgRsrNumCnt")
	public ArrayList<CalanderDTO> pgRsrNumCnt(){
		
		ArrayList<CalanderDTO> result =  new ArrayList<CalanderDTO>();
		List<PgMemberDTO> list =  facilService.pgRsrNumCntEachDate();
		
		CalanderDTO tmpCal = new CalanderDTO();
		
		for(PgMemberDTO i : list) {
			tmpCal = new CalanderDTO();
			tmpCal.setStart(i.getPgm_date());
			tmpCal.setEnd(i.getPgm_date());
			tmpCal.setTitle(i.getCnt()+"건 예약중");
			tmpCal.setColor("#a8ef97");
			tmpCal.setAllDay(true);
			result.add(tmpCal);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pgRsrInfoByDate")
	public List<PgMemberDTO> pgRsrInfoByDate(String pgm_date){
		
		List<PgMemberDTO> result =  new ArrayList<PgMemberDTO>();
		
		result = facilService.pgRsrInfoByDate(pgm_date);
		
		return result;
		
	}

}