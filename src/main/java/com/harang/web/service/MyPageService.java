package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;

public interface MyPageService {

	public List<RecordDTO> pointList(RecordDTO record);
	public List<RecordDTO> pointListSearch(RecordDTO record);
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
	
}
