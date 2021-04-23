
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="stylesheet" href="css/list/list.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link
	href="https://fonts.googleapis.com/css2?family=Big+Shoulders+Stencil+Text&display=swap"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<meta charset="UTF-8">
<title><s:message code="common.pageTitle"/></title>

<script src="https://kit.fontawesome.com/9eb162ac0d.js"
	crossorigin="anonymous"></script>
<script>
    $(document).ready(function(){
      $('.slider').bxSlider({
    	  auto:true,
    	  speed:2000,
    	  pause:4000,
      });
      
    });
  </script>
<style>
.bx-wrapper {
	box-shadow: none;
	border: none;
	background-color: black;
	margin-bottom: 20px;
}

.bx-pager-item {
	position: relative;
	bottom: 50px;
}

.bx-wrapper img {
	height: 500px;
}
.writing_btn a:hover{
background-image:url(${root}/list_img/btn1.jpg);
color: rgb(202, 201, 192);
}
element .style {
	height: 800px !important;
}
.bx-wrapper img {
    height: 800px !important;
}

</style>
</head>
<body style="background-image:url(${root}/list_img/bgimg3.jpeg)">
	<nav class="navbar">
		<div class="navbar__logo">
			<i class="fas fa-paw"></i> <a href="index">PDL</a>
		</div>
		<form action="boardList">
			<select name="searchType" class="seach_opt">
				<option ${(param.searchType == "title")?"selected":""} value="title">제목</option>
				<option ${(param.searchType == "writer_id")?"selected":""} value="writer_id">글쓴이</option>
			</select>
			<div class="search">

				<input class="sea_input" type="text" name="searchKeyword" value="${bo.searchKeyword}"
					placeholder="검색어 입력" /> <input class="but" type="submit"
					value="Search" />

			</div>
		</form>
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
	<div class="slider">
		<div>
			<img src="${root}/list_img/slider1.jpg">
		</div>
		<div>
			<img src="${root}/list_img/slider2.jpg">
		</div>
		<div>
			<img src="${root}/list_img/slider3.jpg">
		</div>
		<div>
			<img src="${root}/list_img/slider4.jpg">
		</div>
	</div>

	<div class="inner"  style="background-image:url(${root}/list_img/bgimg.jpg)">
		<nav class="left_nav"  style="background-image:url(${root}/list_img/bgimg.jpg)">
			<div class="list_icon">
				<a href="boardList"><i class="fas fa-book-open"></i>
					<p>BOARD</p></a>
			</div>
		</nav>

		<div class="board">
			<table>

				<tr class="tr1">
					<td>번호</td>
					<td>제목</td>
					<td>작성내용</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<%-- 
		<%List<Notice> list =(List<Notice>)request.getAttribute("list");
      for(Notice nt : list){
    	  pageContext.setAttribute("n", nt);

   %>
   --%>
				<c:forEach var="n" items="${lv}" varStatus="t">
					<tr>
						<td><a
							href="boardDetail?boardid=${n.id}">${n.id}</a></td>
						<td><a
							href="boardDetail?boardid=${n.id}">${n.title}</a></td>
						<td><a
							href="boardDetail?boardid=${n.id}">${n.content}</a></td>
						<td><a
							href="boardDetail?boardid=${n.id}">${n.writer_id}</a></td>
						<td><a
							href="boardDetail?boardid=${n.id}">
								<fmt:formatDate pattern="yyyy.MM.dd." value="${n.regDate}"></fmt:formatDate>
						</a></td>
						<td><a
							href="boardDetail?boardid=${n.id}">
								<fmt:formatNumber type="number" pattern="###,###"
									value="${n.hit}"></fmt:formatNumber>
						</a></td>

					</tr>
				</c:forEach>
				<%--<%}%>--%>
			</table>
		

			<!-- 페이지 이동 -->

			<div class="pageing">
				<!-- Prev 버튼-->
				<c:if test="${bo.page > 1}">
					<a href="?page=${bo.page-1}"><i class="fas fa-chevron-left"></i></a>
				</c:if>
				<c:if test="${bo.page<= 1}">
					<a href="#" onclick="alert('첫번째 페이지입니다.');"><i
						class="fas fa-chevron-left"></i></a>
				</c:if>


				<div class="page_num">
					<c:forEach var="i" begin="0" end="4">
						<c:if test="${bo.page == (bo.pageStart+i)}">
							<c:set var="style"
								value="font-weight: bold; color :rgb(85, 81, 81); text-shadow: 1px 1px 1px black;"></c:set>
						</c:if>
						<c:if test="${bo.page != (bo.pageStart+i)}">
							<c:set var="style"
								value="color :ivory;  text-shadow: 1px 1px 1px black;" />
						</c:if>
						<c:if test="${(bo.pageStart)<=bo.pageEnd}"></c:if>
						<a style="${style}"
							href="?page=${bo.pageStart+i}">${bo.pageStart+i}</a>
					</c:forEach>
				</div>

				<!-- next 버튼-->
				<c:if test="${bo.page < bo.pageEnd}">
					<a href="?page=${bo.page+1}"><i
						class="fas fa-chevron-right"></i></a>
				</c:if>
				<c:if test="${bo.page >= bo.pageEnd}">
					<a href="#" onclick="alert('마지막 페이지입니다.');"><i
						class="fas fa-chevron-right"></i></a>
				</c:if>
			</div>
<!-- 			글쓰기 버튼 -->
			<div class="writing_btn">
				<c:if test="${not empty sessionScope.userid}">
					<a href="writing">글쓰기</a>
				</c:if>
				<c:if test="${empty sessionScope.userid}">

					<a href="#" onclick="login()">글쓰기</a>
				</c:if>

			</div>
		</div>
	</div>
	<hr>



	<script>
	
	function login() {
		alert('로그인 후 가능한 기능입니다.');
		location.href='/myproject/login'
	}
	
	</script>
	<script src="js/List_jqu.js"></script>
</body>
</html>