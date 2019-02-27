package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	// @Autowired
	// private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		// List list = galleryService.getGalleryList();
		
		// model.addAttribute("list", list);
		
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@ModelAttribute SiteVo siteVo, @RequestParam(value="upload-image") MultipartFile multipartFile) {
//		String url = fileuploadService.restore(multipartFile);
//		siteVo.setProfile(url);
//		
//		siteService.update(siteVo);
//		
		return "redirect:/gallery";
	}
}
