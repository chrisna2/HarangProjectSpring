package com.harang.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;
import com.harang.web.service.BambooService;
import com.harang.web.service.FacilService;
import com.harang.web.service.LoginService;
import com.harang.web.service.MessageService;
import com.harang.web.service.MyPageService;
import com.harang.web.service.ParttimeService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.UploadBean;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ParttimeService parttimeService;
	
	@Autowired
	private BambooService bambooService;
	
	@Autowired
	private FacilService facilService;
	
	private String uploadPath;
	
	
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
	
	@RequestMapping(value="/regform" ,method = RequestMethod.GET)
	public ModelAndView regformGet(){
		ModelAndView mav = new ModelAndView("login/regform");
		mav.addObject("sido", myPageService.sidoList());
		return mav;
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
	
	
	
	@RequestMapping(value="/regform" ,method = RequestMethod.POST)
	public ModelAndView regformPost(MemberDTO member, HttpSession session, HttpServletRequest request, MultipartFile file) throws IOException{
		
		//파일업로드는 이제 이렇게 간단하게 가능하다.
		//웹서버의 고정 경로 찾기 : 실제 도메인 관련
		uploadPath = request.getSession().getServletContext().getRealPath("/");
		//현재 웹서버 저장 경로 : C:\NahyunKee\FrameWorkspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\HarangProjectSpring\

		//실제 파일 저장 메소드 호춯!
		String uploadedFileName = UploadBean.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
		member.setM_photo(uploadedFileName);
		
		loginService.register(member);
			
		System.out.println(uploadedFileName);
		
		ModelAndView mav = new ModelAndView("/login/loginPost");
		
		session.invalidate();
		
		mav.addObject("loginType", "confirm");
		
		return mav;
	}
	
	
	@RequestMapping(value="/main" ,method = RequestMethod.GET)
	public ModelAndView loginMainGet(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		int tt_grade = member.getM_grade();
		int tt_term = 0;
	
		Calendar cal = Calendar.getInstance();
		
		int nowMonth = cal.get(Calendar.MONTH) + 1; 
		
			if(nowMonth>=3&&nowMonth<9){
				tt_term = 1;
			}
			else if((nowMonth>=1&&nowMonth<3)||(nowMonth>=9&&nowMonth<=12)){
				tt_term = 2;
			}
		
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setTt_grade(tt_grade);
		cri.setTt_term(tt_term);
		
		ModelAndView mav = new ModelAndView("login/main");
		mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	@RequestMapping(value="/main" ,method = RequestMethod.POST)
	public ModelAndView loginMainPost(HttpServletRequest request){
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		int tt_grade = member.getM_grade();
		int tt_term = 0;
	
		Calendar cal = Calendar.getInstance();
		
		int nowMonth = cal.get(Calendar.MONTH) + 1; 
		
			if(nowMonth>=3&&nowMonth<9){
				tt_term = 1;
			}
			else if((nowMonth>=1&&nowMonth<3)||(nowMonth>=9&&nowMonth<=12)){
				tt_term = 2;
			}
		
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setTt_grade(tt_grade);
		cri.setTt_term(tt_term);
		
		ModelAndView mav = new ModelAndView("login/main");
		mav.addObject("ttlist", myPageService.timeTalbeLesson(cri));
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	
	@RequestMapping(value="/a_main" ,method = RequestMethod.GET)
	public ModelAndView loginAmainGet(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setPerPageNum(25);
		
		ModelAndView mav = new ModelAndView("login/a_main");
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("scPglist", facilService.scheduleToPg());
		mav.addObject("scSrlist", facilService.scheduleToSr());
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
	}
	
	@RequestMapping(value="/a_main" ,method = RequestMethod.POST)
	public ModelAndView loginAmainPost(HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		SearchCriteria cri = new SearchCriteria();
			
		cri.setM_id(member.getM_id());
		cri.setPerPageNum(25);
			
		ModelAndView mav = new ModelAndView("login/a_main");
		mav.addObject("pList",  myPageService.pointListSearch(cri));
		mav.addObject("scPglist", facilService.scheduleToPg());
		mav.addObject("scSrlist", facilService.scheduleToSr());
		mav.addObject("p_list", parttimeService.getParttimeList(cri));
		mav.addObject("d_list", parttimeService.getDaetaList(cri));
		mav.addObject("blist",  bambooService.bbList(cri));
		return mav;
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


