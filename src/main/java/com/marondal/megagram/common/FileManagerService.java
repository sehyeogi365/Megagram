package com.marondal.megagram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.megagram.common.FileManagerService;

public class FileManagerService {//파일저장 서비스 

	
	public static final String FILE_UPLOAD_PATH = "D:\\임세혁\\spring\\springProject\\upload\\megagram\\image"; //이렇게 되면 상수가 된다.
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	//member변수 웬만하면 public안쓴다.				//경로 잘 지정하고 슬래쉬 잘구분하기(메가그램거이므로 경로 수정)
// 파일 저장 -> 경로 생성
// 객체 생성없이 쓸수 있는 메소드 -> static 객체생성없이 쓸수 있도록 구성
	public static String saveFile(int userId, MultipartFile file) {//접근하기 위한 경로 리턴
				//리턴값: 경로가 되야하므로 스트링 사용자정보 이므로 userId 그리고 메모거 그대로 복사하는건 잘함 ㅇㅇ.
	
		if(file == null) {//file이 널이라면?
			
			return null;
			
		}
	
	// 파일명이 같으면 안되니 그거 생각 
	// 사용자 별로 폴더 구분 
	// 시간을 포함해서 폴더를 구분
	// UNIX TIME : 1970년 1월 1일 부터 흐른 시간을 (milli second 1/1000)
	// 폴더 이름 : userId_time(3_3949828284 15:00:11)		
	
	String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";//로그인 한정보 필요 파라미터 추가
	
	// 디렉토리 생성
	String directoryPath = FILE_UPLOAD_PATH + directoryName;
	File directory = new File(directoryPath);//java.io.file 
	if(!directory.mkdir()){//makedirectory
		//디렉토리 생성 실패 
		logger.error("saveFile : 디렉토리 생성 실패 " + directoryPath);
		return null;
	}
	
	// 파일 저장
	try {
		byte[] bytes = file.getBytes();//이게 널이라서 500error Cannot invoke "org.springframework.web.multipart.MultipartFile.getBytes()" because "file" is null
		
		String filePath = directoryPath + file.getOriginalFilename();//파일명 포함한 전체경로
		Path path = Paths.get(filePath);
		
		Files.write(path, bytes); //이타입이 저장된다.
		
		
	} catch (IOException e) {
		
		e.printStackTrace();
		
		return null;
	}
	
	// 클라이언트에서 저장된 파일을 접근할 수 있는 경로를 리턴
	// 경로 규칙 /images/2_39i980139/test.png
	// http://localhost:8080//images/2_39i980139/test.png
	
	return "/images" + directoryName + file.getOriginalFilename();//
	
}

	public static boolean removeFile(String filePath) {
		
		
		if(filePath == null) {
			logger.info("삭제 대상 파일 없음");
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		Path path = Paths.get(fullFilePath);
		
		if(Files.exists(path)) {
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("removeFile : 파일 삭제 에러 " + fullFilePath);
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		Path dirPath = path.getParent();
		
		if(Files.exists(dirPath)) {
			
			try {
				Files.delete(dirPath);
			} catch (IOException e) {
				logger.error("removeFile : 디렉토리 삭제 " + fullFilePath);
				e.printStackTrace();
				return false;
			}
			
		}
		
		
		
		
		return true;
		
	}
	
	
	

}
