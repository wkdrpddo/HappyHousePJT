package com.ssafy.happyHouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyHouse.dao.LoginDao;
import com.ssafy.happyHouse.dto.UserDto;
import com.ssafy.happyHouse.dto.UserResultDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao dao;
	
	@Override
	public UserResultDto login(String id, String password) {
		UserResultDto resultDto = new UserResultDto();
		System.out.println(id);
		
		try {
			UserDto dto = dao.login(id);
//			System.out.println(dto);
//			System.out.println(password);
			if(dto == null) {
				resultDto.setResult(-1);
				return resultDto;
			}
			
			if(dto.getPassword().equals(password)) {
				System.out.println("same");
				resultDto.setDto(dto);
				resultDto.setResult(1);
			} else {
				resultDto.setResult(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(-1);
		}
		
		return resultDto;
	}

	@Override
	public UserResultDto kakaoLogin(String id, String email) {
		UserResultDto resultDto = new UserResultDto();
		System.out.println(id);
		System.out.println(email);
		
		try {
			UserDto dto = dao.kakaoLogin(id);
			
			System.out.println(dto);

			if(dto == null) {
				resultDto.setResult(-1);
				return resultDto;
			}
			
			if(dto.getAddress().equals(email)) {
				System.out.println("same");
				resultDto.setDto(dto);
				resultDto.setResult(1);
			} else {
				resultDto.setResult(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(-1);
		}
		
		return resultDto;
	}

}
