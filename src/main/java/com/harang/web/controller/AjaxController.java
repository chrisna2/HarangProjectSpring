package com.harang.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.FoodDTO;
import com.harang.web.service.AjaxService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private AjaxService ajaxService;

	ModelAndView mav;
	Gson gson;
	PrintWriter out;
	
	@RequestMapping(value="/food", method = RequestMethod.GET)
	public void amenuListGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//반복되는 코드임.. 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		out = response.getWriter();
		gson = new Gson();
		
		
		List<CalanderDTO> list = new ArrayList<>();
		
		String check = request.getParameter("check");	
		
		if("umenu".equals(check)){
			
		}
		else if("amenu".equals(check)){
			list = ajaxService.amenuList();
		}
		
		
		//반복되는 코드임.. 
		String jsonList = gson.toJson(list);
		out.write(jsonList);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/foodinfo", method = RequestMethod.GET)
	public void amenuinfo(String f_num, HttpServletResponse response) throws IOException{
		//반복되는 코드임.. 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		out = response.getWriter();
		gson = new Gson();
		
		
		List<FoodDTO> list = new ArrayList<>();
		
		list = ajaxService.menuinfo(f_num);
		
		//반복되는 코드임.. 
		String jsonList = gson.toJson(list);
		out.write(jsonList);
		out.flush();
		out.close();
		
	}
	
}
