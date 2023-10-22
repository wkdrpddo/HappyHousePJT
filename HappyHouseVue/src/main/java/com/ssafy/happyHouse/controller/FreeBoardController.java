package com.ssafy.happyHouse.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyHouse.dto.BoardParamDto;
import com.ssafy.happyHouse.dto.BoardResultDto;
import com.ssafy.happyHouse.dto.BoardDto;
import com.ssafy.happyHouse.dto.UserDto;
import com.ssafy.happyHouse.service.FreeBoardService;

@RestController
@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS}
)
public class FreeBoardController {
	@Autowired
	FreeBoardService service;
	

	// Board List 검색
	@GetMapping(value="/freeboards")
	private ResponseEntity<BoardResultDto> freeBoardList(BoardParamDto boardParam) {
		BoardResultDto boardResultDto;
		
		if(boardParam.getSearchWord().isEmpty()) {
			boardResultDto = service.freeBoardListSearchWord(boardParam);
		} else {
			boardResultDto = service.freeBoardList(boardParam);
		}
		
		if(boardResultDto.getResult() == 1) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	// 게시글 상세
	@GetMapping(value="/freeboards/{boardId}")
	private ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session) {
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);
		boardParamDto.setUserId( ((UserDto)session.getAttribute("userDto")).getId() );
		
		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		
		if(boardResultDto.getResult() == 1) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	// 게시글 작성
	@PostMapping(value="/freeboards")
	private ResponseEntity<BoardResultDto> freeBoardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDto user = (UserDto) session.getAttribute("userDto");
		
		System.out.println(user);
		
		boardDto.setUserId(user.getId());
		
		BoardResultDto boardResultDto = service.freeBoardInsert(boardDto, request);
		
		if(boardResultDto.getResult() == 1) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
	}
	// 게시글 수정
	@PostMapping(value="/freeboards/{boardId}")
	private ResponseEntity<BoardResultDto> freeBoardModify(BoardDto boardDto, MultipartHttpServletRequest request) {
		HttpSession session = request.getSession();
		UserDto user = (UserDto) session.getAttribute("userDto");
		boardDto.setUserId(user.getId());
		
		BoardResultDto boardResultDto = service.freeBoardModify(boardDto, request);
		
		if(boardResultDto.getResult() == 1) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	// 게시글 삭제
	@DeleteMapping(value="/freeboards/{boardId}")
	private ResponseEntity<BoardResultDto> freeBoardDelete(@PathVariable int boardId) {
		BoardResultDto boardResultDto = service.freeBoardDelete(boardId);
		System.out.println(boardId);
		
		if(boardResultDto.getResult() == 1) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
	}
	
}
