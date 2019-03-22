package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CommentDao;
import com.douzone.jblog.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public void insertComment(String comment, long postNo) {
		commentDao.insertComment(comment, postNo);
	}
	
	public List<CommentVo> selectComment(long postNo){
		return commentDao.selectComment(postNo);
	}
}
