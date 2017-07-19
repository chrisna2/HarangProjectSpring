package com.harang.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.MemberDTO;
import com.harang.web.repository.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public MemberDTO login(MemberDTO member) {
		return loginDao.login(member);
	}

	@Override
	public MemberDTO refresh(String m_id) {
		return loginDao.refresh(m_id);
	}

}
