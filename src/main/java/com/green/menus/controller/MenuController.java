package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller
public class MenuController {
	
	@Autowired
//	container 에 미리 new 된 component 를 찾아서 meunMapper 변수에 저장 - 객체 타입으로 찾아서 연결
//  @Qulified() - 객체 이름으로 찾아서 연결
//  최근에는 생성자를 이용한 방식 사용
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list(Model model) {
		// 조회한결과를 ArrayList 로 돌려준디
		List<MenuDTO> menuList = menuMapper.getMenuList();
//		System.out.println(menuList);
		
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
	@RequestMapping("/Menus/WriteForm")
	public String writeform() {
		return "menus/write";
	}
	
//  메뉴이름만으로만 추가하기
	@RequestMapping("/Menus/WriteForm2")
	public String writeform2() {
		return "menus/write2";
	}
	
	@RequestMapping("/Menus/Write")
//	public String write(String menu_id, String menu_name, int menu_seq) {
	public String write(MenuDTO menuDTO, Model model) {
		
		// 넘어온 값
//		System.out.println(menuDTO.getMenu_id());
//		System.out.println(menuDTO.getMenu_name());
//		System.out.println(menuDTO.getMenu_seq());
				
		// db 저장
		menuMapper.insertMenu(menuDTO);
		
		// 다시 조회 -> menuList
		List<MenuDTO> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList",menuList);
		
		return "menus/list";
	}
	
	@RequestMapping("/Menus/Write2")
	public String write2(MenuDTO menuDTO) {
		menuMapper.insertMenu2(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	@RequestMapping("/Menus/Delete")
//  public String delete(MenuDTO menuDTO)
//	public String delete(String menu_id) {
	public String delete(@RequestParam("menu_id") String menu_id) {
		
		// MenuDTO menuDTO = new MenuDTO(menu_id,null,0);
		// menuMapper.deleteMenu(menuDTO);
	
		menuMapper.deleteMenu(menu_id);
		
		return "redirect:/Menus/List";
	}
	
	// 수정할 자료 조회
	@RequestMapping("/Menus/UpdateForm")
	public String updateform(MenuDTO menuDTO, Model model) {
		
		// 수정할 자료를 db에서 검색 (수정할 정보가 담긴 menu)
  		MenuDTO menu = menuMapper.getMenu(menuDTO);
  		model.addAttribute("menu",menu);
		
		return "menus/update";
	}
	
	// 넘어온자료로 db수정
	@RequestMapping("/Menus/Update")
	public String update(MenuDTO menuDTO) {
		menuMapper.updateMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
}













