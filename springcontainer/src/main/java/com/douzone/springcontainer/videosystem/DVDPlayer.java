package com.douzone.springcontainer.videosystem;

public class DVDPlayer {
	private DigitalVideoDisc dvd; // 주입 받을 함수 만들어야함
	
	
	public DVDPlayer() {}
	
	// 생성자로 주입받을 경우
	public DVDPlayer(DigitalVideoDisc dvd) {
		this.dvd = dvd;
	}

	public void setDigitalVideoDisc(DigitalVideoDisc dvd) {
		this.dvd = dvd;
	}
	
	public void play() {
		dvd.play();
	}
}
