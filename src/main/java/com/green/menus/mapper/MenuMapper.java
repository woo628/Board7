package com.green.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.menus.dto.MenuDTO;

@Mapper
public interface MenuMapper {

	List<MenuDTO> getMenuList();

	void insertMenu(MenuDTO menuDTO);

	void deleteMenu(String menu_id);

	void updateMenu(MenuDTO menuDTO);

	void insertMenu2(MenuDTO menuDTO);

	MenuDTO getMenu(MenuDTO menuDTO);

	String getname(String menu_id);

}




