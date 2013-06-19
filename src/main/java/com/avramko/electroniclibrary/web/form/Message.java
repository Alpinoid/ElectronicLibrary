package com.avramko.electroniclibrary.web.form;

public class Message {

	/**
	 * @uml.property  name="type"
	 */
	private String type;
	/**
	 * @uml.property  name="message"
	 */
	private String message;
	
	public Message() {
	}
	
	public Message(String type, String message) {
		this.type = type;
		this.message = message;
	}

	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 * @uml.property  name="message"
	 */
	public void setMessage(String message) {
		this.message = message;
	}	
	
}
