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
	public void bbDelete(String bb_num) {
		
		sqlSession.delete(namespace + ".bbDelete", bb_num);
		
	}
	@Override
	public void bbUpdate(BambooDTO bambooDTO) {
		
		sqlSession.update(namespace + ".bbUpdate", bambooDTO);
	}
	@Override
	public List<BbreplyDTO> bbRList(String bb_num) {
		return sqlSession.selectList(namespace + ".bbRList", bb_num);
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
	
	@Override
	public void abbPost(BambooDTO bambooDTO) {
		sqlSession.insert(namespace + ".abbPost", bambooDTO);
		
	}
	
	@Override
	public void bbrpost(BbreplyDTO bbreplyDTO) {
		sqlSession.insert(namespace + ".bbrPost", bbreplyDTO);
		
	}
	@Override
	public void bbrdelete(String br_num) {
		sqlSession.delete(namespace + ".bbrDelete", br_num);
		
	}
	@Override
	public void bbLike(LikeDTO likeDTO) {
		sqlSession.insert(namespace + ".bbLike", likeDTO);
		
	}
	@Override
	public void bbDLike(LikeDTO likeDTO) {
		sqlSession.insert(namespace + ".bbDLike", likeDTO);
		
	}
	@Override
	public void bbLikeCancle(LikeDTO likeDTO) {
		sqlSession.delete(namespace + ".bbLikeCancle", likeDTO);
		
	}
	@Override
	public void bbDLikeCancle(LikeDTO likeDTO) {
		sqlSession.delete(namespace + ".bbDLikeCancle", likeDTO);
		
	}
	
		
	

}
