package com.harang.web.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.schedule-mapper";

	@Override
	public void schJoin(String s_num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void schJoinCancle(String s_num) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScheduleDTO> schList(int m_grade) {
		
		return sqlSession.selectList(namespace + ".schList", m_grade);
	}

	@Override
	public void schPost(String s_num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void schDelete(String s_num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void schUpdate(String s_num) {
		// TODO Auto-generated method stub

	}

}
