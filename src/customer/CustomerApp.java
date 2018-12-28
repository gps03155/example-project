package customer;

import chapter02.Customer;

public class CustomerApp {

	public static void main(String[] args) {
		Customer c = new Customer();
		
		// protected는  다른 패키지에서 접근 X
		// c.name = "둘리";
		
		// private는 외부에서 접근 X
		// c.age = 10;
		c.setAge(10);
	}

}
