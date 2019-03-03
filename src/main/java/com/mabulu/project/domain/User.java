package com.mabulu.project.domain;

public class User {
	
	private String userName;
	
	private String password;
	
	private int id;
	
	private String randId;

	public String getRandId() {
		return randId;
	}

	public void setRandId(String randId) {
		this.randId = randId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
