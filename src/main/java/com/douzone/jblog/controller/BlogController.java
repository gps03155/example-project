package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("/{id}")
	public String blog(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.selectBlog(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("id", id);
		
		return "blog/blog-main";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/admin")
	public String admin() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable String id, Model model, @ModelAttribute BlogVo blogVo, @RequestParam(value="logo-file") MultipartFile multipartFile) {
		// model.addAttribute("id", id);
		
		String url = fileuploadService.restore(multipartFile);
		
		blogVo.setId(id);
		blogVo.setLogo(url);
		
		blogService.updateBlog(blogVo);
		
		return "redirect:/blog/" + id;
	}
	
	@RequestMapping("{id}/admin/category")
	public String category(@PathVariable String id) {
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/insert/{name}/{desc}")
	public JSONResult insertCategory(@PathVariable String id, @PathVariable String name, @PathVariable String desc) {
		categoryService.addCategory(id, name, desc);
		long lastInsert = categoryService.lastInsert();
		
		return JSONResult.success(categoryService.getInsert(lastInsert));
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/list")
	public JSONResult selectCategory(@PathVariable String id) {
		List<CategoryVo> list = categoryService.selectCategory();
		
		return JSONResult.success(list);
	}
}
