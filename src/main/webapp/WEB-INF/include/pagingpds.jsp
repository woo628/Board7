<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<style>
#paging > table {width: 60%; margin: 0 auto; 
	td{background-color: white; color: black; border-color: white;
	  a{text-decoration: none; display: block; 
	  &:hover{text-decoration: underline;}}}}
.active-page {font-weight: bold; color: black !important; text-decoration: underline;}
</style>

<c:set var="startnum" value="${searchDto.pagination.startPage}"/>
<c:set var="endnum" value="${searchDto.pagination.endPage}"/>
<c:set var="totalpagecount" value="${searchDto.pagination.totalPageCount}"/>
<div id="paging">
  <table>
    <tr>
     <c:if test="${startnum gt 1}">     
       <td>
         <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=1&searchType=${map.searchType}&keyword=${map.keyword}"> 처음 </a>
       </td>
       <td>
         <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${startnum-1}&searchType=${map.searchType}&keyword=${map.keyword}"> 이전 </a>
       </td>
     </c:if>
     
     <c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
        <td>
          <c:if test="${pagenum le totalpagecount}">
          <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${pagenum}&searchType=${map.searchType}&keyword=${map.keyword}"
          class="${pagenum == searchDto.pageNo ? 'active-page' : ''}">
          ${pagenum}
          </a>
          </c:if>
        </td>
     </c:forEach>
     
      <c:if test="${endnum lt totalpagecount}">     
       <td>
         <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${endnum+1}&searchType=${map.searchType}&keyword=${map.keyword}"> 다음 </a>
       </td>
       <td>
         <a href="/Pds/List?menu_id=${map.menu_id}&nowpage=${totalpagecount}&searchType=${map.searchType}&keyword=${map.keyword}"> 마지막 </a>       
       </td>
     </c:if>
    </tr>
  </table>
</div>