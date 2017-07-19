package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.plugin.Intercepts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.harang.web.domain.MemberDTO;
import com.harang.web.service.MyPageService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Autowired
	private MyPageService myPageService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member") != null){
			logger.info("clear login data before");
			//새로 로그인을 하면 이전에 로그인 했던 모든 세션기록은 사라짐.
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
		MemberDTO loginData = (MemberDTO)modelMap.get("loginData");
		
		if(null == loginData){
			logger.info("login fail");
			request.setAttribute("loginType", "bad");
			return;
		}
		
		String m_dept = loginData.getM_dept();
		String m_mail = loginData.getM_mail();
		String m_tel = loginData.getM_tel();
		String m_addr = loginData.getM_addr();
		String m_id = loginData.getM_id();
		
		
			if(!m_dept.equals("관리자") && null != m_mail && null != m_tel && null != m_addr){
				logger.info("member login");
				session.setAttribute("member", loginData);
				session.setAttribute("PLH", myPageService.pointListHeader(m_id));
				//session.setAttribute("head_msg", messege.getGivenMessageListMini(input_id));
				//session.setAttribute("NRM", messege.getNotReadMessage(input_id));
				request.setAttribute("loginType", "member");
			}
			else if(m_dept.equals("관리자")){
				logger.info("admin login");
				session.setAttribute("admin", loginData);
				session.setAttribute("PLH", myPageService.pointListHeader(m_id));
				//session.setAttribute("PLH", point.pointListHeader(input_id));
				//session.setAttribute("head_msg", messege.getGivenMessageListMini(input_id));
				//session.setAttribute("NRM", messege.getNotReadMessage(input_id));
				request.setAttribute("loginType", "admin");
			}
			else if(null == m_mail && null == m_tel && null == m_addr){
				logger.info("newbee login");
				session.setAttribute("newbee", loginData);
				request.setAttribute("loginType", "newbee");
			}
			//로그인 포스트(loginPost.jsp)로 가서 "loginType"데이터를 근거로 페이지 이동함
		
	}
	
}
