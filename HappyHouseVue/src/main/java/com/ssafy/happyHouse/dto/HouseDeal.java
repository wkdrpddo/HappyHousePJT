package com.ssafy.happyHouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseDeal {
	static String APT_DEAL;
	static String APT_RENT;
	static String HOUSE_DEAL;
	static String HOUSE_RENT;
	int no;
	String dong;
	String AptName;
	int code;
	String dealAmount;
	int buildYear;
	int dealYear;
	int dealMonth;
	double area;
	int floor;
	String lat;
	String lng;
	String jibun;
	String type;
	String rentMoney;
	String img;
	public HouseDeal() {};
	public HouseDeal(int no) {
		this.no = no;
	}
	
}
