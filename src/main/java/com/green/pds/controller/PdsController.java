package com.green.pds.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.pds.dto.FilesDto;
import com.green.pds.dto.PdsDto;
import com.green.pds.mapper.PdsMapper;
import com.green.pds.service.PdsService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Pds")
public class PdsController {
	
	@Value("${part1.upload-path}")
	private String uploadPath;
	
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
		// 조회수 증가
		pdsService.setReadCountUpdate(map); // map: idx
		// 넘겨줄 pdsdDto 정보를 idx로 조회
		PdsDto pds = pdsService.getPds(map);
		// 넘겨줄 fileDto 정보를 idx로 조회
		List<FilesDto> fileList = pdsService.getFile(map);
		if (pds.getContent() != null) {
			String content = pds.getContent().replace("\n","<br>");
			pds.setContent(content);
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/view");
		mv.addObject("map", map);
		mv.addObject("pds", pds);
		mv.addObject("fileList", fileList);
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
	
	@GetMapping("/filedownload/{file_num}") // 데이터를 조회
	@ResponseBody // data를 내려준다 / HttpServletResponse return 안해도됨
	public void downloadFile(HttpServletResponse res, @PathVariable(value = "file_num") Long file_num) throws UnsupportedEncodingException {
		FilesDto fileInfo = pdsService.getFileInfo(file_num);
		// 다운로드 할 파일의 경로 생성
		Path saveFilePath = Paths.get(uploadPath + File.separator + fileInfo.getSfilename());
		// http 헤더 설정 (클라이언트 브라우저에 주는 정보)
		setFileHeader(res, fileInfo);
		// 파일을 복사 (실제 다운로드)
		fileCopy(res, saveFilePath);
	}

	public void fileCopy(HttpServletResponse res, Path saveFilePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(saveFilePath.toFile());
			FileCopyUtils.copy(fis,res.getOutputStream());
			res.getOutputStream().flush(); // 남아있는 버퍼 초기화
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	public void setFileHeader(HttpServletResponse res, FilesDto fileInfo) throws UnsupportedEncodingException {
		res.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String)fileInfo.getFilename(),"UTF-8") + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Content-Type", "application/octet-stream; utf-8");
		res.setHeader("Pragma", "no-cache;");
		res.setHeader("Expires", "-1");
	}
	
	@RequestMapping("/Delete")
	public ModelAndView delete(@RequestParam HashMap<String, Object> map) {
		String menu_id = String.valueOf(map.get("menu_id"));
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		pdsService.delete(map);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Pds/List?menu_id=" + menu_id + "&nowpage=" + nowpage);
		mv.addObject("map", map);
		return mv;
	}
	
	@RequestMapping("/UpdateForm")
	public ModelAndView updateform(@RequestParam HashMap<String, Object> map) {
		PdsDto pds = pdsService.getPds(map);
		List<FilesDto> fileList = pdsService.getFile(map);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pds/update");
		mv.addObject("map",map);
		mv.addObject("pds", pds);
		mv.addObject("fileList", fileList);
		return mv;
	}
	
	@RequestMapping("/Update")
	public ModelAndView update(@RequestParam HashMap<String, Object> map,@RequestParam(value="upfile") MultipartFile [] uploadfiles) {
		pdsService.setUpdate(map,uploadfiles);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Pds/List?menu_id=" + map.get("menu_id") + "&nowpage=" + map.get("nowpage"));
		mv.addObject("map", map);
		mv.addObject("uploadfiles", uploadfiles);	
		return mv;
	}
	
}
