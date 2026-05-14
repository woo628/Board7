<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/img/favicon1.png" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet"/>
<style>
 table {width: 100%; text-align: center; margin-top: 10%;}
 tr:first-child {background-color: black; color: white;}
 tr:nth-of-type(3) {text-align: right;}
</style>
</head>
<body>
<main>
 	<table>
 	 <tr>
 	 	<td>아이디</td>
 	 	<td>이름</td>
 	 	<td>이메일</td>
 	 	<td>번호</td> 	 	
 	 	<td>가입일</td> 	 			 		
 	 </tr>	 
	 <tr>
	 	<td>${user.userid}</td>
	 	<td>${user.username}</td>
	 	<td>${user.email}</td>
	 	<td>${user.upoint}</td>
	 	<td>${user.regdate}</td>	
	 </tr>
	 <tr>
	   	<td colspan="5">[<a href="/Users/Index">돌아가기</a>]</td>
	 </tr>
 	</table>
 </main>
</body>
</html>