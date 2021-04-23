package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;

public class TodoDao {
	private DBUtil dbUtil;
	
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException {//final static 순서는 상관 없다.
		this.dbUtil = new DBUtil();
		int rowCnt=0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_Member);
			stmt.setInt(1, memberNo);
			System.out.println("deleteTodoByMember stmt :" + stmt);
			rowCnt = stmt.executeUpdate();
		} finally {
			this.dbUtil.close(null, stmt, null);
		}
		
		return rowCnt;
		
	}
}
