package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import gdu.diary.dao.MemberDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	//select -> get
	//insert -> add
	// update -> modify
	// delete -> remove
	public Member getMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		this.memberDao = new MemberDao(); 
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			//conn.setAutoCommit(false);//dbUtil에서 해야함 따라서 여기선 생략
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(conn, null, null);
		}
		return returnMember;
	}
}
