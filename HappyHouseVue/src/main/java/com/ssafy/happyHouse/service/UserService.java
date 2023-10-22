package com.ssafy.happyHouse.service;

import com.ssafy.happyHouse.dto.UserDto;
import com.ssafy.happyHouse.dto.UserResultDto;

public interface UserService {
	public UserResultDto userDetail(String id);
	public UserResultDto register(UserDto dto);
	public UserResultDto modify(UserDto dto);
	public UserResultDto delete(String id);
	
	public UserResultDto kakaoRegist(UserDto dto);

}
