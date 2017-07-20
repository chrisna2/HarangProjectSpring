package com.harang.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.MemberDTO;
import com.harang.web.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/loginPost",method = RequestMethod.POST)
	public void LoginPost(MemberDTO member,HttpSession session,Model model){
		
		MemberDTO loginData = loginService.login(member);
		
		if(null == loginData){
			return;
		}
		
		model.addAttribute("loginData",loginData);
		
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
