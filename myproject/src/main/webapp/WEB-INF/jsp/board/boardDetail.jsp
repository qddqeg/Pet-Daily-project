<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/list/detail.css">
<link
	href="https://fonts.googleapis.com/css2?family=Big+Shoulders+Stencil+Text&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<title><s:message code="common.pageTitle"/></title>
<script src="https://kit.fontawesome.com/9eb162ac0d.js"
	crossorigin="anonymous"></script>

<c:set var="root" value="${pageContext.request.contextPath}"/>
</head>
<body>
<nav class="navbar">
		<div class="navbar__logo">
			<i class="fas fa-paw"></i> <a href="index">PDL</a>
		</div>
	<ul class="navbar__icons">
			<li><i class="fab fa-facebook"></i></li>
			<li><i class="fab fa-instagram"></i></li>
			<c:if test="${empty sessionScope.userid}">
				<li class="active"><a href="login">Login</a></li>
				<li><a href="signUp">Sign up</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.userid}">
				<li class="active"><a href="Logout">Logout</a></li>
				<li><a href="signUp">Sign up</a></li>
			</c:if>
		</ul>
		<a href="#" class="navbar__toogleBtn"> <i class="fas fa-bars"></i>
		</a>
	</nav>

<div class="inner">
		<nav class="left_nav">
			<div class="list_icon">
				<a href="boardList"><i class="fas fa-book-open"></i>
				<p>BOARD</p></a>
			</div>
		</nav>
	<div class="board">
	<table border=1>
		<tr>
			<td>제목</td>
			<td colspan="3">${bv.title}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td colspan="3"><fmt:formatDate pattern="yyyy.MM.dd. hh:mm"
					value="${bv.regDate}" /></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${bv.writer_id}</td>

			<td>조회수</td>
			<td><fmt:formatNumber type="number" pattern="###,###"
					value="${bv.hit}"></fmt:formatNumber></td>
		</tr>
		<tr>
		<c:if test="${bv.img != null}">
			<td>이미지<img src="${root}/upload/${bv.img}"></td>
			</c:if>
			
		</tr>
		<tr>
			<td colspan="4" >${bv.content}</td>
		</tr>

	</table>
	<!-- 목록 버튼 -->
	
	<!-- 	댓글 리스트  -->
	<form id="CommentDelete" action="Commnetremove">
	<c:set var="cnt" value="${count}" />
	<c:if test="%{cnt==0}">
		<h3>댓글이 업수다</h3>
	</c:if>
	<c:if test="${cnt!=0}">
		<table border=1>
			<c:forEach var="cl" items="${cl}" varStatus="st">
				<tr>
					<th>${cl.writer}</th>
					<td>${cl.comment}</td>
					<td><fmt:formatDate pattern="yyyy.MM.dd" value="${cl.regdate}"></fmt:formatDate></td>
					<c:if test="${sessionScope.userid.equals(cl.writer)}">
						<input type="hidden" name="mid" value="${cl.mid}">
							<td><button type="submit"  name="id" value="${cl.id}">삭제</button></td>
							
					</c:if>	
						
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</form>
	
	<form action="boardComment" >
<!-- 	댓글 입력창 -->
	<textarea rows="10" cols="10" name="comment" placeholder="댓글을 입력하세요."></textarea>
		<input type="hidden" name="mid" value="${param.boardid}">
		
			<input type="hidden" name="writer"
				value="${sessionScope.userid}">

				<c:if test="${not empty sessionScope.userid}">
						<input type="submit" value="등록" /> 
				</c:if> 
				<c:if test="${empty sessionScope.userid}">
						<input type="button" onclick="login()" value="등록" /> 
				</c:if>
			</form>
			
<!-- 			글삭제 버튼 -->
			<c:if test="${sessionScope.userid.equals(bv.writer_id)}">
					<input type="button" value="글삭제" onclick="Delet_btn()">
				</c:if>
			<form  id="DetailDelete" action="remove">
				<input type ="hidden" name="boardid" value="${param.boardid}"/>
			</form> 
		
		
		<div class="right_menu">
		 <div class="button">
		 	<a href="list?p=${param.p}&f=${param.f}&q=${param.q}">목록</a>
			
		</div>
		<br>
		<br>
		<br>
		<br>
		<div class="button">
		<a href="main"><i class="fas fa-cat"></i><i class="fas fa-dog"></i></a>
		</div>
		</div>
	
	</div>
</div>
	<script type="text/javascript">
	function Delet_btn(){
		let yn = confirm("정말로 삭제하시겠습니까?")
		if(yn==true){
			alert('휴지통에 가서 찾아도 없어요')
			document.getElementById("DetailDelete").submit();
		}else if(yn==false){
			alert('삭제 안할꺼면서 누르지마쇼');
			return;
		}
	}
		
</script>

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