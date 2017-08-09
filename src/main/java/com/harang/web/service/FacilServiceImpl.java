package com.harang.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;
import com.harang.web.repository.FacilDao;

@Service
public class FacilServiceImpl implements FacilService {

	
	@Autowired
	private FacilDao facilDao;
	
	@Override
	public List<PgMemberDTO> loadPgReserListAll() {
		List<PgMemberDTO> list = facilDao.ReserPgListAll();
		return list;
	}

	@Override
	public List<SrMemberDTO> loadSrReserListAll() {
		 List<SrMemberDTO> list = facilDao.ReserSrListAll();	
		return list;
	}
	
	// 운동장 예약 목록 불러오기 / m_id로 검색.
	@Override
	public List<PgMemberDTO> loadPgReserList(String m_id) {
		List<PgMemberDTO> list = facilDao.ReserPgList(m_id);
		
		// Timecode를  불러와서 계산하는 과정. 
		for(int i = 0;  i< list.size() ; i++){
			int count = 0;
			String timecode = list.get(i).getPgm_timecode();
			
			for(int j = 0; j < timecode.length() ; j++){
				if(timecode.charAt(j) == '1'){
					count++;
				}
			}
			int payoutpoint = count * list.get(i).getPg_point();
			list.get(i).setPayoutpoint(payoutpoint);
		}
		
		return list;
	}

	// 스터디룸 예약 목록 불러오기 / m_id로 검색.
	@Override
	public List<SrMemberDTO> loadSrReserList(String m_id) {
		List<SrMemberDTO> list = facilDao.ReserSrList(m_id); 
		
		for(int i = 0;  i< list.size() ; i++){
			int count = 0;
			String timecode = list.get(i).getSrm_timecode();
			
			for(int j = 0; j < timecode.length() ; j++){
				if(timecode.charAt(j) == '1'){
					count++;
				}
			}
			int payoutpoint = count * list.get(i).getSr_point();
			list.get(i).setPayoutpoint(payoutpoint);
		}
		
		return list;
	}
	
	@Override
	public void selectReserPg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectReserSr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findReserPg() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findReserSr() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReserPg(String pgm_num) {
		facilDao.deletePgReser(pgm_num);
	}

	@Override
	public void deleteReserSr(String srm_num) {
		facilDao.deleteSrReser(srm_num);
	}

	@Override
	public List<PgMemberDTO> schedulePgList() {
		return facilDao.schedulePgListLoad();
	}

	@Override
	public List<SrMemberDTO> scheduleSrList() {
		return facilDao.scheduleSrListLoad();
	}

	@Override
	public List<ScheduleDTO> scheduleToPg() {
		return facilDao.scheduleToPgList();
	}

	@Override
	public List<ScheduleDTO> scheduleToSr() {
		
		return facilDao.scheduleToSrList();
	}

	@Override
	public List<PlaygroundDTO> schPgNameAjax(String pg_type) {
		System.out.println("테스트2");
		System.out.println(pg_type);
		return facilDao.schduleNamePgLoadAjax(pg_type);
	}

	@Override
	public List<StudyroomDTO> schSrNameAjax(String sr_type) {
		return facilDao.schduleNameSrLoadAjax(sr_type);
	}

	@Override
	public List<PlaygroundDTO> schPgTypeAjax() {
		return facilDao.schduleTypePgLoadAjax();
	}

	@Override
	public List<StudyroomDTO> schSrTypeAjax() {
		return facilDao.schduleTypeSrLoadAjax();
	}

	@Override
	public List<PlaygroundDTO> schPgNumAjax(PlaygroundDTO pgdto) {
		return facilDao.schdulePgNumAjax(pgdto);
	}

	@Override
	public List<StudyroomDTO> schSrNumAjax(StudyroomDTO srdto) {
		return facilDao.schduleSrNumAjax(srdto);
	}

	@Override
	public void schPgAdd(PgMemberDTO pgmdto) {
		facilDao.schdulePgAdd(pgmdto);
	}

	@Override
	public void schSrAdd(SrMemberDTO srmdto) {
		facilDao.schduleSrAdd(srmdto);
	}

	@Override
	public List<PlaygroundDTO> loadPgList() {
		return facilDao.loadPgList();
	}

	@Override
	public List<StudyroomDTO> loadSrList() {
		return facilDao.loadSrList();
	}


	



}
