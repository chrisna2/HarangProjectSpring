package com.harang.web.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.service.FoodService;
import com.harang.web.utill.PageMaker;

@Controller
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	private ModelAndView mav;
	
	
	// 학식 메뉴 등록 수정 페이지 관련 컨트롤러. 시작! //
	@RequestMapping(value="/Amenu",method = RequestMethod.GET)
	public ModelAndView amenuPost(){
		mav = new ModelAndView("food/a_menuList");
		return mav;
	}
	@RequestMapping(value="/AmenuUpdate",method = RequestMethod.POST)
	public ModelAndView AmenuUpdatePost(FoodDTO food){
		foodService.updateFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	@RequestMapping(value="/AmenuCreate",method = RequestMethod.POST)
	public ModelAndView AmenuCreatePost(FoodDTO food){
		foodService.insertFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	@RequestMapping(value="/AmenuDelete",method = RequestMethod.POST)
	public ModelAndView AmenuDeletePost(FoodDTO food){
		foodService.deleteFood(food);
		mav = new ModelAndView("redirect:/food/Amenu");
		return mav;
	}
	//ajax
	@RequestMapping(value="/amenuJson")
	public @ResponseBody List<CalanderDTO> amenuJson(){
		List<CalanderDTO> list = foodService.amenuJson();
		return list;
	}
	@RequestMapping(value="/afoodinfo")
	public @ResponseBody List<FoodDTO> afoodinfoJson(String f_num){
		List<FoodDTO> list = foodService.afoodinfoJson(f_num);
		return list;
	}
	// 학식 메뉴 등록 수정 페이지 관련 컨트롤러. 끝! //
	
	// 학식 메뉴 티켓 판매 확인. 시작 //
	@RequestMapping(value="/Aticket",method = RequestMethod.GET)
	public ModelAndView aticketGet(SearchCriteria cri){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(foodService.aticketCountPaging());
		
		mav = new ModelAndView("food/a_ticketList");
		
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("fmlist", foodService.aticketList(cri));
		
		return mav;
	}
	
	
	@RequestMapping(value="/menu",method = RequestMethod.GET)
	public ModelAndView menuGet(){
		mav = new ModelAndView("food/menuList");
		return mav;
	}
	
	@RequestMapping(value="/ticket",method = RequestMethod.GET)
	public ModelAndView ticketGet(){
		mav = new ModelAndView("food/ticketList");
		return mav;
	}
	
	
}
