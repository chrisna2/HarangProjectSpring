package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public List<PgMemberDTO> ReserPgListAll() {
		List list = sqlSession.selectList(namespace + ".ReserPgListAll");
		return list;
	}

	@Override
	public List<SrMemberDTO> ReserSrListAll() {
		List list = sqlSession.selectList(namespace + ".ReserSrListAll");
		return list;
	}

	@Override
	public List<PgMemberDTO> ReserPgList(String m_id) {
		List list = sqlSession.selectList(namespace + ".ReserPgList", m_id);
		return list;
	}

	@Override
	public List<SrMemberDTO> ReserSrList(String m_id) {
		
		List list = sqlSession.selectList(namespace + ".ReserSrList", m_id);
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
	public void deletePgReser(String pgm_num) {
		System.out.println("2-1");
	sqlSession.delete(namespace + ".ReserPgCancel", pgm_num);
	}

	@Override
	public void deleteSrReser(String srm_num) {
		System.out.println("2-2");
	sqlSession.delete(namespace + ".ReserSrCancel",srm_num);	
	}



	

}
