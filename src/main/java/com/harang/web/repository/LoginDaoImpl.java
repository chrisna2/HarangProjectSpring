package com.harang.web.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.MemberDTO;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.login-mapper";
		
	
	@Override
	public MemberDTO login(MemberDTO member) {
		
		return sqlSession.selectOne(namespace+".login", member);
	}


	@Override
	public MemberDTO refresh(String m_id) {
		return sqlSession.selectOne(namespace+".refresh", m_id);
	}

}
