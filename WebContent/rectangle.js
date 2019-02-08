// Rectangle class

	var rect1 = {
			x1: 100,
			y1: 100,
			x2: 200,
			y2: 200,
			backgroundColor: "#0000FF",
			show: function(){
				document.write("<div style='position:absolute; left:" + this.x1 + "px; top:" + this.y1 + "px; width:" + (this.x2 - this.x1) + "px; height:" + (this.y2 - this.y1) + "px; background-color:" + this.backgroundColor + "'></div>");
			}
	}
	
	var rect2 = {
			x1: 250,
			y1: 300,
			x2: 400,
			y2: 350,
			backgroundColor: "#FF0000",
			show: function(){
				document.write("<div style='position:absolute; left:" + this.x1 + "px; top:" + this.y1 + "px; width:" + (this.x2 - this.x1) + "px; height:" + (this.y2 - this.y1) + "px; background-color:" + this.backgroundColor + "'></div>");
			}
	}
	
	var Rectangle = function(x1, y1, x2, y2, backgroundColor){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.backgroundColor = backgroundColor;
	}
	
	Rectangle.prototype.show = function(){
		document.write("<div style='position:absolute; left:" + this.x1 + "px; top:" + this.y1 + "px; width:" + (this.x2 - this.x1) + "px; height:" + (this.y2 - this.y1) + "px; background-color:" + this.backgroundColor + "'></div>");
	}