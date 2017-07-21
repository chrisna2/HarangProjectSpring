package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.MessageDTO;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.message-mapper";
	
	@Override
	public MemberDTO getMember(String m_id) {
		return sqlSession.selectOne(namespace+".getMember", m_id);
	}
	@Override
	public List<String> getMember_id(MemberDTO member) {
		return sqlSession.selectList(namespace+".getMember_id", member);
	}
	@Override
	public void postMessage(MessageDTO message) {
		sqlSession.insert(namespace+".postMessage");
	}
	@Override
	public List<MessageDTO> getGivenMessageList(MessageDTO message) {
		return sqlSession.selectList(namespace+".getGivenMessageList", message);
	}
	@Override
	public List<MessageDTO> getSentMessageList(MessageDTO message) {
		return sqlSession.selectList(namespace+".getSentMessageList", message);
	}
	@Override
	public List<MessageDTO> getToMeMessageList(MessageDTO message) {
		return sqlSession.selectList(namespace+".getToMeMessageList", message);
	}
	@Override
	public MessageDTO getMessage(String t_num) {
		return sqlSession.selectOne(namespace+".getMessage", t_num);
	}
	@Override
	public void deleteGivenMessage_first(String t_num) {
		sqlSession.delete(namespace+".deleteGivenMessage_first", t_num);
	}
	@Override
	public void deleteSentMessage_first(String t_num) {
		sqlSession.delete(namespace+".deleteSentMessage_first", t_num);
	}
	@Override
	public void deleteMessage(String t_num) {
		sqlSession.delete(namespace+".deleteMessage", t_num);
	}
	@Override
	public void readMessage(MessageDTO message) {
		sqlSession.update(namespace+".readMessage", message);
	}
	@Override
	public int getNotReadMessage(MessageDTO message) {
		return sqlSession.selectOne(namespace+".getNotReadMessage", message);
	}
	@Override
	public int getNotReadMessage_toMe(MessageDTO message) {
		return sqlSession.selectOne(namespace+".getNotReadMessage_toMe", message);
	}
	
	

}
