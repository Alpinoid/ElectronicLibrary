package com.avramko.electroniclibrary.security;

public class LibraryUser {//extends User {
	
	/**
	 * @uml.property  name="username"
	 */
	private String username;
	/**
	 * @uml.property  name="password"
	 */
	private String password;
	
	public LibraryUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public LibraryUser() {
	}

	/**
	 * @return
	 * @uml.property  name="username"
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 * @uml.property  name="username"
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
