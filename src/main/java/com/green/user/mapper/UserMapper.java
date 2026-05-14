package com.green.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.user.dto.UserDTO;

@Mapper
public interface UserMapper {

	List<UserDTO> getUserList();

	void insertuser(UserDTO userDTO);

	void deleteuser(UserDTO userDTO);

	void updateuser(UserDTO userDTO);

	void getuser(UserDTO userDTO);

	UserDTO finduser(UserDTO userDTO);

	UserDTO searchuser(UserDTO userDTO);

	UserDTO getIdDupCheck(UserDTO userDTO);



}
