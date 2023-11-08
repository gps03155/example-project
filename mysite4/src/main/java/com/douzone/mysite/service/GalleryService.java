package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;
	
	// 이미지 가져오기
	public List<GalleryVo> getGalleryList(){
		return galleryDao.getGalleryList();
	}
	
	// 이미지 넣기
	public int galleryInsert(GalleryVo galleryVo) {
		return galleryDao.galleryInsert(galleryVo);
	}
	
	// 이미지삭제
	public int galleryDelete(long no) {
		return galleryDao.galleryDelete(no);
	}
}
