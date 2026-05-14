package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@RequestMapping("/List")
	private ModelAndView list(BoardDto boardDto, int nowpage, String searchType, String keyword) {
		
		// 공통 데이터 로드 (메뉴 등)
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 전체 카운트 조회 ex) menu_id
		int totalCount = boardPagingMapper.count(boardDto,searchType,keyword);
	
		// 페이징을 위한 초기설정 (안해도 되긴함 DTO에 만들어놔서)
		SearchDto searchDto = new SearchDto();
		searchDto.setPageNo(nowpage); // 현재 페이지 정보
		searchDto.setNumOfRows(10); // 한페이지에 출력할 정보
		searchDto.setPageSize(10); // paging.jsp에 출력될 페이지 번호 수
		
		// pagination 설정
		Pagination pagination = new Pagination(totalCount, searchDto);
		searchDto.setPagination(pagination);
		
		String menu_id = boardDto.getMenu_id();
		int offset = searchDto.getOffset();
		int numOfRows = searchDto.getNumOfRows();
		String menu_name = menuMapper.getname(boardDto.getMenu_id());
		
		// 페이지조회
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(menu_id, searchType, keyword, offset, numOfRows);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id); // 현재정보메뉴
		mv.addObject("nowpage", nowpage);		
		mv.addObject("searchDto", searchDto); // pagination 포함
		mv.addObject("menu_name", menu_name);
		mv.addObject("boardList", list);
		mv.addObject("searchType", searchType);
		mv.addObject("keyword", keyword);
		return mv;
	}
	
	@RequestMapping("/View")
	private ModelAndView view(BoardDto boardDto, int nowpage) {
		boardMapper.incHit(boardDto);
		List<MenuDTO> menuList = menuMapper.getMenuList();
		BoardDto board = boardMapper.getBoard(boardDto);
		String menu_name = menuMapper.getname(boardDto.getMenu_id());
		
		// content 의 "\n" -> "<br>"
		if (board.getContent() != null) {
			board.setContent(board.getContent().replace("\n", "<br>"));			
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/view");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("board",board);
		mv.addObject("nowpage",nowpage);
		return mv;
	}
	
	@RequestMapping("/WriteForm")
	private ModelAndView writeform(BoardDto boardDto, int nowpage) {
		List<MenuDTO> menuList = menuMapper.getMenuList();
		String menu_name = menuMapper.getname(boardDto.getMenu_id());
		String menu_id = boardDto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/write");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage",nowpage);
		return mv;
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto boardDto) {
		String menu_id = boardDto.getMenu_id();
		boardMapper.insertboard(boardDto);
		
		ModelAndView mv = new ModelAndView();
		// redirect 쓸땐 addobject 안해도됨
		mv.setViewName("redirect:/BoardPaging/List?menu_id=" + menu_id + "&nowpage=1");
		return mv;
	}
	
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardDto boardDto, int nowpage) {
		String menu_id = boardDto.getMenu_id();
		boardMapper.deleteBoard(boardDto);

		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/BoardPaging/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
		return mv;
	}
	
	@RequestMapping("/UpdateForm")
	public ModelAndView updateform (BoardDto boardDto, int nowpage) {
		List<MenuDTO> menuList = menuMapper.getMenuList();
		BoardDto board = boardMapper.getBoard(boardDto);
		String menu_id = boardDto.getMenu_id();
		String menu_name = menuMapper.getname(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/update");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		mv.addObject("board",board);
		mv.addObject("nowpage",nowpage);
		return mv;
	}
	
	@RequestMapping("/Update")
	public ModelAndView update (BoardDto boardDto, int nowpage) {
		String menu_id = boardDto.getMenu_id();
		boardMapper.updateBoard(boardDto);

		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/BoardPaging/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
		return mv;
	}
}
