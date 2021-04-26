<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
</head>
<body>
<!--
	<div>${diaryMap.currentYear}년 ${diaryMap.currentMonth+1}월</div>
	<div>endBlank: ${diaryMap.endBlank}</div>
	<div>startBlank: ${diaryMap.startBlank}</div>
	<div>endDay: ${diaryMap.endDay}</div>
	<div> totalCell: ${totalCell}</div>
-->
	<div>${diaryMap.todoList}</div>
	<c:set var="totalCell" value="${diaryMap.startBlank+diaryMap.endDay+diaryMap.endBlank}"></c:set>
	<!-- c:set 변수만들고 초기화-->
	<h2><!-- 다음달이나 전달로 넘어갈때 년도와 월을 넘겨줌 -->
		<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.currentYear}&targetMonth=${diaryMap.currentMonth-1}">이전달</a>
			${diaryMap.currentYear}년 ${diaryMap.currentMonth+1}월
		<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.currentYear}&targetMonth=${diaryMap.currentMonth+1}">다음달</a>
	</h2>
	<table border="1" width="90%">
		<tr><!-- i-diaryMap.startBlank: 전체셀에서 비어있는 셀을 빼고 0이하의 수는 보여주지 않는다. -->
			<c:forEach var="i" begin="1" end="${totalCell}" step="1">
				<c:set var="num" value="${i-diaryMap.startBlank}"></c:set>
				<td>
					<c:if test="${num > 0 && num <= diaryMap.endDay}"><!-- db에는 달을 +1해서 넘겨줘야한다. -->
						<a href="${pageContext.request.contextPath}/auth/addTodo?year=${diaryMap.currentYear}&month=${diaryMap.currentMonth+1}&day=${num}">${num}</a>
						<div>
							<c:forEach var="todo" items="${diaryMap.todoList}">
								<!-- todoDate와 num가 일치하면 일정을 보여준다. -->
								<c:if test="${todo.todoDate == num}">
									<div style="background-color:${todo.todoFontColor}">
										<a href="${pageContext.request.contextPath}/auth/todoOne?todoNo=${todo.todoNo}">${todo.todoTitle}</a>
									</div>
									<!-- todoOne상세정보 - 수정 - 삭제 -->
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<!-- 뒤에 비어있는 셀 -->
					<c:if test="${num <= 0 || num > diaryMap.endDay}">
						&nbsp;
					</c:if>	
				</td>
				<!-- 일주일이 지나면 다음줄로 간다. -->
				<c:if test="${i%7 == 0 }">
					</tr><tr>
				</c:if>
			</c:forEach>
			 
		</tr>
	</table>
</body>
</html>