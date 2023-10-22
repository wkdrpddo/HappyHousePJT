package com.ssafy.happyHouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyHouse.dto.UserDto;
import com.ssafy.happyHouse.dto.UserResultDto;
import com.ssafy.happyHouse.service.UserService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value="/users/{id}")
	private ResponseEntity<UserResultDto> userDetail(@PathVariable String id) {
		System.out.println("detail");
		UserResultDto userResultDto = service.userDetail(id);
		System.out.println(userResultDto);
		
		if( userResultDto.getResult() == 1) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if( userResultDto.getResult() == 0) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping(value="/users")
	private ResponseEntity<Map<String, String>> register(@RequestBody UserDto dto) {
		
		UserResultDto userResultDto = service.register(dto);
		Map<String, String> map = new HashMap<String, String>();
		
		if( userResultDto.getResult() == 1) {
			map.put("result", "success");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else if( userResultDto.getResult() == 0) {
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PutMapping(value="/users/{id}")
	private ResponseEntity<UserResultDto> modify(@RequestBody UserDto dto, HttpSession session) {
		
		UserResultDto userResultDto = service.modify(dto);
		System.out.println(session.getAttribute("userDto"));
		
		if( userResultDto.getResult() == 1) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if( userResultDto.getResult() == 0) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping(value="/users/{id}")
	private ResponseEntity<UserResultDto> delete(@PathVariable String id) {
		
		UserResultDto userResultDto = service.delete(id);
		
		if( userResultDto.getResult() == 1) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else if( userResultDto.getResult() == 0) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	@PostMapping(value="/kakaoUsers")
	private ResponseEntity<Map<String, String>> kakaoRegist(@RequestBody UserDto dto) {
		
		UserResultDto userResultDto = service.kakaoRegist(dto);
		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println(dto);
		
		if( userResultDto.getResult() == 1) {
			map.put("result", "success");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else if( userResultDto.getResult() == 0) {
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		} else {
			map.put("result", "fail");
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

}
