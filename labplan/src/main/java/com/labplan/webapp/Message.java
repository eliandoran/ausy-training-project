package com.labplan.webapp;

public class Message {
	public enum MessageType {
		MSG_ERROR("Error"), MSG_SUCCESS("Success"), MSG_INFO("Info");

		private final String text;

		private MessageType(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	private MessageType type;
	private String title;
	private String content;

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
