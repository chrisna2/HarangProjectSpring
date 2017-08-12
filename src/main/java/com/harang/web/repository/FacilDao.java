package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

public interface FacilDao {
	public List<PgMemberDTO> reserPgListAll();
	public List<SrMemberDTO> reserSrListAll();

	public List<PgMemberDTO> reserPgList(String m_id);
	public List<SrMemberDTO> reserSrList(String m_id);
	
	public void userReserPg(PgMemberDTO pgmdto);
	public void userReserSr(SrMemberDTO srmdto);
	
	public List<PgMemberDTO> schedulePgListLoad();
	public List<SrMemberDTO> scheduleSrListLoad();
	
	public List<ScheduleDTO> scheduleToPgList();
	public List<ScheduleDTO> scheduleToSrList();
	
	public List<PlaygroundDTO> schduleTypePgLoadAjax();
	public List<StudyroomDTO> schduleTypeSrLoadAjax();
	
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type);
	//public List<PlaygroundDTO> testchduleNamePgLoadAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schduleNameSrLoadAjax(String sr_type);
	
	public List<PlaygroundDTO> loadPgList();
	public List<StudyroomDTO> loadSrList();
	
	public List<PlaygroundDTO> schdulePgNumAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schduleSrNumAjax(StudyroomDTO srdto);
	
	public List<PgMemberDTO> loadPgTimecodeAjax(PgMemberDTO pgmdto);
	public List<SrMemberDTO> loadSrTimecodeAjax(SrMemberDTO srmdto);
	
	public void facilPgDel(String pg_num);
	public void facilSrDel(String sr_num);
	
	public void facilPgModi(PlaygroundDTO pgdto);
	public void facilSrModi(StudyroomDTO srdto);
	
	public void facilPgAdd(PlaygroundDTO pgdto);
	public void facilSrAdd(StudyroomDTO srdto);
	
	public void schdulePgAdd(PgMemberDTO pgmdto);
	public void schduleSrAdd(SrMemberDTO srmdto);
	
	public PgMemberDTO selectPgReser(String pgm_num);
	public SrMemberDTO selectSrReser(String srm_num);
	
	public void deletePgReser(String pgm_num);
	public void deleteSrReser(String srm_num);
}
