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
	public List<PgMemberDTO> ReserPgListAll() {
		List list = sqlSession.selectList(namespace + ".ReserPgListAll");
		return list;
	}

	@Override
	public List<SrMemberDTO> ReserSrListAll() {
		List list = sqlSession.selectList(namespace + ".ReserSrListAll");
		return list;
	}

	@Override
	public List<PgMemberDTO> ReserPgList(String m_id) {
		List list = sqlSession.selectList(namespace + ".ReserPgList", m_id);
		return list;
	}

	@Override
	public List<SrMemberDTO> ReserSrList(String m_id) {
		
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
		List list = sqlSession.selectList(namespace + ".schPgTypeAjax");
		return list;
	}

	@Override
	public List<StudyroomDTO> schduleTypeSrLoadAjax() {
		List list = sqlSession.selectList(namespace + ".schSrTypeAjax");
		return list;
	}

	@Override
	public List<PlaygroundDTO> schduleNamePgLoadAjax(String pg_type) {
		return sqlSession.selectList(namespace + ".schPgNameAjax", pg_type);
	}

	@Override
	public List<StudyroomDTO> schduleNameSrLoadAjax(String sr_type) {
		List list = sqlSession.selectList(namespace + ".schSrNameAjax", sr_type);
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
}
