package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;

public interface FacilDao {
	public List<PgMemberDTO> ReserPgListAll();
	public List<SrMemberDTO> ReserSrListAll();

	public List<PgMemberDTO> ReserPgList(String m_id);
	public List<SrMemberDTO> ReserSrList(String m_id);
	
	public PgMemberDTO selectPgReser(String pgm_num);
	public SrMemberDTO selectSrReser(String srm_num);
	
	public void deletePgReser(String pgm_num);
	public void deleteSrReser(String srm_num);
}
