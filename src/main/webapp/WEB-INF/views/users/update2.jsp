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
table { width: 100%; margin-top: 10%;}
td { padding: 5px 10px; text-align: center; &:nth-of-type(1) {background: black; color: white; }}
tr:last-child> td {background: white; border: 1px solid black;}
input[type=text] {width: 100%;}
input[type=button],input[type=submit] {width: 100px;}
</style>
</head>
<body>
	<main>
		<form action="/Users/Update2" method="get">
		 <table>
		 	<tr>
		 		<td>아이디</td>
		 		<td><input type="text" name="userid" value="${user.userid}" readonly></td>
		 	</tr>
		 	<tr>
		 		<td>비밀번호</td>
		 		<td><input type="text" name="passwd" value="${user.passwd}" ></td>
		 	</tr>
		 	<tr>
		 		<td>이름</td>
		 		<td><input type="text" name="username" value="${user.username}"></td>
		 	</tr>
		 	<tr>
		 		<td>이메일</td>
		 		<td><input type="text" name="email" value="${user.email}"></td>
		 	</tr>
		 	<tr>
		 		<td colspan="2">
		 		 <input type="submit" value="수정">
		 		 <input type="button" value="돌아가기" onclick="location.href='/Users/Index'">
		 		</td> 
		 	</tr>
		 </table>
		</form>
	</main>
</body>
</html>