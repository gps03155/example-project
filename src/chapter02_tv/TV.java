package chapter02_tv;

public class TV {
	private boolean power;
	private int channel; // 0~50
	private int volume; // 0~50
	
	public TV() {}
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	public boolean isPower() {
		return power;
	}

	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public void channel(int channel) {
		if(channel > 50) {
			this.channel = 50;
		}
		else if(channel < 0) {
			this.channel = 0;
		}
		
		this.channel = channel;
	}
	
	public void volume(int volume) {
		if(volume > 50) {
			this.volume = 50;
		}
		else if(volume < 0) {
			this.volume = 0;
		}
		
		this.volume = volume;
	}
	
	public void channel(boolean up) {
		if(up) {
			channel++;
		}
		else {
			channel--;
		}
	}
	
	public void volume(boolean up) {
		if(up) {
			volume++;
		}
		else {
			volume--;
		}
	}
	
	public void power(boolean on) {
		if(on) {
			power = true;
		}
		else {
			power = false;
		}
	}
	
	public void status() {
		System.out.println("TV[power= " + power + ", channel= " + channel + ", volume= " + volume + "]");
	}
}
