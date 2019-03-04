package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertCategory(long userNo) {
		sqlSession.insert("category.insertCategory", userNo);
	}
}
