package com.labplan.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationError extends RuntimeException {
	private static final long serialVersionUID = 5963249023483958037L;
	
	private Map<String, String> fields;

	public ValidationError() {
		fields = new HashMap<>();
	}
	
	public void addField(String fieldName, String message) {
		fields.put(fieldName, message);
	}
	
	public void removeField(String fieldName) {
		fields.remove(fieldName);
	}
	
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
