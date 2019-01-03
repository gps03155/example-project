package chapter03;

public class StringTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "abc";
		String s2 = "def";
		String s3 = s2;

		// 공유해서 쓰는 것은 불변 객체
		// 함수형 프로그래밍 : 함수에서 가변객체를 변경할 수 있는 접근을 막아버림 - 값을 변경할 수 없음, 순수함수들만 만들어서 쓰기 - synchronized 안써도 됨
		// rock을 안걸고 붋변 객체를 가지고 프로그래밍을 하는 것을 뜻함
		s2 = s1.toUpperCase(); // 새로운 객체가 만들어져서 return : s1 != s2 -> 다른 객체
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
