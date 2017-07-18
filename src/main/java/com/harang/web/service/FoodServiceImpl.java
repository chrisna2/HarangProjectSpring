package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.SearchCriteria;
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

	//Ajax
	@Override
	public List<CalanderDTO> amenuJson() {
		return foodDao.amenuJson();
	}
	@Override
	public List<FoodDTO> afoodinfoJson(String f_num) {
		return foodDao.afoodinfoJson(f_num);
	}

	//티켓 판매 내역 조회
	@Override
	public List<FoodMemberDTO> aticketList(SearchCriteria cri) {
		return foodDao.aticketList(cri);
	}
	@Override
	public int aticketCountPaging() {
		return foodDao.aticketCountPaging();
	}

}
