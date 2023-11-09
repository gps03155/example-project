package chapter03;

public class SingletonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Singleton obj = new Singleton(); // new로 객체 생성 안되게 만들기
		Singleton obj1 = Singleton.getInstance(); // Singleton apply
		Singleton obj2 = Singleton.getInstance();
		
		System.out.println(obj1 == obj2); // hashCode()나 identityHashCoede() 사용 가능
	}

}
