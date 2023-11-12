package prob02;

public class Div implements Arithmetic{

	@Override
	public int calculate(int a, int b) {
		if(b == 0) {
			System.out.println("0으로 나눌 수 없습니다.");
			return 0;
		}
		
		return a / b;
	}

}
