package com.harang.web.service;

import java.util.List;

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
	public List allList() {
		return null;
		
	}
	
	@Override
	public List<PgMemberDTO> loadPgmlist() {
		List list = facilDao.ReserPgList();
		return list;
	}

	@Override
	public List<SrMemberDTO> loadSrmlist() {
		List list = facilDao.ReserSrList();
		return list;
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
	public void deleteReserPg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReserSr() {
		// TODO Auto-generated method stub
		
	}
}
