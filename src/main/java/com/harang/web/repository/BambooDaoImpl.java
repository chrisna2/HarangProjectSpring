package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.BbreplyDTO;
import com.harang.web.domain.DlikeDTO;
import com.harang.web.domain.LikeDTO;
import com.harang.web.domain.SearchCriteria;

@Repository
public class BambooDaoImpl implements BambooDao {

	
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.bamboo-mapper";
	@Override
	public List<BambooDTO> bbList(SearchCriteria cri) {

		System.out.println("BambooDaoImpl의 bbList 에서 테스트 시작");
		System.out.println("Keyfield : " + cri.getKeyfield()+ ", Keyword : " +cri.getKeyword());
		System.out.println("BambooDaoImpl의 bbList 에서 테스트 끝");
		
		
		return sqlSession.selectList(namespace + ".bbList", cri);
	}
	@Override
	public List<BambooDTO> bbNList() {
		
		System.out.println("BambooDaoImpl의 bbNList 에서 테스트 시작");
		System.out.println("BambooDaoImpl의 bbNList 작동중...");
		System.out.println("BambooDaoImpl의 bbNList 에서 테스트 끝");
		
		return sqlSession.selectList(namespace + ".bbNList");
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
	public List<BbreplyDTO> bbRList(String bb_num) {
		return sqlSession.selectList(namespace + ".bbRList", bb_num);
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
		
		return sqlSession.selectOne(namespace + ".bbListCount", cri);
	}
	@Override
	public BambooDTO bbCon(String bb_num) {
		
		System.out.println("BambooDaoImpl의 bbCon 에서 테스트 시작");
		System.out.println("BambooDaoImpl의 bbCon 작동중...");
		System.out.println("BambooDaoImpl의 bbCon 에서 테스트 끝");
		
		
		return sqlSession.selectOne(namespace + ".bbCon", bb_num);
	}
	@Override
	public List<LikeDTO> bbLCnt(String bb_num) {
		
		return sqlSession.selectList(namespace + ".bbLCnt", bb_num);
	}
	@Override
	public List<DlikeDTO> bbDLCnt(String bb_num) {
		
		return sqlSession.selectList(namespace + ".bbDLCnt", bb_num);
	}
	@Override
	public void bbUpdateCnt(String bb_num) {
		
		sqlSession.insert(namespace + ".bbUpdateCnt", bb_num);
		
	}
	@Override
	public void bbPost(BambooDTO bambooDTO) {
		sqlSession.insert(namespace + ".bbPost", bambooDTO);
		
	}
		
	

}
