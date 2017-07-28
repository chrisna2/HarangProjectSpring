package com.harang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.service.FacilService;

@Controller
@RequestMapping("/facil")
public class FacilController {

	@Autowired
	private FacilService facilService;
	
	@RequestMapping(value="/FacilMain", method=RequestMethod.GET)
	public ModelAndView AllloadList(){
		ModelAndView mav = new ModelAndView("/facil/facilities_main");
		
		List pgmlist = facilService.loadPgmlist();
		List srmlist = facilService.loadSrmlist();
		
		mav.addObject("pgmlist", pgmlist);
		mav.addObject("srmlist", srmlist);
		
		return mav;
	}
}