package com.harang.web.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.harang.web.domain.FoodDTO;

@Repository
public class FoodDaoImpl implements FoodDao {

	//mybatis의 끝판왕! mapper 클래스도 이제 안 만들어도 됨!
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

}
