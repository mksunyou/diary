<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyMemberPw</title>
</head>
<body>
	<h1>modifyMemberPw</h1>
	
	<form action="${pageContext.request.contextPath}/auth/modifyMemberPw" method="post">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td>
					<input type="hidden" name="memberNo" value="${member.memberNo}">
					<input type="text" name="memberId" value="${member.memberId}" readonly>
				</td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td>
					<input type="password" name="memberPw">
				</td>
			</tr>
		</table>
		<button type="submit">비밀번호변경</button>
	</form>
	<a href="${pageContext.request.contextPath}/auth/myAccount">취소</a>
</body>
</html>
[출처] 72. [MVC 다이어리] 회원가입, 회원정보, 비밀번호 수정 (자바 교실) | 작성자 박성환
