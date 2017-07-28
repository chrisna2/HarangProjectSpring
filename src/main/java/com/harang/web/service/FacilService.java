package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;

public interface FacilService {
	public List allList();
	
	public List<PgMemberDTO> loadPgmlist();
	public List<SrMemberDTO> loadSrmlist();
	
	public void selectReserPg();
	public void selectReserSr();
	
	public void findReserPg();
	public void findReserSr();
	
	public void deleteReserPg();
	public void deleteReserSr();
}
