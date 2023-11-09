package chapter03;

public class WrapperClassTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer i = new Integer(10);
		Character c = new Character('A'); // 클래스로 만든 객체
		Boolean b = new Boolean(true);
		
		System.out.println(i);
		System.out.println(c);
		System.out.println(b);
		
		// 웹 프로그래밍 할 때 좋음
		// 사용자가 입력을 안할 경우 Integer로 사용하면 null 세팅으로 exception이 발생하지 않음
		// 같은 경우 int를 사용하였을 경우 exception 발생
		// -> 프레임워크 입장에서 좋음
		// -> 성능상 좋지는 않음
		
		
		// AutoBoxing : 기본 타입 쓰는 것처럼 사용 가능
		// 동일성, 동질성 같이 확보 가능 (==, equals()) - Literal 사용하기
		Integer i2 = 10;
		Integer i3 = 10;
		
		System.out.println(i2 == i3); // true : 공유해서 쓰기 때문에 true -> new를 이용해서 하면 false
		
		// Auto Unboxing
		int j = 20 + i2.intValue();
		int j2 = 20 + i2;
		
		System.out.println(j);
		System.out.println(j2);
	}

}
