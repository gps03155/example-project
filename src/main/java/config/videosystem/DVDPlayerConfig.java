package config.videosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douzone.springcontainer.videosystem.Avengers;
import com.douzone.springcontainer.videosystem.DVDPlayer;
import com.douzone.springcontainer.videosystem.DigitalVideoDisc;

@Configuration
public class DVDPlayerConfig {
	
	@Bean
	public Avengers avengers() {
		return new Avengers();
	}
	
	// 싱글톤 유지
	// @Bean
	public DVDPlayer dvdPlayer() {
		return new DVDPlayer(avengers());
	}
	
	@Bean
	public DVDPlayer dvdPlayer(DigitalVideoDisc dvd) { // 주입 받을 때 추천하는 방식
		return new DVDPlayer(dvd);
	}
	
	/*
	@Bean(name="dvdPlayer1")
	public DVDPlayer dvdPlayer() {
		return new DVDPlayer(avengers());
	}
	
	@Bean(name="dvdPlayer2")
	public DVDPlayer anotherDVDPlayer() {
		return new DVDPlayer(avengers());
	}
	*/
}
