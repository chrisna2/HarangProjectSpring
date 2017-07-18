package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;

@Repository
public class MyPageDaoImpl implements MyPageDao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.mypage-mapper";
		
	
	@Override
	public List<RecordDTO> pointList(RecordDTO record) {
		return sqlSession.selectList(namespace+".pointList", record);
	}

	@Override
	public List<RecordDTO> pointListSearch(RecordDTO record) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".pointListSearch", record);
	}

	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
