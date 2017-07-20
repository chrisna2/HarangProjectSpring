package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;

public interface MyPageDao {
	//해더 관련
	public List<RecordDTO> pointListHeader(String m_id);
	
	//포인트 페이지 관련
	public List<RecordDTO> pointListSearch(SearchCriteria cri);
	public int pointPagingNum(String m_id);
	
	//스펙리스트 페이지 관련
	public List<CertiMemberDTO> specListMember(SearchCriteria cri);
	public int specListCount(String m_id);
	
	
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson);
}
