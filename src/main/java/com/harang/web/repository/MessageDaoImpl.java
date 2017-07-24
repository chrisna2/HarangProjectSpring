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
	public List<MessageDTO> getGivenMessageList(String m_id) {
		return sqlSession.selectList(namespace+".getGivenMessageList", m_id);
	}
	@Override
	public List<MessageDTO> getSentMessageList(String m_id) {
		return sqlSession.selectList(namespace+".getSentMessageList", m_id);
	}
	@Override
	public List<MessageDTO> getToMeMessageList(String m_id) {
		return sqlSession.selectList(namespace+".getToMeMessageList", m_id);
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
	public void readMessage(String t_num) {
		sqlSession.update(namespace+".readMessage", t_num);
	}
	@Override
	public int getNotReadMessage(String m_id) {
		return sqlSession.selectOne(namespace+".getNotReadMessage", m_id);
	}
	@Override
	public int getNotReadMessage_toMe(String m_id) {
		return sqlSession.selectOne(namespace+".getNotReadMessage_toMe", m_id);
	}
	
	

}
