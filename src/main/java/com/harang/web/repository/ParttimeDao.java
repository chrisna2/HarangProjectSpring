package com.harang.web.repository;

import java.util.List;

import com.harang.web.domain.D_ApplyDTO;
import com.harang.web.domain.DaetaDTO;
import com.harang.web.domain.MemberDTO;
import com.harang.web.domain.P_ApplyDTO;
import com.harang.web.domain.ParttimeDTO;
import com.harang.web.domain.SearchCriteria;

public interface ParttimeDao {
	public List<ParttimeDTO> getParttimeList(SearchCriteria cri);
	public List<ParttimeDTO> getMyParttimeList(Object params);
	public List<DaetaDTO> getDaetaList(SearchCriteria cri);
	public List<DaetaDTO> getMyDaetaList(Object params);
	public int getParttimeCnt_apply(String p_num);
	public int getDaetaCnt_apply(String d_num);
	public ParttimeDTO getParttime(String p_num);
	public DaetaDTO getDaeta(String d_num);
	public void counterUp_Parttime(String p_num);
	public void counterUp_Daeta(String d_num);
	public MemberDTO getMember(String m_id);
	public void createParttimeResume(P_ApplyDTO p_apply);
	public void createDaetaResume(D_ApplyDTO d_apply);
	public void deleteParttime(String p_num);
	public void deleteDaeta(String d_num);
	public void deleteParttimeApply(String p_num);
	public void deleteDaetaApply(String d_num);
	public List<P_ApplyDTO> getParttimeApplyList(String p_num);
	public List<D_ApplyDTO> getDaetaApplyList(String d_num);
	public List<P_ApplyDTO> getMyParttimeApplyList(Object params);
	public List<D_ApplyDTO> getMyDaetaApplyList(Object params);
	public P_ApplyDTO getParttimeApply(String p_num);
	public D_ApplyDTO getDaetaApply(String d_num);
	public void updateParttimeChoice(Object params);
	public void updateDaetaChoice(Object params);
	public void deleteParttimeApply(Object params);
	public void deleteDaetaApply(Object params);
	public void insertParttime(ParttimeDTO parttime);
	public void insertDaeta(DaetaDTO daeta);
	public void updateParttime(ParttimeDTO parttime);
	public void updateDaeta(DaetaDTO daeta);
	public List<String> getPicked(String d_num);
	public void updateDaetaMember(D_ApplyDTO d_apply);
	public void report(D_ApplyDTO d_apply);
}
