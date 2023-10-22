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
public class BoardParamDto {
	private String searchWord;
	
	private int boardId;
	
	private String userId;

}
