package exception;

import java.io.IOException;

public class MyClass {
	public void dangerMethod() throws IOException, MyException {
		System.out.println("normal state1");
		boolean isDanger = true;

		if (isDanger) {
			// 예외 상황 발생
			throw new MyException("예외 발생");
		}
		
		System.out.println("normal state2");
	}
}
