package com.douzone.springcontainer;

import java.util.List;

public class User {
	private String name = "PSH";
	private long no;
	private Friend friend;
	private List<String> friends;
	
	public User() {}
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, long no) {
		this.name = name;
		this.no = no;
	}
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", no=" + no + ", friend=" + friend + ", friends=" + friends + "]";
	}	
}
