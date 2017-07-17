package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.harang.web.domain.MemberDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		//한 세션에서 중복 로그인을 막기위함
		if(session.getAttribute("member") != null){
			logger.info("clear login data before");
			session.removeAttribute("admin");
			session.removeAttribute("member");
			session.removeAttribute("newbee");
		}
		if(session.getAttribute("admin") != null){
			logger.info("clear login data before");
			session.removeAttribute("admin");
			session.removeAttribute("member");
			session.removeAttribute("newbee");
		}
		
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		MemberDTO member = (MemberDTO)modelMap.get("member");
		
		if(null == member){
			logger.info("login fail");
			request.setAttribute("loginType", "bad");
			return;
		}
		
		String m_dept = member.getM_dept();
		String m_mail = member.getM_mail();
		String m_tel = member.getM_tel();
		String m_addr = member.getM_addr();
		
		
			//일반 회원 일때
			if(!m_dept.equals("관리자") && null != m_mail && null != m_tel && null != m_addr){
				logger.info("member login");
				session.setAttribute("member", member);
				//session.setAttribute("PLH", point.pointListHeader(input_id));
				//session.setAttribute("head_msg", messege.getGivenMessageListMini(input_id));
				//session.setAttribute("NRM", messege.getNotReadMessage(input_id));
				
				request.setAttribute("loginType", "member");
			}
			//관리자 일때.
			else if(m_dept.equals("관리자")){
				logger.info("admin login");
				session.setAttribute("admin", member);
				//session.setAttribute("PLH", point.pointListHeader(input_id));
				//session.setAttribute("head_msg", messege.getGivenMessageListMini(input_id));
				//session.setAttribute("NRM", messege.getNotReadMessage(input_id));
				
				request.setAttribute("loginType", "admin");
			}
			//신규 회원 일때.
			else if(null == m_mail && null == m_tel && null == m_addr){
				logger.info("newbee login");
				session.setAttribute("newbee", member);
				
				request.setAttribute("loginType", "newbee");
			}
		
	}
	
}
