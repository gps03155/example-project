package com.douzone.jblog.repository;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.sun.javafx.collections.MappingChange.Map;

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
	
	public int insertCategory(String id, String name, String desc) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("name", name);
		map.put("description", desc);
		
		return sqlSession.insert("blog.insertCategory", map);
	}
	
	public BlogVo selectLogo(String id) {
		return sqlSession.selectOne("blog.selectLogo", id);
	}
}
