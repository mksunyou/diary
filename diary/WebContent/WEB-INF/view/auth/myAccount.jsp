<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
<body>
	<h1>MyAccount</h1>
	<table border="1">
		<tr>
			<td>memberId</td>
			<td>${member.memberId}</td>
		</tr>
		<tr>
			<td>memberDate</td>
			<td>${member.memberDate}</td>
		</tr>
		<tr>
			<td>memberPw</td>
			<td><a href="${pageContext.request.contextPath}/auth/modifyMemberPw">변경</a></td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/auth/removeMember"><button type="button">회원탈퇴</button></a>
	<a href="${pageContext.request.contextPath}/login"><button type="button">홈으로</button></a>
</body>
</html>
