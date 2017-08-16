package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.MessageDao;
import com.harang.web.repository.MyPageDao;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageDao messageDao;
	
	@Override
	public MemberDTO getMember(String m_id) {
		return messageDao.getMember(m_id);
	}

	@Override
	public List<String> getMember_id(String m_name) {
		return messageDao.getMember_id(m_name);
	}

	@Override
	public void postMessage(MessageDTO message) {
		messageDao.postMessage(message);
	}
	
	@Override
	public void postMessage(String title, String content, String sender, String reader){
		MessageDTO message = new MessageDTO();
		message.setT_title(title);
		message.setT_content(content);
		message.setM_sender(sender);
		message.setM_reader(reader);
		postMessage(message);
	}

	@Override
	public List<MessageDTO> getGivenMessageList(SearchCriteria cri) {
		return messageDao.getGivenMessageList(cri);
	}

	@Override
	public List<MessageDTO> getSentMessageList(SearchCriteria cri) {
		return messageDao.getSentMessageList(cri);
	}

	@Override
	public List<MessageDTO> getToMeMessageList(SearchCriteria cri) {
		return messageDao.getToMeMessageList(cri);
	}
	@Override
	public int getGivenMessageListCount(SearchCriteria cri) {
		return messageDao.getGivenMessageListCount(cri);
	}

	@Override
	public int getSentMessageListCount(SearchCriteria cri) {
		return messageDao.getSentMessageListCount(cri);
	}

	@Override
	public int getToMeMessageListCount(SearchCriteria cri) {
		return messageDao.getToMeMessageListCount(cri);
	}
	@Override
	public MessageDTO getMessage(String t_num) {
		return messageDao.getMessage(t_num);
	}

	@Override
	public void deleteGivenMessage_first(String t_num) {
		messageDao.deleteGivenMessage_first(t_num);
	}

	@Override
	public void deleteSentMessage_first(String t_num) {
		messageDao.deleteSentMessage_first(t_num);
	}

	@Override
	public void deleteMessage(String t_num) {
		messageDao.deleteMessage(t_num);
	}

	@Override
	public void readMessage(String t_num) {
		messageDao.readMessage(t_num);
	}

	@Override
	public int getNotReadMessage(String m_id) {
		return messageDao.getNotReadMessage(m_id);
	}

	@Override
	public int getNotReadMessage_toMe(String m_id) {
		return messageDao.getNotReadMessage_toMe(m_id);
	}

	@Override
	public List<MessageDTO> getGivenMessageListHeader(String m_id) {
		return messageDao.getGivenMessageListHeader(m_id);
	}


}
