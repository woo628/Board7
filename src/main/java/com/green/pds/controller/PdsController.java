package com.green.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.pds.dto.PdsDto;
import com.green.pds.mapper.PdsMapper;
import com.green.pds.service.PdsService;

@Controller
@RequestMapping("/Pds")
public class PdsController {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@Autowired
	private PdsService pdsService;
	
	@ModelAttribute("menuList")
	public List<MenuDTO> getMenuList() {
	    return menuMapper.getMenuList();
	}
	
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		
		// 목록 조회 
		int totalcount = pdsMapper.count(map); // 해당 메뉴의 전체 자료수
		
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		SearchDto searchDto = new SearchDto();
		searchDto.setPageNo(nowpage);
		searchDto.setPageSize(10);
		searchDto.setNumOfRows(10);
		
		Pagination pagination = new Pagination(totalcount, searchDto);
		searchDto.setPagination(pagination);
		
		int offset = searchDto.getOffset();
		int numOfRows = searchDto.getNumOfRows();
		map.put("offset", offset);
		map.put("numOfRows", numOfRows);
		
		List<PdsDto> pdsList = pdsService.getPdsList(map);
		
		String menu_id = (String) map.get("menu_id");
		String menu_name = menuMapper.getname(menu_id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/list");
		mv.addObject("map", map);
		mv.addObject("menu_name", menu_name);
		mv.addObject("searchDto", searchDto);
		mv.addObject("totalcount", totalcount);
		return mv;
	}
}
