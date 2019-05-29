package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.harang.web.service.LoginService;
import com.harang.web.service.MessageService;
import com.harang.web.service.MyPageService;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MessageService messageService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		logger.debug("SESSION DATA Before :" + session.getAttribute("m_id"));
		//세션에 데이터가 있는 경우 무조건 다 지워 주고 시직한다.
		if(null != session.getAttribute("m_id")) {
			session.removeAttribute("admin");
			session.removeAttribute("member");
			session.removeAttribute("newbee");
			session.removeAttribute("admin");
			session.removeAttribute("PLH");
			session.removeAttribute("head_msg");
			session.removeAttribute("NRM");
			session.removeAttribute("m_id");
			session.invalidate();
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse response, 
						   Object handler,
						   ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("LoginPost : " + auth.toString());
		
		String check = auth.getAuthorities().toString();
		String m_id = auth.getName();
		String detail = auth.getDetails().toString();
		
		logger.debug("...SET THE SESSION DATA");
		logger.debug(check);
		logger.debug(m_id);
		logger.debug(detail);
		
		//세션 데이터 입력
		if("[ROLE_MEMBER]".equals(check)){
			session.setAttribute("member", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			session.setAttribute("head_msg", messageService.getGivenMessageListHeader(m_id));
			session.setAttribute("NRM", messageService.getNotReadMessage(m_id));
			session.setAttribute("m_id", m_id);
			request.setAttribute("loginType", "member");
		}
		else if("[ROLE_ADMIN]".equals(check)){
			session.setAttribute("admin", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			session.setAttribute("head_msg", messageService.getGivenMessageListHeader(m_id));
			session.setAttribute("NRM", messageService.getNotReadMessage(m_id));
			session.setAttribute("m_id", m_id);
			request.setAttribute("loginType", "admin");
		}
		else if("[ROLE_NEWBEE]".equals(check)){
			session.setAttribute("newbee", loginService.refresh(m_id));
			session.setAttribute("m_id", m_id);
			request.setAttribute("loginType", "newbee");
		}
		else if("[ROLE_ANONYMOUS]".equals(check)){
			session.setAttribute("m_id", m_id);
			request.setAttribute("loginType", "bad");
		}
	}
	
}
