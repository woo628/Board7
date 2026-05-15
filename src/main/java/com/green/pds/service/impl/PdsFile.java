package com.green.pds.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.FilesDto;

public class PdsFile {

	public static void save(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		// uploadfiles 에 넘오온 파일들을 저장
		String uploadPath = String.valueOf(map.get("uploadPath"));
		// 파일들을 저장하고 table에 저장할 정보를 map에 담는다
		List<FilesDto> fileList = new ArrayList<>();
		// 파일별로 반복
		for (MultipartFile uploadfile : uploadfiles) {
			if (uploadfile.isEmpty()) { // 전송할 파일 없어도 반복해라
				continue;
			}	
			String orgName = uploadfile.getOriginalFilename();
			String fileName = (orgName.lastIndexOf("\\") < 0 ) ? orgName : orgName.substring(orgName.lastIndexOf("\\") + 1); // data.abc.txt
			String fileExt = (orgName.lastIndexOf(".") < 0 ) ? orgName : orgName.substring(orgName.lastIndexOf(".")); // .txt
			// 날짜 폴더 생성
			String folderPath = makeFolder(uploadPath);
			// 파일 중복 방지 UUID(고유한 문자열)
			String uuid = UUID.randomUUID().toString();
			// 저장할 sfilename 생성 ( 경로포함 실제 파일명)
			String savename = uploadPath + File.separator + folderPath + File.separator + uuid + "." + fileName; 
			String savename2 = folderPath + File.separator + uuid + "." + fileName; 	// sfilename 
			Path savePath = Paths.get(savename); // 특정 경로의 파일정보를 가져온다
			// 파일 저장
			try {
				uploadfile.transferTo(savePath.toFile());
			} catch (IllegalStateException | IOException e) {				
				e.printStackTrace();
			}
			FilesDto filesDto = new FilesDto(0, 0, fileName, fileExt, savename2);
			// filesDto에 파일정보 저장
			fileList.add(filesDto);
		}
			// map 에 fileList 정보추가
			map.put("fileList", fileList);
	}

	private static String makeFolder(String uploadPath) {
		String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// File.separator = 운영체제마다 파일구분자 다르게
		String folderPath = dateStr.replace("/", File.separator);
		// 폴더가 존재하지 않으면 하위 폴더까지 한 번에 생성(mkdirs)
		File uploadPathFolder = new File(uploadPath,folderPath);
		if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs(); 
        }
        return folderPath;
	}

}
