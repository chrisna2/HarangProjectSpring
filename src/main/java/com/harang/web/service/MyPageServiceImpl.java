package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.CertiMemberDTO;
import com.harang.web.domain.LessonDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.RecordDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.ZipDTO;
import com.harang.web.repository.MyPageDao;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private MyPageDao myPageDao;

	@Override
	public List<RecordDTO> pointListHeader(String m_id) {
		return myPageDao.pointListHeader(m_id);
	}

	@Override
	public List<RecordDTO> pointListSearch(SearchCriteria cri) {
		return myPageDao.pointListSearch(cri);
	}

	@Override
	public List<LessonDTO> defaultTimeTable(LessonDTO lesson) {
		return null;
	}

	@Override
	public int pointPagingNum(String m_id) {
		return myPageDao.pointPagingNum(m_id);
	}

	

	//주소 목록 출력 검색
	@Override
	public List<ZipDTO> sidoList() {
		return myPageDao.sidoList();
	}
	@Override
	public List<ZipDTO> gugunList(ZipDTO zip) {
		return myPageDao.gugunList(zip);
	}
	@Override
	public List<ZipDTO> dongList(ZipDTO zip) {
		return myPageDao.dongList(zip);
	}

	
	
	@Override
	public List<CertiMemberDTO> specListMember(SearchCriteria cri) {
		return null;
	}

	@Override
	public int specListCount(String m_id) {
		return 0;
	}

	@Override
	public List<CertiMemberDTO> achallengeList(SearchCriteria cri) {
		return myPageDao.achallengeList(cri);
	}

	@Override
	public int achallengePage() {
		return myPageDao.achallengePage();
	}

	//내 정보 수정
	@Override
	public void updateMyinfo(MemberDTO member) {
		myPageDao.updateMyinfo(member);
	}

	//포인트 제로 : 학비 감면
	@Override
	public int pointZero(long r_point, String m_giver) {
		
		RecordDTO record = new RecordDTO();
		
		record.setR_point(r_point);
		record.setM_giver(m_giver);
		
		return myPageDao.pointZero(record);
	}

}
