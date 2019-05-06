package com.harang.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//권한을 체크하는 인터셉터다. 이것을 응용하면 각 페이지가 이동할 때마다 새롭게 세션을 받아오는 인터셉트를 만들수 있을지도 모른다.
		HttpSession session = request.getSession();
		
		if(null == session.getAttribute("member") && null == session.getAttribute("admin")){
			logger.info("current user is not a member");
			//saveDest(request);
			response.sendRedirect("/");
			
			return false;
		}
		
		return true;
	}
	
	/*
	private void saveDest(HttpServletRequest req){
			
			String uri = req.getRequestURI();
			
			String query = req.getQueryString();
			
			if(query==null||query.equals("null")){
				query="";
			}else{
				query="?"+query;
			}
			
			if(req.getMethod().equals("GET")){
				
				req.getSession().setAttribute("dest", uri+query);
				
			}
		}
	*/
	
}
