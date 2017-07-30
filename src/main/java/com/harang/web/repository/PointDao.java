package com.harang.web.repository;

import com.harang.web.domain.RecordDTO;

public interface PointDao {

	public String recordPointTrade(RecordDTO record);
	public long pointInfo(String m_id);
	
}
