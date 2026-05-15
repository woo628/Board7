<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<table class="menu">
	<tr>
	 <c:forEach var="menu" items="${menuList}">
		<td>
		 <a href="/Pds/List?menu_id=${menu.menu_id}&nowpage=${map.nowpage}" 
		 class="${menu.menu_id == map.menu_id ? 'active' : ''}">${menu.menu_name}</a>
		</td>	 
	 </c:forEach>
	</tr>
</table>