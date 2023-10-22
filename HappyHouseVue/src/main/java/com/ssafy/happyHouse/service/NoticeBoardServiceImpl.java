package com.ssafy.happyHouse.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.happyHouse.dao.NoticeBoardDao;
import com.ssafy.happyHouse.dao.UserDao;
import com.ssafy.happyHouse.dto.BoardDto;
import com.ssafy.happyHouse.dto.BoardFileDto;
import com.ssafy.happyHouse.dto.BoardParamDto;
import com.ssafy.happyHouse.dto.BoardResultDto;
import com.ssafy.happyHouse.dto.UserDto;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Autowired
	NoticeBoardDao dao;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;

	@Override
	public BoardResultDto noticeBoardList(BoardParamDto boardParam) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.noticeBoardList(boardParam);
			int listCnt = dao.noticeBoardTotalCnt();
			
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
	public BoardResultDto noticeBoardListSearchWord(BoardParamDto boardParam) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.noticeBoardListSearchWord(boardParam);
			int listCnt = dao.noticeBoardTotalCntSearchWord(boardParam);
			
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
		
		System.out.println(boardParam);
		
		try {
			int readCnt = dao.getReadCount(boardParam);
			if(readCnt == 0) {
				dao.insertReadCount(boardParam.getBoardId(), boardParam.getUserId());
				dao.updateReadCount(boardParam.getBoardId());
			}
			
			BoardDto noticeBoard = dao.boardDetail(boardParam);
			
			List<BoardFileDto> fileList = dao.boardFileList(noticeBoard.getBoardId());
			
			noticeBoard.setFileList(fileList);
			boardResultDto.setDto(noticeBoard);
			boardResultDto.setResult(1);
			
			System.out.print(">>>>>> noticeBoard : ");
			System.out.println(noticeBoard);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	@Transactional
	public BoardResultDto noticeBoardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			dao.noticeBoardInsert(boardDto);
			
			// Insert할 파일 데이터 가져옴
			List<MultipartFile> fileList = request.getFiles("file");
			
			// 파일 저장 폴더 생성
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);			
			if(!uploadDir.exists()) {
				System.out.println("Maked uploadDir");
				uploadDir.mkdir();
			}
			
			// 파일 리스트의 파일들을 저장한다.
			for(MultipartFile partFile : fileList) {
				int boardId = boardDto.getBoardId();
				
				// 실제 파일 명
				String fileName = partFile.getOriginalFilename();
				
				// 유일한 ID의 생성
				UUID uuid = UUID.randomUUID();
				
				// 저장 파일 명 생성
				String extension = FilenameUtils.getExtension(fileName);
				String savingFileName = uuid + "." + extension;
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(destFile.getPath());
				
				partFile.transferTo(destFile);
				
				// Insert Table FileData
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				dao.boardFileInsert(boardFileDto);
				
			}
			
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto noticeBoardModify(BoardDto boardDto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		int boardId = boardDto.getBoardId();
		
		try {
			dao.noticeBoardModify(boardDto);
			
			// Insert할 파일 데이터 가져옴
			List<MultipartFile> fileList = request.getFiles("file");
			
			// 파일 저장 폴더 생성
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);			
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			// 저장된 파일 삭제
			// 실제 파일 삭제
			List<String> fileUrlList = dao.boardFileUrlDeleteList(boardId);
			for(String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl);
				if(file.exists()) file.delete();
			}
			// DB 삭제
			dao.boardFileDelete(boardId);
			
			
			// 파일 리스트의 파일들을 저장한다.
			for(MultipartFile partFile : fileList) {
				
				// 실제 파일 명
				String fileName = partFile.getOriginalFilename();
				
				// 유일한 ID의 생성
				UUID uuid = UUID.randomUUID();
				
				// 저장 파일 명 생성
				String extension = FilenameUtils.getExtension(fileName);
				String savingFileName = uuid + "." + extension;
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(destFile.getPath());
				
				partFile.transferTo(destFile);
				
				// Insert Table FileData
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				dao.boardFileInsert(boardFileDto);
				
			}
			
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto noticeBoardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {// 저장된 파일 삭제
			// 실제 파일 삭제
			List<String> fileUrlList = dao.boardFileUrlDeleteList(boardId);
			for(String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl);
				if(file.exists()) file.delete();
			}
			// DB 삭제
			dao.boardFileDelete(boardId);
			dao.deleteReadCount(boardId);
			dao.noticeBoardDelete(boardId);
			
			boardResultDto.setResult(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(-1);
		}
		return boardResultDto;
	}

}
