package com.harang.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harang.web.domain.RecordDTO;
import com.harang.web.repository.PointDao;

@Service
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao pointDao;
	
	@Override
	public String recordPointTrade(String r_content,long giver_point,long r_point,String giver_id,String haver_id) {
		
		if(giver_point < r_point){
			System.out.println("보유 포인트 보다 전송 포인트가 더 많습니다.");
			
			return "overpoint";
		}
		
		RecordDTO record = new RecordDTO();
		
		record.setM_giver(giver_id);
		record.setM_haver(haver_id);
		record.setR_content(r_content);
		record.setR_point(r_point);
		
		return pointDao.recordPointTrade(record);
	}

	@Override
	public long pointInfo(String m_id) {
		return pointDao.pointInfo(m_id);
	}

}
