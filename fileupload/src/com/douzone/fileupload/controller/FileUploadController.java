package com.douzone.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping({"", "/form"})
	public String form() {
		return "form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="email", required=true, defaultValue="") String email,
						 @RequestParam(value="upload-file") MultipartFile multipartFile,
						 Model model) {// 업로드된 파일 정보
		
		System.out.println("email : " + email);
		
		String url = fileuploadService.restore(multipartFile);
		
		model.addAttribute("url", url);
		
		return "result";
	}
}
