package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//권한을 체크하는 인터셉터다. 이것을 응용하면 각 페이지가 이동할 때마다 새롭게 세션을 받아오는 인터셉트를 만들수 있을지도 모른다.
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		logger.debug(uri);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String check = auth.getAuthorities().toString();
		
		if("/".equals(uri)) {
			if(null == session.getAttribute("m_id")) {
				return true;
			}
			else {
				if("[ROLE_MEMBER]".equals(check)){
					response.sendRedirect("/base/main");
				}
				else if("[ROLE_ADMIN]".equals(check)){
					response.sendRedirect("/base/a_main");
				}
				else if("[ROLE_NEWBEE]".equals(check)){
					response.sendRedirect("/login/regform");
				}
				else if("[ROLE_ANONYMOUS]".equals(check)){
					logger.warn("this user is [ROLE_ANONYMOUS]");
					return true;
				}
				return false;
			}
		}
		else {
			if(null == session.getAttribute("m_id")){
				logger.info("current user is not a member or an user");
				saveDest(request);
				response.sendRedirect("/login/badAccess");
				return false;
			}
			else {
				if("/login/regform".equals(uri)) {
					if("[ROLE_MEMBER]".equals(check)){
						response.sendRedirect("/base/main");
					}
					else if("[ROLE_ADMIN]".equals(check)){
						response.sendRedirect("/base/a_main");
					}
					else if("[ROLE_ANONYMOUS]".equals(check)){
						response.sendRedirect("/");
					}
					return false;
				}
				else {
					if("[ROLE_NEWBEE]".equals(check)){
						logger.info("current user is newbee user, plaese regist first");
						response.sendRedirect("/login/regform");
						return false;
					}
					else if("[ROLE_ANONYMOUS]".equals(check)){
						logger.warn("this user is [ROLE_ANONYMOUS], call the administrator");
						response.sendRedirect("/login/badAccess");
						return false;
					}
				}
			}
			return true;
		}
		
	}
	
	private void saveDest(HttpServletRequest req){
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		if(query==null||query.equals("null")){
			query="";
		}else{
			query="?"+query;
		}
		if(req.getMethod().equals("GET")){
			logger.info("dest: "+(uri+query));
			req.getSession().setAttribute("dest", uri+query);
			
		}
	}
	
}
