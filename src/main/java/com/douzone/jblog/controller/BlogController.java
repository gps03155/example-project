package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
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
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"/{id}", "/{id}/{title}/{content}", "/{id}/{categoryNo}"})
	public String blog(@PathVariable String id, @PathVariable Optional<String> title, @PathVariable Optional<String> content, @PathVariable Optional<String> categoryNo, Model model) {
		BlogVo blogVo = blogService.selectBlog(id);
		List<PostVo> postList = postService.selectPost(id);
		List<CategoryVo> categoryList = categoryService.getCategoryName(id);
		
		if(title.isPresent() && content.isPresent()) {
			System.out.println("게시글 클릭");
			
			String titleStr = title.get();
			String contentStr = content.get();
			
			model.addAttribute("title", titleStr);
			model.addAttribute("content", contentStr);
		}
	
		if(categoryNo.isPresent()) {
			System.out.println("카테고리 클릭");
			
			long categoryLong = Long.parseLong(categoryNo.get());
			postList = postService.categoryPost(categoryLong);
			
			model.addAttribute("postList", postList);
		}
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("id", id);
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		
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
		int result = categoryService.addCategory(id, name, desc);
	
		if(result == 1) {
			long lastInsert = categoryService.lastInsert();
			
			return JSONResult.success(categoryService.getInsert(lastInsert));
					
		}
		else {
			return JSONResult.fail("카테고리 존재");
		}
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/list")
	public JSONResult selectCategory(@PathVariable String id) {
		List<CategoryVo> list = categoryService.selectCategory(id);
		
		for (CategoryVo categoryVo : list) {
			categoryVo.setCountPost(categoryService.countPost(categoryVo.getNo()));
		}
		
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/delete/{no}")
	public JSONResult deleteCategory(@PathVariable String id, @PathVariable long no) {
		System.out.println(id);
		System.out.println(no);
		
		int result = categoryService.deleteCategory(id, no);
		
		if(result == 1) {
			return JSONResult.success(true);
		}
		else {
			return JSONResult.fail("삭제실패");
		}
	}
	
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.GET)
	public String write(@PathVariable String id, Model model) {
		List<CategoryVo> categoryList = categoryService.getCategoryName(id);
		
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.POST)
	public String write(@PathVariable String id, @ModelAttribute PostVo postVo, @RequestParam String category) {
		postVo.setCategoryNo(categoryService.getCategoryNo(id, category));
		postService.insertPost(postVo);
		
		return "redirect:/blog/" + id;
	}	
}
