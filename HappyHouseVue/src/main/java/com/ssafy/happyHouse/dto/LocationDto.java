package com.ssafy.happyHouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationDto {
	int code;
	String name;
	int city_code;
	String city_name;
	int gugun_code;
	String gugun_name;
}
