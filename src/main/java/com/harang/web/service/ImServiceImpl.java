package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.Im2DTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.WarningDTO;
import com.harang.web.repository.ImDao;
import com.harang.web.repository.LoginDao;


@Service
public class ImServiceImpl implements ImService {

	
	@Autowired
	private ImDao imDao;
	
	@Override
	public List<Im2DTO> ImAlllist(SearchCriteria cri) {
		return imDao.ImAlllist(cri);
	}

	@Override
	public int Imcount(SearchCriteria cri) {
	
		return imDao.Imcount(cri);
	}

	@Override
	public Im2DTO Imread(String lm_num) {
		return imDao.Imread(lm_num);
	}

	@Override
	public List<Im2DTO> Immylist(String m_id) {
	
		return imDao.Immylist(m_id);
	}

	@Override
	public Im2DTO Imread2(String l_num) {
		
		return imDao.Imread2(l_num);
	}

	@Override
	public void insertIm(Im2DTO im) {
		imDao.insertIm(im);
		
	}

	@Override
	public Im2DTO Imread3(String l_num) {
	
		return imDao.Imread3(l_num);
	}

	@Override
	public void insertIm2(Im2DTO im) {
		imDao.insertIm2(im);
		
	}

	@Override
	public void updateIm(Im2DTO im) {
		imDao.updateIm(im);
		
	}

	@Override
	public void waringIm(WarningDTO Wn) {
		imDao.waringIm(Wn);
		
	}

	@Override
	public List<Im2DTO> Imalist(SearchCriteria cri) {
		
		return imDao.Imalist(cri);
	}

	@Override
	public Im2DTO Imaread(String lm_num) {
	
		return imDao.Imaread(lm_num);
	}

	@Override
	public List<Im2DTO> Imawaringread(String lm_num) {
	
		return imDao.Imawaringread(lm_num);
	}

	@Override
	public void deleteImwaring(WarningDTO wn) {
		imDao.deleteImwaring(wn);
		
	}

	@Override
	public void deleteim(Im2DTO im) {
		imDao.deleteim(im);
		
	}



}
