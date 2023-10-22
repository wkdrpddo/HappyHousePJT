package com.ssafy.happyHouse.service;

import java.util.HashMap;

public interface KakaoLoginService {
	public String getAccessToken(String authorize_code);
	public HashMap<String, String> getUserInfo(String accessToken);
}
