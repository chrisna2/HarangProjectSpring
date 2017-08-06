package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.repository.FacilDao;

@Service
public class FacilServiceImpl implements FacilService {

	
	@Autowired
	private FacilDao facilDao;
	
	@Override
	public List<PgMemberDTO> loadPgReserList(String m_id) {
	
		return facilDao.ReserPgList(m_id);
	}

	@Override
	public List<SrMemberDTO> loadSrReserList(String m_id) {
		
		return facilDao.ReserSrList(m_id);
	}
	
	@Override
	public void selectReserPg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectReserSr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findReserPg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findReserSr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReserPg(String pgm_num) {
		System.out.println("1-1");
		facilDao.deletePgReser(pgm_num);
	}

	@Override
	public void deleteReserSr(String srm_num) {
		System.out.println("1-2");
		facilDao.deleteSrReser(srm_num);
	}
}
