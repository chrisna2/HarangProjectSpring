package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.SearchCriteria;

public interface FoodDao {
	
	public void updateFood(FoodDTO food);
	public void deleteFood(FoodDTO food);
	public void insertFood(FoodDTO food);

	//ajax사용 
	public List<CalanderDTO> amenuJson();
	public List<FoodDTO> afoodinfoJson(String f_num);
	
	
	//티켓 리스트 출력
	public List<FoodMemberDTO> aticketList(SearchCriteria cri);
	public int aticketCountPaging();
	
	
	
	
}
