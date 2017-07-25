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
	
	//@Autowired
	//private BCryptPasswordEncoder bcrypt;
		
	
	@Override
	public MemberDTO login(MemberDTO member) {
		return sqlSession.selectOne(namespace+".login", member);
	}


	@Override
	public MemberDTO refresh(String m_id) {
		return sqlSession.selectOne(namespace+".refresh", m_id);
	}


	@Override
	public void register(MemberDTO member) {
		//회원 가입시 비밀 번호 암호화는 잠시 미뤄둠
		//member.setM_pw(bcrypt.encode(member.getM_pw()));
		sqlSession.update(namespace+".register", member);
		sqlSession.update(namespace+".toMember", member.getM_id());
		
		
	}


}
