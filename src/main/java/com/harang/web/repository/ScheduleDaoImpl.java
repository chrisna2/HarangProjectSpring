package com.harang.web.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria2;
import com.harang.web.domain.SrMemberDTO;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.schedule-mapper";

	@Override
	public void schJoin(SearchCriteria2 cri) {
		sqlSession.insert(namespace + ".schJoin", cri);
	}
	@Override
	public void schJoinCancle(SearchCriteria2 cri) {
		sqlSession.delete(namespace + ".schJoinCancle", cri);
	}
	@Override
	public List<ScheduleDTO> schList(SearchCriteria2 cri) {
		return sqlSession.selectList(namespace + ".schList", cri);
	}
	@Override
	public List<ScheduleDTO> aschList(SearchCriteria2 cri) {
		return sqlSession.selectList(namespace + ".aschList", cri);
	}
	@Override
	public void schPost(ScheduleDTO scheduleDTO) {
		sqlSession.insert(namespace + ".schPost", scheduleDTO);
	}
	@Override
	public void schDelete(String s_num) {
		sqlSession.delete(namespace + ".schDelete", s_num);
	}
	@Override
	public void schUpdate(ScheduleDTO scheduleDTO) {
		sqlSession.update(namespace + ".schUpdate", scheduleDTO);
	}
	@Override
	public ScheduleDTO schRead(String s_num) {
		return sqlSession.selectOne(namespace + ".schRead", s_num);
	}
	@Override
	public List<ScheduleDTO> schCal(SearchCriteria2 cri) {
		return sqlSession.selectList(namespace + ".schCal", cri);
	}
	@Override
	public List<ScheduleDTO> schCal2() {
		return sqlSession.selectList(namespace + ".schCal2");
	}

	@Override
	public int schListCount(SearchCriteria2 cri) {
		return sqlSession.selectOne(namespace + ".schListCount", cri);
	}

	@Override
	public List<SrMemberDTO> srList() {
		
		return sqlSession.selectList(namespace + ".srList");
	}

	@Override
	public List<PgMemberDTO> pgList() {
		return sqlSession.selectList(namespace + ".pgList");
	}

	@Override
	public List<SrMemberDTO> srName(String sr_type) {
		
		return sqlSession.selectList(namespace + ".srName", sr_type);
	}

	@Override
	public List<PgMemberDTO> pgName(String pg_type) {
		return sqlSession.selectList(namespace + ".pgName", pg_type);
	}

	@Override
	public int uschListCount(SearchCriteria2 cri) {
		return sqlSession.selectOne(namespace + ".uschListCount", cri);
	}
	
}
