package com.ssafy.happyHouse.service;

import java.util.List;

import com.ssafy.happyHouse.dto.LocationDto;

public interface LocationService {
	List<LocationDto> searchSido();
	List<LocationDto> searchGugun(int code);
	List<LocationDto> searchDong(int code);
}
