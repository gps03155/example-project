package prob03;

public class Tank extends Unit{
	public void move(int x, int y) {
		/* 지정된 위치로 이동 */
		System.out.println("Tank Move");
	}

	public void stop() {
		/* 현재 위치에 정지 */
		System.out.println("Tank Stop");
	}
	
	public void changeMode() {
		/* 공격모드를 변환한다. */
		System.out.println("Tank changeMode");
	}
}
