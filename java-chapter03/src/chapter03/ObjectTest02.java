package chapter03;

public class ObjectTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(10, 20);
		Point p2 = new Point(10, 20); // p1과 동일한 객체 아님
		Point p3 = p2;
		
		// == : 두 객체의 동일성 비교 (같은 객체인가)
		System.out.println(p1 == p2); // false : p1과 p2는 다른 객체
		System.out.println(p3 == p2); // true : p3와 p2는 같은 객체
		
		// equals : 두 객체의 동질성 비교 (객체의 내용이 같은가)
		// Object의 equals()의 기본 구현은 == 연산 결과와 같다.
		System.out.println(p1.equals(p2)); // false
		System.out.println(p3.equals(p2)); // true
		System.out.println(p1.equals(p3)); // false
		System.out.println(p1.hashCode() + " : " + p2.hashCode());
		System.out.println(p3.hashCode() + " : " + p2.hashCode());
		
		// String
		String s1 = new String("ABC");
		String s2 = new String("ABC");
		
		System.out.println(s1 == s2); // false
		System.out.println(s1.equals(s2)); // true
		System.out.println(s1.hashCode() + " : " + s2.hashCode()); // hashCode() : String은 내용 기반으로 오버라이딩 됨 (원래는 주소 기반)
																   // hashSet : 중복된 값은 넣지 않음 (동일한 객체는 들어가지 않음)
																   // hashCode()를 이용해서 값이 먼저 같은지 확인 후 equals() 호출하게 됨 - 성능향상을 꾀함
																   // hashCode()를 오버라이딩 하지 않을 경우 중복된 데이터가 있어도 계속 들어감 (주소기반)
																   // 툴을 이용해 오버라이딩 해서 사용하는 것이 원칙
																   // 객체의 내용 비교를 할 때 hashCode()를 호출해서 비교하는 경우가 많음 - Spring, Libarary etc...
		
		System.out.println(System.identityHashCode(s1)); // 객체의 유일한 id - address 기반의 hash 값
		System.out.println(System.identityHashCode(s1) + " : " + System.identityHashCode(s2)); // 주소 기반의 hash 값 - 오버라이딩하기 전
		// hashCode()랑 equals()는 같이 오버라이딩 해야 한다.!!!!!
		// hashCode()가 같으면 내용이 완전 똑같다는 것이 아니라 동일한 값이 나올 수 있어서 equals() 호출
		
		// String Literal
		String s3 = "ABC";
		String s4 = "ABC";
		
		System.out.println(s3 == s4); // true
		System.out.println(s3.equals(s4)); // true
		System.out.println(s3.hashCode() + " : " + s4.hashCode()); // hashCode() 동일
		System.out.println(System.identityHashCode(s3) + " : " + System.identityHashCode(s4)); // 동일
		// 공유되기 때문에 불변 const - new String()으로 사용하는 것 보다 좋음
		
		
	}

}
