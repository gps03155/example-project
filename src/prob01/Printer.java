package prob01;

public class Printer {
//	public void println(int num) {
//		System.out.println(num);
//	}
//	
//	public void println(boolean visible) {
//		System.out.println(visible);
//	}
//	
//	public void println(double num) {
//		System.out.println(num);
//	}
//	
//	public void println(String name) {
//		System.out.println(name);
//	}
//	
	// Generic Method
	// 타입에 관해서만 오버로드 대체 가능 - 개수 똑같을 때
	public <T> void println(T t) {
		System.out.println(t);
	}

	// 가변 변수
	public Integer sum(Integer... nums) { // 인수를 여러개 쓸 수 있지만 배열로 넘김
		Integer sum = 0;

		for (Integer i : nums) {
			sum += i;
		}

		return sum;
	}

	// Generic + 가변변수
	// 오버로드를 완벽히 대체 - 매개변수, 개수 달라도 실행가능
	@SuppressWarnings("unchecked")
	public <T> void println(T... ts) {
		for (T t : ts) {
			System.out.println(t);
		}
	}
}
