package com.harang.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;
import com.harang.web.repository.FacilDao;

@Service
public class FacilServiceImpl implements FacilService {

	@Autowired
	private FacilDao facilDao;
	
	@Override
	public List<PgMemberDTO> loadPgReserListAll(SearchCriteria cri) {
		List<PgMemberDTO> list = facilDao.reserPgListAll(cri);
		return list;
	}

	@Override
	public List<SrMemberDTO> loadSrReserListAll(SearchCriteria cri) {
		 List<SrMemberDTO> list = facilDao.reserSrListAll(cri);	
		return list;
	}
	
	// 운동장 예약 목록 불러오기 / m_id로 검색.
	@Override
	public List<PgMemberDTO> loadPgReserList(SearchCriteria cri) {

		List<PgMemberDTO> list = facilDao.reserPgList(cri);
		
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
	public List<SrMemberDTO> loadSrReserList(SearchCriteria cri) {
		List<SrMemberDTO> list = facilDao.reserSrList(cri); 
		
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
	public List<PlaygroundDTO> loadPgList(SearchCriteria cri) {
		return facilDao.loadPgList(cri);
	}

	@Override
	public List<StudyroomDTO> loadSrList(SearchCriteria cri) {
		return facilDao.loadSrList(cri);
	}

	@Override
	public void facilPgAdd(PlaygroundDTO pgdto) {
		facilDao.facilPgAdd(pgdto);
	}

	@Override
	public void facilSrAdd(StudyroomDTO srdto) {
		facilDao.facilSrAdd(srdto);
	}

	@Override
	public void facilPgmodi(PlaygroundDTO pgdto) {
		facilDao.facilPgModi(pgdto);
	}

	@Override
	public void facilSrmodi(StudyroomDTO srdto) {
		facilDao.facilSrModi(srdto);
	}

	@Override
	public void facilPgDel(String pg_num) {
		facilDao.facilPgDel(pg_num);
	}

	@Override
	public void facilSrDel(String sr_num) {
		facilDao.facilSrDel(sr_num);
	}

	@Override
	public String loadPgTimecodeAjax(PgMemberDTO pgmdto) {
		
		List<PgMemberDTO> list = facilDao.loadPgTimecodeAjax(pgmdto);
		
		for(int i =0 ; i<list.size(); i++){
			String a = list.get(i).getPgm_timecode();
			System.out.println("Service에서의 타임코드는 : " + a);
		}
		
		ArrayList getTimecode = new ArrayList();
		
		String result = null;
		long basic = 10000000000000L;
		
		for(int i = 0; i<list.size(); i++){
			getTimecode.add(list.get(i).getPgm_timecode());
			
			
			for(int j = 0; j<getTimecode.size(); j++){
				String cutA = getTimecode.get(i).toString().substring(1);
				long b = Long.parseLong(cutA);
				basic += b;
			}
			
			String a = Long.toString(basic);
			result = a.substring(1);
			System.out.println(result);
		}
		return result;
	}

	@Override
	public String loadSrTimecodeAjax(SrMemberDTO srmdto) {
		List<SrMemberDTO> list = facilDao.loadSrTimecodeAjax(srmdto);
		
		for(int i =0 ; i<list.size(); i++){
			String a = list.get(i).getSrm_timecode();
			System.out.println("Service에서의 타임코드는 : " + a);
		}
		
		ArrayList getTimecode = new ArrayList();
		
		String result = null;
		long basic = 10000000000000L;
		
		for(int i = 0; i<list.size(); i++){
			getTimecode.add(list.get(i).getSrm_timecode());
			
			
			for(int j = 0; j<getTimecode.size(); j++){
				String cutA = getTimecode.get(i).toString().substring(1);
				long b = Long.parseLong(cutA);
				basic += b;
			}
			
			String a = Long.toString(basic);
			result = a.substring(1);
			System.out.println(result);
		}
		
		return result;
	}

	@Override
	public void userReserPg(PgMemberDTO pgmdto) {
		facilDao.userReserPg(pgmdto);
	}

	@Override
	public void userReserSr(SrMemberDTO srmdto) {
		facilDao.userReserSr(srmdto);
	}


}
