package com.labplan.persistence.exceptions;

public class ConnectionFailedException extends RuntimeException {
	public ConnectionFailedException() {
		
	}
	
	public ConnectionFailedException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -4385061762347641614L;
}
