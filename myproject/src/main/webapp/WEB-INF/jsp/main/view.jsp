<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- root 경로 -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main_view.css">
<link
	href="https://fonts.googleapis.com/css2?family=Big+Shoulders+Stencil+Text&display=swap"
	rel="stylesheet">
<title><s:message code="common.pageTitle"/></title>
</head>
<body>
	<div class="header">
	<h1><a href="index">Pet Daily</a></h1>
	</div>
		<div class="pet_img">
			<img src="${root}/${ph.path}"/>
		</div>
		<div class="Information">
		
		 <h1>${ph.pname}</h1>
		
		 <p>${ph.content}</p>
		</div>
		<hr>
	<div class="board">
		<table>
			<tr>
				<th>작성자</th>
				<th>댓글</th>
				<th>작성일</th>
			</tr>
		<c:forEach var="cl" items="${commetlist}" varStatus="st">
			<tr>
			   	<td>${cl.userID}</td>
				<td>${cl.comment}</td>
				<td><fmt:formatDate pattern="yyyy.MM.dd" value="${cl.regdate}"></fmt:formatDate></td>
<%-- 					<c:if test="${sessionScope.userid.equals(cl.writer)}"> --%>
<%-- 						<input type="hidden" name="pid" value="${cl.pid}"> --%>
<%-- 							<td><button type="submit"  name="wid" value="${cl.wid}">삭제</button></td> --%>
							
<%-- 					</c:if>	 --%>
			</tr>
		</c:forEach>
		</table>
	
	<br>
	<br>
	<!-- 	댓글 입력창 -->
	<form action="imgComment">
		<textarea rows="10" cols="10" name="comment" placeholder="댓글을 입력하세요."></textarea>
		<input type="hidden" name="pid" value="${param.pid}">
		
			<input type="hidden" name="userID"
				value="${sessionScope.userid}">
				<div class="commet_btn">
				<c:if test="${not empty sessionScope.userid}">
						<input type="submit" value="등록" /> 
				</c:if> 
				<c:if test="${empty sessionScope.userid}">
						<input type="button" onclick="login()" value="등록" /> 
				</c:if>
				</div>
	</form>
	</div>

	<script>
		function login() {
			alert('로그인 후 가능한 기능입니다.');
			location.href='/myproject/login'
			}
	</script>
	
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jqu2.js"></script>
</body>
</html>