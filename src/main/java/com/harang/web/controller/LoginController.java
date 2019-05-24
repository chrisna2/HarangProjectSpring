package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.service.LoginService;
import com.harang.web.service.MessageService;
import com.harang.web.service.MyPageService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/login")
	public void Login(){
	}
	
	//로그인 권한명 설정
	@Secured({"ROLE_MEMBER","ROLE_ADMIN","ROLE_NEWBEE"})
	@RequestMapping(value="/loginPost")
	public String LoginPost(HttpSession session,HttpServletRequest request){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println("LoginPost : " + auth.toString());
		
		String chechek = request.getParameter("fail");
		
		//System.out.println(chechek);
		
		String check = auth.getAuthorities().toString();
		String m_id = auth.getName();
		String detail = auth.getDetails().toString();
		
		//여기서 세션 값 처리를 해줘야 한다.
		//System.out.println("권한 : "+ check);
		//System.out.println("아이디 : "+ m_id);
		//System.out.println("디테일 : " + detail);
		
		
		if("[ROLE_MEMBER]".equals(check)){
			session.setAttribute("member", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			session.setAttribute("head_msg", messageService.getGivenMessageListHeader(m_id));
			session.setAttribute("NRM", messageService.getNotReadMessage(m_id));
			request.setAttribute("loginType", "member");
		}
		else if("[ROLE_ADMIN]".equals(check)){
			session.setAttribute("admin", loginService.refresh(m_id));
			session.setAttribute("PLH", myPageService.pointListHeader(m_id));
			session.setAttribute("head_msg", messageService.getGivenMessageListHeader(m_id));
			session.setAttribute("NRM", messageService.getNotReadMessage(m_id));
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
	
	@RequestMapping(value="/badAccess")
	public String badAccess(HttpSession session,HttpServletRequest request){
		return "login/badAccess";
	}
}


/*
//multipart로는 중간에 request가 걸려서 사용 할수 없음. cos.jar사용 불가.
String realPath = request.getServletContext().getRealPath("upload/member");

//파일 폴더가 없으면 생성해주는 기능
File dir = new File(realPath);
if(!dir.isDirectory()){
dir.mkdirs();
}

int maxSize = 1024 * 1024 * 1024; 
MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new RandomFileRenamePolicy());

String photoName = null;

Enumeration enumer =  multi.getFileNames();
while(enumer.hasMoreElements()){
String name = (String)enumer.nextElement();
File file = multi.getFile(name);
photoName = realPath+"\\"+multi.getFilesystemName(name);
}

MemberDTO member = new MemberDTO();

member.setM_pw(multi.getParameter("m_pw"));
member.setM_mail(multi.getParameter("m_mail"));
member.setM_addr(multi.getParameter("m_addr"));
member.setM_tel(multi.getParameter("m_tel"));
member.setM_photo(photoName);
member.setM_id(multi.getParameter("m_id"));
*/


