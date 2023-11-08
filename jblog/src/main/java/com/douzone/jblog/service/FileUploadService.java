package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/uploads";
	private static final String URL = "/uploads/images";

	public String restore(MultipartFile multipartFile) {
		String url = "";
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartFile.getSize();
			
			System.out.println(originFileName);
			System.out.println(extName);
			System.out.println(saveFileName);
			System.out.println(fileSize);
			
			byte[] fileData = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/"  + saveFileName);
			
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
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
