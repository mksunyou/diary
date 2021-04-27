package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdu.diary.vo.Todo;

public class TodoDao {
	
	
	public List<Map<String,Object>> selectTodoDdayList(Connection conn, int memberNo) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_DDAY_LIST);
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("todoNo", rs.getInt("todoNo"));
				map.put("todoDate", rs.getString("todoDate"));
				map.put("todoTitle", rs.getString("todoTitle"));
				map.put("dday", rs.getInt("dday"));
				list.add(map);
			}
			
		} finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	
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
		public void deleteTodoOne(Connection conn, int todoNo, int memberNo) throws SQLException {
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_ONE_BY_TODO_NO);
				stmt.setInt(1, todoNo);
				stmt.setInt(2, memberNo);
				System.out.printf("stmt: %s<TodoDao.deleteTodo()>\n", stmt);
				stmt.executeUpdate();			
			} finally {
				stmt.close();
			}
		}
		//todoOne : 일정 자세히 보기
		public Todo selectTodoOneByTodoNo(Connection conn, int todoNo, int memberNo) throws SQLException {
			Todo todo = new Todo();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {//todoNo 넘기고 todoDate(일정), todoTitle(제목), todoFontColor(배경색), todoContent(상세일정설명) 받기
				stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_ONE_BY_TODO_NO);
				stmt.setInt(1, todoNo);
				stmt.setInt(2, memberNo);
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
		
		//diary에 1달 일정 보여주기
		public List<Todo> selectTodoListByDate(Connection conn, int memberNo, int targetYear, int targetMonth) throws SQLException{
			List<Todo> list = new ArrayList<>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {//SELECT todo_no todoNo, LEFT(todo_title) todoTitle, todo_date todoDate FROM todo WHERE member_no=? AND Year(todo_date)=? AND MONTH(todo_date)=? 
				stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
				stmt.setInt(3, memberNo);
				stmt.setInt(1, targetYear);
				stmt.setInt(2, targetMonth);
				System.out.printf("stmt: %s<TodoDao.selectTodoListByDate()>\n", stmt);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					Todo todo = new Todo();
					todo.setTodoNo(rs.getInt("todoNo"));
					todo.setTodoDate(rs.getString("todoDate"));
					todo.setTodoTitle(rs.getString("todoTitle"));
					todo.setTodoFontColor(rs.getString("todoFontColor"));
					list.add(todo);
				}
				
			}finally {
				rs.close();
				stmt.close();
			}
			return list;
		}
		//일정입력(todo table)
		public int insertTodo(Connection conn, Todo todo) throws SQLException {
			int rowCnt = 0;
			PreparedStatement stmt = null;
			
			try{
				stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
				stmt.setInt(1, todo.getMemberNo());
				stmt.setString(2, todo.getTodoDate());
				stmt.setString(3, todo.getTodoTitle());
				stmt.setString(4, todo.getTodoContent());
				stmt.setString(5, todo.getTodoFontColor());
				rowCnt = stmt.executeUpdate();
			} finally {
				stmt.close();
			}
			return rowCnt;
		}
		
		//게시물 삭제 : 아이디 삭제를 위해서
		public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException {

			int rowCnt = 0;
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
				stmt.setInt(1, memberNo);
				System.out.printf("stmt: %s<TodoDao.deleteTodoByKey()>\n", stmt);
				rowCnt = stmt.executeUpdate();
			} finally {
				stmt.close();//conn은 service에서 닫는다.
			}
		
			return rowCnt;
			
		}

	}
