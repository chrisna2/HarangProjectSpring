package com.harang.web.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.PlaygroundDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SrMemberDTO;
import com.harang.web.domain.StudyroomDTO;

@Repository
public class FacilDaoImpl implements FacilDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.harang.mapper.facil-mapper";
	
	@Override
	public List<PgMemberDTO> reserPgListAll() {
		List list = sqlSession.selectList(namespace + ".ReserPgListAll");
		return list;
	}

	@Override
	public List<SrMemberDTO> reserSrListAll() {
		List list = sqlSession.selectList(namespace + ".ReserSrListAll");
		return list;
	}

	@Override
	public List<PgMemberDTO> reserPgList(String m_id) {
		List list = sqlSession.selectList(namespace + ".ReserPgList", m_id);
		return list;
	}

	@Override
	public List<SrMemberDTO> reserSrList(String m_id) {
		
		List list = sqlSession.selectList(namespace + ".ReserSrList", m_id);
		return list;
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
	public void deletePgReser(String pgm_num) {
	sqlSession.delete(namespace + ".ReserPgCancel", pgm_num);
	}

	@Override
	public void deleteSrReser(String srm_num) {
	sqlSession.delete(namespace + ".ReserSrCancel",srm_num);	
	}

	@Override
	public List<PgMemberDTO> schedulePgListLoad() {
		List list = sqlSession.selectList(namespace + ".schedulePgLoad");
		return list;
	}

	@Override
	public List<SrMemberDTO> scheduleSrListLoad() {
		List list = sqlSession.selectList(namespace + ".scheduleSrLoad");
		return list;
	}

	@Override
	public List<ScheduleDTO> scheduleToPgList() {
		List list = sqlSession.selectList(namespace + ".scheduleToPg");
		return list;
	}

	@Override
	public List<ScheduleDTO> scheduleToSrList() {
		List list = sqlSession.selectList(namespace + ".scheduleToSr");
		return list;
	}

	@Override
	public List<PlaygroundDTO> schduleTypePgLoadAjax() {
		List<PlaygroundDTO> list = sqlSession.selectList(namespace + ".schPgTypeAjax");
		return list;
	}

	@Override
	public List<StudyroomDTO> schduleTypeSrLoadAjax() {
		List<StudyroomDTO> list = sqlSession.selectList(namespace + ".schSrTypeAjax");
		return list;
	}

	@Override
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type) {

		List<PlaygroundDTO> list = sqlSession.selectList(namespace + ".schPgNameAjax", pg_type);

		return list;
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
		List<StudyroomDTO> list = sqlSession.selectList(namespace + ".schSrNameAjax", sr_type);
		return list;
	}

	@Override
	public List<PlaygroundDTO> schdulePgNumAjax(PlaygroundDTO pgdto) {
		List list = sqlSession.selectList(namespace + ".schPgNumAjax", pgdto);
		return list;
	}

	@Override
	public List<StudyroomDTO> schduleSrNumAjax(StudyroomDTO srdto) {
		List list = sqlSession.selectList(namespace + ".schSrNumAjax", srdto);
		return list;
	}

	@Override
	public void schdulePgAdd(PgMemberDTO pgmdto) {
		sqlSession.insert(namespace+".aFacilPgAdd", pgmdto);
	}

	@Override
	public void schduleSrAdd(SrMemberDTO srmdto) {
		sqlSession.insert(namespace+".aFacilSrAdd", srmdto);
	}

	@Override
	public List<PlaygroundDTO> loadPgList() {
		List list = sqlSession.selectList(namespace + ".loadPgList");
		return list;
	}

	@Override
	public List<StudyroomDTO> loadSrList() {
		List list = sqlSession.selectList(namespace + ".loadSrList");
		return list;
	}

	@Override
	public void facilPgDel(String pg_num) {
		sqlSession.delete(namespace + ".facilPgDel", pg_num);
	}

	@Override
	public void facilSrDel(String sr_num) {
		sqlSession.delete(namespace +".facilSrDel" , sr_num);
	}

	@Override
	public void facilPgModi(PlaygroundDTO pgdto) {
		sqlSession.update(namespace + ".modifyPg", pgdto);
	}

	@Override
	public void facilSrModi(StudyroomDTO srdto) {
		sqlSession.update(namespace + ".modifySr", srdto);
	}

	@Override
	public void facilPgAdd(PlaygroundDTO pgdto) {
		sqlSession.insert(namespace + ".addFacilPg", pgdto);
	}

	@Override
	public void facilSrAdd(StudyroomDTO srdto) {
		sqlSession.insert(namespace + ".addFacilSr", srdto);
	}

	@Override
	public List<PgMemberDTO> loadPgTimecodeAjax(PgMemberDTO pgmdto) {
					
		List<PgMemberDTO> list = sqlSession.selectList(namespace + ".loadPgTimecode", pgmdto);
		return list;
	}

	@Override
	public List<SrMemberDTO> loadSrTimecodeAjax(SrMemberDTO srmdto) {
		List<SrMemberDTO> list = sqlSession.selectList(namespace + ".loadSrTimecode", srmdto);
		return list;
	}

	@Override
	public void userReserPg(PgMemberDTO pgmdto) {
		sqlSession.insert(namespace + ".reserPgComplate", pgmdto);
	}

	@Override
	public void userReserSr(SrMemberDTO srmdto) {
		sqlSession.insert(namespace + ".reserSrComplate", srmdto);
	}


}
