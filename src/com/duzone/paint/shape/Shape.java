package com.duzone.paint.shape;

import com.duzone.paint.i.Drawable;

public abstract class Shape implements Drawable {
	private String lineColor;
	private String fillColor;
	
	public String getLineColor() {
		return lineColor;
	}
	
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	
	public String getFillColor() {
		return fillColor;
	}
	
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	
	// abstract public void draw(); 
}
