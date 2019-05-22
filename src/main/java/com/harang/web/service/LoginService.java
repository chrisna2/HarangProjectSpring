package com.harang.web.service;

import com.harang.web.domain.MemberDTO;

public interface LoginService {

	public MemberDTO login(MemberDTO member);
	public MemberDTO refresh(String m_id);
	
	public void register(MemberDTO member);

}
