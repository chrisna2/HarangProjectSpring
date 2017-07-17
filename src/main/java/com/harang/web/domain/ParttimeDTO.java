package com.harang.web.domain;

public class ParttimeDTO {
	private int list_num; //寃뚯떆�뙋 湲�踰덊샇
	private String p_num; //DB 湲�踰덊샇
	private String p_title; //湲��젣紐�
	private String p_regdate; //�벑濡앸궇吏�
	private String p_deadline; //留덇컧�씪
	private int p_wage; //�떆湲�
	private String p_term; //湲곌컙
	private String p_content; //蹂몃Ц�궡�슜
	private String p_tel; //�뿰�씫泥�
	private String p_daycode; //�븣諛붾궇吏�(肄붾뱶泥섎━) 7�옄由� 0 OR 1
	private String p_location; //�옣�냼
	private String p_header; //癒몃━留�
	private int p_cnt; //議고쉶�닔
	private String m_id; //湲��벖�씠
	private String m_name; //湲��벖�씠 �씠由�
	private int cnt_apply; //吏��썝�옄�닔
	private String pm_choice; // 梨꾩슜�뿬遺�
	private boolean checkDeadline; // 留덇컧�씪 吏��궗�뒗吏�
	
	
	public int getList_num() {
		return list_num;
	}
	public void setList_num(int list_num) {
		this.list_num = list_num;
	}
	public String getP_num() {
		return p_num;
	}
	public void setP_num(String p_num) {
		this.p_num = p_num;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_regdate() {
		return p_regdate;
	}
	public void setP_regdate(String p_regdate) {
		this.p_regdate = p_regdate;
	}
	public String getP_deadline() {
		return p_deadline;
	}
	public void setP_deadline(String p_deadline) {
		this.p_deadline = p_deadline;
	}
	public int getP_wage() {
		return p_wage;
	}
	public void setP_wage(int p_wage) {
		this.p_wage = p_wage;
	}
	public String getP_term() {
		return p_term;
	}
	public void setP_term(String p_term) {
		this.p_term = p_term;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public String getP_daycode() {
		return p_daycode;
	}
	public void setP_daycode(String p_daycode) {
		this.p_daycode = p_daycode;
	}
	public String getP_location() {
		return p_location;
	}
	public void setP_location(String p_location) {
		this.p_location = p_location;
	}
	public String getP_header() {
		return p_header;
	}
	public void setP_header(String p_header) {
		this.p_header = p_header;
	}
	public int getP_cnt() {
		return p_cnt;
	}
	public void setP_cnt(int p_cnt) {
		this.p_cnt = p_cnt;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public int getCnt_apply() {
		return cnt_apply;
	}
	public void setCnt_apply(int cnt_apply) {
		this.cnt_apply = cnt_apply;
	}
	public String getPm_choice() {
		return pm_choice;
	}
	public void setPm_choice(String pm_choice) {
		this.pm_choice = pm_choice;
	}
	public boolean isCheckDeadline() {
		return checkDeadline;
	}
	public void setCheckDeadline(boolean checkDeadline) {
		this.checkDeadline = checkDeadline;
	}
	
}
