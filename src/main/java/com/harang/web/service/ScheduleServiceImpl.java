package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.ScheduleDao;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	

	@Override
	public void schJoin(String s_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schJoinCancle(String s_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ScheduleDTO> schList(int m_grade) {
		
		return scheduleDao.schList(m_grade);
	}

	@Override
	public void schPost(String s_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schDelete(String s_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schUpdate(String s_num) {
		// TODO Auto-generated method stub
		
	}

	

	
	
	

}
