package chapter03;

public class ObjectTest03 {
	public static void main(String[] args) {
		Rect r1 = new Rect(10, 20);
		System.out.println(r1);
		
		Rect r2 = new Rect(10, 20);
		System.out.println(r2.equals(r1)); // true
		
		Rect r3 = new Rect(50, 5);
		System.out.println(r3.equals(r1));
		
		// String equals
		String s = "Hello World";
		System.out.println(s.equals(s.equals("Hello")));
	}	
	
	public static boolean equalHello(String s) {
		String h = "Hello";
		
		return s.equals("Hello");
	}
	
}
