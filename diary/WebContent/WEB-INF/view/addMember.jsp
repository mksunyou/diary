<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMember</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<form action="${pageContext.request.contextPath}/addMember" method="post">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId" placeholder="example@example.com">
				</td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td>
					<input type="password" name="memberPw">
				</td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
	<a href="${pageContext.request.contextPath}/login">취소</a>
</body>
</html>
[출처] 72. [MVC 다이어리] 회원가입, 회원정보, 비밀번호 수정 (자바 교실) | 작성자 박성환
