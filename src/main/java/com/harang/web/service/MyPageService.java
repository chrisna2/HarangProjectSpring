package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;

public interface MyPageService {

	public List<RecordDTO> pointListHeader(String m_id);
	
	
	public List<RecordDTO> pointListSearch(SearchCriteria cri);
	
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
	
	public int pointPagingNum(String m_id);
	
}
