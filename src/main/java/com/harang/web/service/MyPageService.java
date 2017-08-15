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
	public int pointPagingNum(SearchCriteria cri);
	public List<MemberDTO> apointMember(SearchCriteria cri);
	public int apointMemberCount(SearchCriteria cri);
	public MemberDTO pointPersonInfo(String m_id);
	
	public List<CertiMemberDTO> achallengeList(SearchCriteria cri);
	public int achallengePage(SearchCriteria cri);
	public List<CertiMemberDTO> uchallengeList(SearchCriteria cri);
	public int uchallengePage(SearchCriteria cri);
	public void uchallenge_challenge(CertiMemberDTO certi);
	public void uchallenge_rechallenge(CertiMemberDTO certi);
	public List<CertiMemberDTO> specListJson(SearchCriteria cri);
	public int specListJsonCount(SearchCriteria cri);
	public String specInsert(CertiMemberDTO certi);
	public String specUpdateAll(CertiMemberDTO certi);
	public String specUpdateName(CertiMemberDTO certi);
	public String specUpdateAgency(CertiMemberDTO certi);
	public String specUpdatePoint(CertiMemberDTO certi);
	public String specDelete(CertiMemberDTO certi);
	public int challengeComplete(CertiMemberDTO certi);
	public int challengeReturn(CertiMemberDTO certi);
	
	public List<ZipDTO> sidoList();
	public List<ZipDTO> gugunList(ZipDTO zip);
	public List<ZipDTO> dongList(ZipDTO zip);
	
	//유저 리스트 불러오기
	public List<MemberDTO> userList();
	public List<MemberDTO> memberList(SearchCriteria cri);
	public MemberDTO memberData(String m_id);
	public int memberListCount(SearchCriteria cri);
	public String newMemberNum(String num4);
	public int addMemberInsert(MemberDTO member);
	
	//내 정보 수정
	public void updateMyinfo(MemberDTO member);
	
	//포인트 감면
	public int pointZero(long r_point, String m_giver);
	
	//시간표 관련 
	public List<LessonDTO> timeTalbeLesson(SearchCriteria cri);
	public List<LessonDTO> lessonList(SearchCriteria cri);
	public int lessonCount(SearchCriteria cri);
	public String enrollCheck(LessonDTO lesson);
	public String deleteCheck(LessonDTO lesson);
	public List<LessonDTO> aLessonList(SearchCriteria cri);
	public int aLessonListCount(SearchCriteria cri);
	public int aLessonUpdateCell(LessonDTO lesson);
	public int aLessonDelete(LessonDTO lesson);
	public int aLessonInsert(LessonDTO lesson);
	public List<LessonDTO> l_roomList();
	public List<LessonDTO> l_deptList();
	public List<LessonDTO> l_teacherList();
	public List<LessonDTO> findTimetableRoom(LessonDTO lesson);
	public List<LessonDTO> findTimetableTeacher(LessonDTO lesson);
		
}
