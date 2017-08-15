package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;

public interface ScheduleService {

	public void schJoin(String s_num);
	public void schJoinCancle(String s_num);
	public List<ScheduleDTO> schList(int m_grade);
	
	public void schPost(String s_num);
	public void schDelete(String s_num);
	public void schUpdate(String s_num);
	

}
