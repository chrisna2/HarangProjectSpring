package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.harang.web.domain.MemberDTO;
import com.harang.web.service.LoginService;
import com.harang.web.service.MessageService;
import com.harang.web.service.MyPageService;
import com.harang.web.utill.LoginBean;


public class RefreshInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private LoginService loginService;
	
	//로그인후 페이지가 이동할때마다 새로 새션을 리프레쉬 해주는 인터셉터
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		String m_id = (String)session.getAttribute("m_id");
		
		logger.info("로그인 아이디 : "+m_id);
		
		session.setAttribute("member", loginService.refresh(m_id));
		session.setAttribute("PLH", myPageService.pointListHeader(m_id));
		session.setAttribute("head_msg", messageService.getGivenMessageListHeader(m_id));
		session.setAttribute("NRM", messageService.getNotReadMessage(m_id));
		
	}
}
