package com.douzone.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @SpringBootConfiguration // springboot config 파일이라는 것을 알려줌
// @EnableAutoConfiguration
// @ComponentScan("com.douzone.hellospring.controller")	

@SpringBootApplication // 앞의 3개의 annotation을 하나로 묶음
public class BootApp {
	public static void main(String[] args) { // jar 파일은 main이 있어야함
		SpringApplication.run(BootApp.class, args);
	}
}
