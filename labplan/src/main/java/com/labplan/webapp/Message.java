package com.labplan.webapp;

public class Message {
	public enum MessageType {
		MSG_ERROR,
		MSG_SUCCESS,
		MSG_INFO
	}
	
	MessageType type;
	String title;
	String content;
	
	public Message() {
		this.type = MessageType.MSG_INFO;
	}
	
	public Message(String content) {
		this();
		this.content = content;
	}
	
	public Message(String title, String content) {
		this();
		this.title = title;
		this.content = content;
	}
	
	public Message(String title, String content, MessageType type) {
		this();
		this.title = title;
		this.content = content;
	}
	
	public MessageType getType() {
		return type;
	}
	
	public void setType(MessageType type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return getType().toString() + ": " + getContent();
	}
}
