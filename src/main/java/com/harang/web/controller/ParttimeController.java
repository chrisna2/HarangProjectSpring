package com.harang.web.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.DaetaDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.P_ApplyDTO;
import com.harang.web.domain.ParttimeDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.MessageService;
import com.harang.web.service.ParttimeService;
import com.harang.web.utill.DateBean;
import com.harang.web.utill.LoginBean;


@Controller
@RequestMapping("/parttime")
public class ParttimeController {
	
	@Autowired
	private ParttimeService parttimeService;
	
	@Autowired
	private MessageService messageService;
	
	private LoginBean loginBean = new LoginBean();
	private DateBean dateBean = new DateBean();
	
	/** 알바모집 */
	@RequestMapping("/PMAIN")
	public ModelAndView parttimeList(HttpSession session, SearchCriteria cri){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_main";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_parttime_main";}
	
		ModelAndView mav = new ModelAndView(url);
		List<ParttimeDTO> list = parttimeService.getParttimeList(cri);
		
		// 마감일이 지나면 [마감]으로 말머리 변경
		afterDeadline(list, "p");	

		// 추가정보 저장=> 1.글번호 2.해당 글에 지원한 지원자
		for (int i = 0; i < list.size(); i++) {
			ParttimeDTO dto = list.get(i);
			dto.setList_num(list.size() - i); // 글번호
			dto.setCnt_apply(parttimeService.getParttimeCnt_apply(dto.getP_num())); // 지원자수
			if (dto.getM_id().equals("admin02")) {
				dto.setM_name("관리자");
			} else {
				dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name()); // 이름을  저장
			}
			list.set(i, dto);
		}
		mav.addObject("list", list);
		return mav;
	}
	
	/**
	 * 마감일이 지나면 자동으로 말머리가 [마감]으로 변경되는 메서드.
	 * @param list 리스트
	 * @param pORd 알바게시판이면 "p" 대타게시판이면 "d"
	 */
	public void afterDeadline(List list, String pORd){
		if(pORd.equals("p")){
			for(int i=0; i<list.size(); i++){
				ParttimeDTO dto = parttimeService.getParttime(((ParttimeDTO)list.get(i)).getP_num());
				if(dateBean.checkDeadline(dto.getP_deadline()) && !dto.getP_header().equals("[마감]")){
					dto.setP_header("[마감]");
					parttimeService.updateParttime(dto);
				}
			}
		}else if(pORd.equals("d")){
			for(int i=0; i<list.size(); i++){
				DaetaDTO dto = parttimeService.getDaeta(((DaetaDTO)list.get(i)).getD_num());
				if(dateBean.checkDeadline(dto.getD_deadline()) && !dto.getD_header().equals("[마감]")){
					dto.setD_header("[마감]");
					parttimeService.updateDaeta(dto);
				}
			}
		}
	}
	
	@RequestMapping("insertParttime")
	public ModelAndView insertParttime(HttpSession session, ParttimeDTO parttime){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "redirect:/parttime/PMAIN";
		ModelAndView mav = new ModelAndView(url);
		parttime.setP_daycode(transCode(parttime.getP_daycode()));
		parttime.setM_id(login.getM_id());
		parttimeService.insertParttime(parttime);
		return mav;
	}
	
	/**
	 * 체크된 요일을 매개변수로 받아서 daycode로 변환하는 메서드.
	 */
	public String transCode(String p_daycode) {
		String daycode = "";
		String[] day = { "월", "화", "수", "목", "금", "토", "일" };
		String[] arr2 = {"0","0","0","0","0","0","0"};
		
		if(p_daycode != null){
			String arr[] = p_daycode.split(",");
			for (int i = 0; i < 7; i++) {
				for(int j=0; j<arr.length;j++){
					// 해당 요일이 있으면 1로 변경
					if(arr[j].equals(day[i])){
						arr2[i] = "1";
					}
				}
				daycode += arr2[i];
			}
		}
		return daycode;
	}
	
	@RequestMapping("/deleteParttime")
	public ModelAndView deleteParttime(String p_num){
		ModelAndView mav = new ModelAndView("redirect:/parttime/PMAIN");
		parttimeService.deleteParttime(p_num);
		return mav;
	}
	
	@RequestMapping("/PPOST")
	public ModelAndView parttimePost(){
		ModelAndView mav = new ModelAndView("parttime/parttime_post");
		String[] day = {"월","화","수","목","금","토","일"};
		mav.addObject("day", day);
		return mav;
	}

	@RequestMapping("/cancelApply")
	public ModelAndView cancelApply(HttpSession session, String p_num){
		ModelAndView mav = new ModelAndView("redirect:parttime/PREAD");
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", member.getM_id());
		params.put("p_num", p_num);
		
		parttimeService.deleteParttimeApply(params); // 지원 정보를 DB에서 삭제
		ParttimeDTO dto = parttimeService.getParttime(p_num); // 해당 글 정보
		MemberDTO writer = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보

		// 지원 취소 메시지 보내기 : 관리자 -> 글 게시자
		String title = member.getM_name() + "님이 지원을 취소하였습니다.";
		String content = member.getM_name() + "님이 \"" + dto.getP_title() + "\"글에 대한 지원을 취소하였습니다."
				+ "\n해당 글을 확인해주세요.";
		MessageDTO message = new MessageDTO();
		message.setT_title(title);
		message.setT_content(content);
		message.setM_sender("admin02");
		message.setM_reader(member.getM_id());
		messageService.postMessage(message);
		
		return mav;
	}
	
	@RequestMapping("/updateParttime")
	public ModelAndView updateParttime(HttpSession session, ParttimeDTO parttime){
		MemberDTO login = loginBean.getLoginIngfo(session);
		ModelAndView mav = new ModelAndView("redirect:/parttime/PREAD");
		parttimeService.updateParttime(parttime);
		
		/** 해당 글의 지원자들에게 글의 내용이 수정되었음을 알리는 메시지를 보낸다.*/
		List list = parttimeService.getParttimeApplyList(parttime.getP_num()); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			P_ApplyDTO dto1 = (P_ApplyDTO) list.get(i);
			MemberDTO applicant = parttimeService.getMember(dto1.getM_id()); // 지원자의 회원정보
			
			String title = applicant.getM_name()+"님이 지원한 글의 내용이 수정되었습니다.";
			String content =applicant.getM_name()+"님이 지원한 알바 모집 글의 작성자 " + login.getM_name() + "님이 \"" + parttime.getP_title() + "\"글의 내용을 수정하였습니다."
							+ "\n해당 글을 반드시 확인해주세요.";
						
			messageService.postMessage(title, content, "admin02", dto1.getM_id());
		}
		return mav;
	}
	
	@RequestMapping("/PREAD")
	public ModelAndView readParttime(HttpSession session, String p_num, String tab){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_read";
		if(loginBean.adminCheck(login.getM_id())){url = "parttime/a_parttime_read";}
		ModelAndView mav = new ModelAndView(url);
		
		/** 글 정보 */
		char[] day = { '월', '화', '수', '목', '금', '토', '일' };
		ParttimeDTO parttime = parttimeService.getParttime(p_num);
		isApply(mav, login.getM_id(), p_num); // 이 글에 지원했는지 안했는지
		
		/** 지원자 정보 */
		List list = parttimeService.getParttimeApplyList(p_num); // 지원자 목록 데이터
		for (int i = 0; i < list.size(); i++) {
			P_ApplyDTO dto = (P_ApplyDTO) list.get(i);
			dto.setList_num(i + 1); // 글번호 설정
			dto.setM_name(parttimeService.getMember(dto.getM_id()).getM_name());// 지원자의 회원번호로 검색하여 지원자이름을 받아온다.
			list.set(i, dto);
		}
		
		mav.addObject("resume", list);
		mav.addObject("info", parttime);
		mav.addObject("daycode", splitCode(parttime.getP_daycode()));
		mav.addObject("day", day);
		return mav;
	}
	
	
	public String[] splitCode(String code) {
		String[] daycode = null;
		if(code != null){
			// String을 한글자씩 쪼개서 저장
			char[] _daycode = code.toCharArray();
	
			// char배열을 String배열로 변환
			daycode = new String[7];
			for (int i = 0; i < _daycode.length; i++) {
				daycode[i] = Character.toString(_daycode[i]);
			}
		}
		return daycode;
	}
	
	@RequestMapping("/applyParttime")
	public String applyParttime(HttpSession session, P_ApplyDTO apply){
		MemberDTO login = loginBean.getLoginIngfo(session);
		ParttimeDTO dto = parttimeService.getParttime(apply.getP_num()); // 해당 글 정보
		MemberDTO writer = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보

		parttimeService.createParttimeResume(apply);

		// 지원 확인 메시지 보내기 : 관리자 -> 글쓴이
		String title = "\"" + dto.getP_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다.";
		String content = "\"" + dto.getP_title() + "\"글에 " + login.getM_name() + "님이 지원하였습니다. 해당 글에서 이력서를 확인해주세요.";
		messageService.postMessage(title, content, "admin02", login.getM_id());
		
		return "parttime/parttime_read";
	}
	
	@RequestMapping("/choiceParttime")
	public String choiceP_apply(HttpSession session, String choice_id, String p_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", choice_id);
		params.put("p_num", p_num);
		
		parttimeService.updateParttimeChoice(params); // 채용 정보를 DB에 저장
		ParttimeDTO dto = parttimeService.getParttime(p_num); // 해당 글 정보
		MemberDTO member = parttimeService.getMember(dto.getM_id()); // 글쓴이의 회원정보
		MemberDTO applicant = parttimeService.getMember(choice_id);

		// 채용 확인 메시지 보내기 : 채용한 사람 -> 지원자
		String title = "축하드립니다. 알바 모집에 채용되셨습니다.";
		String content = "축하드립니다. 알바 모집 게시판의 \"" + dto.getP_title() + "\"에 채용되셨습니다." + "\n채용담당자와 직접 연락하세요. 연락처는 "
				+ dto.getP_tel() + " 입니다.";
		messageService.postMessage(title, content, login.getM_id(), choice_id);

		// 채용 확인 메시지 보내기 : 관리자 -> 채용한 사람
		String title2 = "축하드립니다. 알바 채용에 성공하였습니다.";
		String content2 = member.getM_name() + "님이 알바 모집 게시판에 작성한 \"" + dto.getP_title()
				+ "\" 글에 알바를 채용하셨습니다. \n채용하신 " + applicant.getM_name() + "님과 직접 연락하세요. 연락처는 " + applicant.getM_tel()
				+ " 입니다.";
		messageService.postMessage(title2, content2, "admin02", login.getM_id());
		return "redirect:parttime/PREAD";
	}
	
	public void isApply(ModelAndView mav, String m_id, String p_num) {
		P_ApplyDTO applydto = new P_ApplyDTO();
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", m_id);
		params.put("p_num", p_num);
		
		List<P_ApplyDTO> list = (List<P_ApplyDTO>) parttimeService.getParttimeApply(params);
		if(!list.isEmpty()){applydto = list.get(0);}

		if (applydto.getM_id() != null) {
			mav.addObject("applied", "Y");
		} else {
			mav.addObject("applied", "N");
		}
	}
	
	@RequestMapping("/PRESUME")
	public ModelAndView getResume(HttpSession session, String m_id, String p_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_resume";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_parttime_resume";
		}
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("m_id", m_id);
		params.put("p_num", p_num);
		
		List<P_ApplyDTO> list = parttimeService.getParttimeApply(params);
		P_ApplyDTO resume = list.get(0);
		
		MemberDTO applicant = parttimeService.getMember(m_id);
		applicant.setM_age(getAge(applicant.getM_birth()));
		
		ModelAndView mav = new ModelAndView(url);
		mav.addObject("resume", resume);
		mav.addObject("applicant", applicant);
		return mav;
	}
	
	/**
	 * 현재 날짜와 출생일을 비교하여 나이를 계산하는 메서드.
	 * @param birth
	 * @return 나이
	 */
	public int getAge(String birth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date birthday = null;
		try {
			birthday = (Date) sdf.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthDay = new GregorianCalendar();
		birthDay.setTime(birthday);

		int factor = 0;
		if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
			factor = -1;
		}
		return today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR) + factor;
	}
	
	@ModelAttribute("/PUPDATE")
	public ModelAndView updateParttime(HttpSession session, String p_num){
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "parttime/parttime_update";
		if(loginBean.adminCheck(login.getM_id())){
			url = "parttime/a_parttime_update";
		}
		
		char[] day = { '월', '화', '수', '목', '금', '토', '일' };
		ParttimeDTO parttime = parttimeService.getParttime(p_num);

		ModelAndView mav = new ModelAndView(url);
		mav.addObject("info", parttime);
		//mav.addObject("daycode", splitCode(parttime.getP_daycode()));
		mav.addObject("day", day);
		return mav;
	}
	
	/** 알바 모집 끝*/
	
	/** 대타 모집 */
	/** 대타 모집 끝 */
	
	/** 내가 쓴 글 */
	/** 내가 쓴 글 끝 */
}
