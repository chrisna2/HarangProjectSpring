package com.harang.web.repository;

import java.util.ArrayList;
import java.util.List;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.FoodDTO;

public interface AjaxDao {

	public List<CalanderDTO> umenuList();
	public List<CalanderDTO> amenuList();
	public List<FoodDTO> menuinfo(String f_num);
	
}
