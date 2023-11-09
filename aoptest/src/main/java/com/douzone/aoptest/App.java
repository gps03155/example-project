package com.douzone.aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml"); // bean 만드는 설정을 xml에서 classpath로 찾음
		
		ProductService ps = ac.getBean(ProductService.class);
		ProductVo vo = ps.find("TV");
		
		System.out.println(vo.toString());
	}

}
