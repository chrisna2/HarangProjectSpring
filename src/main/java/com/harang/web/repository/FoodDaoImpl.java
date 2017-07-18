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

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				CalanderDTO cdto = new CalanderDTO();
				cdto.setStart(rs.getString("f_selldate"));
				cdto.setTitle(rs.getString("f_content"));
				cdto.setId(rs.getString("f_num"));
				cdto.setAllDay(true);
				return cdto;
			}
		};	
		result = jdbcTemplate.query("SELECT f_selldate, f_content, f_num FROM tbl_food where f_selldate > sysdate()",rowmapper);
		return result;
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
	public int aticketCountPaging() {
		return sqlSession.selectOne(namespace+".aticketCountPaging");
	}

}
