package com.ssafy.happyHouse.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyHouse.dto.UserDto;


@Mapper
public interface LoginDao {
	UserDto login(String id);
	UserDto kakaoLogin(String id);
}
