package com.harang.web.service;

import java.util.List;

import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;

public interface MyPageService {

	public List<RecordDTO> pointListHeader(String m_id);
	public List<RecordDTO> pointListSearch(SearchCriteria cri);
	public int pointPagingNum(String m_id);
	
	public List<CertiMemberDTO> achallengeList(SearchCriteria cri);
	public int achallengePage(SearchCriteria cri);
	public List<CertiMemberDTO> uchallengeList(SearchCriteria cri);
	public int uchallengePage(SearchCriteria cri);
	
	public void uchallenge_challenge(CertiMemberDTO certi);
	public void uchallenge_rechallenge(CertiMemberDTO certi);
	
	public List<ZipDTO> sidoList();
	public List<ZipDTO> gugunList(ZipDTO zip);
	public List<ZipDTO> dongList(ZipDTO zip);
	
	//내 정보 수정
	public void updateMyinfo(MemberDTO member);
	
	//포인트 감면
	public int pointZero(long r_point, String m_giver);
	
	//시간표 관련 
	public List<LessonDTO> timeTalbeLesson(SearchCriteria cri);
	public List<LessonDTO> lessonList(SearchCriteria cri);
	public int lessonCount(SearchCriteria cri);
		
}
