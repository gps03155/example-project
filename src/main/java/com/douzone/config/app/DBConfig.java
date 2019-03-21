package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // DB rollback 기능
public class DBConfig {

	// Transaction Manager 생성 : error나면 rollback
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/webdb?useSSL=false&allowMultiQueries=true&characterEncoding=utf8&serverTimezone=UTC"); // java config : &amp; 쓰면 에러
		basicDataSource.setUsername("webdb");
		basicDataSource.setPassword("webdb");
		basicDataSource.setInitialSize(20);
		basicDataSource.setMaxActive(20);
		
		return basicDataSource;
	}
}
