package com.ssafy.happyHouse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyHouse.dto.BoardDto;
import com.ssafy.happyHouse.dto.BoardParamDto;
import com.ssafy.happyHouse.dto.BoardResultDto;

public interface NoticeBoardService {
	// 전체 List 검색
	public BoardResultDto noticeBoardList(BoardParamDto boardParam);
	
	// 단어를 통한 List 검색
	public BoardResultDto noticeBoardListSearchWord(BoardParamDto boardParam);
	
	// 게시글 상세
	public BoardResultDto boardDetail(BoardParamDto boardParam);
	// 게시글 작성
	public BoardResultDto noticeBoardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
	// 게시글 수정
	public BoardResultDto noticeBoardModify(BoardDto boardDto, MultipartHttpServletRequest request);
	// 게시글 삭제
	public BoardResultDto noticeBoardDelete(int boardId);

}
