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


@WebServlet("/auth/modifyTodoOne")
public class ModifyTodoOneController extends HttpServlet {
	private TodoService todoService;
	//수정폼으로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request로 todoNo 받아옴
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		//다른 사람들이 들어오는 것을 방하기 위해 섹션에서 memberNo를 받아옴
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		
		//수정할 내용(todoOne과 같은 내용) 받아옴
		Todo todoOne = new Todo();
		this.todoService = new TodoService();
		todoOne = this.todoService.getTodoOneByTodoNo(todoNo, memberNo);
		
		request.setAttribute("todoOne", todoOne);
		//수정폼으로 forward
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodoOne.jsp").forward(request, response);
	}

	//수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정폼에서 수정할 내용들과 수정게시물을 찾을 todoNo를 받아옴
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");
		//모두 Todo타입에 담아서 한번에 service로 넘김
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoDate(todoDate);
		todo.setTodoFontColor(todoFontColor);
		
		System.out.println(todo);//todo.toString()
		
		//service 호출
		this.todoService = new TodoService();
		this.todoService.modifyTodoOne(todo);
		//다시 todoOne으로 돌아감
		response.sendRedirect(request.getContextPath()+"/auth/todoOne?todoNo=" + todoNo);
	}

}