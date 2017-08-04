package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.BbreplyDTO;
import com.harang.web.domain.DlikeDTO;
import com.harang.web.domain.LikeDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.BambooDao;

@Service
public class BambooServiceImpl implements BambooService {

	@Autowired
	private BambooDao bambooDao;

	@Override
	public List<BambooDTO> bbList(SearchCriteria cri) {
		return bambooDao.bbList(cri);
	}

	@Override
	public List<BambooDTO> bbNList() {
		

		
		return bambooDao.bbNList();
	}

	@Override
	public BambooDTO bbDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public BambooDTO bbLike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbDLike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbLikeCancle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BambooDTO bbDLikeCancle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bbListCount(SearchCriteria cri) {
		
		return bambooDao.bbListCount(cri);
	}

	@Override
	public BambooDTO bbCon(String bb_num) {
		
		System.out.println("BambooServiceImpl의 bbCon 에서 테스트 시작");
		System.out.println("BambooServiceImpl의 bbCon 작동중...");
		System.out.println("BambooServiceImpl의 bbCon 에서 테스트 끝");
		
		return bambooDao.bbCon(bb_num);
	}

	@Override
	public List<BbreplyDTO> bbRList(String bb_num) {
		
		return bambooDao.bbRList(bb_num);
	}

	@Override
	public List<LikeDTO> bbLCnt(String bb_num) {
		
		return bambooDao.bbLCnt(bb_num);
	}

	@Override
	public List<DlikeDTO> bbDLCnt(String bb_num) {
		
		return bambooDao.bbDLCnt(bb_num);
	}

	@Override
	public void bbUpdateCnt(String bb_num) {
		
		bambooDao.bbUpdateCnt(bb_num);
	}

	@Override
	public void bbPost(BambooDTO bambooDTO) {
		
		bambooDao.bbPost(bambooDTO);
		
	}
	
	

}
