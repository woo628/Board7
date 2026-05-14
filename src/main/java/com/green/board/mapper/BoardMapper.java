package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;

@Mapper
public interface BoardMapper {

	List<BoardDto> getBoardList(MenuDTO menuDTO);

	void insertboard(BoardDto boardDto);

	void deleteBoard(BoardDto boardDto);

	BoardDto getBoard(BoardDto boardDto);

	void incHit(BoardDto boardDto);

	void updateBoard(BoardDto boardDto);

}
