package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.repository.MyPageDao;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private MyPageDao myPageDao;

	@Override
	public List<RecordDTO> pointList(RecordDTO record) {
		return myPageDao.pointList(record);
	}

	@Override
	public List<RecordDTO> pointListSearch(RecordDTO record) {
		return myPageDao.pointListSearch(record);
	}

	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		// TODO Auto-generated method stub
		return null;
	}

}
