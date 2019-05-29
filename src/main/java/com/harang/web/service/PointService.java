package com.harang.web.service;

public interface PointService {
	
	/**
	 * 포인트 레코드 기록 및 거래 
	 * @param record
	 */
	public String recordPointTrade(String r_content,long giver_point,long r_point,String giver_id,String haver_id);
	
	/**
	 * 학번으로 사람의 보유 포인트 출력
	 * @param m_id 학번
	 * @return 학번이 가지고 있는 포인트 총량
	 */
	public long pointInfo(String m_id);
}
