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
  #table1 {table-layout: fixed;}
  #table1 td:nth-child(odd) {background-color: black; color: white; border-bottom-color: white;}
  #table1 tr:nth-of-type(2) td {border-bottom-color: black;}
  input[type=submit] {margin-right: 10px;}
  input[type=submit],input[type=button] {float: right;}
  input[name=title] {margin-bottom: 10px; padding: 5px;}
  p {margin-bottom: 10px; margin-top: 10px; font-weight: bold;}
</style>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menuspaging.jsp" %>
   <form action="/BoardPaging/Update" method="post">
	<h2>${menu_name} 수정</h2>
    <input type="hidden" name="menu_id" value="${menu_id}">
    <input type="hidden" name="idx" value="${board.idx}"> <!-- 또는 action에 /Update?idx=&{board.idx} -->
    <input type="hidden" name="nowpage" value="${nowpage}" />
    <table id="table1">
    	<tr>
    	  <td>글번호</td>
    	  <td>${board.idx}</td>
    	  <td>조회수</td>
    	  <td>${board.hit}</td>
    	</tr>
    	<tr>
    	  <td>작성자</td>
    	  <td>${board.writer}</td>
    	  <td>작성일</td>
    	  <td>${board.regdate}</td>
    	</tr>
    </table>
   	<p>제목</p>
   	<input type="text" name="title" value="${board.title}">
   	<p>내용</p> 
   	<textarea name="content">${board.content}</textarea><br>  
   	<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${nowpage}"><input type="button" value="돌아가기"></a>
   	<input type="submit" value="수정">
   </form>
 </main>
</body>
</html>