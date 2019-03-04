package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(long userNo) {
		sqlSession.insert("blog.insertBlog", userNo);
	}
	
	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.updateBlog", blogVo);
	}
	
	public BlogVo selectBlog(String id) {
		return sqlSession.selectOne("blog.selectBlog", id);
	}
}
