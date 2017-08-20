package com.harang.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.PagingDto;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.MessageService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;
import com.harang.web.utill.PagingBean;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	private LoginBean loginBean = new LoginBean();
	
	/*
	public PagingDto paging(int listSize, String _nowPage, String _nowBlock) {
		PagingBean pbean = new PagingBean();
		int nowPage = 0, nowBlock = 0;
		//System.out.println("CHECK1 : "+_nowPage);
		if(!("").equals(_nowPage) || null != _nowPage ){
			//System.out.println("CHECK2 : "+_nowPage);
			nowPage = Integer.parseInt(_nowPage);}
		if(!("").equals(_nowBlock) || null != _nowBlock) {nowBlock = Integer.parseInt(_nowBlock);}
		PagingDto paging = pbean.Paging(listSize, 20, nowPage, 1, nowBlock);
		return paging;
	}
	*/
	
	private PageMaker pageMaker;
	
	/**
	 * 받은 메시지 함에 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping(value="/INBOX", method = RequestMethod.GET)
	public ModelAndView getListGet(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_inbox_main";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_inbox_main";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> givenList = messageService.getGivenMessageList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getGivenMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", givenList);
		mav.addObject("tab", "INBOX");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	@RequestMapping(value="/INBOX", method = RequestMethod.POST)
	public ModelAndView getListPost(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_inbox_main";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_inbox_main";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> givenList = messageService.getGivenMessageList(cri);
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getGivenMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", givenList);
		mav.addObject("tab", "INBOX");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 보낸 메시지 함에 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping(value="/SENT", method = RequestMethod.GET)
	public ModelAndView sentListGet(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		String url = "message/message_sent";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_sent";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> sentList = messageService.getSentMessageList(cri); 

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getSentMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", sentList);
		mav.addObject("tab", "SENT");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	@RequestMapping(value="/SENT", method = RequestMethod.POST)
	public ModelAndView sentLisPost(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_sent";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_sent";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> sentList = messageService.getSentMessageList(cri); 
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getSentMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", sentList);
		mav.addObject("tab", "SENT");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 내게 쓴 메시지 함에 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping(value="/TOME", method = RequestMethod.GET)
	public ModelAndView toMeListGet(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_toMe";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_toMe";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> toMeList = messageService.getToMeMessageList(cri); 

		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getToMeMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", toMeList);
		mav.addObject("tab", "TOME");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	@RequestMapping(value="/TOME", method = RequestMethod.POST)
	public ModelAndView toMeListPost(HttpSession session,SearchCriteria cri) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_toMe";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_toMe";
		}
		
		cri.setM_id(login.getM_id());
		
		List<MessageDTO> toMeList = messageService.getToMeMessageList(cri); 
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.getToMeMessageListCount(cri));
		
		ModelAndView mav = new ModelAndView(url);
		messageNotRead(mav, session); //안 읽은 메시지
		mav.addObject("list", toMeList);
		mav.addObject("tab", "TOME");
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}
	
	/**
	 * 안 읽은 메시지 개수를 뷰에 보내는 메서드. (반복되는 코드라서 메서드로 분리)
	 * @param mav ModelAndView
	 * @param session 세션
	 */
	public void messageNotRead(ModelAndView mav, HttpSession session) {
		MemberDTO login = loginBean.getLoginIngfo(session);
		
		mav.addObject("notRead",messageService.getNotReadMessage(login.getM_id()));
		mav.addObject("notRead_toMe",messageService.getNotReadMessage_toMe(login.getM_id()));
		mav.addObject("member", login);
	}
	
	/**
	 * 메시지 리스트를 보내기 전 글 번호와 글쓴이의 이름을 세팅하는 메서드. (반복되는 코드라서 메서드로 분리)
	 * @param list 메시지 리스트
	 * @return list 글번호와 보내는 사람 이름이 추가된 메시지 리스트
	 */
	public List<MessageDTO> setList(List<MessageDTO> list) {
		for(int i=0; i<list.size();i++){
			MessageDTO msg = (MessageDTO) list.get(i);
			msg.setList_num(list.size()-i);
			msg.setM_reader_name(messageService.getMember(msg.getM_reader()).getM_name());
			list.set(i, msg);
		}
		return list;
	}
	
	/**
	 * 받은 메시지를 삭제하는 메서드.
	 * @param arr 삭제할 메시지번호
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/deleteINBOX")
	public ModelAndView deleteInboxMessage(@ModelAttribute("deleteList") String arr) {
		ModelAndView mav = new ModelAndView("message/message_inbox_main");
		
		if (arr != null){
			String deleteList[] = arr.split(",");
			if(deleteList != null){
				for(int i=0; i<deleteList.length;i++){
					MessageDTO msg = messageService.getMessage(deleteList[i]); //메시지 정보
					if(msg.getT_read_del() == "N" && msg.getT_send_del() == "N"){ // 둘다 삭제하지 않은 경우
						messageService.deleteGivenMessage_first(deleteList[i]); // 받은메시지 삭제
					}else{ // 둘 중 한명이 삭제한 경우
						messageService.deleteMessage(deleteList[i]); // 메시지 삭제 
					}
				}
			}
		}
		return mav;
	}
	
	/**
	 * 보낸 메시지를 삭제하는 메서드.
	 * @param arr 삭제할 메시지번호
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/deleteSENT")
	public ModelAndView deleteSentMessage(@ModelAttribute("deleteList") String arr) {
		ModelAndView mav = new ModelAndView("message/message_sent");
		
		if (arr != null){
			String deleteList[] = arr.split(",");
			if(deleteList != null){
				for(int i=0; i<deleteList.length;i++){
					MessageDTO msg = messageService.getMessage(deleteList[i]); //메시지 정보
					if(msg.getT_read_del() == "N" && msg.getT_send_del() == "N"){ // 둘다 삭제하지 않은 경우
						messageService.deleteSentMessage_first(deleteList[i]); // 받은메시지 삭제
					}else{ // 둘 중 한명이 삭제한 경우
						messageService.deleteMessage(deleteList[i]); // 메시지 삭제 
					}
				}
			}
		}
		return mav;
	}
	
	/**
	 * 내가 쓴 메시지를 삭제하는 메서드.
	 * @param arr 삭제할 메시지번호
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/deleteTOME")
	public ModelAndView deleteToMeMessage(@ModelAttribute("deleteList") String arr) {
		ModelAndView mav = new ModelAndView("message/message_toMe");
		
		if (arr != null){
			String deleteList[] = arr.split(",");
			if(deleteList != null){
				for(int i=0; i<deleteList.length;i++){
					messageService.deleteMessage(deleteList[i]); // 메시지 삭제 
				}
			}
		}
		return mav;
	}
	
	/**
	 * 메시지 보내기 페이지로 이동하는 메서드.
	 * @param session 세션
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping(value="/POST", method=RequestMethod.GET)
	public ModelAndView post(HttpSession session) {
		
		MemberDTO login = loginBean.getLoginIngfo(session);
		String url = "message/message_post";
		if(loginBean.adminCheck(login.getM_id())){
			url = "message/a_message_post";
		}
		
		ModelAndView mav = new ModelAndView(url);
		messageNotRead(mav, session);
		return mav;
	}
	
	/**
	 * 메시지를 보내는 메서드.
	 * @param session 세션
	 * @param message MessageDTO 메시지 정보
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping(value="/POST", method=RequestMethod.POST)
	public ModelAndView postMessage(HttpSession session, MessageDTO message) {
		ModelAndView mav = new ModelAndView("redirect:/message/SENT");
		System.out.println("m_sender : "+message.getM_sender());
		messageService.postMessage(message);
		return mav;
	}
	
	@RequestMapping("/ajax")
	public @ResponseBody List<String> nameAjax(HttpServletRequest req) throws UnsupportedEncodingException{
		String m_name = URLDecoder.decode(req.getParameter("m_name"), "UTF-8");
		List<String> list = messageService.getMember_id(m_name);
		return list;
	}
	
	/**
	 * 메시지에 답장하기 위해 메시지 번호로 받는 사람을 조회하고 메시지 보내기 페이지로 이동하는 메서드.
	 * @param t_num 메시지 번호
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/reply")
	public ModelAndView reply(HttpSession session, String t_num) {
		String url = "/message/message_post";
		if(loginBean.adminCheck(loginBean.getLoginIngfo(session).getM_id())){
			url = "/message/a_message_post";
		}
		
		ModelAndView mav = new ModelAndView(url);
		MessageDTO msg = messageService.getMessage(t_num);
		messageNotRead(mav, session);
		mav.addObject("reader", messageService.getMember(msg.getM_sender()).getM_name());
		return mav;
	}
	
	/**
	 * 해당 메시지를 읽고 읽은 시간 정보를 저장하는 메서드.
	 * @param t_num 메시지 번호
	 * @param tab 페이지 정보
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/READ")
	public ModelAndView read(String t_num, 
							@ModelAttribute("tab") String tab, 
							HttpSession session) {
		String url = "message/message_read";
		if(loginBean.adminCheck(loginBean.getLoginIngfo(session).getM_id())){
			url = "message/a_message_read";
		}
		
		ModelAndView mav = new ModelAndView(url);
		MessageDTO msg = messageService.getMessage(t_num);
		
		/** 메시지 보낸사람, 받은 사람 이름 검색 및 저장 */
		msg.setM_sender_name(messageService.getMember(msg.getM_sender()).getM_name());
		msg.setM_reader_name(messageService.getMember(msg.getM_reader()).getM_name());
		
		/** 메시지 읽은 시간 저장 */
		if(msg.getT_read_date() == null){
			// 보낸 메시지는 읽음 처리 하지 않는다.
			if("INBOX".equals(tab) || "TOME".equals(tab)){
				messageService.readMessage(t_num);
			}
		}
		messageNotRead(mav, session); // 안 읽은 메시지 개수
		mav.addObject("msg", msg);
		return mav;
	}
	
}
