<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet"/>
<style>
  table { width: 100%; }
  td { padding: 5px 10px; text-align: center; width: 150px;}
  td:nth-of-type(1) {background: black; color: white; border-bottom-color: white;}
  td:nth-of-type(3) {background: black; color: white; border-bottom-color: white;}
  tr:nth-of-type(2) td:nth-of-type(3) {border-bottom-color: black;}
  tr:nth-of-type(3) td:nth-of-type(2) {text-align: left;}
  tr:nth-of-type(4) td {border-bottom-color: white; height: 400px;}
  tr:nth-of-type(4) td:nth-of-type(2) {border-bottom-color: black;}
  tr:nth-of-type(5) td {border-bottom-color: black;}
  tr:last-child td {background: white; border: 1px solid black;}
</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js" integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y" crossorigin="anonymous"></script>
</head>
<body>
 <main>
 	<%@include file = "/WEB-INF/include/menuspdspaging.jsp" %>
 	<h2>${menu_name}</h2>
 	<table>
 	 <tr>
 	 	<td>번호</td>
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
	 <tr>
	   	<td>제목</td>
	   	<td colspan="3">${pds.title}</td>
	 </tr>
	 <tr>
	   	<td>내용</td>
	   	<td colspan="3">${pds.content}</td>
	 </tr>
	 <tr>
	 	<td>파일</td>
	 	<td colspan="3" id="tdfile">
	 		<c:forEach var="file" items="${fileList}">
	 			<div>
	 				<a href="/Pds/filedownload/${file.file_num}">${file.filename}</a>
	 			</div>
	 		</c:forEach>
	 	</td>
	 </tr>
	 <tr>
	    <td colspan="4">
	     <a href="/Pds/WriteForm?menu_id=${map.menu_id}&nowpage=${map.nowpage}" class="btn btn-primary">새글쓰기</a>
	     <c:if test="${sessionScope.login.userid eq board.writer or sessionScope.login.userid eq 'admin'}"> 
	     <a href="/Pds/UpdateForm?idx=${map.idx}&menu_id=${map.menu_id}&nowpage=${map.nowpage}" class="btn btn-warning">수정</a>
	     <a href="/Pds/Delete?idx=${map.idx}&menu_id=${map.menu_id}&nowpage=${map.nowpage}" class="btn btn-danger">삭제</a>
	     </c:if>
	     <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${map.nowpage}" class="btn btn-info">목록</a>
	     <a href="/" class="btn btn-success">돌아가기</a>
	    </td>
	 </tr>
 	</table>
 </main>
</body>
</html>