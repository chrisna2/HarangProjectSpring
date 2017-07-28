package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.SearchCriteria;

public interface MessageService {
	public MemberDTO getMember(String m_id);
	public List<String> getMember_id(String m_name);
	public void postMessage(MessageDTO message);
	public void postMessage(String title, String content, String sender, String reader);
	public List<MessageDTO> getGivenMessageList(String m_id);
	public List<MessageDTO> getSentMessageList(String m_id);
	public List<MessageDTO> getToMeMessageList(String m_id);
	public MessageDTO getMessage(String t_num);
	public void deleteGivenMessage_first(String t_num);
	public void deleteSentMessage_first(String t_num);
	public void deleteMessage(String t_num);
	public void readMessage(String t_num);
	public int getNotReadMessage(String m_id);
	public int getNotReadMessage_toMe(String m_id);
}
