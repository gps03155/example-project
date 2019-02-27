package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getGalleryList();
		
		model.addAttribute("list", list);
		
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@ModelAttribute GalleryVo galleryVo, @RequestParam(value="upload-image") MultipartFile multipartFile) {
		String url = fileuploadService.restore(multipartFile);
		galleryVo.setImage_url(url);
		
		System.out.println(url);
		System.out.println(galleryVo);
	
		int result = galleryService.galleryInsert(galleryVo);
		
		if(result == 1) {
			System.out.println("이미지 등록 성공");
		}
		
		return "redirect:/gallery";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") long no) {
		int result = galleryService.galleryDelete(no);
		
		if(result == 1) {
			System.out.println("이미지 삭제 성공");
		}
		
		return "redirect:/gallery";
	}
}
