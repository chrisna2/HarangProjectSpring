package com.harang.web.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.RecordDTO;

@Repository
public class PointDaoImpl implements PointDao{

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.harang.mapper.point-mapper";
	
	@Override
	public String recordPointTrade(RecordDTO record) {
		
		sqlSession.insert(namespace+".recordPointTrade", record);
		sqlSession.update(namespace+".giverPointMinus", record);
		sqlSession.update(namespace+".haverPointPlus", record);
		
		return "complete";
	}

	@Override
	public long pointInfo(String m_id) {
		return sqlSession.selectOne(namespace+".pointInfo", m_id);
	}

	
}
