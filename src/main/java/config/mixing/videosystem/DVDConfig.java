package config.mixing.videosystem;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douzone.springcontainer.videosystem.Avengers;
import com.douzone.springcontainer.videosystem.BlankDisc;

// 설정 클래스라는 것을 알려줌
@Configuration
public class DVDConfig {

	@Bean
	public Avengers avengers() {
		return new Avengers();
	}
	
	@Bean(name="avengersInfinityWar")
	public BlankDisc blankDisc() {
		BlankDisc blankDisc = new BlankDisc();
		
		blankDisc.setTitle("Avengers Infinity War");
		blankDisc.setStudio("MARVEL");
		blankDisc.setActors(Arrays.asList("Iron man", "Thor", "Captin America"));
		
		return blankDisc;
	}
}
