package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoDao {
	
	//todo수정
		public void updateTodo(Connection conn, Todo todo) throws SQLException {
			PreparedStatement stmt = null;

			//"UPDATE todo SET todo_date=?, todo_title=?, todo_content=?, todo_font_color=? WHERE todo_no=?";
			try {
				stmt = conn.prepareStatement(TodoQuery.UPDATE_TODO_ONE_BY_TODO);
				stmt.setString(1, todo.getTodoDate());
				stmt.setString(2, todo.getTodoTitle());
				stmt.setString(3, todo.getTodoContent());
				stmt.setString(4, todo.getTodoFontColor());
				stmt.setInt(5, todo.getTodoNo());
				System.out.printf("stmt: %s<TodoDao.updateTodo()>\n", stmt);
				stmt.executeUpdate();

			} finally {
				stmt.close();
			}

		}
		//todo삭제: todoNo를 이용하여
		public void deleteTodo(Connection conn, int todoNo) throws SQLException {
			PreparedStatement stmt = null;

			try {
				stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_TODO_NO);
				stmt.setInt(1, todoNo);
				System.out.printf("stmt: %s<TodoDao.deleteTodo()>\n", stmt);
				stmt.executeUpdate();			
			} finally {
				stmt.close();
			}
		}
		//todoOne : 일정 자세히 보기
		public Todo selectTodoOneByTodoNo(Connection conn, int todoNo) throws SQLException {
			Todo todo = new Todo();
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {//todoNo 넘기고 todoDate(일정), todoTitle(제목), todoFontColor(배경색), todoContent(상세일정설명) 받기
				stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_ONE_BY_TODO_NO);
				stmt.setInt(1, todoNo);
				System.out.printf("stmt: %s<TodoDao.selectTodoOneByTodoNo()>\n", stmt);
				rs = stmt.executeQuery();
				if(rs.next()) {
					todo.setTodoNo(rs.getInt("todoNo"));
					todo.setTodoContent(rs.getString("todoContent"));
					todo.setTodoDate(rs.getString("todoDate"));
					todo.setTodoFontColor(rs.getString("todoFontColor"));
					todo.setTodoTitle(rs.getString("todoTitle"));
				}

			}finally {
				rs.close();
				stmt.close();
			}

			return todo;
		}
	
	public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException {
		List<Todo> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * SELECT todo_no todoNo, LEFT(todo_title, 10) todoTitle FROM todo 
			 * WHERE member_no=? AND YEAR(todo_date)=? AND MONTH(todo_date)=?
			 */
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Todo todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoTitle(rs.getString("todoTitle"));
                todo.setTodoFontColor(rs.getString("todoFontColor"));
				list.add(todo);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		int rowCnt=0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1,  todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			stmt.setString(5, todo.getTodoAddDate());;
		} finally {
			stmt.close();
		}
		return rowCnt;
	}
	
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException {//final static 순서는 상관 없다.
		int rowCnt=0;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			System.out.println("deleteTodoByMember stmt :" + stmt);
			rowCnt = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		
		return rowCnt;
		
	}
}
