package com.green.paging.dto;

import lombok.Getter;
import lombok.ToString;

// paging.jsp 페이지 번호를 출력할 파일 

@Getter
@ToString
public class Pagination {
	private int totalCount; // 해당메뉴의 조회된 자료수
	private int totalPageCount; // 전체 페이지수 totalCount / numOfRows
	
	private int startPage; // 첫 페이지
	private int endPage; // 끝 페이지
	
	private int limitStart; // limit 시작 위ㅣ

	private boolean existPrevPage; // 이전 페이지 존재 여부
	private boolean existNextPage; // 다음 페이지 존재 여부
	
	public Pagination(int totalCount, SearchDto searchDto) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			calculation(searchDto);
		}
	}

	private void calculation(SearchDto searchDto) {
		// 전체 페이지수 계산
		int numOfRows =searchDto.getNumOfRows();
		this.totalPageCount = (int) Math.ceil((double)this.totalCount / (double)numOfRows);
		
		// 현재 페이지수 계산
		int pageNo = searchDto.getPageNo();
		if (pageNo > this.totalPageCount) {
			pageNo = this.totalPageCount;
			searchDto.setPageNo(pageNo);
		}
		
		// 첫 페이지 계산
		int pageSize = searchDto.getPageSize(); //한줄에 출력할 페이지 번호 수
		startPage = ((pageNo - 1) / pageSize) * pageSize + 1;
		
		// 끝 페이지 계산
		endPage = startPage + pageSize - 1;
		
		// limit 시작 위치계산
		// limitStart = (pageNo - 1) * numOfRows;
		limitStart = searchDto.getOffset();
		
		// 이전 페이지
		existPrevPage = startPage > 1;
		
		// 다음 페이지
		existNextPage = endPage < totalPageCount; 
	}
	
}















