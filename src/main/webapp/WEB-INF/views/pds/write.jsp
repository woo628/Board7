<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
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
  #f{margin-bottom: 10px; border: 1px solid black; padding: 10px;}
</style>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menuspdspaging.jsp" %>
   <form action="/Pds/Write" method="post" enctype="multipart/form-data">
	<h2>${menu_name} 글쓰기</h2>
    <input type="hidden" name="menu_id" value="${map.menu_id}">
    <input type="hidden" name="nowpage" value="${map.nowpage}" />
   	<p>제목</p>
   	<input type="text" name="title" placeholder="제목을 입력하세요">
   	<p>작성자</p>
   	<input type="text" name="writer" value="${sessionScope.login.userid}">
   	<p>내용</p> 
   	<textarea name="content" placeholder="내용을 입력하세요"></textarea><br>
   	<p>파일</p>
   	<div id="f">
   	 <input type="button" id="btnAddFile" value="파일추가(최대 100MByte)">
   	 <div id="tdfile"><input type="file" name="upfile" class="upfile" multiple><br></div> 
   	</div> 
   	<input type="button" id="goList" value="돌아가기">
   	<input type="submit" value="등록">
   </form>
 </main>
<script>
  // 목록이동
  	const goListEl = document.querySelector("#goList");
  	goListEl.onclick= function () {
		location.href="/Pds/List?menu_id=${map.menu_id}&nowpage=${map.nowpage}"
	}
  // 파일 입력창 추가
  	const btnAddFileEl = document.querySelector("#btnAddFile")
  	const tdfileEl = document.querySelector("#tdfile")
  	btnAddFileEl.addEventListener('click', function() {
  		const tag = '<input type="file" name="upfile" class="upfile" multiple><br>';
        tdfileEl.insertAdjacentHTML('beforeend', tag);
  	});
  // 입력항목 체크
</script>
</body>
</html>