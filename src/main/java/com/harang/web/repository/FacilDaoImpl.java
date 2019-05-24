package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

@Repository
public class FacilDaoImpl implements FacilDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.harang.mapper.facil-mapper";
	
	@Override
	public List<PgMemberDTO> reserPgListAll(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".ReserPgListAll", cri);
	}
	
	@Override
	public List<SrMemberDTO> reserSrListAll(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".ReserSrListAll", cri);
	}
	
	@Override
	public int reserPgListAllCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".ReserPgListAllCount", cri);
	}

	@Override
	public int reserSrListAllCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".ReserSrListAllCount", cri);
	}

	@Override
	public int reserPgListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".ReserPgListCount", cri);
	}

	@Override
	public int reserSrListCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".ReserSrListCount", cri);
	}

	
	
	@Override
	public List<PgMemberDTO> reserPgList(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".ReserPgList", cri);
	}

	@Override
	public List<SrMemberDTO> reserSrList(SearchCriteria cri) {
		
		return sqlSession.selectList(namespace + ".ReserSrList", cri);
	}

	@Override
	public PgMemberDTO selectPgReser(String pgm_num) {
	
		return null;
	}

	@Override
	public SrMemberDTO selectSrReser(String srm_num) {

		return null;
	}

	@Override
	public int deletePgReser(String pgm_num) {
		return sqlSession.delete(namespace + ".ReserPgCancel", pgm_num);
	}

	@Override
	public int deleteSrReser(String srm_num) {
		return sqlSession.delete(namespace + ".ReserSrCancel",srm_num);	
	}

	@Override
	public List<PgMemberDTO> schedulePgListLoad() {
		return sqlSession.selectList(namespace + ".schedulePgLoad");
	}

	@Override
	public List<SrMemberDTO> scheduleSrListLoad() {
		return sqlSession.selectList(namespace + ".scheduleSrLoad");
	}

	@Override
	public List<ScheduleDTO> scheduleToPgList() {
		return sqlSession.selectList(namespace + ".scheduleToPg");
	}

	@Override
	public List<ScheduleDTO> scheduleToSrList() {
		return sqlSession.selectList(namespace + ".scheduleToSr");
	}

	@Override
	public List<PlaygroundDTO> schduleTypePgLoadAjax() {
		return sqlSession.selectList(namespace + ".schPgTypeAjax");
	}

	@Override
	public List<StudyroomDTO> schduleTypeSrLoadAjax() {
		return sqlSession.selectList(namespace + ".schSrTypeAjax");
	}

	@Override
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type) {

		return sqlSession.selectList(namespace + ".schPgNameAjax", pg_type);

	}
	
	/*
	 //테스트 코드로 남겨놓음. macOS사용시 MySQL datasource 중 url에 DB명 뒤에 ?
	 //<property name="url" value="jdbc:mysql://localhost:3306/harang?characterEncoding=UTF-8"/>이렇게 추가해줄것.
	@Override
	public List<PlaygroundDTO> testchduleNamePgLoadAjax(PlaygroundDTO pgdto) {
	System.out.println("테스트 dao에 접근함.");
	System.out.println("가지고 온 값은? : " + pgdto.getPg_type());
		
	List<PlaygroundDTO> list = sqlSession.selectList(namespace + ".schPgNameAjax2", pgdto.getPg_type());
		
		for(int i=0; i<list.size();i++){
			System.out.println("값이 들어을까요?");
			System.out.println("여기에 들어와야되는데 왜 안들어오니?" + list.get(i).getPg_name());
			
			if(list.get(i).getPg_name() == null){
				System.out.println("값이 없습니다.");
			}
			else{
				System.out.println("성공적.");
			}
		}
		
		return list;
	}
	*/

	@Override
	public List<StudyroomDTO> schduleNameSrLoadAjax(String sr_type) {
		return sqlSession.selectList(namespace + ".schSrNameAjax", sr_type);
	}

	@Override
	public List<PlaygroundDTO> schdulePgNumAjax(PlaygroundDTO pgdto) {
		return sqlSession.selectList(namespace + ".schPgNumAjax", pgdto);
	}

	@Override
	public List<StudyroomDTO> schduleSrNumAjax(StudyroomDTO srdto) {
		return sqlSession.selectList(namespace + ".schSrNumAjax", srdto);
	}

	@Override
	public int schdulePgAdd(PgMemberDTO pgmdto) {
		return sqlSession.insert(namespace+".aFacilPgAdd", pgmdto);
	}

	@Override
	public int schduleSrAdd(SrMemberDTO srmdto) {
		return sqlSession.insert(namespace+".aFacilSrAdd", srmdto);
	}

	@Override
	public List<PlaygroundDTO> loadPgList(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".loadPgList", cri);
	}

	@Override
	public List<StudyroomDTO> loadSrList(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".loadSrList", cri);
	}

	@Override
	public int facilPgDel(String pg_num) {
		return sqlSession.delete(namespace + ".facilPgDel", pg_num);
	}

	@Override
	public int facilSrDel(String sr_num) {
		return sqlSession.delete(namespace +".facilSrDel" , sr_num);
	}

	@Override
	public int facilPgModi(PlaygroundDTO pgdto) {
		return sqlSession.update(namespace + ".modifyPg", pgdto);
	}

	@Override
	public int facilSrModi(StudyroomDTO srdto) {
		return sqlSession.update(namespace + ".modifySr", srdto);
	}

	@Override
	public int facilPgAdd(PlaygroundDTO pgdto) {
		return sqlSession.insert(namespace + ".addFacilPg", pgdto);
	}

	@Override
	public int facilSrAdd(StudyroomDTO srdto) {
		return sqlSession.insert(namespace + ".addFacilSr", srdto);
	}

	@Override
	public List<PgMemberDTO> loadPgTimecodeAjax(PgMemberDTO pgmdto) {
					
		return sqlSession.selectList(namespace + ".loadPgTimecode", pgmdto);
	}

	@Override
	public List<SrMemberDTO> loadSrTimecodeAjax(SrMemberDTO srmdto) {
		return sqlSession.selectList(namespace + ".loadSrTimecode", srmdto);
	}

	@Override
	public int userReserPg(PgMemberDTO pgmdto) {
		return sqlSession.insert(namespace + ".reserPgComplate", pgmdto);
	}

	@Override
	public int userReserSr(SrMemberDTO srmdto) {
		return sqlSession.insert(namespace + ".reserSrComplate", srmdto);
	}


}
