package config.mixing.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.springcontainer.videosystem.DVDPlayer;
import com.douzone.springcontainer.videosystem.DigitalVideoDisc;

@Configuration
@Import(DVDConfig.class) // 다른 config 설정 파일을 불러옴
public class DVDPlayerConfig {

	@Bean
	public DVDPlayer dvdPlayer(@Qualifier("avengersInfinityWar") DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd); // 생성자를 이용하여 객체 주입
	}
}
