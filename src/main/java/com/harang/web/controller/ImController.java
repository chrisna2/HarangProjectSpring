package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.Im2DTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.WarningDTO;
import com.harang.web.service.ImService;
import com.harang.web.utill.LoginBean;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/impage")
public class ImController {
	
	
	
	
	
	@Autowired
	private ImService imService;
	
	private ModelAndView mav;

	
	private PageMaker pagemaker ;
	
	
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public ModelAndView getimmain(SearchCriteria cri,HttpServletRequest request){
		
		LoginBean login = new LoginBean();
		MemberDTO member = login.getLoginInfo(request);
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(imService.Imcount(cri));
		
		mav = new ModelAndView("imPage/immain");
		mav.addObject("alllist",imService.ImAlllist(cri));
		mav.addObject("imlist",imService.Immylist(member.getM_id()));
		mav.addObject("pageMaker",pagemaker);
		return mav;
	}
	
	@RequestMapping(value="/main",method = RequestMethod.POST)
	public ModelAndView postimmain(SearchCriteria cri,String m_id){
		
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(imService.Imcount(cri));
		
		mav = new ModelAndView("imPage/immain");
		mav.addObject("alllist",imService.ImAlllist(cri));
		mav.addObject("imlist",imService.Immylist(m_id));
		mav.addObject("pageMaker",pagemaker);
		return mav;
	}
	
	
	

	
	
	@RequestMapping(value="/read",method = RequestMethod.GET)
	public ModelAndView imread(String lm_num){
		

		
		mav = new ModelAndView("imPage/imdetail");
		mav.addObject("read2",imService.Imread(lm_num));
	
		return mav;
	}
	
	
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public ModelAndView imread2(String l_num){
		

		
		mav = new ModelAndView("imPage/immodify");
		mav.addObject("read3",imService.Imread3(l_num));
	
		return mav;
	}
	
	
	
	@RequestMapping(value="/insert",method = RequestMethod.GET)
	public ModelAndView iminsert(String l_num){
		

	
		mav = new ModelAndView("imPage/immake");
		
		mav.addObject("read",imService.Imread2(l_num));
	
		return mav;
	}
	
	
	
	@RequestMapping(value="/insert2",method = RequestMethod.POST)
	public ModelAndView iminsert2(Im2DTO im){

		mav = new ModelAndView("imPage/imcomplete");
		imService.insertIm(im);
		imService.insertIm2(im);
		return mav;
	}
	
	
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ModelAndView imupdate(Im2DTO im){

		mav = new ModelAndView("imPage/imupdate");
		imService.updateIm(im);
		return mav;
	}
	
	@RequestMapping(value="/waring",method = RequestMethod.POST)
	public ModelAndView imWaring(WarningDTO Wr){
		

	
		mav = new ModelAndView("imPage/imwaring");
		
		imService.waringIm(Wr);
	
		return mav;
	}
	
	
	
	

	@RequestMapping(value="/amain",method = RequestMethod.GET)
	public ModelAndView getimamain(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(imService.Imcount(cri));
		
		mav = new ModelAndView("imPage/a_imlist");
		mav.addObject("alllist",imService.Imalist(cri));
		
		mav.addObject("pageMaker",pagemaker);
		
	
		return mav;
	}
	
	
	@RequestMapping(value="/amain",method = RequestMethod.POST)
	public ModelAndView postimamain(SearchCriteria cri){
		
		pagemaker = new PageMaker();
		pagemaker.setCri(cri);
		pagemaker.setTotalCount(imService.Imcount(cri));
		
		mav = new ModelAndView("imPage/a_imlist");
		mav.addObject("alllist",imService.Imalist(cri));
		
		mav.addObject("pageMaker",pagemaker);
		
	
		return mav;
	}
	
	
	
	@RequestMapping(value="/adetail",method = RequestMethod.GET)
	public ModelAndView adeatail(String lm_num){

		mav = new ModelAndView("imPage/a_imdetail");
		mav.addObject("read3",imService.Imaread(lm_num));
		mav.addObject("read4",imService.Imawaringread(lm_num));
	
		return mav;
	}
	
	
	@RequestMapping(value="/adeleteim",method = RequestMethod.POST)
	public ModelAndView adeleteim(Im2DTO im,WarningDTO wn ){

		mav = new ModelAndView("imPage/imdcomplete");
		imService.deleteImwaring(wn);
		imService.deleteim(im);
		
	
	
		return mav;
	}
	
	
	


	
	
	
}
