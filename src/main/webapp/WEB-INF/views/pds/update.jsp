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
  #f{margin-bottom: 10px; padding: 10px;}
  p {margin-bottom: 10px; margin-top: 10px; font-weight: bold;}
</style>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menuspdspaging.jsp" %>
   <form action="/Pds/Update" method="post" enctype="multipart/form-data">
	<h2>${menu_name} 수정</h2>
    <input type="hidden" name="menu_id" value="${map.menu_id}">
    <input type="hidden" name="idx" value="${map.idx}"> 
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
	 			<a href="/Pds/deletefile?file_num=${file.file_num}">X</a>
	 			<input type="button" class="btnDeleteFile" value="삭제">
	 			</div>
	 	</c:forEach>
   	 <div id="f">
   	 <input type="button" id="btnAddFile" value="파일추가(최대 100MByte)">
   	 <div id="tdfile"><input type="file" name="upfile" class="upfile" multiple><br></div> 
   	 </div>
   	</div>
   	<a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${map.nowpage}"><input type="button" value="돌아가기"></a>
   	<input type="submit" value="수정">
   </form>
 </main>
<script>
	const btnDeleteEl = document.querySelectorAll(".btnDeleteFile");
	btnDeleteEl.forEach(btn => {
		btn.addEventListener('click',function(){
			this.parentElement.remove();
		})
	});
	
	const btnAddFileEl = document.querySelector("#btnAddFile")
  	const tdfileEl = document.querySelector("#tdfile")
  	btnAddFileEl.addEventListener('click', function() {
  		const tag = '<input type="file" name="upfile" class="upfile" multiple><br>';
        tdfileEl.insertAdjacentHTML('beforeend', tag);
  	});
</script>
</body>
</html>












