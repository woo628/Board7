package com.green.pds.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.pds.dto.FilesDto;
import com.green.pds.dto.PdsDto;

@Mapper
public interface PdsMapper {

	int count(HashMap<String, Object> map);

	List<PdsDto> getPdsList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map);

	void setFileWriter(HashMap<String, Object> map);

	void setReadCountUpdate(HashMap<String, Object> map);

	PdsDto getPds(HashMap<String, Object> map);

	List<FilesDto> getFile(HashMap<String, Object> map);

	FilesDto getFileInfo(Long file_num);

	void delete(HashMap<String, Object> map);

	void filedelete(HashMap<String, Object> map);

	void update(HashMap<String, Object> map);


}
