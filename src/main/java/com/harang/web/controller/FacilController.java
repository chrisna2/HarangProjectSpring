package com.harang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.service.FacilService;

@Controller("/facil")
public class FacilController {

	private FacilService facilService;
	
	@RequestMapping(value="/facilMain", method=RequestMethod.GET)
	public ModelAndView AllloadList(){
		ModelAndView mav = new ModelAndView("/facil/facilities_main");
		facilService.allList();
		return mav;
	}
}