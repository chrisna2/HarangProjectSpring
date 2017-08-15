package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;

@Repository
public class MyPageDaoImpl implements MyPageDao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.mypage-mapper";
		
	
	//포인트
	//[회원]
	@Override
	public List<RecordDTO> pointListHeader(String m_id) {
		return sqlSession.selectList(namespace+".pointListHeader", m_id);
	}
	@Override
	public List<RecordDTO> pointListSearch(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".pointListSearch", cri);
	}
	@Override
	public int pointPagingNum(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".pointPagingNum", cri);
	}
	//[관리자]
	@Override
	public List<MemberDTO> apointMember(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".apointMember", cri);
	}
	@Override
	public int apointMemberCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".apointMemberCount", cri);
	}
	@Override
	public MemberDTO pointPersonInfo(String m_id) {
		return sqlSession.selectOne(namespace+".pointPersonInfo", m_id);
	}

	
	//스펙업!
	//[관리자] 자격증 등록 신청 정보 확인
	@Override
	public List<CertiMemberDTO> achallengeList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".achallengeList", cri);
	}
	//[관리자] 자격증 등록 신청 정보 패이징 총수
	@Override
	public int achallengePage(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".achallengePage",cri);
	}
	//[회원] 자격증 등록 및 신청 확인
	@Override
	public List<CertiMemberDTO> uchallengeList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".uchallengeList", cri);
	}
	//[회원] 자격증 등록 및 신청 확인 페이징
	@Override
	public int uchallengePage(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".uchallengePage", cri);
	}
	//[회원] 스펙 등록 도전
	@Override
	public void uchallenge_challenge(CertiMemberDTO certi) {
		sqlSession.insert(namespace+".uchallenge_challenge", certi);
	}
	//[회원] 스펙 등록 재도전
	@Override
	public void uchallenge_rechallenge(CertiMemberDTO certi) {
		sqlSession.update(namespace+".uchallenge_rechallenge", certi);
	}
	//[관리자] 스펙 리스트 제이손
	@Override
	public List<CertiMemberDTO> specListJson(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".specListJson", cri);
	}
	//[관리자] 스펙 리스트 제이손 카운팅
	@Override
	public int specListJsonCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".specListJsonCount", cri);
	}
	//[관리자] 자격증 신규 등록
	@Override
	public String specInsert(CertiMemberDTO certi) {
		
		String result = "";
		
			try{
				sqlSession.insert(namespace+".specInsert", certi);
				result = "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
		
		return result; 
	}
	//[관리자] 자격증 모두 수정
	@Override
	public String specUpdateAll(CertiMemberDTO certi) {
		
		String result = "";
		
			try{
				sqlSession.update(namespace+".specUpdateAll", certi);
				result = "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
		
		return result; 
	}
	//[관리자] 자격증 이름만 수정
	@Override
	public String specUpdateName(CertiMemberDTO certi) {
		
		String result = "";
		
			try{
				sqlSession.update(namespace+".specUpdateName", certi);
				result = "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
		
		return result; 
	}
	//[관리자] 자격증 기관만 수정
	@Override
	public String specUpdateAgency(CertiMemberDTO certi) {
		
		String result = "";
		
			try{
				sqlSession.update(namespace+".specUpdateAgency", certi);
				result = "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
		
		return result; 
	}
	//[관리자] 자격증 포인트만 수정
	@Override
	public String specUpdatePoint(CertiMemberDTO certi) {
		
		String result = "";
		
			try{
				sqlSession.update(namespace+".specUpdatePoint", certi);
				result = "success";
			}
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			}
		
		return result; 
	}
	//[관리자] 자격증 삭제
	@Override
	public String specDelete(CertiMemberDTO certi) {
		
		String result = "";
		
			try {
				sqlSession.delete(namespace+".specDelete", certi);
				result = "success";
			} 
			catch (Exception e) {
				e.printStackTrace();
				result = "fail";
			} 
		
		return result;
	}
	
	@Override
	public int challengeComplete(CertiMemberDTO certi) {
		return sqlSession.update(namespace+".challengeComplete", certi);
	}
	@Override
	public int challengeReturn(CertiMemberDTO certi) {
		return sqlSession.update(namespace+".challengeReturn", certi);
	}
	
	
	// 주소 검색  Dao
	@Override
	public List<ZipDTO> sidoList() {
		return sqlSession.selectList(namespace+".sidoList");
	}
	@Override
	public List<ZipDTO> gugunList(ZipDTO zip) {
		return sqlSession.selectList(namespace+".gugunList",zip);
	}
	@Override
	public List<ZipDTO> dongList(ZipDTO zip) {
		return sqlSession.selectList(namespace+".dongList",zip);
	}
	
	//내정보 수정
	@Override
	public void updateMyinfo(MemberDTO member) {
		sqlSession.update(namespace+".updateMyinfo", member);
	}
	
	//포인트 제로
	@Override
	public int pointZero(RecordDTO record) {
		
		return sqlSession.update(namespace+".pointZero",record);
		
	}
	
	//[시간표]
	@Override
	public List<LessonDTO> timeTalbeLesson(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".timeTalbeLesson", cri);
	}
	@Override
	public List<LessonDTO> lessonList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".lessonList", cri);
	}
	@Override
	public int lessonCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".lessonCount", cri);
	}
	@Override
	public String enrollCheck(LessonDTO lesson) {
		
		//등록을 원하는 시간과 요일
		String el_time = lesson.getL_time();
		String el_day = lesson.getL_day();
		
		List<LessonDTO> checkLesson = sqlSession.selectList(namespace+".enrollCheck", lesson); 
		
		String cl_time = null;
		String cl_day = null;
		
		//중복시간 체크
		for(int i=0; i<checkLesson.size();){
			
			cl_time = checkLesson.get(i).getL_time();
			cl_day = checkLesson.get(i).getL_day();
			
			//중복 확인
			if(el_time.equals(cl_time)&&el_day.equals(cl_day)){
				return "duplicate";
			}
			else{
				i++;
			}
		}
		
		//시간표 등록
		sqlSession.insert(namespace+".enrollLesson", lesson);
		return "enroll";
		
	}
	@Override
	public String deleteCheck(LessonDTO lesson) {
		
		String check = sqlSession.selectOne(namespace+".deletecheck", lesson);
		
		if("N".equals(check)){
			sqlSession.delete(namespace+".deleteLesson", lesson);
			return "delete";
		}
		else{
			return "evaluated"; 
		}
	}
	//[관리자] 시간표 수업 등록 및 수정
	@Override
	public List<LessonDTO> aLessonList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".aLessonList", cri);
	}
	@Override
	public int aLessonListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".aLessonListCount", cri);
	}
	@Override
	public int aLessonUpdateCell(LessonDTO lesson) {
		System.out.println(lesson.toString());
		return sqlSession.update(namespace+".aLessonUpdateCell", lesson);
	}
	@Override
	public int aLessonDelete(LessonDTO lesson) {
		return sqlSession.delete(namespace+".aLessonDelete", lesson);
	}
	@Override
	public int aLessonInsert(LessonDTO lesson) {
		return sqlSession.insert(namespace+".aLessonInsert", lesson);
	}
	@Override
	public List<LessonDTO> l_roomList() {
		return sqlSession.selectList(namespace+".l_roomList");
	}
	@Override
	public List<LessonDTO> l_deptList() {
		return sqlSession.selectList(namespace+".l_deptList");
	}
	@Override
	public List<LessonDTO> l_teacherList() {
		return sqlSession.selectList(namespace+".l_teacherList");
	}
	@Override
	public List<LessonDTO> findTimetableRoom(LessonDTO lesson) {
		return sqlSession.selectList(namespace+".findTimetableRoom", lesson);
	}
	@Override
	public List<LessonDTO> findTimetableTeacher(LessonDTO lesson) {
		return sqlSession.selectList(namespace+".findTimetableTeacher", lesson);
	}
	
	
	
	@Override
	public List<MemberDTO> userList() {
		return sqlSession.selectList(namespace+".userList");
	}
	@Override
	public List<MemberDTO> memberList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".memberList", cri);
	}
	@Override
	public int memberListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".memberListCount", cri);
	}
	@Override
	public MemberDTO memberData(String m_id) {
		return sqlSession.selectOne(namespace+".memberData", m_id);
	}
	@Override
	public String newMemberNum(String num4) {
		return sqlSession.selectOne(namespace+".newMemberNum", num4);
	}
	@Override
	public int addMemberInsert(MemberDTO member) {
		return sqlSession.insert(namespace+".addMemberInsert", member);
	}
}
