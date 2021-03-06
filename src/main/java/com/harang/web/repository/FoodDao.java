package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.CalanderDTO;
import com.harang.web.domain.Criteria;
import com.harang.web.domain.FoodDTO;
import com.harang.web.domain.FoodMemberDTO;
import com.harang.web.domain.SearchCriteria;

public interface FoodDao {
	
	public void updateFood(FoodDTO food);
	public void deleteFood(FoodDTO food);
	public void insertFood(FoodDTO food);

	//ajax사용 
	public List<CalanderDTO> amenuJson(); 
	public List<FoodDTO> afoodinfoJson(String f_num); //유저 메뉴 관리자 메뉴 공통
	public List<CalanderDTO> menuJson(String m_id);
	public int insertTicket(FoodMemberDTO fm);
	
	
	//티켓 리스트 출력
	public List<FoodMemberDTO> aticketList(SearchCriteria cri);
	public List<FoodMemberDTO> aticketListChart(SearchCriteria cri);
	public int aticketCountPaging(SearchCriteria cri);
	
	
	//티켓 구매 내역
	public List<FoodMemberDTO> ticketList(SearchCriteria cri);
	public int ticketListPaging(SearchCriteria cri);
	
	//티켓 출력
	public FoodMemberDTO ticketPrint(FoodMemberDTO foodmember);
	public int ticketUsing(FoodMemberDTO foodmember);
	public int ticketRefund(FoodMemberDTO foodmember);
	
}
