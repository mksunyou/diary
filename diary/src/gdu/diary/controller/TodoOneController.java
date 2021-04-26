package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Todo;


@WebServlet("/auth/todoOne")
public class TodoOneController extends HttpServlet {
	private TodoService todoService;
	//일정 상세보기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//todoNo - request 로 받음
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));

		//service에서 일정 상세정보 받아오기
		this.todoService = new TodoService();
		Todo todoOne = new Todo();
		todoOne = this.todoService.getTodoOneByTodoNo(todoNo);
		System.out.println(todoOne.toString());

		request.setAttribute("todoOne", todoOne);
		request.getRequestDispatcher("/WEB-INF/view/auth/todoOne.jsp").forward(request, response);
	}

}