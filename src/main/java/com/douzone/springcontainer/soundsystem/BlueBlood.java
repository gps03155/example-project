package com.douzone.springcontainer.soundsystem;

import org.springframework.stereotype.Component;

@Component
public class BlueBlood implements CompactDisc {
	private String title = "Endless Rain";
	private String artist = "X japan";
			
	@Override
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}

	@Override
	public String toString() {
		return "BlueBlood [title=" + title + ", artist=" + artist + "]";
	}
}
