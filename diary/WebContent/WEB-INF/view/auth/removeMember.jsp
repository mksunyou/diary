<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>remonveMember</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/auth/removeMember">
		<div>
			memberPw:
		</div>
		<div>
			<input type="password" name="memberPw">
		</div>
		<button type="submit">탈퇴</button>
	</form>
</body>
</html>