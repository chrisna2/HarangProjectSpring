package com.harang.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.FoodDTO;
import com.harang.web.repository.FoodDao;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodDao foodDao;
	
	@Override
	public void updateFood(FoodDTO food) {
		foodDao.updateFood(food);
	}

	@Override
	public void deleteFood(FoodDTO food) {
		foodDao.deleteFood(food);
	}

	@Override
	public void insertFood(FoodDTO food) {
		foodDao.insertFood(food);
	}

}
