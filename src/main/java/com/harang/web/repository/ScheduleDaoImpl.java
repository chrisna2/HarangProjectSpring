package com.harang.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.PgMemberDTO;
import com.harang.web.domain.ScheduleDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.domain.SearchCriteria2;
import com.harang.web.domain.SrMemberDTO;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
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
	public List<CalanderDTO> schCal(SearchCriteria2 cri, HttpServletRequest req) {
		List<CalanderDTO> result = new ArrayList<CalanderDTO>();
		
		HttpSession session = req.getSession();
		
		MemberDTO mdto = (MemberDTO)session.getAttribute("member");
		
		int m_grade = mdto.getM_grade();
		String m_dept = mdto.getM_dept();
		
		
		RowMapper rowmapper = new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CalanderDTO cdto = new CalanderDTO();
				
				cdto.setId(rs.getString("s_num"));
				cdto.setStart(rs.getString("s_dstart"));
				cdto.setTitle(rs.getString("s_title"));
				cdto.setEnd(rs.getString("s_dend"));
				cdto.setColor("#E4FFB7");
				cdto.setAllDay(true);
				
				return cdto;
			}
		};	
		
		String sql = "select s_num, s_title, s_dstart, s_dend from tbl_schedule where (s_grade like '%"+m_grade+"%' or s_grade = '1234') and (s_dept = '전체' or s_dept = '" + m_dept + "') and s_ispoint = 'N'";
		
		result = jdbcTemplate.query(sql,rowmapper);
		
		return result;
	}
	
	@Override
	public List<CalanderDTO> schCal2() {
		List<CalanderDTO> result = new ArrayList<CalanderDTO>();
		
		
		RowMapper rowmapper = new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CalanderDTO cdto = new CalanderDTO();
				
				cdto.setId(rs.getString("s_num"));
				cdto.setStart(rs.getString("s_dstart"));
				cdto.setTitle(rs.getString("s_title"));
				cdto.setEnd(rs.getString("s_dend"));
				cdto.setColor("#df98e0");
				cdto.setAllDay(true);
				
				return cdto;
			}
		};	
		
		String sql = "select s_num, s_title, s_dstart, s_dend from tbl_schedule where (select count(m_id) from tbl_schedule_member where s_num = tbl_schedule.s_num) >0";
		
		result = jdbcTemplate.query(sql,rowmapper);
		
		return result;
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

	
	
	
}
