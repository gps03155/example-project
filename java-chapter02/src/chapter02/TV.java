package chapter02;

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
		channel = channelCheck(channel);
		this.channel = channel;
	}
	
	public void volume(int volume) {
		volume = volumeCheck(volume);
		this.volume = volume;
	}
	
	public void channel(boolean up) {
		if(!power) {
			return;
		}
		
		if(up) {
			channel++;
		}
		else {
			channel--;
		}
		
		channel = channelCheck(channel);
	}
	
	public void volume(boolean up) {
		if(!power) {
			return;
		}
		
		if(up) {
			volume++;
		}
		else {
			volume--;
		}
		
		volume = volumeCheck(volume);
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
	
	public int volumeCheck(int volume) {
		if(volume > 50) {
			volume = 0;
		}
		else if(volume < 0) {
			volume = 50;
		}
		
		return volume;
	}
	
	public int channelCheck(int channel) {
		if(channel > 50) {
			channel = 0;
		}
		else if(channel < 0) {
			channel = 50;
		}
		
		return channel;
	}
}
