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
  #btnDelteFile {margin-left: 10px;}
  p {margin-bottom: 10px; margin-top: 10px; font-weight: bold;}
</style>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menuspdspaging.jsp" %>
   <form action="/Pds/Update" method="post">
	<h2>${menu_name} 수정</h2>
    <input type="hidden" name="menu_id" value="${menu_id}">
    <input type="hidden" name="idx" value="${map.idx}"> <!-- 또는 action에 /Update?idx=&{board.idx} -->
    <input type="hidden" name="nowpage" value="${map.nowpage}" />
    <table id="table1">
    	<tr>
    	  <td>글번호</td>
    	  <td>${pds.idx}</td>
    	  <td>조회수</td>
    	  <td>${pds.hit}</td>
    	</tr>
    	<tr>
    	  <td>작성자</td>
    	  <td>${pds.writer}</td>
    	  <td>작성일</td>
    	  <td>${pds.regdate}</td>
    	</tr>
    </table>
   	<p>제목</p>
   	<input type="text" name="title" value="${pds.title}">
   	<p>내용</p> 
   	<textarea name="content">${pds.content}</textarea><br>  
   	<p>파일</p>
   	<div style="border: 1px solid black; margin-bottom: 20px;">
   		<c:forEach var="file" items="${fileList}">
	 			<div style="padding: 10px;">
	 			<a href="/Pds/filedownload/${file.file_num}">${file.filename}</a>
	 			<input type="button" id="btnDelteFile" value="삭제">
	 			<input type="button" id="btnUpdateFile" value="수정">
	 			</div>
	 	</c:forEach>
   	</div>
   	<a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${map.nowpage}"><input type="button" value="돌아가기"></a>
   	<input type="submit" value="수정">
   </form>
 </main>
</body>
</html>