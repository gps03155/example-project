package com.douzon.bookmall.test;

import java.util.List;

import com.douzon.bookmall.dao.CategoryDao;
import com.douzon.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// 카테고리 리스트 3개
		insertCategory("소설");
		getList();
	}
	
	public static void insertCategory(String category) {
		int result = new CategoryDao().insert(category);
		
		if(result == 1) {
			System.out.println("카테고리가 등록되었습니다.");
		}
	}
	
	public static void getList() {
		List<CategoryVo> list = new CategoryDao().selectList();
		
		for(CategoryVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getCategory());
		}
	}

}
