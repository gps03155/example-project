package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	public void insertBlog(long userNo) {
		blogDao.insertBlog(userNo);
	}
	
	public void updateBlog(BlogVo blogVo) {
		blogDao.updateBlog(blogVo);
	}
	
	public BlogVo selectBlog(String id) {
		return blogDao.selectBlog(id);
	}
}
