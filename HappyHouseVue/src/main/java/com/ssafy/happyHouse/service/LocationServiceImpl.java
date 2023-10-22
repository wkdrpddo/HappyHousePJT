package com.ssafy.happyHouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyHouse.dao.LocationDao;
import com.ssafy.happyHouse.dto.LocationDto;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationDao location;
	
	@Override
	public List<LocationDto> searchSido() {
		// TODO Auto-generated method stub
		return location.searchSido();
	}

	@Override
	public List<LocationDto> searchGugun(int code) {
		// TODO Auto-generated method stub
		return location.searchGugun(code);
	}

	@Override
	public List<LocationDto> searchDong(int code) {
		// TODO Auto-generated method stub
		return location.searchDong(code);
	}

}
