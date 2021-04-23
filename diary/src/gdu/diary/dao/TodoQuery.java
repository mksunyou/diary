package gdu.diary.dao;

public class TodoQuery {
	public final static String DELETE_TODO_BY_Member;
	static {
		DELETE_TODO_BY_Member = "DELETE FROM todo WHERE member_no=?";
	}
}
