package com.harang.web.repository;

import com.harang.web.domain.FoodDTO;

public interface FoodDao {
	
	public void updateFood(FoodDTO food);
	public void deleteFood(FoodDTO food);
	public void insertFood(FoodDTO food);
	
}
