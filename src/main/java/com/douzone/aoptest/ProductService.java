package com.douzone.aoptest;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	public ProductVo find(String name) {
		System.out.println("finding.....");
		
		// if(true) {
		// throw new RuntimeException("myException");
		// }
		
		return new ProductVo(name);
		// 여기에서 AOP 끼어듦
	}
}
