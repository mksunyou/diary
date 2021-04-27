<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>todo 입력</h1>
	<form action="${pageContext.request.contextPath}/auth/addTodo" method="post">
		<div>todoDate: </div><!-- 날짜 입력 방식 ex) 2021-04-26 -->
		<div><input type="text" name="todoDate" value="${todoDate.toString()}" readOnly="readonly"></div>
		<div>todoTitle :</div>
		<div><input type="text" name="todoTitle"></div>
		<div>todoContent</div>
		<div><textarea name="todoContent" cols="80" rows="5"></textarea></div>
		<div>todoFontColor: </div>
		<div><input type="color" name="todoFontColor"></div>
		<div><button type="submit">addTodo</button></div>
	</form>
	
</body>
</html>