package com.labplan.webapp;

/**
 * A message is a generic information presented to the user in a visually distinctive way.
 * 
 * <p>It consists of:</p>
 * <ul>
 * 	<li>A title describing the message as short as possible;</li>
 *  <li>The content of the message per se;</li>
 *  <li>The type of the message (i.e. an error, an information, etc.);</li>
 * </ul>
 * @author elian
 *
 */
public class Message {
	/**
	 * An enumeration which describes the type of a {@link Message} (i.e. an error, an information, etc.).
	 * @author elian
	 *
	 */
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

	/**
	 * Creates a new instance of a {@link Message}.
	 */
	public Message() {
		this.type = MessageType.MSG_INFO;
	}

	/**
	 * Creates a new instance of a {@link Message} with the given {@code content}.
	 * <p>The type of the message is assumed to be {@link MessageType#MSG_INFO}.</p>
	 * @param content	The content of the message.
	 */
	public Message(String content) {
		this();
		this.content = content;
	}

	/**
	 * Creates a new instance of a {@link Message} with the given {@code title} and {@code content}.
	 * <p>The type of the message is assumed to be {@link MessageType#MSG_INFO}.</p>
	 * @param title		The title of the message.
	 * @param content	The content of the message.
	 */
	public Message(String title, String content) {
		this();
		this.title = title;
		this.content = content;
	}

	/**
	 * Creates a new instance of a {@link Message} with all the given information.
	 * @param title		The title of the message.
	 * @param content	The content of the message.
	 * @param type		The type of the message.
	 */
	public Message(String title, String content, MessageType type) {
		this();
		this.title = title;
		this.content = content;
	}

	/**
	 * Gets the type of the message.
	 * @return The type of the message.
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * Sets the type of the message.
	 * @param type The type of the message.
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	/**
	 * Gets the title of the message.
	 * @return The title of the message.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the message.
	 * @param title	The title of the message.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the content of the message.
	 * @return The content of the message.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the message.
	 * @param content The content of the message.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return getType().toString() + ": " + getContent();
	}
}
