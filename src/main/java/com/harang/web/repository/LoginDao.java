package com.harang.web.repository;

import com.harang.web.domain.MemberDTO;

public interface LoginDao {

	public MemberDTO login(MemberDTO member);
	public MemberDTO refresh(String m_id);
	
	
}
