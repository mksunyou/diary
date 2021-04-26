package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	private DBUtil dbUtil;
	//회원탈퇴 - rowCnt 가 0이 아니면 삭제 성공
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException {
		int rowCnt = 0; //삭제된 줄 수를 구함
		this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			System.out.printf("stmt: %s<MemberDao.deleteMemberByKey()>\n", stmt);
			rowCnt = stmt.executeUpdate();
		}finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	//회원 비밀번호 업데이트
	public void updateMemberPw(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_PW);
			stmt.setString(1, member.getMemberPw());
			stmt.setInt(2, member.getMemberNo());
			System.out.printf("stmt: %s<MemberDao.updateMemberPw()>\n", stmt);
			stmt.executeUpdate();
			
		} finally {
			stmt.close();
		}
	}
	//회원가입 아이디 유효성검사 - 아이디가 있으면 checkMemberId를 리턴한다.
	public String checkMemberId(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String checkMemberId = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.CHECK_MEMEBER_ID);
			stmt.setString(1, member.getMemberId());
			System.out.printf("stmt: %s<MemberDao.checkMemberId()>\n", stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				checkMemberId = rs.getString("memberId");
			}
			
		} finally {
			rs.close();
			stmt.close();		
		}
		return checkMemberId;
	}
	
	//회원가입
	public void insertMember(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.printf("stmt: %s<MemberDao.insertMember()>\n", stmt);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		
	}
	
	//로그인
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.printf("stmt: %s<MemberDao.selectMemberByKey()>\n", stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
			
		} finally {
			rs.close();
			stmt.close(); //conn 닫으면 안됨 conn은 service에서 생성하고 닫음
			
		}
		
		return returnMember;
	}
}