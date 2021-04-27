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

//일정 삭제
@WebServlet("/auth/removeTodoOne")
public class RemoveTodoOneController extends HttpServlet {
	private TodoService todoService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제할 일정을 찾기위해 todoNo를 넘겨받음
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		
		//!!!!혹시 다른 사람들이 들어와서 삭제할 가능성이 있으므로 섹션에 memberNo 넘겨받기
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		//일정 삭제 후 삭제한 일정이 있었던 달로 돌아가기 위해 받아옴
		String todoDate = request.getParameter("todoDate");
		//삭제를 위해 service를 호출
		this.todoService = new TodoService();
		this.todoService.removeTodoOneByTodoNo(todoNo, memberNo);
		
		//삭제한 일정 달의 달력으로 돌아가기
		//넘겨받음 todoDate는 2021-04-21로 되어 있으므로 "-"를 기준으로 잘라서 년도와 달을 구한다.
		String[] arr = todoDate.split("-");
		response.sendRedirect(request.getContextPath() + "/auth/diary?targetYear="+arr[0]+"&targetMonth="+(Integer.parseInt(arr[1])-1));
	}
}