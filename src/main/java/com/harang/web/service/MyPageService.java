package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;

public interface MyPageService {

	public List<RecordDTO> pointListHeader(String m_id);
	public List<RecordDTO> pointListSearch(SearchCriteria cri);
	public int pointPagingNum(String m_id);
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
	
	
	public List<ZipDTO> sidoList();
	public List<ZipDTO> gugunList(ZipDTO zip);
	public List<ZipDTO> dongList(ZipDTO zip);
	
	
}
