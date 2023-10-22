package com.ssafy.happyHouse.dao;

import java.awt.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyHouse.dto.UserDto;

@Mapper
public interface UserDao {
	public UserDto userDetail(String id);
	public int register(UserDto dto);
	public int modify(UserDto dto);
	public int delete(String id);
	
	public int kakaoRegist(UserDto dto);
}
