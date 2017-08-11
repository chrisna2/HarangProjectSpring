package com.harang.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.SearchCriteria;
import com.harang.web.repository.FoodDao;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodDao foodDao;
	
	@Override
	public void updateFood(FoodDTO food) {
		foodDao.updateFood(food);
	}

	@Override
	public void deleteFood(FoodDTO food) {
		foodDao.deleteFood(food);
	}

	@Override
	public void insertFood(FoodDTO food) {
		foodDao.insertFood(food);
	}

	//Ajax
	@Override
	public List<CalanderDTO> amenuJson() {
		return foodDao.amenuJson();
	}
	@Override
	public List<FoodDTO> afoodinfoJson(String f_num) {
		return foodDao.afoodinfoJson(f_num);
	}
	@Override
	public List<CalanderDTO> menuJson(String m_id) {
		return foodDao.menuJson(m_id);
	}


	//티켓 판매 내역 조회
	@Override
	public List<FoodMemberDTO> aticketList(SearchCriteria cri) {
		return foodDao.aticketList(cri);
	}
	@Override
	public int aticketCountPaging(SearchCriteria cri) {
		return foodDao.aticketCountPaging(cri);
	}
	
	//티켓 판매
	@Override
	public int insertTicket(FoodMemberDTO fm) {
		return foodDao.insertTicket(fm);
	}

	@Override
	public List<FoodMemberDTO> ticketList(SearchCriteria cri) {
		return foodDao.ticketList(cri);
	}

	@Override
	public int ticketListPaging(SearchCriteria cri) {
		return foodDao.ticketListPaging(cri);
	}

	@Override
	public FoodMemberDTO ticketPrint(FoodMemberDTO foodmember) {
		return foodDao.ticketPrint(foodmember);
	}

	@Override
	public int ticketUsing(FoodMemberDTO foodmember) {
		return foodDao.ticketUsing(foodmember);
	}

	@Override
	public int ticketRefund(FoodMemberDTO foodmember) {
		return foodDao.ticketRefund(foodmember);
	}


}
