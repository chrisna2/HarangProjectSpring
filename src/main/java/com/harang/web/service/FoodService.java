package com.harang.web.service;

import com.harang.web.domain.FoodDTO;

public interface FoodService {
	
	public void updateFood(FoodDTO food);
	public void deleteFood(FoodDTO food);
	public void insertFood(FoodDTO food);

}
