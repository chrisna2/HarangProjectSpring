package com.harang.web.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.SearchCriteria;

@Repository
public class FoodDaoImpl implements FoodDao {
	
		@Inject
		private JdbcTemplate jdbcTemplate;

		@Autowired
		private SqlSession sqlSession;
		private static final String namespace = "com.harang.mapper.food-mapper";
	
	@Override
	public void updateFood(FoodDTO food) {
		sqlSession.update(namespace+".updateFood", food);
	}

	@Override
	public void deleteFood(FoodDTO food) {
		sqlSession.delete(namespace+".deleteFood", food);
	}

	@Override
	public void insertFood(FoodDTO food) {
		sqlSession.insert(namespace+".insertFood", food);

	}

	@Override
	public List<CalanderDTO> amenuJson() {
		List<CalanderDTO> result = new ArrayList<CalanderDTO>();
		
		RowMapper rowmapper = new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CalanderDTO cdto = new CalanderDTO();
				cdto.setStart(rs.getString("f_selldate"));
				cdto.setTitle(rs.getString("f_content"));
				cdto.setId(rs.getString("f_num"));
				cdto.setAllDay(true);
				return cdto;
			}
		};	
		
		String sql = "SELECT f_selldate, f_content, f_num FROM tbl_food where f_selldate > sysdate()";
		
		result = jdbcTemplate.query(sql,rowmapper);
		
		return result;
	}
	
	@Override
	public List<CalanderDTO> menuJson(String m_id) {
		List<CalanderDTO> result = new ArrayList<CalanderDTO>();
		
		String sql = "SELECT f.f_selldate, f.f_content, f.f_num, fm.fm_isuse "
				+ "FROM tbl_food f left join tbl_food_member fm "
				+ "on f.f_num = fm.f_num and fm.m_id = ? "
				+ "where f_selldate > sysdate()";
		
		RowMapper rowmapper = new RowMapper(){
			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CalanderDTO cdto = new CalanderDTO();
				cdto.setStart(rs.getString("f_selldate"));
				cdto.setTitle(rs.getString("f_content"));
				cdto.setId(rs.getString("f_num"));
				cdto.setIsuse(rs.getString("fm_isuse"));
				cdto.setAllDay(true);
				   if(("used").equals(rs.getString("fm_isuse"))){
	                   cdto.setColor("#616872");
	               }
	               else if(("return").equals(rs.getString("fm_isuse"))){
	                   cdto.setColor("#ff7f9d");
	               }
	               else if(("unuse").equals(rs.getString("fm_isuse"))){
	                   cdto.setColor("#7793ff");
	               }
	               else{
	            	   cdto.setColor("#F9FFAB");
	               }
				return cdto;
			}
		};
		
		Object[] param = new Object[]{m_id};
		
		result = jdbcTemplate.query(sql, rowmapper, param);
		return result;
	}

	@Override
	public int insertTicket(FoodMemberDTO fm) {
		return sqlSession.insert(namespace+".insertTicket" ,fm);
	}

	@Override
	public List<FoodDTO> afoodinfoJson(String f_num) {
		return sqlSession.selectList(namespace+".afoodinfoJson", f_num);
	}

	@Override
	public List<FoodMemberDTO> aticketList(SearchCriteria cri) {
		System.out.println(cri.getKeyfield()+":"+cri.getKeyword());
		return sqlSession.selectList(namespace+".aticketList", cri);
	}

	@Override
	public int aticketCountPaging(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".aticketCountPaging", cri);
	}
	@Override
	public List<FoodMemberDTO> aticketListChart(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".aticketListChart", cri);
	}

	@Override
	public List<FoodMemberDTO> ticketList(SearchCriteria cri) {
		return sqlSession.selectList(namespace+".ticketList", cri);
	}

	@Override
	public int ticketListPaging(SearchCriteria cri) {
		return sqlSession.selectOne(namespace+".ticketListPaging", cri);
	}

	@Override
	public FoodMemberDTO ticketPrint(FoodMemberDTO foodmember) {
		return sqlSession.selectOne(namespace+".ticketPrint", foodmember);
	}

	@Override
	public int ticketUsing(FoodMemberDTO foodmember) {
		return sqlSession.update(namespace+".ticketUsing", foodmember);
	}

	@Override
	public int ticketRefund(FoodMemberDTO foodmember) {
		return sqlSession.update(namespace+".ticketRefund", foodmember);
	}




}
