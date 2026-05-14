<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet"/>
<style>
 table {width: 100%; text-align: center;}
 tr:first-child {background-color: black; color: white;}
 main {margin-bottom: 150px;}
 .title {text-align: left; padding-left: 10px;}
 #search {width: 30%; margin: 10px auto; display: flex; gap: 10px;  justify-content: center;}
</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js" integrity="sha384-G/EV+4j2dNv+tEPo3++6LCgdCROaejBqfUeNjuKAiuXbjrxilcCdDz6ZAVfHWe1Y" crossorigin="anonymous"></script>
</head>
<body>
 <main>
 	<%@ include file = "/WEB-INF/include/menuspaging.jsp" %>
 	<h2>${menu_name} 목록</h2>
 	<table class="table table-striped">
 	 <colgroup>
        <col style="width: 8%;" />  <!-- 번호 -->
        <col style="width: 47%;" /> <!-- 제목 -->
        <col style="width: 15%;" /> <!-- 작성자 -->
        <col style="width: 20%;" /> <!-- 날짜 -->
        <col style="width: 10%;" /> <!-- 조회수 -->
   	 </colgroup>
 	 <tr>
 	 	<td>번호</td>
 	 	<td>제목</td> 	
 	 	<td>작성자</td> 	
 	 	<td>날짜</td>
 	 	<td>조회수</td>	
 	 </tr>	 
	   <tr>
	   	<td colspan="5" style="text-align: right; padding-right: 20px;">
	   	[<a href="/BoardPaging/WriteForm?menu_id=${menu_id}&nowpage=${nowpage}">새 글 추가</a>]&nbsp;&nbsp;&nbsp;
	   	[<a href="/">돌아가기</a>]
	   	</td>
	   </tr>
 	 <c:forEach var="board" items="${boardList}">
	   <tr>
	   	<td>${board.idx}</td>
	   	<td class="title"><a href="/BoardPaging/View?idx=${board.idx}&menu_id=${menu_id}&nowpage=${nowpage}">${board.title}</a></td>
	   	<td>${board.writer}</td>
	   	<td>${board.regdate}</td>
	   	<td>${board.hit}</td>
	   </tr>
	   </c:forEach>
 	</table>
 	<form action="/BoardPaging/List" >
	  <input type="hidden" name="menu_id" value="${ menu_id }" />	  
	  <input type="hidden" name="nowpage" value="${ nowpage }" />	  
	  <div id="search">
	    <select name="searchType">
	      <option value="title">제목</option> 
	      <option value="content">내용</option> 
	      <option value="writer">작성자</option> 
	    </select>
	    <input type="text" name="keyword" />
	    <input type="submit" value="검색" />	    
	  </div>
	  </form>
 	<%@ include file="/WEB-INF/include/paging.jsp" %>
 </main>
</body>
</html>