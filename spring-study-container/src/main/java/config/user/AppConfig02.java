package config.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douzone.springcontainer.user.User;

@Configuration
public class AppConfig02 {
	
	// Container에서 bean으로 설정해주어야함을 의미
	@Bean
	public User user() {
		return new User("둘리");
	}
}
