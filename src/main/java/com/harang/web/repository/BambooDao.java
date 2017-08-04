package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.BbreplyDTO;
import com.harang.web.domain.DlikeDTO;
import com.harang.web.domain.LikeDTO;
import com.harang.web.domain.SearchCriteria;

public interface BambooDao {

	public List<BambooDTO> bbList(SearchCriteria cri);
	public List<BambooDTO> bbNList();
	public BambooDTO bbDelete();
	public BambooDTO bbUpdate();
	public List<BbreplyDTO> bbRList(String bb_num);
	public BambooDTO bbLike();
	public BambooDTO bbDLike();
	public BambooDTO bbLikeCancle();
	public BambooDTO bbDLikeCancle();
	public int bbListCount(SearchCriteria cri);
	
	public BambooDTO bbCon(String bb_num);
	
	public List<LikeDTO> bbLCnt(String bb_num);
	public List<DlikeDTO> bbDLCnt(String bb_num);
	
	public void bbUpdateCnt(String bb_num);
	
	public void bbPost(BambooDTO bambooDTO);
	
}
