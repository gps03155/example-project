package chapter03;

public class ObjectTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p = new Point(10, 20);
		
		System.out.println(p.getClass()); // reflection 객체가 어떤 클래스로 생성이 되었나
		Class k = p.getClass();

		System.out.println(p.hashCode()); // reference value x (레퍼런스 변수에 저장되어 있다)
										  // address x
										  // address 기반의 hashing 값
		System.out.println(p);
		System.out.println(p.toString()); // 오버로딩 되면 객체인지 확인해서 toString() 호출 (객체 출력)
										  // getClass() + "@" + hashCode()
										  // 대체적으로 오버로딩 (객체의 내용을 알고 싶을때 - 변수값)
	}

}
