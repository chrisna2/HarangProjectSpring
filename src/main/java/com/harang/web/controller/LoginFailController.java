package com.harang.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailController implements AuthenticationFailureHandler {
	
	private String m_id_p;
	private String m_pw_p;
	private String fail_url;

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
			throws IOException, ServletException {
		
		String m_id = req.getParameter(m_id_p);
		String m_pw = req.getParameter(m_pw_p);
		
		req.setAttribute(m_id, "m_id");
		req.setAttribute(m_pw, "m_pw");
		
		req.getRequestDispatcher(fail_url).forward(req, res);
		
	}
	
	public String getM_id_p() {
		return m_id_p;
	}

	public void setM_id_p(String m_id_p) {
		this.m_id_p = m_id_p;
	}

	public String getM_pw_p() {
		return m_pw_p;
	}

	public void setM_pw_p(String m_pw_p) {
		this.m_pw_p = m_pw_p;
	}

	public String getFail_url() {
		return fail_url;
	}

	public void setFail_url(String fail_url) {
		this.fail_url = fail_url;
	}
}
