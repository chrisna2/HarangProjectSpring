package com.harang.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.Criteria;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.MessageService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	private PageMaker pageMaker;
	
	/**
	 * 받은 메시지 함에 리스트를 가져오는 메서드.
	 * @param session 세션
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/INBOX")
	public ModelAndView getList(HttpSession session, SearchCriteria cri) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		cri.setM_id(member.getM_id());
		List<MessageDTO> givenList = setList(messageService.getGivenMessageList(cri));
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(givenList.size());
		
		ModelAndView mav = new ModelAndView("message/message_inbox_main");
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
	@RequestMapping("/SENT")
	public ModelAndView sentList(HttpSession session, SearchCriteria cri) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		cri.setM_id(member.getM_id());
		List<MessageDTO> sentList = setList(messageService.getSentMessageList(cri)); 
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(sentList.size());

		ModelAndView mav = new ModelAndView("message/message_sent");
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
	@RequestMapping("/TOME")
	public ModelAndView toMeList(HttpSession session, SearchCriteria cri) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		cri.setM_id(member.getM_id());
		List<MessageDTO> toMeList = setList(messageService.getToMeMessageList(cri)); 
		
		pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(toMeList.size());

		ModelAndView mav = new ModelAndView("message/message_toMe");
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
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		mav.addObject("notRead",messageService.getNotReadMessage(member.getM_id()));
		mav.addObject("notRead_toMe",messageService.getNotReadMessage_toMe(member.getM_id()));
		mav.addObject("member", member);
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
	@RequestMapping("/inboxDelete")
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
	@RequestMapping("/sentDelete")
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
	@RequestMapping("/toMeDelete")
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
		ModelAndView mav = new ModelAndView("message/message_post");
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
		ModelAndView mav = new ModelAndView("redirect:/SENT");
		messageService.postMessage(message);
		return mav;
	}
	
	/**
	 * 메시지에 답장하기 위해 메시지 번호로 받는 사람을 조회하고 메시지 보내기 페이지로 이동하는 메서드.
	 * @param t_num 메시지 번호
	 * @return ModelAndView 페이지 경로와 파라미터
	 */
	@RequestMapping("/reply")
	public ModelAndView reply(String t_num) {
		ModelAndView mav = new ModelAndView("redirect:/POST");
		MessageDTO msg = messageService.getMessage(t_num);
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
	public ModelAndView read(String t_num, String tab, HttpSession session) {
		ModelAndView mav = new ModelAndView("message/message_read");
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
