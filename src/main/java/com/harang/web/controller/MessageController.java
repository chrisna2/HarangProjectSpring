package com.harang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MessageDTO;
import com.harang.web.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping("/inboxlist")
	public ModelAndView getList(MessageDTO message) {
		ModelAndView mav = new ModelAndView("message/message_inbox_main");
		List<MessageDTO> givenList = messageService.getGivenMessageList(message);
		for(int i=0; i<givenList.size();i++){
			MessageDTO msg = (MessageDTO) givenList.get(i);
			msg.setList_num(givenList.size()-i);
			msg.setM_sender_name(messageService.getMember(msg.getM_sender()).getM_name());
			givenList.set(i, msg);
		}
		
		//페이징
		mav.addObject("list", givenList);
		mav.addObject("tab", "INBOX");
		return mav;
	}
	
	@RequestMapping("/inboxDelete")
	public ModelAndView deleteMessage(@ModelAttribute("deleteList") String arr, String tab) {
		ModelAndView mav = new ModelAndView("message/message_inbox_main");
		
		if (arr != null){
			String deleteList[] = arr.split(",");
			if(deleteList != null){
				for(int i=0; i<deleteList.length;i++){
					MessageDTO msg = messageService.getMessage(deleteList[i]); //메시지 정보
					if(msg.getT_read_del() == "N" && msg.getT_send_del() == "N"){ // 둘다 삭제하지 않은 경우
						if(tab == "INBOX"){ // 받은 메시지 함이라면
							messageService.deleteGivenMessage_first(deleteList[i]); // 받은메시지 삭제
						}else if(tab == "SENT"){ // 보낸 메시지 함이라면
							messageService.deleteSentMessage_first(deleteList[i]); // 보낸메시지 삭제
						}else{ // 내게 쓴 메시지함이라면
							messageService.deleteMessage(deleteList[i]); // 메시지 삭제
						}
					}else{ // 둘 중 한명이 삭제한 경우
						messageService.deleteMessage(deleteList[i]); // 메시지 삭제 
					}
				}
			}
		}
		return mav;
	}
	
	
}
