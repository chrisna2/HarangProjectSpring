package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.MyPageDao;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private MyPageDao myPageDao;

	@Override
	public List<RecordDTO> pointListHeader(String m_id) {
		return myPageDao.pointListHeader(m_id);
	}

	@Override
	public List<RecordDTO> pointListSearch(SearchCriteria cri) {
		return myPageDao.pointListSearch(cri);
	}

	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pointPagingNum(String m_id) {
		// TODO Auto-generated method stub
		return myPageDao.pointPagingNum(m_id);
	}

}
