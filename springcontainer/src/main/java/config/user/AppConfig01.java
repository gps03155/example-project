package config.user;

import org.springframework.context.annotation.Bean;

import com.douzone.springcontainer.user.User;

// Annotation 안다는 경우
public class AppConfig01 {

	// Container에서 bean으로 설정해주어야함을 의미
	@Bean
	public User user() {
		return new User("둘리");
	}
}
