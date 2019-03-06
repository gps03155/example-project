package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<PostVo> categoryPost(long categoryNo){
		return sqlSession.selectList("post.categoryPost", categoryNo);
	}
	
	public PostVo getNoPost(long postNo, String id){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("no", postNo);
		map.put("id", id);
		
		return sqlSession.selectOne("post.getNoPost", map);
	}
	
	public long lastSelect(String id) {
		return sqlSession.selectOne("post.lastSelect", id);
	}
}
