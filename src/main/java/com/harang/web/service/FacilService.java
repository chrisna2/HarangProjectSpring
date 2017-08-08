package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;

public interface FacilService {
	public List<PgMemberDTO> loadPgReserListAll();
	public List<SrMemberDTO> loadSrReserListAll();
	
	public List<PgMemberDTO> loadPgReserList(String m_id);
	public List<SrMemberDTO> loadSrReserList(String m_id);
	
	public List<PgMemberDTO> scheduleFacilList();

	public void selectReserPg();
	public void selectReserSr();
	
	public void findReserPg();
	public void findReserSr();
	
	public void deleteReserPg(String pgm_num);
	public void deleteReserSr(String pgm_num);
}
