package chapter03;

public class ObjectTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p = new Point(10, 20);
		
		System.out.println(p.getClass()); // reflection 객체가 어떤 클래스로 생성이 되었나
		Class k = p.getClass();

		System.out.println(p.hashCode()); // reference value x (레퍼런스 변수에 저장되어 있다)
	}

}
