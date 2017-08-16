package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.SearchCriteria;

public interface MessageDao {
	public MemberDTO getMember(String m_id);
	public List<String> getMember_id(String m_name);
	public void postMessage(MessageDTO message);
	public List<MessageDTO> getGivenMessageListHeader(String m_id);
	public List<MessageDTO> getGivenMessageList(SearchCriteria cri);
	public List<MessageDTO> getSentMessageList(SearchCriteria cri);
	public List<MessageDTO> getToMeMessageList(SearchCriteria cri);
	public int getGivenMessageListCount(SearchCriteria cri);
	public int getSentMessageListCount(SearchCriteria cri);
	public int getToMeMessageListCount(SearchCriteria cri);
	public MessageDTO getMessage(String t_num);
	public void deleteGivenMessage_first(String t_num);
	public void deleteSentMessage_first(String t_num);
	public void deleteMessage(String t_num);
	public void readMessage(String t_num);
	public int getNotReadMessage(String m_id);
	public int getNotReadMessage_toMe(String m_id);
	
}
