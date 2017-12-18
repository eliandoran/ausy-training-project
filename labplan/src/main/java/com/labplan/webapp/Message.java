package com.labplan.webapp;

public class Message {
	public enum MessageType {
		MSG_ERROR,
		MSG_SUCCESS
	}
	
	MessageType type;
	String title;
	String content;
	
	public Message() {
		
	}
	
	public Message(String content) {
		this.content = content;
		this.type = MessageType.MSG_SUCCESS;
	}
	
	public Message(String title, String content) {
		this.title = title;
		this.content = content;
		this.type = MessageType.MSG_SUCCESS;
	}
	
	public Message(String title, String content, MessageType type) {
		this.title = title;
		this.content = content;
		this.type = type;
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
