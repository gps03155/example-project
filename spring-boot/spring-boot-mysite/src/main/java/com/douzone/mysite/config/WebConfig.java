package com.douzone.mysite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MVCConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration // @SpringBootApplication이랑 같은 패키지면 자동 scanning - 하위 패키지 자동 스캔
@Import(value= {MVCConfig.class, FileUploadConfig.class, SecurityConfig.class})
public class WebConfig implements WebMvcConfigurer {
	
}
