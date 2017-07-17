package com.harang.web.domain;

public class PagingDto {
	private int totalRecord; // 珥� 湲��쓽 媛쒖닔
	private int numPerPage; // �븳�럹�씠吏��떦 蹂댁뿬吏� 湲��쓽 媛쒖닔
	private int totalPage; // �쟾泥� �럹�씠吏� 媛쒖닔
	private int nowPage; // �쁽�옱 �럹�씠吏� �쐞移�
	private int beginPerPage; // 媛� �럹�씠吏��쓽 �떆�옉踰덊샇
	private int pagePerBlock; // [�떎�쓬]�쓣 �닃���쓣 �븣 �븳 釉붾윮�떦 �씠�룞�븷 �럹�씠吏� �닔
	private int totalBlock; // �쟾泥� 釉붾윮�쓽 媛쒖닔
	private int nowBlock; // �쁽�옱 釉붾윮 �쐞移�
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getBeginPerPage() {
		return beginPerPage;
	}
	public void setBeginPerPage(int beginPerPage) {
		this.beginPerPage = beginPerPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
}
