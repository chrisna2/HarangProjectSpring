package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.SearchCriteria;

public interface BambooDao {

	public List<BambooDTO> bbList(SearchCriteria cri);
	public List<BambooDTO> bbNList();
	public BambooDTO bbDelete();
	public BambooDTO bbUpdate();
	public BambooDTO bbRList();
	public BambooDTO bbLike();
	public BambooDTO bbDLike();
	public BambooDTO bbLikeCancle();
	public BambooDTO bbDLikeCancle();
	public int bbListCount(SearchCriteria cri);
	
}
