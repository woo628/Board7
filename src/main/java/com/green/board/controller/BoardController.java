package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private MenuMapper  menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	// /Board/List
	@RequestMapping("/List")
	public ModelAndView list(MenuDTO menuDTO) {
		
		// menus.jsp
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// list.jsp
		List<BoardDto> boardList = boardMapper.getBoardList(menuDTO);
		
		String menu_id = menuDTO.getMenu_id();
		String menu_name = menuMapper.getname(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("boardList", boardList);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id); // 현재 메뉴정보
		return mv;
	}
	
	// /Board/WriteFrom
	@RequestMapping("/WriteForm")
	public ModelAndView writeform(BoardDto boardDto) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		String menu_id = boardDto.getMenu_id();
		String menu_name = menuMapper.getname(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menu_id", menu_id);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		return mv;
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto boardDto) {
		
		String menu_id = boardDto.getMenu_id();
		
		boardMapper.insertboard(boardDto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
// --------------------------------------------------------------
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardDto boardDto) {
		
		String menu_id = boardDto.getMenu_id();
		// db 의 자료를 삭제
		boardMapper.deleteBoard(boardDto);
		
		// 목록으로 이동
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
// --------------------------------------------------------------	
	@RequestMapping("/View")
	public ModelAndView view (BoardDto boardDto) {
		// 조회수증가
		boardMapper.incHit(boardDto);
		// 메뉴 목록
		List<MenuDTO> menuList = menuMapper.getMenuList();
		// idx로 조회한 글
		BoardDto board = boardMapper.getBoard(boardDto);
		
		String menu_id = boardDto.getMenu_id();
		String menu_name = menuMapper.getname(menu_id);
		
		// content 안에 있는 \n를 <br>로 변경
		if(board !=null && board.getContent() != null)
		board.setContent(board.getContent().replace("\n", "<br>"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("board",board);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		return mv;
	}
// --------------------------------------------------------------
	@RequestMapping("/UpdateForm")
	public ModelAndView updateform (BoardDto boardDto) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		BoardDto board = boardMapper.getBoard(boardDto);
		
		String menu_id = boardDto.getMenu_id();
		String menu_name = menuMapper.getname(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/update");
		mv.addObject("menu_id", menu_id);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("board",board);
		return mv;
	}
// --------------------------------------------------------------	
	@RequestMapping("/Update")
	public ModelAndView update (BoardDto boardDto) {
		String menu_id = boardDto.getMenu_id();
		
		boardMapper.updateBoard(boardDto);

		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
