<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
</head>
<body>
	<!-- 디버깅 -->
	<div>${diaryMap.targetYear} 년 ${diaryMap.targetMonth}월</div>
	<div>startBlank : ${diaryMap.startBlank}</div>
	<div>endDay : ${diaryMap.endDay}</div>
	<div>endBlank : ${diaryMap.endBlank}</div>
	
	<c:set var="totalCell" value = "${diaryMap.startBlank+diaryMap.endDay+ diaryMap.endBlank}"></c:set>
	<div>totalCell : ${totalCell}</div>
	
	<h1>
		<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth-1}">이전</a>
		${diaryMap.targetYear}년 ${diaryMap.targetMonth}월
		<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}">이후</a>
	</h1>
	<table border="1" width="90%">
		<tr>
			<c:forEach var = "i" begin="1" end="${totalCell}" step="1">
				<c:set var="num" value="${i-diaryMap.startBlank }"></c:set>
				<td>
					<c:if test="${num>0 && num<=diaryMap.endDay}">	
						<a href="${pageContext.request.contextPath}/auth/addTodo?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth}&targetDate=${num}">${num}</a>
					</c:if>
					<c:if test="${num<=0 || num>diaryMap.endDay}">	
						&nbsp;
					</c:if>
				</td>
				<c:if test="${i%7==0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>