package com.douzon.jdbc.bookshop.test;

import java.util.ArrayList;
import java.util.List;

import com.douzon.jdbc.bookshop.dao.AuthorDao;
import com.douzon.jdbc.bookshop.vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		//insertTest("스테파니메이어");
		getListTest();
	}
	
	public static void getListTest() {
		List<AuthorVo> list = new AuthorDao().getList();
		
		for(AuthorVo authorVo : list) {
			System.out.println(authorVo.getNo() + " " + authorVo.getName() + " " + authorVo.getBio());
		}
	}
	
	public static void insertTest(String name) {
		AuthorVo vo = new AuthorVo();
		
		vo.setName(name);
		vo.setBio("");
		
		new AuthorDao().insert(vo);
	}
	
	public static List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		return list;
	}
}
