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
import com.douzone.jblog.service.CommentService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
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
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping({"/{id}", "/{id}/{no}", "/{id}/{no}/{categoryNo}"})
	public String blog(@PathVariable String id, @PathVariable Optional<Long> no, @PathVariable Optional<String> categoryNo, Model model) {
		BlogVo blogVo = blogService.selectBlog(id);
		List<PostVo> postList = postService.selectPost(id);
		List<CategoryVo> categoryList = categoryService.getCategoryName(id);
		List<CommentVo> commentList = null;
		
		if(!no.isPresent() && !categoryNo.isPresent()) {
			long postNo = postService.lastSelect(id);
			PostVo postVo = postService.getNoPost(postNo, id);
			commentList = commentService.selectComment(postNo);
			
			model.addAttribute("commentList", commentList);
			model.addAttribute("post", postVo);
		}
		else if(no.isPresent() && !categoryNo.isPresent()) {
			System.out.println("게시글 클릭");
			
			long postNo = no.get();
			PostVo postVo = postService.getNoPost(postNo, id);
			commentList = commentService.selectComment(postNo);
			
			model.addAttribute("commentList", commentList);
			model.addAttribute("post", postVo);
		}
		else if(no.isPresent() && categoryNo.isPresent()) {
			System.out.println("카테고리 클릭");
			
			long categoryLong = Long.parseLong(categoryNo.get());
			postList = postService.categoryPost(categoryLong);
			
			long postNo = no.get();
			PostVo postVo = postService.getNoPost(postNo, id);
			commentList = commentService.selectComment(postNo);

			model.addAttribute("commentList", commentList);
			model.addAttribute("post", postVo);
			model.addAttribute("postList", postList);
			model.addAttribute("isCategory", true);
			model.addAttribute("categoryNo", categoryLong);
		}
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("id", id);
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-main";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/admin")
	public String admin(@RequestParam(value="id", required=true, defaultValue="") String id, Model model) {
		BlogVo blogVo = blogService.selectLogo(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable String id, Model model, @ModelAttribute BlogVo blogVo, @RequestParam(value="logo-file") MultipartFile multipartFile) {
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
		int addResult = categoryService.addCategory(id, name, desc);
	
		if(addResult == 1) {
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
		
		long countPost = categoryService.countPost(no);
		
		if(countPost == 0) {
			int result = categoryService.deleteCategory(id, no);
			
			if(result == 1) {
				return JSONResult.success(true);
			}
			else {
				return JSONResult.fail("삭제실패");
			}
		}
		else {
			return JSONResult.fail("카테고리에 게시글 존재");
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
	
	@RequestMapping("/comment")
	public String comment(@RequestParam String comment, @RequestParam String id, @RequestParam long postNo) {
		commentService.insertComment(comment, postNo);
		
		return "redirect:/blog/" + id + "/" + postNo;
	}
}
