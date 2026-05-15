package com.green.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	
	@ModelAttribute("menu_name")
	public String getMenuName(@RequestParam HashMap<String, Object> map) {
	    String menu_id = (String) map.get("menu_id");
	    // menu_id가 없을 경우를 대비한 방어 코드
	    if (menu_id == null || menu_id.isEmpty()) {
	        return "전체 목록"; 
	    }   
	    return menuMapper.getname(menu_id);
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
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/list");
		mv.addObject("map", map);	
		mv.addObject("searchDto", searchDto);
		mv.addObject("totalcount", totalcount);
		mv.addObject("pdsList", pdsList);
		return mv;
	}
	
	@RequestMapping("/View")
	public ModelAndView view(@RequestParam HashMap<String, Object> map) {
		
		// 넘겨줄 pdsdDto 정보를 idx로 조회
		
		// 넘겨줄 fileDto 정보를 idx로 조회
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/view");
		mv.addObject("map", map);
		return mv;
	}
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeFrom(@RequestParam HashMap<String, Object> map) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/write");
		mv.addObject("map", map);	
		return mv;	
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(@RequestParam HashMap<String, Object> map, @RequestParam(value="upfile") MultipartFile [] uploadfiles) {
		String menu_id = String.valueOf(map.get("menu_id"));
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		
		pdsService.setWrite(map,uploadfiles);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Pds/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
		mv.addObject("map", map);
		mv.addObject("uploadfiles", uploadfiles);		
		return mv;		
	}
	
	
	
	
	
	
	
	
	
}
