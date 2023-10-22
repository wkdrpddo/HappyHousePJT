package com.ssafy.happyHouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyHouse.dao.FreeBoardDao;
import com.ssafy.happyHouse.dto.BoardParamDto;
import com.ssafy.happyHouse.dto.BoardResultDto;
import com.ssafy.happyHouse.dto.BoardDto;
import com.ssafy.happyHouse.dto.BoardFileDto;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	FreeBoardDao dao;

	@Override
	public BoardResultDto freeBoardList(BoardParamDto boardParam) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.freeBoardList(boardParam);
			int listCnt = dao.freeBoardTotalCnt();
			
			boardResultDto.setList(list);
			boardResultDto.setCount(listCnt);
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto freeBoardListSearchWord(BoardParamDto boardParam) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.freeBoardListSearchWord(boardParam);
			int listCnt = dao.freeBoardTotalCntSearchWord(boardParam);
			
			boardResultDto.setList(list);
			boardResultDto.setCount(listCnt);
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParam) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int readCnt = dao.getReadCount(boardParam);
			if(readCnt == 0) {
				dao.insertReadCount(boardParam.getBoardId(), boardParam.getUserId());
				dao.updateReadCount(boardParam.getBoardId());
			}
			
			BoardDto freeBoard = dao.boardDetail(boardParam);
			
			System.out.println("Login User : ");
			System.out.println(boardParam);
			System.out.println("Board Data : ");
			System.out.println(freeBoard);
			
			if( freeBoard.getUserId().equals( boardParam.getUserId() ) ) {
				freeBoard.setSameUser(true);
			} else {
				freeBoard.setSameUser(false);
			}
			
			boardResultDto.setDto(freeBoard);
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto freeBoardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			boardResultDto.setResult( dao.freeBoardInsert(boardDto) );
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto freeBoardModify(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			boardResultDto.setResult( dao.freeBoardModify(boardDto) );
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto freeBoardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			dao.deleteReadCount(boardId);
			boardResultDto.setResult( dao.freeBoardDelete(boardId) );
			System.out.println(boardId);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		return boardResultDto;
	}

}
