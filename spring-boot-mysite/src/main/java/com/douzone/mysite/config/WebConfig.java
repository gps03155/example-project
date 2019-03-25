package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.SecurityConfig;

@Configuration
@ComponentScan(value= {"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import(value= {SecurityConfig.class})
public class WebConfig {
	
}
