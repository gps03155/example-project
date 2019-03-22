package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 이미지 가져오기
	public List<GalleryVo> getGalleryList() {
		return sqlSession.selectList("gallery.select");
	}
	
	// 이미지 넣기
	public int galleryInsert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insert", galleryVo);
	}
	
	// 이미지 삭제
	public int galleryDelete(long no) {
		return sqlSession.delete("gallery.delete", no);
	}
}
