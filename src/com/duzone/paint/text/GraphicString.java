package com.duzone.paint.text;

import com.duzone.paint.i.Drawable;

public class GraphicString implements Drawable {
	private String text = "";
	
	public GraphicString(String text) {
		// TODO Auto-generated constructor stub
		this.text = text;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("텍스트(" + text + ")를 그렸습니다.");
	}

}
