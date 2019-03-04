package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(long userNo) {
		sqlSession.insert("blog.insertBlog", userNo);
	}
}
