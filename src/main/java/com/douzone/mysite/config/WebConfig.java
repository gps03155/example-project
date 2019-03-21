package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.MVCConfig;
import com.douzone.config.web.MessagesConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@ComponentScan("com.douzone.mysite.controller")
@Import(value= {MVCConfig.class, SecurityConfig.class, MessagesConfig.class})
public class WebConfig {
	
}
