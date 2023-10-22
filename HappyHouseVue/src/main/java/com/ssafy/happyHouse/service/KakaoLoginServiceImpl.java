package com.ssafy.happyHouse.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {

	@Override
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		System.out.println(authorize_code);
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=171ed25a231a774fc4306d7ff03487bd");
//			sb.append("&redirect_uri=http://localhost:5500/kakaologin");
			sb.append("&redirect_uri=http://localhost:8080/kakaologin");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while( (line = br.readLine()) != null ) {
				result += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("accessToken : " + access_Token);
			System.out.println("refreshToken : " + refresh_Token);
			
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return access_Token;
//		return refresh_Token;
	}

	@Override
	public HashMap<String, String> getUserInfo(String accessToken) {
		HashMap<String, String> userInfo = new HashMap<String, String>();
//		String reqURL = "https://kapi.kakao.com/v1/user/access_token_info";
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			String value = "Bearer " + accessToken;
//			System.out.println(value);
			
			conn.setRequestProperty("Authorization", value);
//			conn.addRequestProperty("Authorization", value);
			
			System.out.println(conn.getRequestProperties());
			System.out.println(conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while( (line = br.readLine()) != null ) {
				result += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			System.out.println(element);
			
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String id = element.getAsJsonObject().get("id").getAsString();
			String email = null;
			if(kakao_account.getAsJsonObject().get("email") != null) {
				email = kakao_account.getAsJsonObject().get("email").getAsString();
				userInfo.put("email", email);
			}

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			
			String nickname = null;
			if(properties.getAsJsonObject().get("nickname") != null) {
				nickname = properties.getAsJsonObject().get("nickname").getAsString();
				userInfo.put("name", nickname);
			}
			userInfo.put("id", id);
		} catch(Exception e) {
//			e.printStackTrace();
		}
		return userInfo;
	}

}
