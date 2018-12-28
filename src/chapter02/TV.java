package chapter02;

public class TV {
	private boolean power;
	private int channel;  //0~50
	private int volume;   //0~50
	
	public void power(boolean on) {
	}
	
	public void channel(int channel) {
	}
	
	public void channel(boolean up) {
	}
	
	public void status() {
		System.out.println(
			"TV[power="  + power   + 
			", channel=" + channel + 
			", volume="  + volume  + "]");
	}
}
