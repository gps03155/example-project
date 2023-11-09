package exception;

public class ExceptionText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int a = 0;
			System.out.println("some codes .... ");
			int k = 1000 / a;
			System.out.println("more codes .... ");
			System.out.println("more codes2 .... ");

		} catch (ArithmeticException e) {
			// 1. Log - 가능하면 파일로 남기기
			System.out.println("error : " + e);

			// 2. 사과 - 프로그램 에러 사용자에게 알리기
			System.out.println("죄송합니다. 예기치....");

			// 3. 정상 종료
			return;

			// 4. 이거라도 안되면
			// e.printStackTrace();
		} finally {
			System.out.println("자원 정리");
		}
	}
}
