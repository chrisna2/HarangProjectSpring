package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

public interface FacilService {
	public List<PgMemberDTO> loadPgReserListAll();
	public List<SrMemberDTO> loadSrReserListAll();
	
	public List<PlaygroundDTO> loadPgList();
	public List<StudyroomDTO> loadSrList();
	
	public List<PgMemberDTO> loadPgReserList(String m_id);
	public List<SrMemberDTO> loadSrReserList(String m_id);
	
	public List<PgMemberDTO> schedulePgList();
	public List<SrMemberDTO> scheduleSrList();
	
	public List<ScheduleDTO> scheduleToPg();
	public List<ScheduleDTO> scheduleToSr();
	
	public List<PlaygroundDTO> schPgNameAjax(String pg_type);
	public List<StudyroomDTO> schSrNameAjax(String sr_type);
	
	public List<PlaygroundDTO> schPgTypeAjax();
	public List<StudyroomDTO> schSrTypeAjax();
	
	public List<PlaygroundDTO> schPgNumAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schSrNumAjax(StudyroomDTO srdto);
	
	public String loadPgTimecodeAjax(PgMemberDTO pgmdto);
	public List<SrMemberDTO> loadSrTimecodeAjax(SrMemberDTO srmdto);
	
	public void facilPgAdd(PlaygroundDTO pgdto);
	public void facilSrAdd(StudyroomDTO srdto);
	
	public void facilPgmodi(PlaygroundDTO pgdto);
	public void facilSrmodi(StudyroomDTO srdto);
	
	public void facilPgDel(String pg_num);
	public void facilSrDel(String pg_num);
	
	public void schPgAdd(PgMemberDTO pgmdto);
	public void schSrAdd(SrMemberDTO srmdto);
	
	public void selectReserPg();
	public void selectReserSr();
	
	public void findReserPg();
	public void findReserSr();
	
	public void deleteReserPg(String pgm_num);
	public void deleteReserSr(String srm_num);
}
