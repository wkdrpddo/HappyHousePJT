package com.ssafy.happyHouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyHouse.dto.LocationDto;
import com.ssafy.happyHouse.service.LocationService;

@RestController
@CrossOrigin(
		   // localhost:5500 과 127.0.0.1 구분
		   origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		   allowCredentials = "true", 
		   allowedHeaders = "*", 
		   methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class LocationController {

	@Autowired
	LocationService location;

	@GetMapping(value="/search/sl")
	public ResponseEntity<List<LocationDto>> sidoList(){
	    
	    List<LocationDto> list = location.searchSido();
	    for (LocationDto locationDto : list) {
			System.out.println(locationDto);
		}
	    return new ResponseEntity<List<LocationDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/search/sl2/{code}")
	public ResponseEntity<List<LocationDto>> searchSl2(@PathVariable int code) {
		System.out.println("here");
		List<LocationDto> list = location.searchGugun(code);
		return new ResponseEntity<List<LocationDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/search/sl3/{code}")
	public ResponseEntity<List<LocationDto>> searchSl3(@PathVariable int code) {
		System.out.println("here : "+code);
		List<LocationDto> list = location.searchDong(code);
		for (LocationDto locationDto : list) {
			System.out.println(locationDto);
		}
		return new ResponseEntity<List<LocationDto>>(list, HttpStatus.OK);
	}
}
