package chapter03;

public class WrapperClassTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "123456";
		// int i = parseInt(s, 10); // JavaScript string to int (int, 진수)
		int i = Integer.parseInt(s);
		
		// cf) 반대로 하는 경우 : int to string
		String s2 = String.valueOf(i);
		String s3 = Integer.toString(i);
		
		System.out.println(s + " : " + s2 + " : " + s3);
		
		int a = Character.getNumericValue('A'); // 0 ~ 15
		System.out.println(a);
		
		// 2진수
		String s4 = Integer.toBinaryString(15);
		System.out.println(s4);
		
		// 16진수
		String s5 = Integer.toHexString(256);
		System.out.println(s5);
	}

}
