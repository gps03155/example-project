package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/uploads"; // c 드라이브 밑 upload에 저장 - 저장은 프로젝트 안에서 하는게 아님
	private static final String URL = "/uploads/images"; // 웹으로 접근 시
	
	public boolean delete(String filePath) {
		boolean result = false;
		String saveFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		
		File file = new File(SAVE_PATH + "/" + saveFileName);
		System.out.println(file.getPath());
		
		if(file.exists()) {
			result = file.delete();
		}
		
		return result;
	}
	
	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
			if (multipartFile.isEmpty()) {
				return url;
			}

			String originalFileName = multipartFile.getOriginalFilename(); // 확장자 분류하는 작업 필요 - original로 저장하는거 아님 (덮어버릴 수 있음)
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();

			System.out.println("originalFileName : " + originalFileName);
			System.out.println("extName : " + extName);
			System.out.println("saveFileName : " + saveFileName);
			System.out.println("fileSize : " + fileSize);

			byte[] fileData = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;
		} 
		catch (Exception e) {
			new RuntimeException("upload fail");
		}
		
		return url;
	}

	private String generateSaveFileName(String extName) { // 내부에서 쓰는 함수 private
		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		// upload한 시간 구하기 - db 저장
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}
}
