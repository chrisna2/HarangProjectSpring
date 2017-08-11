package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.Im2DTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.WarningDTO;


@Repository
public class ImDaoImpl implements ImDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.im-mapper";
	




	@Override
	public List<Im2DTO> ImAlllist(SearchCriteria cri) {
		List list = sqlSession.selectList(namespace + ".imallList",cri);
		return list;
	}





	@Override
	public int Imcount(SearchCriteria cri) {
		
		return sqlSession.selectOne(namespace+".Imcount",cri);
	}





	@Override
	public Im2DTO Imread(String lm_num) {
		
		return sqlSession.selectOne(namespace + ".imread2",lm_num);
		
	}





	@Override
	public List<Im2DTO> Immylist(String m_id) {
		List list = sqlSession.selectList(namespace + ".immyList",m_id);
		return list;
	}





	@Override
	public Im2DTO Imread2(String l_num) {
		
		return sqlSession.selectOne(namespace + ".imread",l_num);
	}





	@Override
	public void insertIm(Im2DTO im) {
		
		
		sqlSession.insert(namespace+".iminsert",im);
		
	}

	@Override
	public void insertIm2(Im2DTO im) {
		sqlSession.update(namespace+".iminsert2",im);
		
	}








	@Override
	public Im2DTO Imread3(String l_num) {
		
		return sqlSession.selectOne(namespace + ".imread3",l_num);
	}





	@Override
	public void updateIm(Im2DTO im) {
		sqlSession.update(namespace+".imupdate",im);
		
	}





	@Override
	public void waringIm(WarningDTO Wn) {
		sqlSession.insert(namespace+".warninginsert",Wn);
		
	}





	@Override
	public List<Im2DTO> Imalist(SearchCriteria cri) {
		List list = sqlSession.selectList(namespace + ".imalist",cri);
		return list;
	}





	@Override
	public Im2DTO Imaread(String lm_num) {
	
		return sqlSession.selectOne(namespace + ".imadetail",lm_num);
	}





	@Override
	public List<Im2DTO> Imawaringread(String lm_num) {
		List list = sqlSession.selectList(namespace + ".imadetailwarning",lm_num);
		return list;
	}





	@Override
	public void deleteImwaring(WarningDTO wn) {
		sqlSession.delete(namespace+".imdelete", wn);
		
	}





	@Override
	public void deleteim(Im2DTO im) {
		sqlSession.delete(namespace+".imdelete2", im);
		
	}










	

}
