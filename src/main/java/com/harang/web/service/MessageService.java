package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;

public interface MessageService {
	public MemberDTO getMember(String m_id);
	public List<String> getMember_id(MemberDTO member);
	public void postMessage(MessageDTO message);
	public List<MessageDTO> getGivenMessageList(MessageDTO message);
	public List<MessageDTO> getSentMessageList(MessageDTO message);
	public List<MessageDTO> getToMeMessageList(MessageDTO message);
	public MessageDTO getMessage(String t_num);
	public void deleteGivenMessage_first(String t_num);
	public void deleteSentMessage_first(String t_num);
	public void deleteMessage(String t_num);
	public void readMessage(MessageDTO message);
	public int getNotReadMessage(MessageDTO message);
	public int getNotReadMessage_toMe(MessageDTO message);
}
