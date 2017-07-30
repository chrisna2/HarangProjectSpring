package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.SrMemberDTO;

@Repository
public class FacilDaoImpl implements FacilDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.harang.mapper.facil-mapper";

	@Override
	public List<PgMemberDTO> ReserPgList() {
		List list = sqlSession.selectList(namespace + ".ReserPgList");
		return list;
	}

	@Override
	public List<SrMemberDTO> ReserSrList() {
		List list = sqlSession.selectList(namespace + ".ReserSrList");
		return list;
	}

	@Override
	public PgMemberDTO selectPgReser(String pgm_num) {
	
		return null;
	}

	@Override
	public SrMemberDTO selectSrReser(String srm_num) {

		return null;
	}

	@Override
	public void DeletePgReser(String pgm_num) {
		
		
	}

	@Override
	public void DeleteSrReser(String srm_num) {
		
		
	}

	

}
