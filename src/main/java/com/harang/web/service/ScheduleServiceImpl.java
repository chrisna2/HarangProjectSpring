package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SearchCriteria2;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.repository.ScheduleDao;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	

	@Override
	public void schJoin(SearchCriteria2 cri) {
		scheduleDao.schJoin(cri);
		
	}

	@Override
	public void schJoinCancle(SearchCriteria2 cri) {
		scheduleDao.schJoinCancle(cri);
		
	}

	@Override
	public List<ScheduleDTO> schList(SearchCriteria2 cri) {
		
		return scheduleDao.schList(cri);
	}
	
	@Override
	public List<ScheduleDTO> aschList(SearchCriteria2 cri) {
		
		return scheduleDao.aschList(cri);
	}

	@Override
	public void schPost(ScheduleDTO scheduleDTO) {
		scheduleDao.schPost(scheduleDTO);
		
	}

	@Override
	public void schDelete(String s_num) {
		scheduleDao.schDelete(s_num);
		
	}

	@Override
	public void schUpdate(ScheduleDTO scheduleDTO) {
		scheduleDao.schUpdate(scheduleDTO);
		
	}

	@Override
	public ScheduleDTO schRead(String s_num) {
		
		return scheduleDao.schRead(s_num);
	}

	@Override
	public List<CalanderDTO> schCal(SearchCriteria2 cri, HttpServletRequest req) {
		return scheduleDao.schCal(cri, req);
	}

	@Override
	public List<CalanderDTO> schCal2() {
		return scheduleDao.schCal2();
	}

	@Override
	public int schListCount(SearchCriteria2 cri) {
		return scheduleDao.schListCount(cri);
	}

	@Override
	public List<SrMemberDTO> srList() {
		return scheduleDao.srList();
	}

	@Override
	public List<PgMemberDTO> pgList() {
		return scheduleDao.pgList();
	}

	@Override
	public List<SrMemberDTO> srName(String sr_type) {
		return scheduleDao.srName(sr_type);
	}

	@Override
	public List<PgMemberDTO> pgName(String pg_type) {
		return scheduleDao.pgName(pg_type);
	}

	@Override
	public int uschListCount(SearchCriteria2 cri) {
		return scheduleDao.uschListCount(cri);
	}

	

	

	
	
	

}
