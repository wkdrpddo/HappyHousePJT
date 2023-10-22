package com.ssafy.happyHouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseInfo {
	int no;
	String dong;
	String AptName;
	int code;
	int buildYear;
	String lat;
	String lng;
	String jibun;
	String img;
}
