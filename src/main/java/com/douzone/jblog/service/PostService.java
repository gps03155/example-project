package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
	public void insertPost(PostVo postVo) {
		postDao.insertPost(postVo);
	}
	
	public List<PostVo> selectPost(){
		return postDao.selectPost();
	}
}
