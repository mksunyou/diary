package gdu.diary.dao;

public class TodoQuery {
	public final static String DELETE_TODO_BY_MEMBER;//아이디 삭제시 게시물 삭제
	public final static String INSERT_TODO;
	public final static String SELECT_TODO_LIST_BY_DATE;
	public final static String SELECT_TODO_ONE_BY_TODO_NO;
	public final static String DELETE_TODO_ONE_BY_TODO_NO;
	public final static String UPDATE_TODO_ONE_BY_TODO;
	public final static String SELECT_TODO_DDAY_LIST;

	static {//스태틱 블럭은 스태틱 필드를 초기화 하기 위해서 사용한다.
		DELETE_TODO_BY_MEMBER = "DELETE FROM member WHERE member_no=?";
		INSERT_TODO = "INSERT INTO todo(member_no, todo_date, todo_title, todo_content, todo_font_color, todo_add_date) VALUES(?,?,?,?,?,NOW())";
		SELECT_TODO_LIST_BY_DATE = "SELECT todo_no todoNo, todo_font_color todoFontColor, left(todo_title, 10) todoTitle, DAY(todo_date) todoDate FROM todo WHERE YEAR(todo_date)=? and MONTH(todo_date)=? AND member_no =?";
		SELECT_TODO_ONE_BY_TODO_NO = "SELECT todo_no todoNo, todo_date todoDate, todo_title todoTitle, todo_content todoContent, todo_font_color todoFontColor FROM todo WHERE todo_no=?";
		DELETE_TODO_ONE_BY_TODO_NO = "DELETE FROM todo WHERE todo_no=? AND member_no=?";
		UPDATE_TODO_ONE_BY_TODO = "UPDATE todo SET todo_date=?, todo_title=?, todo_content=?, todo_font_color=? WHERE todo_no=?";
		SELECT_TODO_DDAY_LIST = "SELECT todo_no todoNo,todo_date todoDate,todo_title todoTitle,DATEDIFF(todo_date,DATE(NOW())) dday FROM todo WHERE todo_date > DATE(NOW()) AND member_no=? LIMIT 10";
	}

}