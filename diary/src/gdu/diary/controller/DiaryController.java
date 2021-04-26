package gdu.diary.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.DiaryService;
import gdu.diary.vo.Member;

//다이어리(달력) 뷰와 연결해줌
@WebServlet("/auth/diary")
public class DiaryController extends HttpServlet {
	private DiaryService diaryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		
		//날짜를 바꿀경우 년도와 월을 받아옴
		String targetYear = request.getParameter("targetYear");// "2021" 문자열로 넘어옴,.. 값 없으면 null
		String targetMonth = request.getParameter("targetMonth");// 4월이면 ...3, 5월이면 ... 4
		//diary 서비스와 연결해서 달력을 만들기 위해 정보를 받아옴(dao와 연결x)
		//memberNo는 멤버별 일정을 받아오기 위해 넘겨줌
		this.diaryService = new DiaryService();
		Map<String, Object> diaryMap = this.diaryService.getDiary(memberNo, targetYear, targetMonth);
		System.out.println("TodoList: "+diaryMap.get("todoList"));
		
		
		//받아온 값을 request에 넣어줌
		request.setAttribute("diaryMap", diaryMap);
		//다이어리 뷰로 forward
		request.getRequestDispatcher("/WEB-INF/view/auth/diary.jsp").forward(request, response);
	}



}