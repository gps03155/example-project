package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CommentVo;

@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertComment(String comment, long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("content", comment);
		map.put("postNo", postNo);
		
		sqlSession.insert("comment.insertComment", map);
	}
	
	public List<CommentVo> selectComment(long postNo){
		return sqlSession.selectList("comment.selectComment", postNo);
	}
}
