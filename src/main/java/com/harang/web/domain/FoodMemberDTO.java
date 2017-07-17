package com.harang.web.domain;

import java.util.Date;

public class FoodMemberDTO {

	private String m_id;
	private String f_num;
	private String fm_regdate;
	private String fm_isuse;
	private Date fm_usedate;
	private int f_point;
	private String f_title;
	private String f_content;
	private String f_regdate;
	private Date f_selldate;
	private int fm_sellcount;
	
	public int getFm_sellcount() {
		return fm_sellcount;
	}
	public void setFm_sellcount(int fm_sellcount) {
		this.fm_sellcount = fm_sellcount;
	}
	public int getF_point() {
		return f_point;
	}
	public void setF_point(int f_point) {
		this.f_point = f_point;
	}
	public String getF_title() {
		return f_title;
	}
	public void setF_title(String f_title) {
		this.f_title = f_title;
	}
	public String getF_content() {
		return f_content;
	}
	public void setF_content(String f_content) {
		this.f_content = f_content;
	}
	public String getF_regdate() {
		return f_regdate;
	}
	public void setF_regdate(String f_regdate) {
		this.f_regdate = f_regdate;
	}
	public Date getF_selldate() {
		return f_selldate;
	}
	public void setF_selldate(Date f_selldate) {
		this.f_selldate = f_selldate;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getF_num() {
		return f_num;
	}
	public void setF_num(String f_num) {
		this.f_num = f_num;
	}
	public String getFm_regdate() {
		return fm_regdate;
	}
	public void setFm_regdate(String fm_regdate) {
		this.fm_regdate = fm_regdate;
	}
	public String getFm_isuse() {
		return fm_isuse;
	}
	public void setFm_isuse(String fm_isuse) {
		this.fm_isuse = fm_isuse;
	}
	public Date getFm_usedate() {
		return fm_usedate;
	}
	public void setFm_usedate(Date fm_usedate) {
		this.fm_usedate = fm_usedate;
	}
	
	
	
}
