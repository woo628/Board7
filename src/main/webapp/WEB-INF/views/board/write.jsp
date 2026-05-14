<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet"/>
<style>
  table {width: 100%; text-align: center;}
  textarea {width: 100%; height: 400px; padding: 10px;}
  form {width: 100%;}
  input[type=submit] {margin-right: 10px;}
  input[type=submit],input[type=button] {float: right;}
  input[name=title] {margin-bottom: 10px;}
  input[type=text] {padding: 5px;}
  p {margin-bottom: 10px; margin-top: 10px; font-weight: bold;}
</style>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menus.jsp" %>
   <form action="/Board/Write" method="post">
	<h2>${menu_name} 글쓰기</h2>
    <input type="hidden" name="menu_id" value="${menu_id}">
   	<p>제목</p>
   	<input type="text" name="title" placeholder="제목을 입력하세요">
   	<p>작성자</p>
   	<input type="text" name="writer">
   	<p>내용</p> 
   	<textarea name="content" placeholder="내용을 입력하세요"></textarea><br>  
   	<a href="/Board/List?menu_id=${menu_id}"><input type="button" value="돌아가기"></a>
   	<input type="submit" value="등록">
   </form>
 </main>
</body>
</html>