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
import com.harang.web.domain.FoodDTO;

@Repository
public class AjaxDaoImpl implements AjaxDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	//ajax�� ���ؼ� ��¿�� ���� �����
	
	//mybatis�� ���ǿ�! mapper Ŭ������ ���� �� ���� ��!
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.harang.mapper.ajax-mapper";
	
	
	@Override
	public List<CalanderDTO> umenuList() {
		return null;
	}

	@Override
	public List<CalanderDTO> amenuList() {
		
		List<CalanderDTO> result = new ArrayList<CalanderDTO>();

		//�޷��� ���� Ŭ���� ����ؾ� ��;
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
	public List<FoodDTO> menuinfo(String f_num) {
		return sqlSession.selectList(namespace+".menuinfo",f_num);
	}

}
