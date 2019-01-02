package prob03;

public class DropShip extends Unit{
	public void move(int x, int y) {
		/* 지정된 위치로 이동 */
		System.out.println("Dropship Move");
	}

	public void stop() {
		/* 현재 위치에 정지 */
		System.out.println("Dropship Stop");
	}
	
	public void load() {
		/* 선택된 대상을 태운다.*/ 
		System.out.println("Dropship Load");
	}
	
	public void unload() {
		/* 선택된 대상을 내린다.*/ 
		System.out.println("Dropship Unload");
	}
}