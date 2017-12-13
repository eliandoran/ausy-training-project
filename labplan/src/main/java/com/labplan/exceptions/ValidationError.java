package com.labplan.exceptions;

public class ValidationError extends RuntimeException {
	private static final long serialVersionUID = 5963249023483958037L;
	
	public ValidationError(String message) {
		super(message);
	}
}
