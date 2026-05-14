package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;

@Mapper
public interface BoardPagingMapper {

	List<BoardDto> getBoardPagingList(String menu_id, String searchType, String keyword, int offset, int numOfRows);

	int count(BoardDto boardDto, String searchType, String keyword);

	
}
