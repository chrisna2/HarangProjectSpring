package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.Im2DTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.WarningDTO;


public interface ImDao {
	
	public List<Im2DTO> ImAlllist(SearchCriteria cri);
	public int Imcount(SearchCriteria cri);
	
	public Im2DTO Imread(String lm_num);
	
	public Im2DTO Imread2(String l_num);
	public Im2DTO Imread3(String l_num);
	
	public List<Im2DTO> Immylist(String m_id);
	
	
	public void insertIm(Im2DTO im);
	public void insertIm2(Im2DTO im);
	public void updateIm(Im2DTO im);
	
	public void waringIm(WarningDTO Wn);
	
	public  List<Im2DTO> Imalist(SearchCriteria cri);
	public Im2DTO Imaread(String lm_num);
	public List<Im2DTO> Imawaringread(String lm_num);
	
	public void deleteImwaring(WarningDTO wn);
	public void deleteim(Im2DTO im);
	

	
	
}
