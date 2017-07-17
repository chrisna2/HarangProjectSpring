package com.harang.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harang.web.domain.FoodDTO;
import com.harang.web.service.FoodService;

@Controller
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	private ModelAndView mav;
	
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
	
	
	@RequestMapping(value="/Amenu",method = RequestMethod.GET)
	public ModelAndView amenuGet(HttpServletRequest request, HttpServletResponse response){
		
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
	
	@RequestMapping(value="/Aticket",method = RequestMethod.GET)
	public ModelAndView aticketGet(){
		
		mav = new ModelAndView("food/a_ticketList");
		
		return mav;
	}
	
	
}
