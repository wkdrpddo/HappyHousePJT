package com.ssafy.happyHouse.service;

import com.ssafy.happyHouse.dto.UserResultDto;

public interface LoginService {
	UserResultDto login(String id, String password);
	UserResultDto kakaoLogin(String id, String email);

}
