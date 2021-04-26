package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	private DBUtil dbUtil;
	//service에서는 dao와 다른 용어를 사용하는 경우가 많다.
	//select -> get
	//insert -> add
	//update -> modify
	//delete -> remove
	
	//회원탈퇴 - 삭제 실패(rollback) : false  삭제 성공: true
	//한쪽이 오류가 날경우 롤백하는 트랜잭션 처리를 했음
	public boolean removeMemberByKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		Connection conn = null;
		int todoRowCnt = 0;//게시물 삭제 확인
		int memberRowCnt = 0;//회원 탈퇴 확인
		
		try {
			conn = dbUtil.getConnection();
			todoRowCnt = this.todoDao.deleteTodoByMember(conn, member.getMemberNo());//게시물 삭제
			memberRowCnt = this.memberDao.deleteMemberByKey(conn, member);//회원 탈퇴
			System.out.printf("todo: %s\n member: %s\n",todoRowCnt,memberRowCnt);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				//에러가 난 경우 돌아간다.
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;//return값을 넣었어도 finally는 처리한다.
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (todoRowCnt+memberRowCnt) > 0;
	}
	
	//비밀번호 바꾸기
	public void modifyMemberPw(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
	
		try {
			conn = this.dbUtil.getConnection();
			this.memberDao.updateMemberPw(conn, member);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//아이디 중복검사 + 회원가입...?: 아이디가 중복되어있으면 회원가입 안되고, 아이디가 중복아니면 회원가입
	public boolean checkMemberIdAndAddMember(Member member) {//가입성공 true 가입실패false를 리턴
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		boolean checkId = false;//가입성공 여부
			
		try {
			conn = this.dbUtil.getConnection();
			if(this.memberDao.checkMemberId(conn, member) != null){//아이디 있음..아이디 생성하면 안됨
				System.out.println("아이디가 이미 존재합니다.");
				checkId = false;
			} else {//아이디 없음 ..생성가능
				this.memberDao.insertMember(conn, member);
				checkId = true;
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			checkId = false;//catch로 넘어오면 가입실패
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return checkId;
	}
	
	//로그인 - 로그인 성공하면 member에 no와 id를 담아 리턴해줌
	public Member getMemberByKey(Member member) {
		this.memberDao = new MemberDao();
		this.dbUtil = new DBUtil();
		Member returnMember = null;
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//conn은 service에서 닫아줘야 한다.//나머지는 dao에서 닫아줌
		}
		return returnMember;
	}

}