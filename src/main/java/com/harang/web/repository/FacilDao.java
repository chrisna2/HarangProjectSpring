package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;

public interface FacilDao {
	public List<PgMemberDTO> ReserPgList();
	public List<SrMemberDTO> ReserSrList();
	
	public PgMemberDTO selectPgReser(String pgm_num);
	public SrMemberDTO selectSrReser(String srm_num);
	
	public void DeletePgReser(String pgm_num);
	public void DeleteSrReser(String srm_num);
}
