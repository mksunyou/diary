package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Member;
import gdu.diary.vo.Todo;


@WebServlet("/auth/modifyTodo")
public class ModifyTodoController extends HttpServlet {
	private TodoService todoService;
	//수정폼으로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		Todo todoOne = new Todo();
		this.todoService = new TodoService();
		todoOne = this.todoService.getTodoOneByTodoNo(todoNo);

		request.setAttribute("todoOne", todoOne);
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodo.jsp").forward(request, response);
	}

	//수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");

		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoDate(todoDate);
		todo.setTodoFontColor(todoFontColor);

		System.out.println(todo);//todo.toString()

		//호출
		this.todoService = new TodoService();
		this.todoService.modifyTodoOne(todo);
		response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo=" + todoNo);
	}

}