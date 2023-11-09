package prob03;

public class Marine extends Unit{

	public void move(int x, int y) {
		/* 지정된 위치로 이동 */
		System.out.println("Marine Move");
	}

	public void stop() {
		/* 현재 위치에 정지 */
		System.out.println("Marine Stop");
	}
	
	public void stimPack() { 
		/* 스팀팩을 사용한다.*/
		System.out.println("Marine stimPack");
	}	
}
