package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

public interface FacilDao {
	public List<PgMemberDTO> reserPgListAll(SearchCriteria cri);
	public List<SrMemberDTO> reserSrListAll(SearchCriteria cri);
	
	public int reserPgListAllCount(SearchCriteria cri);
	public int reserSrListAllCount(SearchCriteria cri);
	public int reserPgListCount(SearchCriteria cri);
	public int reserSrListCount(SearchCriteria cri);

	public List<PgMemberDTO> reserPgList(SearchCriteria cri);
	public List<SrMemberDTO> reserSrList(SearchCriteria cri);
	
	public int userReserPg(PgMemberDTO pgmdto);
	public int userReserSr(SrMemberDTO srmdto);
	
	public List<PgMemberDTO> schedulePgListLoad();
	public List<SrMemberDTO> scheduleSrListLoad();
	
	public List<ScheduleDTO> scheduleToPgList();
	public List<ScheduleDTO> scheduleToSrList();
	
	public List<PlaygroundDTO> schduleTypePgLoadAjax();
	public List<StudyroomDTO> schduleTypeSrLoadAjax();
	
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type);
	//public List<PlaygroundDTO> testchduleNamePgLoadAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schduleNameSrLoadAjax(String sr_type);
	
	public List<PlaygroundDTO> loadPgList(SearchCriteria cri);
	public List<StudyroomDTO> loadSrList(SearchCriteria cri);
	
	public List<PlaygroundDTO> schdulePgNumAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schduleSrNumAjax(StudyroomDTO srdto);
	
	public List<PgMemberDTO> loadPgTimecodeAjax(PgMemberDTO pgmdto);
	public List<SrMemberDTO> loadSrTimecodeAjax(SrMemberDTO srmdto);
	
	public int facilPgDel(String pg_num);
	public int facilSrDel(String sr_num);
	
	public int facilPgModi(PlaygroundDTO pgdto);
	public int facilSrModi(StudyroomDTO srdto);
	
	public int facilPgAdd(PlaygroundDTO pgdto);
	public int facilSrAdd(StudyroomDTO srdto);
	
	public int schdulePgAdd(PgMemberDTO pgmdto);
	public int schduleSrAdd(SrMemberDTO srmdto);
	
	public PgMemberDTO selectPgReser(String pgm_num);
	public SrMemberDTO selectSrReser(String srm_num);
	
	public int deletePgReser(String pgm_num);
	public int deleteSrReser(String srm_num);
}
