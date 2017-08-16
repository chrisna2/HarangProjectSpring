package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SearchCriteria2;
import com.harang.web.domain.SrMemberDTO;

public interface ScheduleService {

	public void schJoin(SearchCriteria2 cri);
	public void schJoinCancle(SearchCriteria2 cri);
	public List<ScheduleDTO> schList(SearchCriteria2 cri);
	public List<ScheduleDTO> aschList(SearchCriteria2 cri);
	
	public void schPost(ScheduleDTO scheduleDTO);
	public void schDelete(String s_num);
	public void schUpdate(ScheduleDTO scheduleDTO);
	
	public ScheduleDTO schRead(String s_num);
	public List<CalanderDTO> schCal(SearchCriteria2 cri, HttpServletRequest req);
	public List<CalanderDTO> schCal2();
	
	public int schListCount(SearchCriteria2 cri);
	
	public List<SrMemberDTO> srList();
	public List<PgMemberDTO> pgList();
	
	public List<SrMemberDTO> srName(String sr_type);
	public List<PgMemberDTO> pgName(String pg_type);

}
