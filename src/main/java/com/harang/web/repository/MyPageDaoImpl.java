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
		
	
	@Override
	public List<RecordDTO> pointListHeader(String m_id) {
		return sqlSession.selectList(namespace+".pointListHeader", m_id);
	}
	@Override
	public List<RecordDTO> pointListSearch(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".pointListSearch", cri);
	}
	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		return null;
	}
	@Override
	public int pointPagingNum(String m_id) {
		return sqlSession.selectOne(namespace+".pointPagingNum", m_id);
	}

	
	//여기서 시작//
	@Override
	public List<CertiMemberDTO> specListMember(SearchCriteria cri) {
		return null;
	}
	@Override
	public int specListCount(String m_id) {
		return 0;
	}
	//[관리자] 자격증 등록 신청 정보 확인
	@Override
	public List<CertiMemberDTO> achallengeList(SearchCriteria cri) {
		System.out.println(cri.getKeyfield());
		System.out.println(cri.getKeyword());
		return sqlSession.selectList(namespace+".achallengeList", cri);
	}
	//[관리자] 자격증 등록 신청 정보 패이징 총수
	@Override
	public int achallengePage() {
		return sqlSession.selectOne(namespace+".achallengePage");
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
	
	


}
