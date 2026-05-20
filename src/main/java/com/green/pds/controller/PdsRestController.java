package com.green.pds.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.pds.dto.FilesDto;
import com.green.pds.mapper.PdsMapper;

@RestController // controller + responsebody
public class PdsRestController {
	
	@Value("${part1.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@RequestMapping("/deletefile/{file_num}")
	public Map<String, Object> deletefile(@PathVariable(value = "file_num") Long file_num) {
		// 폴더에서 삭제할 파일 검색
		FilesDto fileInfo = pdsMapper.getFileInfo(file_num);
		// 실제 파일 삭제
		File file = new File(uploadPath + fileInfo.getSfilename());
		if (file.exists()) {
			file.delete();
		}
		// files table 삭제
		pdsMapper.deleteupload(file_num);
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Ok");
		return map;
		// restController 에서 리턴할때는 java map 을 json으로 변환
	}
}
