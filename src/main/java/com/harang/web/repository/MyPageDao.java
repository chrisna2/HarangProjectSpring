package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;

public interface MyPageDao {

	public List<RecordDTO> pointList(RecordDTO record);
	public List<RecordDTO> pointListSearch(RecordDTO record);
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
	
}
