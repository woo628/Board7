package com.green.pds.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.FilesDto;
import com.green.pds.dto.PdsDto;
import com.green.pds.mapper.PdsMapper;
import com.green.pds.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService{
	
	@Value("${part1.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@Override
	public List<PdsDto> getPdsList(HashMap<String, Object> map) {
		List<PdsDto> pdsList = pdsMapper.getPdsList(map);
		return pdsList;
	}

	@Override
	public void setWrite(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		// 파일저장
		// String uploadPath = "d:/dev/springboot/data/";
		map.put("uploadPath", uploadPath);
		PdsFile.save(map,uploadfiles);
		// db저장
		// board table 저장
		pdsMapper.setWrite(map); // insertBoard
		// files table 저장
		List<FilesDto> fileList = (List<FilesDto>) map.get("fileList");
		if (fileList.size() > 0) {
			pdsMapper.setFileWriter(map);
		}
	}

	@Override
	public void setReadCountUpdate(HashMap<String, Object> map) {
		pdsMapper.setReadCountUpdate(map);
	}

	@Override
	public PdsDto getPds(HashMap<String, Object> map) {
		PdsDto pdsDto = pdsMapper.getPds(map);
		return pdsDto;
	}

	@Override
	public List<FilesDto> getFile(HashMap<String, Object> map) {
		List<FilesDto> fileList = pdsMapper.getFile(map);
		return fileList;
	}

	@Override
	public FilesDto getFileInfo(Long file_num) {
		FilesDto fileInfo = pdsMapper.getFileInfo(file_num);
		return fileInfo;
	}

	@Override
	public void delete(HashMap<String, Object> map) {
		List<FilesDto> fileList = pdsMapper.getFile(map); // 해당파일 정보 조회
		PdsFile.delete(uploadPath,fileList); // 실제파일 삭제
		pdsMapper.filedelete(map); // 파일 삭제
		pdsMapper.delete(map); // 자료실 삭제
	}

	@Override
	public void setUpdate(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		// map에 저장
		map.put("uploadPath",uploadPath);
		// 업로드된 파일저장
		PdsFile.save(map,uploadfiles);
		// files 정보 저장
		List<FilesDto> fileList = (List<FilesDto>) map.get("fileList");
		if (fileList.size() > 0) {
			pdsMapper.setFileWriter(map);
		}
		// board 정보 수정
		pdsMapper.update(map);
	}

	
}












