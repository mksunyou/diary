package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;


//회원가입 컨트롤러
@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	private MemberService memberService;
	
	//회원가입 뷰로 forward
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}
	//회원가입 액션 - service와 연결
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request처리 - 가입을 위해 id, pw를 받음
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		//멤버 변수에 넣어줌
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		System.out.println(member.toString());
		//멤버 서비스와 연결
		this.memberService = new MemberService();
		
		boolean check = this.memberService.checkMemberIdAndAddMember(member);
		if(check == false) {// 로그인 실패 - 다시 adaMember로 돌아가서 회원가입함
			response.sendRedirect(request.getContextPath()+"/addMember");
			return;
		}
		
		//회원가입 성공 - 로그인 페이지로 
		response.sendRedirect(request.getContextPath()+"/login");
	}
}