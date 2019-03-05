package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertPost(PostVo postVo) {
		sqlSession.insert("post.insertPost", postVo);
	}
	
	public List<PostVo> selectPost(String id){
		return sqlSession.selectList("post.selectPost", id);
	}
}
