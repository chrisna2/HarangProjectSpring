package com.harang.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.FoodDTO;
import com.harang.web.repository.AjaxDao;

@Service
public class AjaxServiceImpl implements AjaxService {

	@Autowired
	private AjaxDao ajaxDao;
	
	@Override
	public List<CalanderDTO> umenuList() {
		return null;
	}

	@Override
	public List<CalanderDTO> amenuList() {
		return ajaxDao.amenuList();
	}

	@Override
	public List<FoodDTO> menuinfo(String f_num) {
		return ajaxDao.menuinfo(f_num);
	}

}
