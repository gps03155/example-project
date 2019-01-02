package com.duzone.paint.point;

public class ColorPoint extends Point{
	private String color;

	public ColorPoint() {
		System.out.println("ColorPoint() called");
	}
	
	public ColorPoint(int x, int y, String color) {
		super(x, y);
		
		this.color = color;
		
		System.out.println("ColorPoint(int x, int y, String color) called");
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//super.show();
		System.out.println("점[x=" + getX() + ", y=" + getY() + ", color=" + color +"]을 그렸습니다.");
	}
	
}
