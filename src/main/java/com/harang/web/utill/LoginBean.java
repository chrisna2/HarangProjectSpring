package com.harang.web.utill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.harang.web.domain.MemberDTO;


public class LoginBean {

	public MemberDTO getLoginInfo(HttpServletRequest req){
		MemberDTO login = null;
		MemberDTO member = (MemberDTO)req.getSession().getAttribute("member");
		MemberDTO admin = (MemberDTO)req.getSession().getAttribute("admin");
		
		if (admin != null){ 
			login = admin;
		}else{ 
			login = member;
		}
		
		return login;
	}
	
	public MemberDTO getLoginIngfo(HttpSession session){
		MemberDTO login = null;
		MemberDTO member = (MemberDTO)session.getAttribute("member");
		MemberDTO admin = (MemberDTO)session.getAttribute("admin");
		
		if (admin != null){ 
			login = admin;
		}else{ 
			login = member;
		}
		
		return login;
	}

	public Boolean adminCheck(String m_id){
		if(m_id.equals("admin01") || m_id.equals("admin02") || m_id.equals("admin03") 
				|| m_id.equals("admin04") || m_id.equals("admin05") || m_id.equals("admin06") ){
			return true;
		}
		return false;
	}
	
/*
	public void refreshSession(HttpServletRequest req){
		
		MemberDTO member = getLoginInfo(req);
		String m_id = member.getM_id();
		HttpSession session = req.getSession();
		pool = DBConnectionMgr.getInstance();
		
		String sql = "select m_pw, m_name, m_dept, m_mail, m_tel, m_addr, m_point, m_photo, m_fee, m_grade, m_birth, m_regdate "
				+ "from tbl_member where m_id = ?";
		
		MemberDTO mdto = new MemberDTO();
		
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			String m_pw = rs.getString("m_pw");
			String m_name = rs.getString("m_name");
			String m_dept = rs.getString("m_dept");
			String m_mail = rs.getString("m_mail");
			String m_tel = rs.getString("m_tel");
			String m_addr = rs.getString("m_addr");
			long m_point = rs.getLong("m_point");
			String m_photo = rs.getString("m_photo");
			int m_fee = rs.getInt("m_fee");
			int m_grade = rs.getInt("m_grade");
			String m_birth = rs.getString("m_birth");
			String m_regdate = rs.getString("m_regdate");
			
			mdto.setM_id(m_id);
			mdto.setM_name(m_name);
			mdto.setM_dept(m_dept);
			mdto.setM_mail(m_mail);
			mdto.setM_tel(m_tel);
			mdto.setM_addr(m_addr);
			mdto.setM_point(m_point);
			mdto.setM_photo(m_photo);
			mdto.setM_fee(m_fee);
			mdto.setM_grade(m_grade);
			mdto.setM_birth(m_birth);
			mdto.setM_regdate(m_regdate);
		
			//愿�由ъ옄 �씪�븣.
			if(m_dept.equals("愿�由ъ옄")){
				session.setAttribute("admin", mdto);
			}
			//�씪諛� �쉶�썝 �씪�븣
			else{
				session.setAttribute("member", mdto);
			}
			
		}catch(Exception err){
			System.out.println( "session refresh fail : " + err);
			err.printStackTrace();
		}
		finally{
			// DBCP �젒�냽�빐�젣
			pool.freeConnection(con,pstmt,rs);
		}
		session.setAttribute("head_msg",messageRefresh(m_id));
		session.setAttribute("NRM", getNRMRefresh(m_id));
		session.setAttribute("PLH", pointRefresh(m_id));
	}
	

	public ArrayList messageRefresh(String m_id){
		
		ArrayList inboxlist = new ArrayList();
		
		try{
			con = pool.getConnection();
			
			String sql="SELECT t_num, t_title, t_send_date, m_sender, "
					+ "(select m_name from tbl_member where m_id = m_sender) as m_sender_name,"
					+ "(select m_photo from tbl_member where m_id = m_sender) as s_photo "
					+ " FROM tbl_text WHERE m_reader=? AND NOT m_sender=? AND t_read_del = 'N' "
					+ "ORDER BY t_send_date DESC limit 5";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MessageDTO msg = new MessageDTO();
				msg.setT_num(rs.getString("t_num"));
				msg.setT_title(rs.getString("t_title"));
				msg.setT_send_date(rs.getString("t_send_date"));
				msg.setM_sender(rs.getString("m_sender"));
				msg.setS_photo(rs.getString("s_photo"));
				msg.setM_sender_name(rs.getString("m_sender_name"));
				
				inboxlist.add(msg);
			}
			
		}catch(Exception err){
			System.out.println("getGivenMessageList() : " + err);
			err.printStackTrace();
		}finally{
			pool.freeConnection(con,pstmt);
		}
		
		return inboxlist;
	}
	

	public ArrayList pointRefresh(String m_id){
		
		ArrayList plist  = new ArrayList();
		
		String sql  = "select r_regdate, r_content, r_point, m_giver, m_haver, "+
				"(select m_name from tbl_member where m_id = m_giver) as m_givername, "+
				"(select m_name from tbl_member where m_id = m_haver) as m_havername  "+
				"from tbl_record where m_giver = ? or  m_haver = ? order by r_regdate desc limit 5";
		
		try {
			pool = DBConnectionMgr.getInstance();
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				RecordDTO rdto = new RecordDTO();
				
				rdto.setR_regdate(rs.getString("r_regdate"));
				rdto.setR_content(rs.getString("r_content"));
				rdto.setR_point(rs.getLong("r_point"));
				rdto.setM_giver(rs.getString("m_giver"));
				rdto.setM_haver(rs.getString("m_haver"));
				rdto.setM_givername(rs.getString("m_givername"));
				rdto.setM_havername(rs.getString("m_havername"));
				
				plist.add(rdto);
			}
		} 
		catch (Exception e) {
			System.out.println( "header.jsp : " + e);
		}
		finally{
			// DBCP �젒�냽�빐�젣
			pool.freeConnection(con, pstmt, rs);
		}
		return plist;
	}
	
	

	public int getNRMRefresh(String m_id){
		int notRead=0;
		try{
			con = pool.getConnection();
			
			String sql="SELECT count(t_num) FROM tbl_text WHERE m_reader = ? "
					+ "AND t_read_date is null AND NOT m_sender = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_id);
			rs = pstmt.executeQuery();
			rs.next();
			notRead = rs.getInt(1);
			
		}catch(Exception err){
			System.out.println("getNotReadMessage() : " + err);
			err.printStackTrace();
		}finally{
			pool.freeConnection(con,pstmt);
		}
		
		return notRead;
	}
	*/
}
