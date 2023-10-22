package com.ssafy.happyHouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyHouse.dao.FreeBoardDao;
import com.ssafy.happyHouse.dto.BoardParamDto;
import com.ssafy.happyHouse.dto.BoardResultDto;
import com.ssafy.happyHouse.dto.BoardDto;

public interface FreeBoardService {
	// 전체 List 검색
	public BoardResultDto freeBoardList(BoardParamDto boardParam);
	
	// 단어를 통한 List 검색
	public BoardResultDto freeBoardListSearchWord(BoardParamDto boardParam);
	
	// 게시글 상세
	public BoardResultDto boardDetail(BoardParamDto boardParam);
	// 게시글 작성
	public BoardResultDto freeBoardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
	// 게시글 수정
	public BoardResultDto freeBoardModify(BoardDto boardDto, MultipartHttpServletRequest request);
	// 게시글 삭제
	public BoardResultDto freeBoardDelete(int boardId);

}
