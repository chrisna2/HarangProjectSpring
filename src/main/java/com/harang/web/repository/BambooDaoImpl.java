package com.harang.web.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.BambooDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.SearchCriteria;

@Repository
public class BambooDaoImpl implements BambooDao {

	
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.bamboo-mapper";
	@Override
	public List<BambooDTO> bbList(SearchCriteria cri) {

		System.out.println("BambooDaoImpl 에서 테스트 시작");
		System.out.println("Keyfield : " + cri.getKeyfield()+ ", Keyword : " +cri.getKeyword());
		System.out.println("BambooDaoImpl 에서 테스트 끝");
		
		
		return sqlSession.selectList(namespace + ".bbList", cri);
	}
	@Override
	public List<BambooDTO> bbNList() {
		
		System.out.println("BambooDaoImpl 에서 테스트 시작");
		System.out.println("BambooDaoImpl 작동중...");
		System.out.println("BambooDaoImpl 에서 테스트 끝");
		
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
	public BambooDTO bbRList() {
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
		
		return sqlSession.selectOne(namespace + ".bbListCount", cri);
	}
		
	

}
