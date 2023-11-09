package chapter02;

public class CustomerApp {
	
	public static void main(String[] args) {
		Customer c = new Customer();
		
		//protected는 같은 패키지에서 접근 O
		c.name = "둘리";
		
		// private는 외부에서 접근 X
		// c.age = 10;
		c.setAge(10);
	}

}
