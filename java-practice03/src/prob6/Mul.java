package prob6;

public class Mul extends Calculate{

	@Override
	void setValue(int a, int b) {
		// TODO Auto-generated method stub
		this.a = a;
		this.b = b;
	}

	@Override
	int calculate() {
		// TODO Auto-generated method stub
		int result = a * b;
		
		return result;
	}

}
