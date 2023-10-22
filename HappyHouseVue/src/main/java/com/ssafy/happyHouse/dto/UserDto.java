package com.ssafy.happyHouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String userCode;
}
