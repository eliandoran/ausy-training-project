package com.labplan.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an exception that is generated when a user enters invalid data in a {@code <form>}.
 * Because validating a single field at once might be undesirable for a user, this exception is
 * actually a collection of errors for each invalid field.
 * @author Elian Doran
 *
 */
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 5963249023483958037L;

	private Map<String, String> fields;

	/**
	 * Creates a new, empty {@link ValidationException}.
	 */
	public ValidationException() {
		fields = new LinkedHashMap<>();
	}

	/**
	 * Adds a new error with the for the given {@code fieldName}. Only one {@code message} can exist
	 * per {@code fieldName}.
	 * @param fieldName	A name representing the {@code <form>} field (i.e. <em>first name</em>).
	 * @param message	The message describing the validation error for the given {@code fieldName}.
	 */
	public void addField(String fieldName, String message) {
		fields.put(fieldName, message);
	}

	/**
	 * Removes any error message associated with the given {@code fieldName}.
	 * @param fieldName	The name of the field whose error message to remove.
	 */
	public void removeField(String fieldName) {
		fields.remove(fieldName);
	}

	/**
	 * Returns a {@link Map} whose <em>keys</em> represent the name of the fields and whose <em>values</em> represent
	 * the error message for the corresponding key.
	 * @return	A {@link Map} of all the fields and the corresponding error messages.
	 */
	public Map<String, String> getFields() {
		return fields;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (String fieldName : fields.keySet()) {
			str.append(fieldName);
			str.append(' ');
			str.append(fields.get(fieldName));
			str.append('\n');
		}

		return str.toString();
	}
}
