package com.green.paging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SearchDto {
	private int pageNo; // 현재페이지 번호 nowpage 
	private int numOfRows; // 페이지당 출력할 데이터 갯수 
	private int pageSize; // 화면하단에 출력할 페이지 번호의 갯수
	private String keyword; // 검색 키워드
	private String searchType; // 검색 유형 title, content, writer
	
	// 페이징된 검색결과 data
	private Pagination pagination; // 페이지네이션 정보
	
	// 초기값 생성자
	public SearchDto() {
		this.pageNo = 1;
		this.numOfRows = 10;
		this.pageSize = 10;
	}
	
	// method 
	public int getOffset() {
		return (this.pageNo - 1) * this.numOfRows;
	}
}
