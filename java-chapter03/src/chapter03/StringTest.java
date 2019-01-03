package chapter03;

public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// c::\temp -> \ 문자를 쓰고 싶을 때
		// /temp - unix <-> d:\temp - window
		// jvm은 unix, window 용이 있음 - unix 처럼 쓰는게 좋음
		// java는 unix에서 온다. window에서 개발
		
		String s = "\n";
		// \n : new Line -> window
		// \n\r : carriage Return -> unix
		
		String s1 = "c:\\temp";
		
		// "Hello"
		String s2 = "\"Hello\"";
		
		System.out.println(s1);
		System.out.println(s2);
		
		// \t -> tab
		// \n -> new line
		// \r -> carriage return
		System.out.println("Hello\tWorld\n");
		System.out.println("Hello\tWorld");
		
		// ''
		char c = '\'';
		System.out.println(c);
	}

}
