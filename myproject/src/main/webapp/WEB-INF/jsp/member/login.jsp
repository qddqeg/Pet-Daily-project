<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<!DOCTYPE html>
<html lang="ko">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content=width=divice-width initial-scale="1">
   <link rel="stylesheet" href="css/login_style.css">
   <link href="https://fonts.googleapis.com/css2?family=Big+Shoulders+Stencil+Text&display=swap" rel="stylesheet">
   <script
      src="https://kit.fontawesome.com/9eb162ac0d.js"
      crossorigin="anonymous"
    ></script>
   <title><s:message code="common.pageTitle"/></title>
</head>

<body>

<!--               메뉴바 -->
<nav>
  <div class="menu-btn">
    <div class="line line--1"></div>
    <div class="line line--2"></div>
    <div class="line line--3"></div>
  </div>

  <div class="nav-links">
    <a href="index" class="link">Home</a>
    <a href="signUp" class="link">SignUp</a>
    <a href="${N_apiURL}" class="link"><img width="177" height="44" src="list_img/2014_Login_with_NAVER_button_png/네이버 아이디로 로그인_축약형_Green.PNG"></a>
    <a href="${K_apiURL}" class="link"><img width="177" src="list_img/Kakao_Login.png"></a>
  </div>
</nav>

<div class="inform">
  Sliding Menu
</div>

<div class="support">
  <a href="https://twitter.com/DevLoop01" target="_blank"><i class="fab fa-twitter-square"></i></a>
  <a href="https://www.facebook.com/"><i class="fab fa-facebook"></i></a>
  <a href="https://www.instagram.com/"><i class="fab fa-instagram"></i></a>
</div>              

             

              
              
              
            
        
  					<div class="logo">
             			 <h1><a href="index"><i class="fas fa-paw"></i>Pet Daily</a></h1>
              		</div>
              		
                  <div class="login_screen">
                  <form method="post" action="memberLoginChk">
                   	
                     <h3>로그인 화면</h3>
                    
                     
                     <div>
                        <input type="text" class="form-control" placeholder="ID" name="userid" maxlength="20">
                     </div>
                     <div>
                        <input type="password" class="form-control" placeholder="PASSWORD" name="userpw"
                           maxlength="20">
                     </div>
                     <div class="login_btn">
                     <input type="submit"value="Login">
                     </div>
                  </form>
                  </div>
                  
                  
         
         
         <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
         </script>
        <script  src="js/jqu2.js"></script>
</body>

</html>