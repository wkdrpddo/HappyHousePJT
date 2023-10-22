package com.ssafy.happyHouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyHouse.dto.LocationDto;

@Mapper
public interface LocationDao {
	List<LocationDto> searchSido();
	List<LocationDto> searchGugun(int code);
	List<LocationDto> searchDong(int code);
}
