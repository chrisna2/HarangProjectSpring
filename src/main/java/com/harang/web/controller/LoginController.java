package com.harang.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.HomeController;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.ZipDTO;
import com.harang.web.service.LoginService;
import com.harang.web.service.MyPageService;
import com.harang.web.utill.UploadBean;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void Login(){
	}
	/*
	@RequestMapping(value="/logout" ,method = RequestMethod.GET)
	public String logoutGet(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		if (auth != null){ 
			new SecurityContextLogoutHandler().logout(request, response, auth); 
		}
		return "redirect:/";
	}
	*/
	//로그인 권한명 설정
	//로그인 권한명 설정
	@Secured({"ROLE_MEMBER","ROLE_ADMIN","ROLE_NEWBEE"})
	@RequestMapping(value="/loginPost")
	public void LoginPost(HttpSession session,HttpServletRequest request){
		
	}
	
	@RequestMapping(value="/logout")
	public void Logout(){
	}
	
	@RequestMapping(value="/loginFail")
	public String LoginFail(HttpSession session,HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.debug("LoginPost : " + auth.toString());
		logger.debug("입력한아이디 : " + request.getParameter("m_id"));
		
		String check = auth.getAuthorities().toString();
		String m_id = auth.getName();
		
		if("[ROLE_ANONYMOUS]".equals(check)){
			session.setAttribute("m_id", m_id);
			request.setAttribute("loginType", "bad");
		}
		
		return "login/badinfo";
	}
	
	@RequestMapping(value="/badAccess")
	public String badAccess(HttpSession session,HttpServletRequest request){
		return "login/badAccess";
	}
	
	@RequestMapping(value="/gugun")
	public @ResponseBody List<ZipDTO> gugunAjax(HttpServletRequest request) throws UnsupportedEncodingException{

		String sido = URLDecoder.decode(request.getParameter("sido"), "UTF-8" );
		System.out.println(sido);
		
		ZipDTO zip = new ZipDTO();
		
		zip.setSido(sido);
		
		List<ZipDTO> list = myPageService.gugunList(zip);
		
		return list;
	}
	
	@RequestMapping(value="/dong")
	public @ResponseBody List<ZipDTO> dongAjax(HttpServletRequest request) throws UnsupportedEncodingException{
		
		String sido = URLDecoder.decode(request.getParameter("sido"), "UTF-8" );
		String gugun = URLDecoder.decode(request.getParameter("gugun"), "UTF-8" );
		String dong = URLDecoder.decode(request.getParameter("dong"), "UTF-8" );
		
		ZipDTO zip = new ZipDTO();
		
		zip.setSido(sido);
		zip.setGugun(gugun);
		zip.setDong(dong);
		
		List<ZipDTO> list = myPageService.dongList(zip);
		
		return list;
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


