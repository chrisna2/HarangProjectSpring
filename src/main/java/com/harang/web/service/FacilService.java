package com.harang.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

public interface FacilService {
	public List<PgMemberDTO> loadPgReserListAll(SearchCriteria cri);
	public List<SrMemberDTO> loadSrReserListAll(SearchCriteria cri);
	
	public int reserPgListAllCount(SearchCriteria cri);
	public int reserSrListAllCount(SearchCriteria cri);
	
	public int reserPgListCount(SearchCriteria cri);
	public int reserSrListCount(SearchCriteria cri);
	
	public List<PlaygroundDTO> loadPgList(SearchCriteria cri);
	public List<StudyroomDTO> loadSrList(SearchCriteria cri);
	
	public List<PgMemberDTO> loadPgReserList(SearchCriteria cri);
	public List<SrMemberDTO> loadSrReserList(SearchCriteria cri);
	
	public List<PgMemberDTO> schedulePgList();
	public List<SrMemberDTO> scheduleSrList();
	
	public List<PgMemberDTO> pgRsrNumCntEachDate();
	public List<SrMemberDTO> srRsrNumCntEachDate();
	
	public List<ScheduleDTO> scheduleToPg();
	public List<ScheduleDTO> scheduleToSr();
	
	public List<PlaygroundDTO> schPgNameAjax(String pg_type);
	//public List<PlaygroundDTO> testschPgNameAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schSrNameAjax(String sr_type);
	
	public List<PlaygroundDTO> schPgTypeAjax();
	public List<StudyroomDTO> schSrTypeAjax();
	
	public List<PlaygroundDTO> schPgNumAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schSrNumAjax(StudyroomDTO srdto);
	
	public List<PgMemberDTO> pgRsrInfoByDate(String pgm_date);
	public List<SrMemberDTO> srRsrInfoByDate(String srm_date);
	
	public String loadPgTimecodeAjax(PgMemberDTO pgmdto);
	public String loadSrTimecodeAjax(SrMemberDTO srmdto);
	
	public int facilPgAdd(PlaygroundDTO pgdto);
	public int facilSrAdd(StudyroomDTO srdto);
	
	public int facilPgmodi(PlaygroundDTO pgdto);
	public int facilSrmodi(StudyroomDTO srdto);
	
	public int facilPgDel(String pg_num);
	public int facilSrDel(String pg_num);
	
	public int schPgAdd(PgMemberDTO pgmdto);
	public int schSrAdd(SrMemberDTO srmdto);
	
	public int userReserPg(PgMemberDTO pgmdto);
	public int userReserSr(SrMemberDTO srmdto);
	
	public int deleteReserPg(String pgm_num);
	public int deleteReserSr(String srm_num);
}
