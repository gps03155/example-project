package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	public void insertBlog(long userNo) {
		blogDao.insertBlog(userNo);
	}
}
