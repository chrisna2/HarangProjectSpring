package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;

public interface MyPageDao {

	public List<RecordDTO> pointListHeader(String m_id);
	public List<RecordDTO> pointListSearch(SearchCriteria cri);
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
	
	public int pointPagingNum(String m_id);
}
