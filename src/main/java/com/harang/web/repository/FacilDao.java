package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

public interface FacilDao {
	public List<PgMemberDTO> ReserPgListAll();
	public List<SrMemberDTO> ReserSrListAll();

	public List<PgMemberDTO> ReserPgList(String m_id);
	public List<SrMemberDTO> ReserSrList(String m_id);
	
	public List<PgMemberDTO> schedulePgListLoad();
	public List<SrMemberDTO> scheduleSrListLoad();
	
	public List<ScheduleDTO> scheduleToPgList();
	public List<ScheduleDTO> scheduleToSrList();
	
	public List<PlaygroundDTO> schduleTypePgLoadAjax();
	public List<StudyroomDTO> schduleTypeSrLoadAjax();
	
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type);
	public List<StudyroomDTO> schduleNameSrLoadAjax(String sr_type);
	
	public List<PlaygroundDTO> schdulePgNumAjax(PlaygroundDTO pgdto);
	public List<StudyroomDTO> schduleSrNumAjax(StudyroomDTO srdto);
	
	
	public void schdulePgAdd(PgMemberDTO pgmdto);
	public void schduleSrAdd(SrMemberDTO srmdto);
	
	public PgMemberDTO selectPgReser(String pgm_num);
	public SrMemberDTO selectSrReser(String srm_num);
	
	public void deletePgReser(String pgm_num);
	public void deleteSrReser(String srm_num);
}
