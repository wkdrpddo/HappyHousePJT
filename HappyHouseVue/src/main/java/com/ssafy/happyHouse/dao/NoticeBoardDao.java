package com.ssafy.happyHouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyHouse.dto.BoardDto;
import com.ssafy.happyHouse.dto.BoardFileDto;
import com.ssafy.happyHouse.dto.BoardParamDto;

@Mapper
public interface NoticeBoardDao {
	// 전체 List 검색
	public List<BoardDto> noticeBoardList(BoardParamDto boardParam);
	public int noticeBoardTotalCnt();
	
	// 단어를 통한 List 검색
	public List<BoardDto> noticeBoardListSearchWord(BoardParamDto boardParam);
	public int noticeBoardTotalCntSearchWord(BoardParamDto boardParam);
	
	// 게시글 상세
	public BoardDto boardDetail(BoardParamDto boardParam);
	// 게시글 상세 - 첨부파일
	public List<BoardFileDto> boardFileList(int boardId);
	
	// 게시글 작성
	public int noticeBoardInsert(BoardDto boardDto);
	// 게시글 작성 - 첨부파일
	public int boardFileInsert(BoardFileDto fileDto);
	
	// 게시글 수정
	public int noticeBoardModify(BoardDto boardDto);
	
	// 게시글 삭제
	public int noticeBoardDelete(int boardId);
	// 게시글 삭제 - 첨부파일
	public int boardFileDelete(int boardId);
	public List<String> boardFileUrlDeleteList(int boardId); 

	// 조회수
	public int getReadCount(BoardParamDto boardParam);
	public int insertReadCount(@Param("boardId") int boardId, @Param("userId") String userId);
	public int updateReadCount(int boardId);
	public int deleteReadCount(int boardId);

}
