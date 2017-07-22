package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.service.LoginService;
import com.harang.web.service.MyPageService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;
	
	@RequestMapping(value="/login")
	public void Login(){
	}
	
	//로그인 권한명 설정
	@Secured({"ROLE_MEMBER","ROLE_ADMIN","ROLE_NEWBEE"})
	@RequestMapping(value="/loginPost")
	public String LoginPost(HttpSession session,HttpServletRequest request){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("LoginPost : " + auth.toString());
		
		String chechek = request.getParameter("fail");
		
		System.out.println(chechek);
		
		String check = auth.getAuthorities().toString();
		String m_id = auth.getName();
		String detail = auth.getDetails().toString();
		
		//여기서 세션 값 처리를 해줘야 한다.
		System.out.println("권한 : "+ check);
		System.out.println("아이디 : "+ m_id);
		System.out.println("디테일 : " + detail);
		
		
		if("[ROLE_MEMBER]".equals(check)){
			session.setAttribute("member", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			request.setAttribute("loginType", "member");
		}
		else if("[ROLE_ADMIN]".equals(check)){
			session.setAttribute("admin", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			request.setAttribute("loginType", "admin");
		}
		else if("[ROLE_NEWBEE]".equals(check)){
			session.setAttribute("newbee", loginService.refresh(m_id));
			request.setAttribute("loginType", "newbee");
		}
		else if("[ROLE_ANONYMOUS]".equals(check)){
			request.setAttribute("loginType", "bad");
		}
		
		
		return "login/loginPost";
	}
	
	@RequestMapping(value="/logout" ,method = RequestMethod.GET)
	public ModelAndView logoutGet(HttpSession session){
		
		session.invalidate();
		
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	

	@RequestMapping(value="/main" ,method = RequestMethod.GET)
	public ModelAndView loginMainGet(){
		ModelAndView mav = new ModelAndView("login/main");
		return mav;
	}
	@RequestMapping(value="/a_main" ,method = RequestMethod.GET)
	public ModelAndView loginAmainGet(){
		ModelAndView mav = new ModelAndView("login/a_main");
		return mav;
	}
	@RequestMapping(value="/main" ,method = RequestMethod.POST)
	public ModelAndView loginMainPost(){
		ModelAndView mav = new ModelAndView("login/main");
		return mav;
	}
	@RequestMapping(value="/a_main" ,method = RequestMethod.POST)
	public ModelAndView loginAmainPost(){
		ModelAndView mav = new ModelAndView("login/a_main");
		return mav;
	}
}
