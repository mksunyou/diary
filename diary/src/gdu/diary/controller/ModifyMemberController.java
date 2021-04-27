package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;


//컨트롤러 do get->비밀번호 변경 폼(modifyMember.jsp)->컨트롤러 post->
//멤버서비스 -> 멤버dao -> 멤버서비스 -> 컨트롤러

@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {
	private MemberService memberService;
	//비밀번호 변경 뷰로 연결해줌
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyMember.jsp").forward(request, response);
	}

	//비밀번호 변경 action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.memberService = new MemberService();
		//request 처리 - 바뀐 비밀번호 받아줌
		String memberPw = request.getParameter("memberPw");
		
		//섹션에서 멤버번호 가져오기
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member)session.getAttribute("sessionMember");//세셔에서 memberNo와 memberId를 받음
		member.setMemberPw(memberPw);
		//비밀번호 변경 하고 로그아웃 후 로그인페이지 돌아가기
		this.memberService.modifyMemberPw(member);
		//비밀 번호 변경후 로그아웃 페이지로 감
		response.sendRedirect(request.getContextPath()+"/auth/logout");
	}

}