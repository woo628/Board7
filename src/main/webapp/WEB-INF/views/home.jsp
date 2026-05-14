<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet"/>
<style>
  main {width: 100%; text-align: center;}
  div {font-size: 20px; margin: 10px;}
</style>
</head>
<body>
  <main>
    <h1>Home</h1>
    <a href="/test">Test</a>
    <div><a href="/Menus/WriteForm">새 메뉴추가</a></div>
    <div><a href="/Menus/WriteForm2">새 메뉴추가2</a></div>
    <div><a href="/Menus/List">메뉴목록</a></div>  
    <div>&nbsp;</div>
    <div><a href="/Users/Index">회원정보</a></div>    
    <div>&nbsp;</div>
    <div><a href="/Board/List?menu_id=MENU01">게시글 목록</a></div>
    <div><a href="/Board/WriteForm?menu_id=MENU01">게시글 추가</a></div>
    <div>&nbsp;</div>
    <div><a href="/BoardPaging/List?menu_id=MENU01&nowpage=1">게시글 목록(페이징)</a></div>
    <div><a href="/BoardPaging/WriteForm?menu_id=MENU01&nowpage=1">게시글 추가(페이징)</a></div>
    <div>&nbsp;</div>
    <div>
      <span style="font-weight: bold;">${sessionScope.login.username}</span> 님 환영합니다<br>
      당신의 가입일은 ${sessionScope.login.regdate} 입니다<br><br>
      <c:choose>
        <c:when test="${sessionScope.login ne null}">
	      <a href="/Users/Logout">로그아웃</a>    
        </c:when>
        <c:otherwise>
	      <a href="/Users/LoginForm">로그인</a><br>        
        </c:otherwise>
      </c:choose>
    </div>
    
    <div>
    <input type="text" id="num" value="1">
    <a id="btnnate" href="https://www.nate.com" class="btn btn-primary">click</a>
    </div>
  </main>
<script>
  const btnEl = document.querySelector("#btnnate")
  const numEl = document.querySelector("#num")
  btnEl.onclick = function (e) {
		e.preventDefault(); // 기본이벤트 취소
		e.stopPropagation(); // 전파안되게
	 	if (numEl.value == '2') {			
			location.href = this.href // this.href == e.target.href
		 }
}
</script>
</body>
</html>