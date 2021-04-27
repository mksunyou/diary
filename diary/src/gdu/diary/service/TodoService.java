package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	private DBUtil dbUtil;
	private TodoDao todoDao;
	//todo수정
		public void modifyTodoOne(Todo todo) {
			this.dbUtil = new DBUtil();
			this.todoDao = new TodoDao();
			Connection conn = null;
			
			try {//commit과 rollback 잘 했는지 확인하기
				conn = this.dbUtil.getConnection();
				this.todoDao.updateTodo(conn, todo);
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
					e.printStackTrace();
				}
			}
		}
		//todo삭제
		public void removeTodoOneByTodoNo(int todoNo, int memberNo) {
			this.dbUtil = new DBUtil();
			this.todoDao = new TodoDao();
			Connection conn = null;
			
			try {
				conn = this.dbUtil.getConnection();
				this.todoDao.deleteTodoOne(conn, todoNo, memberNo);
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		//todoOne - 일정 자세히 보기
		public Todo getTodoOneByTodoNo(int todoNo, int memberNo) {
			Todo todo = new Todo();
			this.dbUtil = new DBUtil();
			this.todoDao = new TodoDao();
			Connection conn = null;
			
			try {
				conn = this.dbUtil.getConnection();
				todo = this.todoDao.selectTodoOneByTodoNo(conn, todoNo, memberNo);
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
					e.printStackTrace();
				}
			}
			
			return todo;
		}
		
		//addTodo - diary에 일정 입력
		public int addTodo(Todo todo) {
			int rowCnt = 0;
			this.dbUtil = new DBUtil();
			Connection conn = null;
			this.todoDao = new TodoDao();
			
			try {
				conn = this.dbUtil.getConnection();
				rowCnt = this.todoDao.insertTodo(conn, todo);
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {//conn 사용했으면 닫기
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return rowCnt;
		}

	}