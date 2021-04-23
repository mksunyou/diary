package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private DBUtil dbUtil;
	private MemberDao memberDao;
	private TodoDao todoDao;
	//service에서 쓰는 언어 : select -> get, insert -> add, update -> modify, delete -> remove
	//이 서비스이 없으면 트랜잭션 처리 불가능.
	
	//삭제성공 : true, 삭제실패(rollback) : false
	public boolean removeMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao(); 
		this.todoDao = new TodoDao();
		Connection conn = null;
		int todoRowCnt = 0;
		int memberRowCnt = 0;
		try {
			conn = dbUtil.getConnection();
			// 둘 중 하나만 처리 된 후 예외가 발생하면 안된다. 따라서 둘중 하나만 처리 되면 rollback. 
			todoRowCnt = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());//sqlException이 dao에서 예외처리가 발생하기 때문에 예외처리를 해줘야함. 
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false; //catch절에서 끝나면 무조건 false(삭제실패)
		} finally {
			this.dbUtil.close(conn, null, null);
		}
		System.out.println("탈퇴 todoRowCnt : "+todoRowCnt+", memberRowCnt :"+memberRowCnt);
		return (todoRowCnt+memberRowCnt) >0;
	}
	
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
