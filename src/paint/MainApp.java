package paint;

public class MainApp {

	public static void main(String[] args) {
		Point p1 = new Point();
		p1.setX(100);
		p1.setY(200);
		p1.show();
		
		Point p2 = new Point(50, 50);
		p2.show();
		p2.show(false);
	}

}
