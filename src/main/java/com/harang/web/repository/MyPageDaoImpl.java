package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;

@Repository
public class MyPageDaoImpl implements MyPageDao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.mypage-mapper";
		
	
	@Override
	public List<RecordDTO> pointListHeader(String m_id) {
		return sqlSession.selectList(namespace+".pointListHeader", m_id);
	}

	@Override
	public List<RecordDTO> pointListSearch(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".pointListSearch", cri);
	}

	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pointPagingNum(String m_id) {
		return sqlSession.selectOne(namespace+".pointPagingNum", m_id);
	}
	


}
