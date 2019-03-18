package config.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.douzone.springcontainer.videosystem.Index;

@Configuration
@ComponentScan(basePackages= {"com.douzone.springcontainer.soundsystem", "com.douzone.springcontainer.videosystem"}) // bean을  찾음 - @Configuration과 동일

// Error - basePackageClasses에 지정한 클래스가 속한 패키지를 Base Package로 사용한다.
// @ComponentScan(basePackageClasses= Index.class) // 지정해둔 클래스가 있는 패키지
public class CDPlayerConfig {

}
