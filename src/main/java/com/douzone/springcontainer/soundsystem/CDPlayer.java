package com.douzone.springcontainer.soundsystem;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
// @Named("cdPlayer") // id 줄 때 사용 - 추천하는 방법
public class CDPlayer {
	// 와이어링 1
	// @Autowired
	// @Inject
	private CompactDisc cd;

	// 와이어링2
	// @Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	public CDPlayer() {
	}

	// 와이어링3
	// @Autowired
	public void setCompactDisc(CompactDisc cd) {
		this.cd = cd;
	}

	// 와이어링4
	// @Autowired // spring
	@Inject 	  // javaEE
	public void insertCompactDisc(CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}
}
