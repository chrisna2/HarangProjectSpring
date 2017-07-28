package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.BambooDao;

@Service
public class BambooServiceImpl implements BambooService {

	@Autowired
	private BambooDao bambooDao;

	@Override
	public List<BambooDTO> bbList(SearchCriteria cri) {
		return bambooDao.bbList(cri);
	}

	@Override
	public List<BambooDTO> bbNList() {
		

		
		return bambooDao.bbNList();
	}

	@Override
	public BambooDTO bbDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbRList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbLike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbDLike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbLikeCancle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbDLikeCancle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bbListCount(SearchCriteria cri) {
		
		return bambooDao.bbListCount(cri);
	}
	
	

}
