package gdu.diary.dao;

public class MemberQuery {
	public final static String SELECT_MEMBER_BY_KEY;//전역변수 static, 변치 않는 final 사용.
	
	static { //쿼리분리
		SELECT_MEMBER_BY_KEY = "SELECT member_no memberNo, member_id memberId FROM member WHERE member_id=? AND member_pw=password(?)";
	}
}
