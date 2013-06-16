package com.avramko.electroniclibrary.security;

public class LibraryUser {//extends User {
	
	private String username;
	private String password;
	
	public LibraryUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public LibraryUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
